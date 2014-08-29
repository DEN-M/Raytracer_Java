package raytracer.geometry;

import raytracer.material.Material;
import raytracer.math.Point3;
import raytracer.raytracer.Ray;

/**
 * Diese Klasse realisiert Kugel.
 */
public class Sphere extends Geometry {
	
	/**
	 * <code>c</code> stellt als Objekt der Klasse {@link Point3} das Zentrum dar.
	 */
	public Point3 c;
	
	/**
	 * <code>r</code> ein <code>double</code> Zahlwert welcher den Radius darstellt.
	 */
	public double r;

	/**
	 * Der Konstruktor der Klasse <code>Sphere</code> 
	 * 
	 * @param c
	 * 			- Mittelpunkt vom Kreis
	 * @param r
	 * 			- Raudius vom Kreis
	 * @param material
	 */
	public Sphere(Point3 c, double r, Material material) {
		super(material);
		this.c = c;
		this.r = r;
	}

	/**
	 * Berechnet Schnittpunkt von dem Strahl <code>ray</code> mit der Kugel
	 * 
	 *  @return  Gibt ein {@link Hit} Objekt zurück, wenn <code>diskriminante</code> gleich 0 ist dann gitb es nur einen Schnittpunkt, 
	 *  wenn größer als 0 dann gibt es zwei Schnittpunkte, sonst wird immer <code>null</code> zurückgeben
	 */
	public Hit hit(Ray ray) {

		// Werte für abc-Formel
		double a = ray.d.dot(ray.d);
		double b = ray.d.dot(ray.o.sub(this.c).mul(2));
		double c = ray.o.sub(this.c).dot(ray.o.sub(this.c)) - (r * r);

		double diskriminante = (b * b) - (4 * a * c);

		// Kein Schnittpunkt
		if(diskriminante < 0) {
			return null;
		}

		// Erster Schnittpunkt wird berechnet
		final double t1 = (-b + Math.sqrt(diskriminante)) / (2 * a);

		// Fall mit einem Schnittpunkt
		if(diskriminante == 0 && t1 >= 0) {
			return new Hit(t1, ray, this, ray.at(t1).sub(this.c).normalized().asNormal());
		}

		// Fall zwei Schnittpunkte werden berechnet und beide werden ausgegeben
		double t2 = (-b - Math.sqrt(diskriminante)) / (2 * a);

		// Beide Schnittpunkte sind hinter dem Bild. Gibt den Wert von t der naeher am Bild ist zurück
		if(t1 < 0 && t2 < 0) {
			return null;
		}

		// Beide Schnittpunkte sind vor dem Bild. Gibt den Wert von t der naeher am Bild ist zurück
		if(t1 >= 0 && t2 >= 0) {
			return new Hit(Math.min(t1, t2), ray, this, ray.at(Math.min(t1, t2)).sub(this.c).normalized().asNormal());
		} 
//		if(t1 >= 0 && t2 < 0) {
//			return new Hit(t1, ray, this, ray.at(t1).sub(this.c).normalized().asNormal());
//		} if(t2 >= 0 && t1 < 0) {
//			return new Hit(t2, ray, this, ray.at(t2).sub(this.c).normalized().asNormal());
//		} 
		
		else {
			return null;
		}

	}

}