package raytracer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import raytracer.camera.PerspectiveCamera;
import raytracer.geometry.AxisAlignedBox;
import raytracer.geometry.Geometry;
import raytracer.geometry.Node;
import raytracer.geometry.Plane;
import raytracer.geometry.Sphere;
import raytracer.light.PointLight;
import raytracer.material.ReflectiveMaterial;
import raytracer.material.TransparentMaterial;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Transform;
import raytracer.math.Vector3;
import raytracer.raytracer.Color;
import raytracer.raytracer.Raytracer;
import raytracer.raytracer.RaytracerNEU;
import raytracer.raytracer.World;

public class Raytracer_Main_NEU {

	private static final long serialVersionUID = 955541339100193324L;

	// Auflösung VGA
//	private static int width = 640;
//	private static int height = 480;
	
	// Auflösung Full-HD
	private final static int X = 1920;
	private final static int Y = 1080;

	public Raytracer_Main_NEU() {
		// Rendet ein image
		renderRaytracerImage();
	}
	
	
	/**
	 * Erzeugt ein Bild von Raytracer
	 */
	private void renderRaytracerImage() {
		// KAMERA
		final Point3  e = new Point3(8, 8, 8);		// Position weit
//		final Point3  e = new Point3(4, 4, 4);		// Position nah
		final Vector3 g = new Vector3(-1, -1, -1);	// Blickrichtung
		final Vector3 t = new Vector3(0, 1, 0);		// up-Vektor
		final double alpha = 45.0;					// Öffnungswinkel
		PerspectiveCamera pCam = new PerspectiveCamera(e, g, t, alpha);		
		
		// WELT
		// Hintergrundfarbe
		Color backgroundColor = new Color(0, 0, 0);
		double colorWert = 0.1;
		Color ambientColor = new Color(colorWert, colorWert, colorWert);
		double refractionIndex = 6;
		World world = new World(backgroundColor, ambientColor, refractionIndex);
		// Liste der GeoObjekte
		List geometryObjects = new ArrayList<Geometry>();
		
		

		// GEO Objects
		world.add(new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), new ReflectiveMaterial(new Color(0.5, 0.5, 0.5), new Color(0, 0, 0), 64, new Color(0.5, 0.5, 0.5))));
		world.add(new Sphere(new Point3(0, 1, 0), 1, new ReflectiveMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5))));
		world.add(new Sphere(new Point3(3, 1, 0), 1, new ReflectiveMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5))));
		
		world.add(new Sphere(new Point3(1, 4, 4), 1, new TransparentMaterial(1.25)));
		world.add(new AxisAlignedBox(new Point3(-0.5, 3, 0.5), new Point3(0.5, 4, -0.5), new TransparentMaterial(1.25)));
		
		geometryObjects.add(new Sphere(new Point3(0, 1, 0), 1, new ReflectiveMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5))));
		geometryObjects.add(new AxisAlignedBox(new Point3(-0.5, 3, 0.5), new Point3(0.5, 4, -0.5), new ReflectiveMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5))));
		
		world.add(new Node(new ReflectiveMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5)), 
				new Transform()
		.scaling(new Point3(2.5, 1.0, 0.5))
		, geometryObjects));
		
		// LICHT
		Color colorLight = new Color(0.5, 0.5, 0.5);		// Farbe vom Licht
		Point3 position = new Point3(8, 8, 8); 		// Position vom Licht
		Vector3 dDLigth = new Vector3(-4,-1, 3);	// Richtund vom Licht bei SpotLight
		double halfAngle = Math.PI/14; 				// Öffnungswinkel pi/14 in Grad bei SpotLight
		
		world.add(new PointLight(colorLight, position, true));
//		world.add(new DirectionalLight(colorLight, dDLigth, true));
//		world.add(new SpotLight(colorLight, position, dDLigth, halfAngle, true));
		
		

		
		
		// RAYTRACER Go
		String path = System.getProperty("user.home");
		String fileName = path + "\\Desktop\\" + "Raytracer.png";
		long startRaytracer = System.currentTimeMillis();
		// START ImageGenareator er erzeugt das bild und es zeigt auch
		RaytracerNEU raytracer = new RaytracerNEU(world, pCam, X, Y, fileName, "png");
//		ImageGenerator render = new ImageGenerator(new Raytracer(world, pCam), width, height, fileName, "png");
		// Ausgabe von der Zeit die zum Berechnen gebraucht wurde
		System.out.println("Gerendet in " + (System.currentTimeMillis() - startRaytracer) + " ms");
		
//		this.image = render.getImage();
	}
	
	public static void main(String args[]) throws IOException {
		new Raytracer_MAIN();		
	}

}