package raytracer.raytracer;

import raytracer.camera.Camera;
import raytracer.geometry.Hit;
import raytracer.material.Tracer;

public class Raytracer implements Painter {

	private World world;
	private Camera camera;
	public final static double EPSILON = 0.0001;
	
	/**
	 * Konstruktor der Klasse {@link Raytracer}, füllt die Attribute mit Werten.
	 * @param world
	 * 				
	 * @param camera
	 */
	public Raytracer(World world, Camera camera){
		this.world = world;
		this.camera = camera;
	}
	
	@Override
	public Color pixelColorAt(int width, int hight, int x, int y)  {
		return raytrace(x, y, width, hight);
	}

	/**
	 * Liefert, durch Aufruf der Funktion "shade", die Farbe des Pixels im Bild an der Stelle <code>x</code> und <code>y</code>
	 * @param <code>x</code> Position des Pixels in der Breite
	 * @param <code>y</code> Position des Pixels in der Höhe
	 * @param <code>width</code> Auflösung des Bildes (Breite)
	 * @param <code>hight</code> Auflösung des Bildes (Höhe)
	 * @return Farbe des Pixels an der Stelle <code>x</code> und <code>y</code> 
	 */
	public Color raytrace(int width, int hight, int x, int y) {
		Ray ray = camera.rayFor(width, hight, x, y);
		
		//Strahl wird in die Szene gesendet
		Hit hit = world.hit(ray);
		if (hit != null) {
//			System.out.println("Hit: " + hit);
//			System.out.println("Ray: " + ray);
			return hit.geo.material.colorFor(hit, world, new Tracer());
		} else {
			//Default-Farbe (also wenn kein Schnittpunkt vorhanden) ist schwarz
			return world.getBackgroundColor();
		}
		
	}
	
}
