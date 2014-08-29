package raytracer.geometry;

import raytracer.math.Normal3;
import raytracer.raytracer.Ray;

/**
 * Realisiert ein <code>Hit</code> (Schnittpunkt).
 *
 */
public class Hit {
	
	/**
	 * @param t
	 * 			- Länge von Strahl (Entfernung von Schnittpunkt)
	 * 	
	 * @param ray
	 * 			- Strahl
	 * 
	 * @param geo
	 * 			- Geometry, Figur zu dem Schnittpunkt gehört
	 * 
	 * @param geo
	 * 			- Normal3, Normale die zu dem Schnittpunkt gehört
	 */
	public double t;
	public Ray ray;
	public Geometry geo;
	public Normal3 normal;
	
	/**
	 * Konstruktor für Hit, versieht alle Attribute mit jeweiligen Parametern.
	 * 
	 * @param t
	 * 			- Länge des Strahls (Entfernung von Schnittpunkt).
	 * 		
	 * @param ray
	 * 			- Strahl
	 * 
	 * @param geo
	 * 			- Geometry, Figur zu der Schnittpunkt gehört.
	 */
	public Hit(double t, Ray ray, Geometry geo, Normal3 normal) {
		super();
		this.t = t;
		this.ray = ray;
		this.geo = geo;
		this.normal = normal;
	}

	@Override
	public String toString() {
		return "Hit \n[t = " + t + ", \nray = " + ray + ", \ngeo = " + geo + ", \nnormal = " + normal + "]";
	}	
}