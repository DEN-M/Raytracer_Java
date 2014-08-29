package raytracer.math;

/**
 * Ein Objekt der Klasse repraesentiert eine Normale aus dem Vektorraum R3.
 */
public class Normal3 {
	
	/**
	 * x-Wert des Vektors
	 */
	public double x;
	/**
	 * y-Wert des Vektors
	 */
	public double y;
	/**
	 * z-Wert des Vektors
	 */
	public double z;
	
	/**
	 * Normalvektor für die <code>AxisAlgnedBox</code>
	 */
	public static Normal3 X = new Normal3(1, 0, 0);
	public static Normal3 Y = new Normal3(0, 1, 0);
	public static Normal3 Z = new Normal3(0, 0, 1);
	
	/**
	 * Konstruktor erzeugt eine {@link Point3} Objekt mit den uebergebenen
	 * Werten fuer x, y und z.
	 * 
	 * @param x
	 *            - Wert fuer <code>x</code>
	 * @param y
	 *            - Wert fuer <code>y</code>
	 * @param z
	 *            - Wert fuer <code>z</code>
	 */
	public Normal3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Multipliziert <code>this</code> mit dem uebergebenen Skalar und gibt
	 * einen neuen Ergebnisvektor zurueck.
	 * 
	 * @param normal
	 *            Skalar mit dem multipliziert wird.
	 * @return {@link Normal3} - neue Normale
	 */
	
	public Normal3 mul(final double normal) {
		return new Normal3((x * normal), (y * normal), (z * normal));
	}
	
	/**
	 * Addiert <code>this</code> mit der uebergebenen Normalen und gibt
	 * eine neue Normale zurueck.
	 * 
	 * @param normal
	 *            Normale mit der addiert wird.
	 * @return {@link Vector3} - Ergebnisvektor
	 */
	
	public Normal3 add(final Normal3 normal) {
		return new Normal3((x + normal.x), (y + normal.y), (z + normal.z));
	}
	
	/**
	 * Subtrahiert die uebergebene Normale von <code>this</code> und gibt einen
	 * neuen Ergebnisvektor zurueck.
	 * 
	 * @param point
	 *            Das {@link Point3}-Objekt mit dem subtrahiert wird.
	 * @return {@link Normal3} - Ergebnisvektor der Subtraktion.
	 */
	public Normal3 sub(final Point3 point) {
		return new Normal3((x - point.x), (y - point.y), (z - point.z));
	}
	
	/**
	 * Liefert das Skalarprodukt von <code>this</code> und dem uebergebenen
	 * Vektor.
	 * 
	 * @param vector
	 *            Das {@link Vector3}-Objekt mit dem multipliziert wird.
	 * @return Das Skalarprodukt als <b>double</b> Wert.
	 */
	
	public double dot(final Vector3 vector) {
		return (x * vector.x) + (y * vector.y) + (z * vector.z);
	}
	
	public double dot(Point3 p) {
		return (x * p.x) + (y * p.y) + (z * p.z);
	}
	
	/**
	 * Berechnet das Kreuzprodukt von <code>this</code> und dem uebergebenen
	 * Vektor. Gibt einen neuen Ergebnisvektor zurueck.
	 * 
	 * @param normal
	 *            Das {@link Normal3}-Objekt, mit dem das Kreuzprodukt gebildet
	 *            wird.
	 * @return {@link Normal3} - Kreuzprodukt
	 */
	public Normal3 x(final Normal3 normal) {
		return new Normal3((y * normal.z) - (z * normal.y), (z * normal.x)
				- (x * normal.z), (x * normal.y) - (y * normal.x));
	}
	
	/**
	 * Berechnet das Kreuzprodukt von <code>this</code> und dem uebergebenen
	 * Vektor. Gibt einen neuen Ergebnisvektor zurueck.
	 * 
	 * @param Vector
	 *            Das {@link Normal3}-Objekt, mit dem das Kreuzprodukt gebildet
	 *            wird.
	 * @return {@link Normal3} - Kreuzprodukt
	 */
	public Normal3 x(final Vector3 Vector) {
		return new Normal3((y * Vector.z) - (z * Vector.y), (z * Vector.x)
				- (x * Vector.z), (x * Vector.y) - (y * Vector.x));
	}
	
	/**
	 * Liefert einen neue Vektor mit den Werten <code>x</code>, <code>y</code>
	 * und <code>z</code> von <code>this</code>.
	 * 
	 * @return {@link Vector3} - <code>this</code> als Vektor.
	 */
	public Vector3 asVector() {
		return new Vector3(this.x, this.y, this.z);
	}
	
	/**
	 * Gibt <code>x</code>-Wert von <code>this</code> zurueck.
	 * 
	 * @return <b>double</b> - x.
	 */

	public double getX() {
		return x;
	}
	
	/**
	 * Gibt <code>y</code>-Wert von <code>this</code> zurueck.
	 * 
	 * @return <b>double</b> - y.
	 */

	public double getY() {
		return y;
	}

	/**
	 * Gibt <code>z</code>-Wert von <code>this</code> zurueck.
	 * 
	 * @return <b>double</b> - z.
	 */
	
	public double getZ() {
		return z;
	}
	
	/**
	 * Gibt einen String der Werte: <code>x</code><code>y</code><code>z</code> des, <code>this</code> Objekts zurueck.
	 * 
	 * @return String
	 */
	
	@Override
	public String toString() {
		return "Normal3 = x:" + this.x + " y:" + this.y + " z:" + this.z;
	}
	
}