package raytracer.material;

import raytracer.geometry.Hit;
import raytracer.light.Light;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.raytracer.Color;
import raytracer.raytracer.Raytracer;
import raytracer.raytracer.World;

/**
 * Lambert-Beleuchtung
 *
 */
public class LambertMaterial extends Material {
	private Color color;

	
	public LambertMaterial(Color color) {
		this.color = color;
	}

	/**
	 * Ermittelt die Farbe 
	 * 
	 * @parm Hit
	 * 			- Schnittpunkt
	 * @parm World
	 * 			- Welt
	 * 
	 * @return {@link Color}-Objekt für den eweiligen Schnittpunkt <code>Hit</code>
	 */
	public Color colorFor(Hit hit, World world, Tracer tracer) {
		Normal3 n = hit.normal; 		// Normlavektor auf der Objekoberlfäche		 
		Point3 p = hit.ray.at(hit.t - Raytracer.EPSILON); 	// Schnittpunkt

		Color cd = this.color; 				// Farbe diffuse Reflektion (Skript:cr)
		Color ca = world.getAmbientColor(); // Farbe ambientes Licht
		Color cl; 							// Farbe Licht
		Color c = cd.mul(ca);  				// Farbe Ergebnis

		for (Light light : world.getLights()) {		
			if (light.illuminates(p, world)) {
								
				Vector3 l = light.directionFrom(p); // Vektor zur Lichtquelle				
				cl = light.getColor();
				
				c = c.add(cd.mul(cl).mul(Math.max(0, n.dot(l))));
			}		
		}
		return c;
	}
}