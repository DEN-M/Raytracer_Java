package raytracer.camera;

import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.raytracer.Ray;

/**
 * Ein Objekt der Klasse repreasentiert eine perspektivische Kamera.
 */
public class PerspectiveCamera extends Camera {

	/**
	 * Oeffnungswinkel der Kamera.
	 */
	private double angle;

	/**
	 * Konstruktor erzeugt ein {@link PerspectiveCamera} Objekt und berechnet
	 * das Koordinatensystem mit den Vektoren <code>u</code>, <code>v</code> und
	 * <code>w</code> und dem Ursprung <code>e</code>.
	 * 
	 * @param e
	 *            - {@link Point3} Objekt für die Position der Kamera.
	 * @param g
	 *            - {@link Vector3} objekt für die Blickrichtung der Kamera.
	 * @param t
	 *            - {@link Vector3} Objekt für den Up-Vektor der Kamera.
	 * @param angle
	 *            - Öffnungswinkel der Kamera als <b>double</b>-Wert.
	 */
	public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t,
			final double angle) {
		super(e, g, t);

		// testen, ob angle Einschränkungen braucht (Graphik)
		this.setAngle(angle);
	}

	@Override
	public Ray rayFor(final int w, final int h, final int x, final int y) {
		
		if (w < 0) {
			throw new IllegalArgumentException(
					"Der Parameter w darf nicht kleiner 0 sein");
		}
		if (h < 0) {
			throw new IllegalArgumentException(
					"Der Parameter h darf nicht kleiner 0 sein");
		}

		if (x < 0 || x > w) {
			throw new IllegalArgumentException(String.format(
					"Der Parameter x muss einen Wert zwischen 0 und %d haben",
					w));
		}
		if (y < 0 || y > h) {
			throw new IllegalArgumentException(String.format(
					"Der Parameter y muss einen Wert zwischen 0 und %d haben",
					h));
		}

		Point3 o = new Point3(e.getX(), e.getY(), e.getZ());

		double skarlarA = (h) / (Math.tan(Math.toRadians(angle))); // das h könne man auch durch 2 teilen dann wird die Ansicht kleiner
		double skarlarX = x - ((w - 1) / 2);
		double skarlarY = y - ((h - 1) / 2);
		Vector3 d = super.w.mul(-1).mul(skarlarA).add(u.mul(skarlarX))
				.add(v.mul(skarlarY)).normalized();

		return new Ray(o, d);
	}

	/**
	 * Gibt den Wert des Öffnungswinkels der Kamera zurueck.
	 * 
	 * @return <b>double</b> <code>angle</code>
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * Setzt den Wert des Öffnunfgswinkels der Kamera auf dem uebergebenen
	 * Wert.
	 * 
	 * @param angle
	 *            - Öffnungswinkel
	 */
	public void setAngle(final double angle) {
		this.angle = angle;
	}

	@Override
	public String toString() {

		return "PerspectiveCamera \n" + "[Öffnungswinkel angle = " + angle
				+ ",\n" + "Position e = " + e + ",\n" + "Blickrichtung g = "
				+ g + ",\n" + "Up-Vektor t = " + t + ",\n" + "u-Achse = " + u
				+ ",\n" + "v-Achse = " + v + ",\n" + "w-Achse = " + w + "]";
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj instanceof PerspectiveCamera) {
			PerspectiveCamera oc = (PerspectiveCamera) obj;

			// Alle Felder pruefen: angle, e, g, t, u, v, w

			if (this.angle != oc.angle) {
				return false;
			}
			if (this.e.x != oc.e.x || this.e.y != oc.e.y || this.e.z != oc.e.z) {
				return false;
			}
			if (this.g.x != oc.g.x || this.g.y != oc.g.y || this.g.z != oc.g.z
					|| this.g.magnitude != oc.g.magnitude) {
				return false;
			}
			if (this.t.x != oc.t.x || this.t.y != oc.t.y || this.t.z != oc.t.z
					|| this.t.magnitude != oc.t.magnitude) {
				return false;
			}
			if (this.u.x != oc.u.x || this.u.y != oc.u.y || this.u.z != oc.u.z
					|| this.u.magnitude != oc.u.magnitude) {
				return false;
			}
			if (this.v.x != oc.v.x || this.v.y != oc.v.y || this.v.z != oc.v.z
					|| this.v.magnitude != oc.v.magnitude) {
				return false;
			}
			if (this.w.x != oc.w.x || this.w.y != oc.w.y || this.w.z != oc.w.z
					|| this.w.magnitude != oc.w.magnitude) {
				return false;
			}

			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prim = 73;
		int erg = 5;

		// Alle Felder einbeziehen: angle, e, g, t, u, v, w

		erg = (int) (prim * erg + angle);
		erg = prim * erg + e.hashCode();
		erg = prim * erg + g.hashCode();
		erg = prim * erg + t.hashCode();
		erg = prim * erg + u.hashCode();
		erg = prim * erg + v.hashCode();
		erg = prim * erg + w.hashCode();

		return erg;
	}

}
