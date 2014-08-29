package raytracer.material;

import raytracer.geometry.Hit;
import raytracer.raytracer.Color;
import raytracer.raytracer.Ray;
import raytracer.raytracer.Raytracer;
import raytracer.raytracer.World;

/**
 * Realisiert einen Tracer
 *
 */
public class Tracer {
	
	/**
	 * Inder der Rekusionstiefe
	 */
	private int rekusionIndex = 0;

	/**
	 * Ein Strahl gibt die Farbe von dem getroffenen Objekt zuruek
	 * @param world Word
	 * @param ray der Strahl
	 * @return <code>Color</code> Farbe von dem was getroffen wurde, wenn nicht getroffen wurde dan gibt die Hintergrundwarbe von der Welt.
	 */
	public Color fr(World world, Ray ray) {
		if(rekusionIndex > 3) {
			return new Color(1, 1, 1);
		}
		
		Color c;
		Hit h = world.hit(ray);
		if(h == null || h.t < Raytracer.EPSILON) {
			c = world.backgroundColor;
		} else {
			c = h.geo.material.colorFor(h, world, this);
		}
		return c;
	}
	
	public int getRekusionIndex() {
		return rekusionIndex;
	}

	public void setRekusionIndex(int rekusionIndex) {
		this.rekusionIndex = rekusionIndex;
	}
	
	public void rekusionIndexUP() {
		rekusionIndex += 1;
	}
	
	public void rekusionIndexDOWN() {
		rekusionIndex -= 1;
	}
	
}