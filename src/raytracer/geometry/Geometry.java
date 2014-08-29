package raytracer.geometry;

import raytracer.material.Material;
import raytracer.raytracer.Ray;

/**
 *Basisklasse für die geometrischen Figuren.
 */
public abstract class Geometry {

	/**
	 * Ein Objekt von der Klasse {@link Material} welches die Material und somit die Farbe darstellt.
	 */
	public Material material;
	
	/**
	 * Konstruktor zum Erzeugen einer Figur.
	 * @param material
	 * 				 Matireal der Figur
	 */
	public Geometry(Material material) {
		this.material = material;
	}
	
	/**
	 * Nimmt ein Strahl entgegen und berechnet die Schnittpunkte mit der Figur.
	 * 
	 * @param r
	 * 			 Der Strahl der geprüft werden soll ob er die Figur schneidet.
	 * 
	 * @return
	 * 			 Falls <code>null</code> gab es kein Schnittpunkt ansonsten wenn es einen Schnittpunkt gibt wird der Wert mit dem kleinstem positivem <code>t</code> zurückgeliefert.
	 */
	public abstract Hit hit(Ray r);
	
}