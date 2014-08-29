package raytracer.camera;

import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.raytracer.Ray;

/**
 * Repreasentiert eine Kamera durch einen Punkt und zwei Vektoren. Berechnet ein
 * neues Koordinatensystem basierend auf der Kamera.
 */
public abstract class Camera {
	
	// Da abstract class, keine ueberschriebene toString() Methode.
	// Da abstract class, keine ueberschriebene equals()   Methode.
	// Da abstract class, keine ueberschriebene hashCode() Methode.

	/**
	 * Position der Kamera.
	 */
	public Point3 e; // Da public, keine Getter-Methode

	/**
	 * Blickrichtung der Kamera.
	 */
	public Vector3 g; // Da public, keine Getter-Methode

	/**
	 * Up-Vektor der Kamera.
	 */
	public Vector3 t; // Da public, keine Getter-Methode

	/**
	 * u-Achse des Koordinatensystems.
	 */
	public Vector3 u; // Da public, keine Getter-Methode

	/**
	 * v-Achse des Koordinatensystems.
	 */
	public Vector3 v; // Da public, keine Getter-Methode

	/**
	 * w-Achse des Koordinatensystems.
	 */
	public Vector3 w; // Da public, keine Getter-Methode

	/**
	 * Konstruktor erzeugt ein {@link Camera} Objekt und berechnet das
	 * Koordinatensystem mit den Vektoren <code>u</code>, <code>v</code> und
	 * <code>w</code> und dem Ursprung <code>e</code>.
	 * 
	 * @param e
	 *            - {@link Point3} Objekt für die Position der Kamera.
	 * @param g
	 *            - {@link Vector3} objekt für die Blickrichtung der Kamera.
	 * @param t
	 *            - {@link Vector3} Objekt für den Up-Vektor der Kamera.
	 */
	public Camera(final Point3 e, final Vector3 g, final Vector3 t) {

		if (e == null) {
			throw new IllegalArgumentException(
					"Der Parameter e darf nicht null sein");
		}
		if (g == null) {
			throw new IllegalArgumentException(
					"Der Parameter g darf nicht null sein");
		}
		if (t == null) {
			throw new IllegalArgumentException(
					"Der Parameter t darf nicht null sein");
		}

		this.e = e;
		this.g = g;
		this.t = t;

		this.w = g.normalized().mul(-1);
		this.u = t.x(w).normalized();
		this.v = this.w.x(this.u);
	}

	/**
	 * Gibt den neu erzeugten Strahl als {@link Ray} Objekt fuer den Pixel (
	 * <code>x</code>, <code>y</code>) zurueck.
	 * 
	 * @param w
	 *            - Breite des Bildes als <b>int</b>-Wert.
	 * @param h
	 *            - Hoehe des Bildes als <b>int</b>-Wert.
	 * @param x
	 *            - x-Koordinate des Pixels als <b>int</b>-Wert.
	 * @param y
	 *            - y-Koordinate des Pixels als <b>int</b>-Wert.
	 * @return {@link Ray}
	 */
	public abstract Ray rayFor(int w, int h, int x, int y);

}