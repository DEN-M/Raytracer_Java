package raytracer.math;

/**
 * Ein Objekt der Klasse repraesentiert eine 3x3 Matrix.
 */
public class Mat3x3 {
	/**
	 * Anordnung der Werte in einer 3x3 Matrix:
	 * 
	 *		 m11 m12 m13
	 *	     m21 m22 m23
	 *		 m31 m32 m33
	 *
	 */
	
	/*************************************************************************************************/
	
	/**
	 * m11-Wert der Matrix
	 */
	public double m11;
	/**
	 * m12-Wert der Matrix
	 */
	public double m12;
	/**
	 * m13-Wert der Matrix
	 */
	public double m13;
	/**
	 * m21-Wert der Matrix
	 */
	public double m21;
	/**
	 * m22-Wert der Matrix
	 */
	public double m22;
	/**
	 * m23-Wert der Matrix
	 */
	public double m23;
	/**
	 * m31-Wert der Matrix
	 */
	public double m31;
	/**
	 * m32-Wert der Matrix
	 */
	public double m32;
	/**
	 * m33-Wert der Matrix
	 */
	public double m33;
	/**
	 * Wert der Determinante
	 */
	public double determinant;
	
	/**
	 * Konstruktor erzeugt ein {@link Mat3x3} Objekt mit den uebergebenen
	 * Werten fuer m11,m12,m13,m21,m22,m23,m31,m32,m33.
	 * 
	 * @param m11
	 *            - Wert fuer <code>m11</code>
	 * @param m12
	 *            - Wert fuer <code>m12</code>
	 * @param m13
	 *            - Wert fuer <code>m13</code>
	 * @param m21
	 *            - Wert fuer <code>m21</code>
	 * @param m22
	 *            - Wert fuer <code>m22</code>     
	 * @param m23
	 *            - Wert fuer <code>m23</code>
	 * @param m31
	 *            - Wert fuer <code>m31</code>
	 * @param m32
	 *            - Wert fuer <code>m32</code>
     * @param m33
	 *            - Wert fuer <code>m33</code>
	 */

	public Mat3x3(final double m11, final double m12, final double m13,
			final double m21, final double m22, final double m23,
			final double m31, final double m32, final double m33) {
		this.m11 = m11;
		this.m12 = m12;
		this.m13 = m13;
		this.m21 = m21;
		this.m22 = m22;
		this.m23 = m23;
		this.m31 = m31;
		this.m32 = m32;
		this.m33 = m33;
		
		
		determinant = 	m11 * m22 * m33 + 
						m12 * m23 * m31 + 
						m13 * m21 * m32 - 
						m31 * m22 * m13 - 
						m32 * m23 * m11 -
						m33 * m21 * m12;
	}

	/**
	 * Multipliziert <code>this</code> mit der uebergebenen 3x3Matrix und gibt
	 * eine neue 3x3 Matrix zurueck.
	 * 
	 * @param mat
	 *            3x3 Matrix mit der multipliziert wird.
	 * @return {@link 3x3Mat} - Ergebnismatrix
	 */
	public Mat3x3 mul(final Mat3x3 mat) {

		double c11 = this.m11 * mat.m11 + this.m12 * mat.m21 + this.m13
				* mat.m31;
		double c12 = this.m11 * mat.m12 + this.m12 * mat.m22 + this.m13
				* mat.m32;
		double c13 = this.m11 * mat.m13 + this.m12 * mat.m23 + this.m13
				* mat.m33;

		double c21 = this.m21 * mat.m11 + this.m22 * mat.m21 + this.m23
				* mat.m31;
		double c22 = this.m21 * mat.m12 + this.m22 * mat.m22 + this.m23
				* mat.m32;
		double c23 = this.m21 * mat.m13 + this.m22 * mat.m23 + this.m23
				* mat.m33;

		double c31 = this.m31 * mat.m11 + this.m32 * mat.m21 + this.m33
				* mat.m31;
		double c32 = this.m31 * mat.m12 + this.m32 * mat.m22 + this.m33
				* mat.m32;
		double c33 = this.m31 * mat.m13 + this.m32 * mat.m23 + this.m33
				* mat.m33;

		return new Mat3x3(c11, c12, c13, c21, c22, c23, c31, c32, c33);
	}

	/**
	 * Multipliziert <code>this</code> mit dem ubergebenem Vektor und gibt
	 * einen neuen Vektor zurueck.
	 * 
	 * @param vector
	 *            Vektor mit dem multipliziert wird.
	 * @return {@link Vector3} - Ergebnisvektor
	 */
	public Vector3 mul(final Vector3 vector) {
		double x = vector.x * this.m11 + vector.y * this.m12 + vector.z
				* this.m13;
		double y = vector.x * this.m21 + vector.y * this.m22 + vector.z
				* this.m23;
		double z = vector.x * this.m31 + vector.y * this.m32 + vector.z
				* this.m33;

		return new Vector3(x, y, z);
	}
	
	/**
	 * Ändert die erste Spalte des <code>this</code> Objektes
	 * mit den Werte des uebergebenem Vektors
	 *       m11 m12 m13
	 *	     m21 m22 m23
	 *		 m31 m32 m33
	 * 
	 * @param vector
	 *            Vektor der die Werte in der ersten Spalte in der Matrix aendert
	 * @return {@link Mat3x3} - Ergbnismatrix
	 */

	public Mat3x3 changeCol1(final Vector3 vector) {
		double c11 = vector.x;
		double c21 = vector.y;
		double c31 = vector.z;

		return new Mat3x3(c11, this.m12, this.m13, 
						c21, this.m22, this.m23,
						c31, this.m32, this.m33);
	}
	
	/**
	 * Ändert die zweite Spalte des <code>this</code> Objektes
	 * mit den Werte des uebergebenem Vektors
	 *       m11 m12 m13
	 *	     m21 m22 m23
	 *		 m31 m32 m33
	 * @param vector
	 *            Vektor der die Werte in der zweite Spalte in der Matrix aendert
	 * @return {@link Mat3x3} - Ergbnismatrix
	 */

	public Mat3x3 changeCol2(final Vector3 vector) {
		double c12 = vector.x;
		double c22 = vector.y;
		double c32 = vector.z;

		return new Mat3x3(this.m11, c12, this.m13, 
							this.m21, c22, this.m23,
							this.m31, c32, this.m33);
	}
	
	/**
	 * Ändert die dritte Spalte des <code>this</code> Objektes
	 * mit den Werte des uebergebenem Vektors
	 * 
	 * @param vector
	 *            Vektor der die Werte in der dritten Spalte in der Matrix aendert
	 * @return {@link Mat3x3} - Ergbnismatrix
	 */

	public Mat3x3 changeCol3(final Vector3 vector) {
		double c13 = vector.x;
		double c23 = vector.y;
		double c33 = vector.z;

		return new Mat3x3(this.m11, this.m12, c13, 
							this.m21, this.m22, c23, 
							this.m31, this.m32, c33);
	}
	
	/**
	 * Gibt einen String der Werte: <code>m11</code><code>m12</code><code>m13</code><code>m21</code><code>m22</code><code>m23</code><code>m31</code><code>m32</code><code>m33</code> des, <code>this</code> Objekts zurueck.
	 * 
	 * @return String
	 */

	@Override
	public String toString() {
		return "Mat3x3 = m11=" + m11 + ", m12=" + m12 + ", m13=" + m13
				+ ", m21=" + m21 + ", m22=" + m22 + ", m23=" + m23 + ", m31="
				+ m31 + ", m32=" + m32 + ", m33=" + m33 + "]\n" + "(" + m11
				+ "  " + m12 + "  " + m13 + ")\n" + "(" + m21 + "  " + m22
				+ "  " + m23 + ")\n" + "(" + m31 + "  " + m32 + "  " + m33
				+ ")\n";
	}

}