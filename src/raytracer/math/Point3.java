package raytracer.math;

/**
 * Ein Objekt der Klasse repraesentiert einen Punkt aus dem Vektorraum R3.
 */
public class Point3 {

	/**
	 * x-Wert des Punktes
	 */
	public double x;
	/**
	 * y-Wert des Punktes
	 */
	public double y;
	/**
	 * z-Wert des Punktes
	 */
	public double z;
	
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
	
	public Point3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Subtrahiert <code>this</code> mit dem uebergebenen Punkt komponentenweise
	 * und gibt einen Ergebnisvektor zurueck.
	 * 
	 * @param point
	 *            Das {@link Point3}-Objekt, mit dem subtrahiert wird.
	 * @return {@link Vector3} - Ergebnisvektor der Punktaddition.
	 */
	
	public Vector3 sub(Point3 point) {
		return new Vector3((this.x - point.getX()), (this.y - point.getY()), (this.z - point.getZ()));
	}
	
	/**
	 * Subtrahiert <code>this</code> mit dem uebergebenen Vektor komponentenweise
	 * und gibt einen Ergebnispunkt zurueck.
	 * 
	 * @param vector
	 *            Das {@link Vector3}-Objekt, mit dem subtrahiert wird.
	 * @return {@link Point3} - Ergebnispunkt der Subtraktion des Punktes mit dem Vektor.
	 */
	
	public Point3 sub(Vector3 vector) {
		return new Point3((this.x - vector.getX()), (this.y - vector.getY()), (this.z - vector.getZ()));
	}
	
	public Vector3 sub(Normal3 n) {
		return new Vector3((this.x - n.getX()), (this.y - n.getY()), (this.z - n.getZ()));
	}
	
	/**
	 * Addiert <code>this</code> mit dem uebergebenen Vektor komponentenweise
	 * und gibt einen neuen Ergebnispunkt zurueck.
	 * 
	 * @param vector
	 *            Das {@link Vector3}-Objekt, mit dem addiert wird.
	 * @return {@link Point3} - Ergebnisvektor der Vektoraddition.
	 */
	public Point3 add(Vector3 vector) {
		return new Point3((this.x + vector.getX()), (this.y + vector.getY()), (this.z + vector.getZ()));
	}
	
	/**
	 * Berechnet das Kreuzprodukt von <code>this</code> und dem uebergebenen
	 * Vektor. Gibt einen neuen Ergebnisvektor zurueck.
	 * 
	 * @param point
	 *            Das {@link Point3}-Objekt, mit dem das Kreuzprodukt gebildet
	 *            wird.
	 * @return {@link Normal3} - Kreuzprodukt
	 */
	public Normal3 x(final Point3 point) {
		return new Normal3((y * point.z) - (z * point.y), (z * point.x)
				- (x * point.z), (x * point.y) - (y * point.x));
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
		return "Point3 = x:" + getX() + " y:" + getY() + " z:" + getZ();
	}

	public double dot(Vector3 vector) {
		return (x * vector.x) + (y * vector.y) + (z * vector.z);
	}

	public double dot(Normal3 n) {
		return (x * n.x) + (y * n.y) + (z * n.z);
	}

	public double dot(Point3 p) {
		return (x * p.x) + (y * p.y) + (z * p.z);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3 other = (Point3) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}
	
}