package raytracer.math;

/**
 * Ein Objekt der Klasse repraesentiert einen Vektor aus dem Vektorraum R3.
 */
public class Vector3 {

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
	 * Laenge des Vektors
	 */
	public double magnitude;

	/**
	 * Konstruktor erzeugt eine {@link Vector3} Objekt mit den uebergebenen
	 * Werten fuer x, y und z.
	 * 
	 * @param x
	 *            - Wert fuer
	 * @param y
	 *            - Wert fuer <code>y</code>
	 * @param z
	 *            - Wert fuer <code>z</code>
	 */
	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.magnitude = Math.sqrt((x * x) + (y * y) + (z * z));
	}

	/**
	 * Addiert <code>this</code> mit dem uebergebenen Vektor komponentenweise
	 * und gibt einen neuen Ergebnisvektor zurueck.
	 * 
	 * @param vector
	 *            Das {@link Vector3}-Objekt, mit dem addiert wird.
	 * @return {@link Vector3} - Ergebnisvektor der Vektoraddition.
	 */
	public Vector3 add(final Vector3 vector) {
		return new Vector3((x + vector.x), (y + vector.y), (z + vector.z));
	}

	/**
	 * Addiert <code>this</code> mit der uebergebenen Normalen komponentenweise
	 * und gibt einen neuen Ergebnisvektor zurueck.
	 * 
	 * @param normal
	 *            Das {@link Normal3}-Objekt mit dem addiert wird.
	 * @return {@link Vector3} - Ergebnisvektor der Addition.
	 */
	public Vector3 add(final Normal3 normal) {
		return new Vector3((x + normal.x), (y + normal.y), (z + normal.z));
	}

	/**
	 * Subtrahiert die uebergebene Normale von <code>this</code> und gibt einen
	 * neuen Ergebnisvektor zurueck.
	 * 
	 * @param normal
	 *            Das {@link Normal3}-Objekt mit dem subtrahiert wird.
	 * @return {@link Vector3} - Ergebnisvektor der Subtraktion.
	 */
	public Vector3 sub(final Normal3 normal) {
		return new Vector3((x - normal.x), (y - normal.y), (z - normal.z));
	}
	
	/**
	 * Subtrahiert die uebergebene Vektor von <code>this</code> und gibt einen
	 * neuen Ergebnisvektor zurueck.
	 * 
	 * @param normal
	 *            Das {@link Vector3}-Objekt mit dem subtrahiert wird.
	 * @return {@link Vector3} - Ergebnisvektor der Subtraktion.
	 */
	public Vector3 sub(final Vector3 v) {
		return new Vector3((x - v.x), (y - v.y), (z - v.z));
	}

	/**
	 * Multipliziert <code>this</code> mit dem uebergebenen Skalar und gibt
	 * einen neuen Ergebnisvektor zurueck.
	 * 
	 * @param c
	 *            Skalar mit dem multipliziert wird.
	 * @return {@link Vector3} - Ergebnisvektor
	 */
	public Vector3 mul(final double c) {
		return new Vector3((x * c), (y * c), (z * c));
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

	/**
	 * Liefert das Skalarprodukt von <code>this</code> und der uebergebenen
	 * Normalen.
	 * 
	 * @param normal
	 *            Das {@link Normal3}-Objekt mit dem multipliziert wird.
	 * @return Das Skalarprodukt als <b>double</b> Wert.
	 */
	public double dot(final Normal3 normal) {
		return (x * normal.x) + (y * normal.y) + (z * normal.z);
	}

	/**
	 * Liefert die Laenge von <code>this</code>.
	 * 
	 * @return Laenge des Vektors als <b>double</b> Wert.
	 */
	public double length() {
		return Math.sqrt((x * x) + (y * y) + (z * z));
	}

	/**
	 * Liefert einen neuen normalisierten Vektor von <code>this</code>.
	 * 
	 * @return {@link Vector3} - Normalisierter Vektor.
	 */
	public Vector3 normalized() {
		return this.mul(1 / magnitude);
	}

	/**
	 * Liefert eine neue Normale mit den Werten <code>x</code>, <code>y</code>
	 * und <code>z</code> von <code>this</code>.
	 * 
	 * @return {@link Normal3} - <code>this</code> als Normale.
	 */
	public Normal3 asNormal() {
		return new Normal3(x, y, z);
	}

	/**
	 * Liefert einen neuen Reflektionsvektor von <code>this</code> und der
	 * uebergebenen Normalen.
	 * 
	 * @param normal
	 *            Das {@link Normal3}-Objekt an dem <code>this</code>
	 *            reflektiert wird.
	 * @return {@link Vector3} - Reflektionsvektor.
	 */
	public Vector3 reflectedOn(final Normal3 normal) {
		Vector3 normalAsVector = new Vector3(normal.getX(), normal.getY(),
				normal.getZ());
		Vector3 reflect = new Vector3(getX(), getY(), getZ());

		return reflect.add(normalAsVector.mul(2 * (-(reflect.dot(normal)))))
				.mul(-1);
	}

	/**
	 * Berechnet das Kreuzprodukt von <code>this</code> und dem uebergebenen
	 * Vektor. Gibt einen neuen Ergebnisvektor zurueck.
	 * 
	 * @param vector
	 *            Das {@link Vector3}-Objekt, mit dem das Kreuzprodukt gebildet
	 *            wird.
	 * @return {@link Vector3} - Kreuzprodukt
	 */
	public Vector3 x(final Vector3 vector) {
		return new Vector3((y * vector.z) - (z * vector.y), (z * vector.x)
				- (x * vector.z), (x * vector.y) - (y * vector.x));
	}
	
	/**
	 * Berechnet das Kreuzprodukt von <code>this</code> und dem uebergebenen
	 * Vektor. Gibt einen neue Normale zurueck.
	 * 
	 * @param vector
	 *            Das {@link Vector3}-Objekt, mit dem das Kreuzprodukt gebildet
	 *            wird.
	 * @return {@link Normal3} - Kreuzprodukt
	 */
	public Normal3 xNormal(final Vector3 vector) {
		return new Normal3((y * vector.z) - (z * vector.y), (z * vector.x)
				- (x * vector.z), (x * vector.y) - (y * vector.x));
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
	 * Gibt <code>magnitude</code>-Wert von <code>this</code> zurueck.
	 * 
	 * @return <b>double</b> - magnitude.
	 */
	public double getMagnitude() {
		return magnitude;
	}

	@Override
	public String toString() {
		return "Vector3 = x:" + getX() + " y:" + getY() + " z:" + getZ()
				+ " Magnitude:" + getMagnitude();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(magnitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Vector3 other = (Vector3) obj;
		if (Double.doubleToLongBits(magnitude) != Double
				.doubleToLongBits(other.magnitude))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}
	

}