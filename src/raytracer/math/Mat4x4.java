package raytracer.math;

/**
 * Ein Objekt der Klasse repraesentiert eine 4x4 Matrix.
 */
public class Mat4x4 {

	/**
	 * Anordnung der Werte in einer 4x4 Matrix:
	 * 
	 *		 m11 m12 m13 m14
	 *	     m21 m22 m23 m24
	 *		 m31 m32 m33 m34
	 *		 m41 m42 m43 m44
	 *
	 */
	
	/*************************************************************************************************/
	
	/**
	 * m11-Wert der Matrix
	 */
	public final double m11;
	/**
	 * m12-Wert der Matrix
	 */
	public final double m12;
	/**
	 * m13-Wert der Matrix
	 */
	public final double m13;
	/**
	 * m14-Wert der Matrix
	 */
	public final double m14;
	/**
	 * m21-Wert der Matrix
	 */
	public final double m21;
	/**
	 * m22-Wert der Matrix
	 */
	public final double m22;
	/**
	 * m23-Wert der Matrix
	 */
	public final double m23;
	/**
	 * m24-Wert der Matrix
	 */
	public final double m24;
	/**
	 * m31-Wert der Matrix
	 */
	public final double m31;
	/**
	 * m32-Wert der Matrix
	 */
	public final double m32;
	/**
	 * m33-Wert der Matrix
	 */
	public final double m33;
	/**
	 * m34-Wert der Matrix
	 */
	public final double m34;
	/**
	 * m41-Wert der Matrix
	 */
	public final double m41;
	/**
	 * m42-Wert der Matrix
	 */
	public final double m42;
	/**
	 * m43-Wert der Matrix
	 */
	public final double m43;
	/**
	 * m44-Wert der Matrix
	 */
	public final double m44;
	
	/**
	 * Konstruktor erzeugt ein {@link Mat4x4} Objekt mit den uebergebenen
	 * Werten fuer m11,m12,m13,m14,m21,m22,m23,m24,m31,m32,m33,m34,m41,m42,m43,m44.
	 * 
	 * @param m11
	 *            - Wert fuer <code>m11</code>
	 * @param m12
	 *            - Wert fuer <code>m12</code>
	 * @param m13
	 *            - Wert fuer <code>m13</code>
	 * @param m14
	 *            - Wert fuer <code>m14</code>
	 * @param m21
	 *            - Wert fuer <code>m21</code>
	 * @param m22
	 *            - Wert fuer <code>m22</code>     
	 * @param m23
	 *            - Wert fuer <code>m23</code>
	 * @param m24
	 *            - Wert fuer <code>m24</code>
	 * @param m31
	 *            - Wert fuer <code>m31</code>
	 * @param m32
	 *            - Wert fuer <code>m32</code>
     * @param m33
	 *            - Wert fuer <code>m33</code>
     * @param m34
	 *            - Wert fuer <code>m34</code>
	 * @param m41
	 *            - Wert fuer <code>m41</code>
	 * @param m42
	 *            - Wert fuer <code>m42</code>
     * @param m43
	 *            - Wert fuer <code>m43</code>
     * @param m44
	 *            - Wert fuer <code>m44</code>
	 */

	public Mat4x4(	final double m11, final double m12, final double m13, final double m14,
					final double m21, final double m22, final double m23, final double m24,
					final double m31, final double m32, final double m33, final double m34,
					final double m41, final double m42, final double m43, final double m44) {
		this.m11 = m11;
		this.m12 = m12;
		this.m13 = m13;
		this.m14 = m14;
		this.m21 = m21;
		this.m22 = m22;
		this.m23 = m23;
		this.m24 = m24;
		this.m31 = m31;
		this.m32 = m32;
		this.m33 = m33;
		this.m34 = m34;
		this.m41 = m41;
		this.m42 = m42;
		this.m43 = m43;
		this.m44 = m44;
	}
	
	/**
	 * Multipliziert <code>this</code> mit dem ubergebenem Vektor und gibt
	 * einen neuen Vektor zurueck, wobei angenommen wird, dass das Element w den Wert 0 hat.
	 * 
	 * @param vector
	 *            Vektor mit dem multipliziert wird.
	 * @return {@link Vector3} - Ergebnisvektor
	 */
	public Vector3 mul(final Vector3 vector) {
		double x = vector.x * this.m11 + vector.y * this.m12 + vector.z	* this.m13;
		double y = vector.x * this.m21 + vector.y * this.m22 + vector.z	* this.m23;
		double z = vector.x * this.m31 + vector.y * this.m32 + vector.z	* this.m33;

		return new Vector3(x, y, z);
	}
	
	/**
	 * Multipliziert <code>this</code> mit dem ubergebenem Punkt und gibt
	 * einen neuen Punkt zurueck, wobei angenommen wird, dass das Element w den Wert 1 hat.
	 * 
	 * @param point
	 *            Punkt mit dem multipliziert wird.
	 * @return {@link Point3} - Punkt nach der Multiplikation
	 */
	public Point3 mul(final Point3 point) {
		double x = point.x * this.m11 + point.y * this.m12 + point.z * this.m13 + this.m14;
		double y = point.x * this.m21 + point.y * this.m22 + point.z * this.m23 + this.m24;
		double z = point.x * this.m31 + point.y * this.m32 + point.z * this.m33 + this.m34;

		return new Point3(x, y, z);
	}

	/**
	 * Multipliziert <code>this</code> mit der uebergebenen 4x4Matrix und gibt
	 * eine neue 4x4 Matrix zurueck. 
	 * 
	 * @param mat
	 *            4x4 Matrix mit der multipliziert wird.
	 * @return {@link 4x4Mat} - Ergebnismatrix
	 */
	public Mat4x4 mul(final Mat4x4 mat) {

		double c11 = this.m11 * mat.m11 + this.m12 * mat.m21 + this.m13	* mat.m31 + this.m14 * mat.m41;
		double c12 = this.m11 * mat.m12 + this.m12 * mat.m22 + this.m13	* mat.m32 + this.m14 * mat.m42;
		double c13 = this.m11 * mat.m13 + this.m12 * mat.m23 + this.m13	* mat.m33 + this.m14 * mat.m43;
		double c14 = this.m11 * mat.m14 + this.m12 * mat.m24 + this.m13	* mat.m34 + this.m14 * mat.m44;

		double c21 = this.m21 * mat.m11 + this.m22 * mat.m21 + this.m23	* mat.m31 + this.m24 * mat.m41;
		double c22 = this.m21 * mat.m12 + this.m22 * mat.m22 + this.m23	* mat.m32 + this.m24 * mat.m42;
		double c23 = this.m21 * mat.m13 + this.m22 * mat.m23 + this.m23	* mat.m33 + this.m24 * mat.m43;
		double c24 = this.m21 * mat.m14 + this.m22 * mat.m24 + this.m23	* mat.m34 + this.m24 * mat.m44;
		
		double c31 = this.m31 * mat.m11 + this.m32 * mat.m21 + this.m33	* mat.m31 + this.m34 * mat.m41;
		double c32 = this.m31 * mat.m12 + this.m32 * mat.m22 + this.m33	* mat.m32 + this.m34 * mat.m42;
		double c33 = this.m31 * mat.m13 + this.m32 * mat.m23 + this.m33	* mat.m33 + this.m34 * mat.m43;
		double c34 = this.m31 * mat.m14 + this.m32 * mat.m24 + this.m33	* mat.m34 + this.m34 * mat.m44;
		
		double c41 = this.m41 * mat.m11 + this.m42 * mat.m21 + this.m43	* mat.m31 + this.m44 * mat.m41;
		double c42 = this.m41 * mat.m12 + this.m42 * mat.m22 + this.m43	* mat.m32 + this.m44 * mat.m42;
		double c43 = this.m41 * mat.m13 + this.m42 * mat.m23 + this.m43	* mat.m33 + this.m44 * mat.m43;
		double c44 = this.m41 * mat.m14 + this.m42 * mat.m24 + this.m43	* mat.m34 + this.m44 * mat.m44;

		return new Mat4x4(c11, c12, c13, c14, c21, c22, c23, c24, c31, c32, c33, c34, c41, c42, c43, c44);
	}
	
	/**
	 * erzeugt und gibt die 4x4 Matrix (<code>Mat4x4</code>) zurück
	 * 	 	 m11 m12 m13 m14
	 *	     m21 m22 m23 m24
	 *		 m31 m32 m33 m34
	 *		 m41 m42 m43 m44
	 */
	public Mat4x4 transposed(){
		return new Mat4x4(m11, m21, m31, m41,
						  m12, m22, m32, m42,
						  m13, m23, m33, m43,
						  m14, m24, m34, m44);
	}
	
}