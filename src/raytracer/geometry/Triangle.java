package raytracer.geometry;

import raytracer.material.Material;
import raytracer.math.Mat3x3;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.raytracer.Ray;

/**
 * Diese Klasse realisiert ein Dreieck
 */
public class Triangle extends Geometry {
	
	/**
	 * <code>a</code> erster Punkt vom Dreieck.
	 */
	public Point3 a;
	
	/**
	 * <code>a</code> erster Punkt vom Dreieck.
	 */
	public Point3 b;
	
	/**
	 * <code>a</code> erster Punkt vom Dreieck.
	 */
	public Point3 c;
	
	/**
	 * <code>n</code> Normale vom Dreieck.
	 */
	public Normal3 n;

	/**
	 * Konstruktor erzeugt ein {@link Triangle} Objekt mit den übergebenen Werten.
	 * 
	 * @param a
	 *            - erster Punkt von Dreieck
	 * @param b
	 *            - zweiter Punkt von Dreieck
	 * @param c
	 *            - dritter Punkt von Dreieck
	 * @param material
	 */
	public Triangle(Point3 a, Point3 b, Point3 c, Material material) {
		super(material);
		this.a = a;
		this.b = b;
		this.c = c;
		this.n = (this.b.sub(this.a)).xNormal(this.c.sub(this.a));
	}

	/**
	 * Berechnet Schnittpunkt mit dem Dreieck.
	 * 
	 * 
	 * @return <b>Hit</b> ein Schnitpunkt mit dem Dreieck, wenn es keins
	 *         vorhenden ist dann wird immer <code>null</code> zurück gegeben
	 */
	public Hit hit(final Ray r) {
		final Mat3x3 matA = new Mat3x3(	a.x - b.x, a.x - c.x, r.d.x, 
										a.y - b.y, a.y - c.y, r.d.y, 
										a.z - b.z, a.z - c.z, r.d.z);

		
		final Vector3 vec = a.sub(r.o);
		final double beta = matA.changeCol1(vec).determinant / matA.determinant;
		final double gamma = matA.changeCol2(vec).determinant / matA.determinant;
		final double t = matA.changeCol3(vec).determinant / matA.determinant;

		if (beta < 0.0 || gamma < 0.0 || (beta + gamma) > 1.0 || t < 0.0) {
			return null;
		} else {
			return new Hit(t, r, this, this.n);			
		}
	}

}