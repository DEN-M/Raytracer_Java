package raytracer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import raytracer.camera.PerspectiveCamera;
import raytracer.geometry.AxisAlignedBox;
import raytracer.geometry.Geometry;
import raytracer.geometry.Node;
import raytracer.geometry.Plane;
import raytracer.geometry.Sphere;
import raytracer.light.PointLight;
import raytracer.material.PhongMaterial;
import raytracer.material.ReflectiveMaterial;
import raytracer.material.TransparentMaterial;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Transform;
import raytracer.math.Vector3;
import raytracer.raytracer.Color;
import raytracer.raytracer.ImageGenerator;
import raytracer.raytracer.Raytracer;
import raytracer.raytracer.World;

/**
 * Startet den Raytracer
 * 
 */
public class Raytracer_MAIN extends JFrame {

	private static final long serialVersionUID = 955541339100193324L;

	// Bild
	private final JLabel label;
	private final ImageIcon icon;
	private BufferedImage image;
	
	// Auflösung VGA
//	private static int width = 640;
//	private static int height = 480;
	
	// Auflösung Full-HD
	private static int width = 1920;
	private static int height = 1080;

	public Raytracer_MAIN() {
		super("Image Saver");

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
					System.err.println("Image konnte NICHT gespeichert werden!");
					e.printStackTrace();
				}
			}
		});

		// Rendet ein image
		renderRaytracerImage();

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
	 * Erzeugt ein Bild von Raytracer
	 */
	private void renderRaytracerImage() {
		// KAMERA
		final Point3  e = new Point3(0, 6, 0);		// Position weit
//		final Point3  e = new Point3(4, 4, 4);		// Position nah
		final Vector3 g = new Vector3(0, -0.5, -1);	// Blickrichtung
//		final Vector3 g = new Vector3(-1, -1, -1);	// Blickrichtung
		final Vector3 t = new Vector3(0, 1, 0);		// up-Vektor
		final double alpha = 45.0;					// Öffnungswinkel
		PerspectiveCamera pCam = new PerspectiveCamera(e, g, t, alpha);		
		
		// WELT
		// Hintergrundfarbe
		Color backgroundColor = new Color(0, 0, 0);
		double colorWert = 0.25;
		Color ambientColor = new Color(colorWert, colorWert, colorWert);
		double reflectionIndex = 1.0;
		World world = new World(backgroundColor, ambientColor, reflectionIndex);
		
		
		// GEO Objects
		world.add(new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), new ReflectiveMaterial(new Color(0.75, 0.75, 0.75), new Color(0, 0, 0), 64, new Color(0.5, 0.5, 0.5))));
		world.add(new Sphere(new Point3(-3, 1, -15), 1, new ReflectiveMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5))));
		world.add(new Sphere(new Point3(0, 1, -15), 1, new ReflectiveMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5))));
		world.add(new Sphere(new Point3(3, 1, -15), 1, new ReflectiveMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5))));
		world.add(new Sphere(new Point3(6, 1, -6), 1, new ReflectiveMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5))));
		world.add(new Sphere(new Point3(1.75, 2, -6), 1, new ReflectiveMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5))));
		
		world.add(new Sphere(new Point3(-4, 1, -10), 1, new TransparentMaterial(1.5)));
		world.add(new Sphere(new Point3(-1, 2, -10), 1, new TransparentMaterial(1.5)));
		world.add(new Sphere(new Point3(1, 2, -9), 1, new TransparentMaterial(1.5)));
		world.add(new Sphere(new Point3(4, 1, -13), 1, new TransparentMaterial(1.5)));
		
		world.add(new Sphere(new Point3(6.7, 4, -2), 1, new TransparentMaterial(1.5)));
		world.add(new AxisAlignedBox(new Point3(5, 1, -10), new Point3(6, 4, -12), new ReflectiveMaterial(new Color(1, 1, 1), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5))));
		world.add(new AxisAlignedBox(new Point3(3.5, 1, -8), new Point3(7, 4, -8.01), new TransparentMaterial(1.5)));
		world.add(new AxisAlignedBox(new Point3(-20, 0.2, -30), new Point3(20, 5, -30.1), new ReflectiveMaterial(new Color(0, 0, 0), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5))));
		
		
		// Liste der GeoObjekte für Node
//		List<Geometry> geometryObjects = new ArrayList<Geometry>();
//		// NODE GEO Objects
////		geometryObjects.add(new Sphere(new Point3(0, 4, -5), 1, new ReflectiveMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5))));
//		geometryObjects.add(new AxisAlignedBox(new Point3(-0.5, 3, -4), new Point3(0.5, 4, -5), new ReflectiveMaterial(new Color(1, 1, 0), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5))));
//		
//		world.add(new Node(new ReflectiveMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5)), 
//		new Transform()
//		.scaling(new Point3(1.5, 1.0, 1.0))
////		.rotationX(20.0)
////		.rotationY(20.0)
////		.rotationZ(20.0)
//		, geometryObjects));
		
		// LICHT
		Color colorLight = new Color(1.0, 1.0, 1.0);		// Farbe vom Licht
		Point3 position = new Point3(0, 5, 0); 		// Position vom Licht
		Vector3 dDLigth = new Vector3(-4,-1, 3);	// Richtund vom Licht bei SpotLight
		double halfAngle = Math.PI/14; 				// Öffnungswinkel pi/14 in Grad bei SpotLight
		
		world.add(new PointLight(colorLight, position, true));
//		world.add(new PointLight(new Color(0.25, 0.25, 0.25), new Point3(-1.5, 6, -15), true));
//		world.add(new PointLight(new Color(0.33, 0.33, 0.33), new Point3(2, 6, 0), true));
//		world.add(new PointLight(new Color(0.33, 0.33, 0.33), new Point3(2.05, 6, 0), true));
//		world.add(new PointLight(new Color(0.33, 0.33, 0.33), new Point3(2.1, 6, 0), true));
//		world.add(new PointLight(new Color(0.33, 0.33, 0.33), new Point3(2.15, 6, 0), true));
//		world.add(new PointLight(new Color(0.33, 0.33, 0.33), new Point3(2.2, 6, 0), true));
		
//		world.add(new DirectionalLight(colorLight, dDLigth, true));
//		world.add(new SpotLight(colorLight, position, dDLigth, halfAngle, true));
		
		// TEST
////	world.add(new Plane(new Point3(0, -1.3, 0), new Normal3(0, 1, 0), new ReflectiveMaterial(new Color(0.7, 0.7, 0.7), new Color(0, 0, 0), 64, new Color(0.5, 0.5, 0.5))));
//	world.add(new Sphere(new Point3(0, 1, -8), 1, new ReflectiveMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5))));
////	world.add(new AxisAlignedBox(new Point3(0.4, -0.9, -3.5), new Point3(1.9, 3.1, -4), new TransparentMaterial(1.5)));
//	world.add(new Sphere(new Point3(-0.2, 0, -2), 1, new TransparentMaterial(1.5)));
//	world.add(new Sphere(new Point3(0.7, 0, -0.5), 1, new TransparentMaterial(1.5)));
//	
//	world.add(new PointLight(new Color(1, 1, 1), new Point3(-5, 30, 0), true));
////	world.add(new PointLight(new Color(0.33, 0.33, 0.33), new Point3(-10, 30, 0), true));
		
		
		
		// RAYTRACER Go
		String path = System.getProperty("user.home");
		String fileName = path + "\\Desktop\\" + "Raytracer.png";
		long startRaytracer = System.currentTimeMillis();
		// START ImageGenareator er erzeugt das bild und es zeigt auch
		ImageGenerator render = new ImageGenerator(new Raytracer(world, pCam), width, height, fileName, "png");
		// Ausgabe von der Zeit die zum Berechnen gebraucht wurde
		System.out.println("Gerendet in " + (System.currentTimeMillis() - startRaytracer) + " ms");
		
		this.image = render.getImage();
	}
	
	public static void main(String args[]) throws IOException {
		new Raytracer_MAIN();		
	}

}
