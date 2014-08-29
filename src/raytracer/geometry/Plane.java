package raytracer.geometry;

import raytracer.material.Material;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.raytracer.Ray;
import raytracer.raytracer.Raytracer;

/**
 * Diese Klasse realisiert eine Fläche.
 */
public class Plane extends Geometry {

	/**
	 * Stellt einen Punkt als {@link Point3} Objekt dar.
	 */
	public Point3 a;

	/**
	 * Stellt eine Normale als {@link Normal3} Objekt dar.
	 */
	public Normal3 n;

	/**
	 * Konstruktor der Klasse {@link Plane}
	 * 
	 * @param a
	 *            - ein bekannter Punkt auf der Fläche
	 * @param n
	 *            - eine Normale <code>n</code>
	 * 
	 * @param material
	 *            - Farbe der Fläche
	 */
	public Plane(Point3 a, Normal3 n, Material material) {
		super(material);
		this.a = a;
		this.n = n;
	}

	/**
	 * Berechnet Schnittpunkt mit der Fläche
	 * 
	 * @return <b>Hit</b> wenn t != 0 && t > 0 sonst wird immer null zurück
	 *         gegeben.
	 */
	public Hit hit(Ray ray) {
		if (n.dot(ray.d) == 0) {
			return null;
		} else {
			final double t = (a.sub(ray.o).dot(n)) / (ray.d.dot(n));
//			final double t = (n.dot(a) - n.dot(ray.o)) / (n.dot(ray.d.normalized()));
			if (t > 0) {
				return new Hit(t - Raytracer.EPSILON, ray, this, this.n);

			} else {
				return null;
			}
		}
		
		
//		double t;
//		final double d1 = this.a.sub(ray.o).dot(this.n);
//		final double d2 = ray.d.dot(this.n);
//		if(d1 == 0.0 && d2 != 0.0) {
//			return null;
//		} else if (d1 == 0.0 && d2 == 0) {
//			t = 0.0;
//		} else {
//			t = d1 / d2;
//		} if (t > 0.0) {
//			return new Hit(t - Raytracer.EPSILON, ray, this, this.n);
//		} else {
//			return null;
//		}
	}

}