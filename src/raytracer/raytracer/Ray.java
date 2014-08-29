package raytracer.raytracer;

import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * Erzeugt ein Objekt <code>Ray</code> welches eien Strahl repäsentiert.
 *
 */
public class Ray {
	
	/**
	 * Ursprung des Strahls <code>Ray</code>.
	 */
	public Point3 o;
	
	/**
	 * Richtung des Strahls <code>Ray</code>.
	 */
	public Vector3 d;
	
	/**
	 * Nimmt als Parameter den Ursprung und die Richtung des Strahls entgegen.
	 */
	public Ray(final Point3 o, final Vector3 d) {
		this.o = o;
		this.d = d;
	}
	
	/**
	 * Nimmt als Parameter ein <code>t</code> entgegen und gibt den entsprechenden Punkt zurück.
	 * @param t als <code>double</code>
	 * @return Punkt als {@link Point3}
	 */
	public Point3 at(final double t) {
		return o.add(d.normalized().mul(t));
	}
	
	/**
	 * Nimmt ein {@link Point3} Objekt als Parameter und gibt das entsprechende <code>t</code> zurück.
	 * @param p als {@link Point3}
	 * @return t als <code>double</code> 
	 */
	public double tOf(final Point3 p) {
		return p.sub(o).getMagnitude();
	}

	/**
	 * Gibt den Wert des Usprungs <code>o</code> zurück.
	 * 
	 * @return <code>o</code> als <b>Point3</b>
	 */
	public Point3 getO() {
		return o;
	}

	/**
	 * Richtung des Strahls <code>Ray</code> als <code>Vector3</code> zurueck.
	 * 
	 * @return <code>d</code> als <b>Vector3</b>
	 */
	public Vector3 getD() {
		return d;
	}

	@Override
	public String toString() {
		return "Ray\n[o = " + o + ", \nd = " + d + "]";
	}
	
	

}