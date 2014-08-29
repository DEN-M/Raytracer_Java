package raytracer.raytracer;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import raytracer.camera.Camera;
import raytracer.geometry.Hit;
import raytracer.material.Tracer;

public class RaytracerNEU extends JFrame {

	private World world;
	private Camera camera;
	public final int x;
	public final int y;
	private String fileName;
	private String imgFormat;

	// Bild
	private final JLabel label;
	private final ImageIcon icon;
	private BufferedImage image;

	public final static double EPSILON = 0.0001;

	/**
	 * Konstruktor der Klasse {@link Raytracer}, füllt die Attribute mit Werten.
	 * 
	 * @param world
	 * 
	 * @param camera
	 */
	public RaytracerNEU(World world, Camera camera, int x, int y, String fileName,
			String imgFormat) {
		super("Raytracer");
		this.world = world;
		this.camera = camera;
		this.x = x;
		this.y = y;
		this.fileName = fileName;
		this.imgFormat = imgFormat;

		// Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Datei");
		menuBar.add(menu);
		setJMenuBar(menuBar);

		// Save Button
		JMenuItem imgSave = new JMenuItem("Speichern");
		menu.add(imgSave);
		imgSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		imgSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					saveImage();
				} catch (IOException e) {
					System.err
							.println("Image konnte NICHT gespeichert werden!");
					e.printStackTrace();
				}
			}
		});

		// Rendet ein image
		BufferedImage image = generateImage();

		icon = new ImageIcon(image);
		label = new JLabel(icon);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(label, BorderLayout.CENTER);

		getContentPane().add(panel);
		setSize(image.getWidth() + 25, image.getHeight() + 50);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private BufferedImage generateImage() {
		this.image = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		Raster raster = image.getData();
		WritableRaster writableRaster = null;
		if (raster instanceof WritableRaster) {
			writableRaster = (WritableRaster) raster;
		} else {
			return null;
		}
		for (int y = 0; y < writableRaster.getHeight(); y++) {
			for (int x = 0; x < writableRaster.getWidth(); x++) {
				final Ray ray = camera.rayFor(writableRaster.getWidth(),
						writableRaster.getHeight(), this.x, this.y);
				final Tracer tracer = new Tracer();
				// Strahl wird in die Szene gesendet
				// final Color color = raytraceColor(writableRaster.getWidth(),
				// writableRaster.getHeight(), this.x, this.y);
				final Color color = tracer.fr(this.world, ray);
				double[] ar = { color.r * 255, color.g * 255, color.b * 255 };
				writableRaster.setPixel(x, writableRaster.getHeight() - y - 1,
						ar);
			}
		}
		image.setData(writableRaster);
		try {
			File file = new File(fileName);
			ImageIO.write(image, imgFormat, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	/**
	 * Liefert, durch Aufruf der Funktion "shade", die Farbe des Pixels im Bild
	 * an der Stelle <code>x</code> und <code>y</code>
	 * 
	 * @param <code>x</code> Position des Pixels in der Breite
	 * @param <code>y</code> Position des Pixels in der Höhe
	 * @param <code>width</code> Auflösung des Bildes (Breite)
	 * @param <code>hight</code> Auflösung des Bildes (Höhe)
	 * @return Farbe des Pixels an der Stelle <code>x</code> und <code>y</code>
	 */
	public Color raytraceColor(int width, int hight, int x, int y) {
		Ray ray = camera.rayFor(width, hight, x, y);

		// Strahl wird in die Szene gesendet
		Hit hit = world.hit(ray);
		if (hit != null) {
			return hit.geo.material.colorFor(hit, world, new Tracer());
		} else {
			// Default-Farbe (also wenn kein Schnittpunkt vorhanden) ist schwarz
			return world.getBackgroundColor();
		}

	}

	/**
	 * Öffnet das erzeugte Bild mit einem Standard Vorschauprogramm.
	 * 
	 * @param fileName
	 *            Name der erzeugten Datei
	 * @return falls Bild geöffnet werden konnte liefert die Methode true
	 *         andernfalls false.
	 */
	public static boolean showImage(String fileName) {
		try {
			File file = new File(fileName);
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Oeffnet {@link JFileChooser} mit der Option zu speichern. Speichert das
	 * Bild, wenn ein Dateiname eingegeben wurde als png. Bei erfolgter
	 * Speicherung wird ein Message Dialog angezeigt.
	 */
	public void saveImage() throws IOException {
		final JFileChooser chooser = new JFileChooser();
		final int returnVal = chooser.showSaveDialog(rootPane);
		chooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "*.png";
			}

			@Override
			public boolean accept(File file) {
				return file.isDirectory()
						|| file.getName().toLowerCase().endsWith(".png");
			}
		});

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			ImageIO.write(image, "png", chooser.getSelectedFile());
			JOptionPane.showMessageDialog(null,
					"Die Datei " + chooser.getSelectedFile()
							+ " wurde gespeichert.", "Speichern",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * gibt das Bildobjekt {@link image} zurück
	 * 
	 * @return <code>image</code>
	 */

	public BufferedImage getImage() {
		return image;
	}

}
