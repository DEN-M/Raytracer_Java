package raytracer.raytracer;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Die Klasse {@link ImageGenerator} generiert ein Bild und speichert es in eine Datei.
 * 
 */
public class ImageGenerator {
	
	/**
	 * {@link image} ein Objekt von der Klasse {@link BufferedImage} stellt die Bildvariable dar.
	 * 
	 */
	private BufferedImage image;

	/**
	 * Der Konstruktor erzeugt ein {@link ImageGenerator} Objekt und füllt es mit den Werten.
	 * 
	 * @param painter
	 * 			   
	 * @param width
	 *             Breite des Bildes.
	 * @param height
	 *             Höhe des Bildes.
	 * @param fileName
	 *             Dateiname in welcher das erzeugte Bild gespeichert werden soll.
	 * @param imgFormat
	 *             Das Format welches verwendet werden soll um das Bild zu speichern(z.B.: .png, .jpg).
	 *             
	 */
	public ImageGenerator(Painter painter, int width, int height, String fileName, String imgFormat) {
		generate(painter, width, height, fileName, imgFormat);
	}

	/**
	 * Öffnet das erzeugte Bild mit einem Standard Vorschauprogramm.
	 * @param fileName
	 * 					Name der erzeugten Datei
	 * @return
	 * 					falls Bild geöffnet werden konnte liefert die Methode true andernfalls false.
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
	 * Generiert ein Bild durch auslesen der Farbe der einzelnen Pixel.
	 * Falls kein Punkt auf der Figur getroffen wurde, wird {@link BackbackgroundColor} als Farbe genommen.
	 * 
	 * @param painter
	 * 			   liefert den Farbwert an einer bestimmten Stelle.
	 * @param width
	 *             Breitenkoordinate
	 * @param height
	 *             Höhenkoordinate
	 * @param fileName
	 *             Dateiname in welcher das erzeugte Bild gespeichert werden soll.
	 * @param imgFormat
	 *             Das Format welches verwendet werden soll um das Bild zu speichern(z.B.: .png, .jpg).
	 *             
	 * @return
	 */
	
	private boolean generate(Painter painter, int width, int height, String fileName, String imgFormat) {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0; i < width; i++) {
			// TODO Thread funktioniren noch nicht richtig!!!
//			new Thread(generateWithThreads(painter, width, height, i)).start();

			for(int j = 0; j < height; j++) {
				image.setRGB(i, j, painter.pixelColorAt(i, height - j - 1, width, height).toAwtColor());
			}
		}
		
		try {
			File file = new File(fileName);
			ImageIO.write(image, imgFormat, file);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * gibt das Bildobjekt {@link image} zurück
	 * @return <code>image</code>
	 */

	public BufferedImage getImage() {
		return image;
	}
	
	private Runnable generateWithThreads(final Painter painter, final int width, final int height, final int i) {
		return new Runnable() {
			
			@Override
			public void run() {	
				for(int j = 0; j < height; j++) {
					image.setRGB(i, j, painter.pixelColorAt(i, height - j - 1, width, height).toAwtColor());
				}
			}
		};
	}
	
	private Runnable generateWithThreads2(final Painter painter, final int width, final int height, final int i, final int j) {
		return new Runnable() {
			
			@Override
			public void run() {	
				image.setRGB(i, j, painter.pixelColorAt(i, height - j - 1, width, height).toAwtColor());
			}
		};
	}
	
}