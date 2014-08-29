package raytracer.math;

import raytracer.raytracer.Ray;

/**
 * Ein Objekt der Klasse repraesentiert Transformationen mit <code>Mat4x4</code>
 */
public class Transform {
	
	/**
	 * Transformation <code>m</code>
	 */
	public final Mat4x4 m;
	
	/**
	 * Inverse <code>i</code> von der Transformation <code>m</code> 
	 */
	public final Mat4x4 i;
	
	/**
	 * öffenlicher Konstruktor nimmt keine Parameter entgegen und initialisiert die Transformation mit der Einheitsmatrix.
	 */
	public Transform() {
		this.m = new Mat4x4(	1.0, 0.0, 0.0, 0.0, 
								0.0, 1.0, 0.0, 0.0, 
								0.0, 0.0, 1.0, 0.0, 
								0.0, 0.0, 0.0, 1.0);
		
		this.i = new Mat4x4(	1.0, 0.0, 0.0, 0.0, 
								0.0, 1.0, 0.0, 0.0, 
								0.0, 0.0, 1.0, 0.0, 
								0.0, 0.0, 0.0, 1.0);
	}
	
	/**
	 * privater Konstruktor nimmt jeweils die beien Parameter entegen und initialisiert die Transformation <code>m</code> und dessen Inverse <code>i</code>
	 * @param m Transformation
	 * @param i  Inverse <code>i</code> von der Transformation <code>m</code>
	 */
	private Transform(final Mat4x4 m, final Mat4x4 i) {
		this.m = m;
		this.i = i;
	}
	
	
	/**
	 * Translation um einen Punkt <code>Point3</code>
	 * @param point Punkt um den die Translation statfinden soll
	 * @return Ergebniss der Translation
	 */
	public Transform translate(final Point3 point) {
		Transform transform = new Transform(new Mat4x4(	1.0, 0.0, 0.0, point.x, 
														0.0, 1.0, 0.0, point.y, 
														0.0, 0.0, 1.0, point.z, 
														0.0, 0.0, 0.0, 1.0), 
										new Mat4x4(		1.0, 0.0, 0.0, point.x * -1.0, 
														0.0, 1.0, 0.0, point.y * -1.0, 
														0.0, 0.0, 1.0, point.z * -1.0, 
														0.0, 0.0, 0.0, 1.0));
		return new Transform(this.m.mul(transform.m), this.i.mul(transform.i));
	}
	
	/**
	 * Skalierung um einem <code>Punkt3</code>
	 * @param point Punkt um den es sklaiert wird
	 * @return Ergebniss der Skalierung
	 */
	public Transform scaling(final Point3 point) {
		Transform transform = new Transform(new Mat4x4(	point.x, 	0.0, 		0.0, 		 0.0, 
														0.0, 		point.y, 	0.0, 	 	 0.0, 
														0.0,		0.0, 		point.z, 	 0.0, 
														0.0, 		0.0, 		0.0, 		 1.0), 
										new Mat4x4(		1.0/point.x,0.0, 0.0,	0.0, 
														0.0, 		1.0/point.y,0.0,	 	 0.0, 
														0.0, 		0.0, 		1.0/point.z, 0.0, 
														0.0, 		0.0, 		0.0,	 	 1.0));
		return new Transform(this.m.mul(transform.m), this.i.mul(transform.i));
	}
	
	/**
	 * Rotation umd die X-Achse
	 * @param x Wert für die X-Koordienate um wiviel Rotiert werden soll
	 * @return Ergebniss der Rotation
	 */
	public Transform rotationX(final double x) {
		Transform transform = new Transform(new Mat4x4(	1.0, 0.0, 				0.0, 				0.0, 
														0.0, Math.cos(x), 		Math.sin(x) * -1.0, 0.0, 
														0.0, Math.sin(x), 		Math.cos(x), 		0.0, 
														0.0, 0.0, 				0.0, 				1.0), 
										new Mat4x4(		1.0, 0.0, 				0.0, 				0.0, 
														0.0, Math.cos(x), 		Math.sin(x), 		0.0, 
														0.0, Math.sin(x) * -1.0,Math.cos(x), 		0.0, 
														0.0, 0.0, 				0.0, 				1.0));
		return new Transform(this.m.mul(transform.m), this.i.mul(transform.i));
	}
	
	/**
	 * Rotation umd die Y-Achse
	 * @param y Wert für die Y-Koordienate um wiviel Rotiert werden soll
	 * @return Ergebniss der Rotation
	 */
	public Transform rotationY(final double y) {
		Transform transform = new Transform(new Mat4x4(	Math.cos(y),		0.0, Math.sin(y), 		0.0, 
														0.0, 				1.0, 0.0, 				0.0, 
														Math.sin(y) * -1.0, 0.0, Math.cos(y), 		0.0, 
														0.0, 				0.0, 0.0, 				1.0), 
										new Mat4x4(		Math.cos(y), 		0.0, Math.sin(y) * -1.0,0.0, 
														0.0, 				1.0, 0.0, 				0.0, 
														Math.sin(y), 		0.0, Math.cos(y), 		0.0, 
														0.0, 				0.0, 0.0, 				1.0));
		return new Transform(this.m.mul(transform.m), this.i.mul(transform.i));
	}
	
	/**
	 * Rotation umd die Z-Achse
	 * @param z Wert für die Z-Koordienate um wiviel Rotiert werden soll
	 * @return Ergebniss der Rotation
	 */
	public Transform rotationZ(final double z) {
		Transform transform = new Transform(new Mat4x4(	Math.cos(z), Math.sin(z) * -1.0, 0.0, 0.0, 
														Math.sin(z), Math.cos(z), 		 0.0, 0.0, 
														0.0, 		 0.0, 				 0.0, 0.0, 
														0.0, 		 0.0, 				 0.0, 1.0), 
										new Mat4x4(		Math.cos(z), 	Math.sin(z), 	 0.0, 0.0, 
														Math.sin(z) * -1.0, Math.cos(z), 0.0, 0.0, 
														0.0, 				0.0, 		 0.0, 0.0, 
														0.0, 				0.0, 		 0.0, 1.0));
		return new Transform(this.m.mul(transform.m), this.i.mul(transform.i));
	}
	
	
	/**
	 * Multiplikation mit dem Strahl
	 * @param ray der eingehende Strahl
	 * @return Strahl nach der Multiplikation
	 */
	public Ray mul(final Ray ray) {
		return new Ray(this.i.mul(ray.o), this.i.mul(ray.d));
	}
	
	/**
	 * Multiplikation mit einer Normalen
	 * @param normal Normale mit der multipliziert wird
	 * @return Normale nach der Multiplikation
	 */
	public Normal3 mul(final Normal3 normal) {
		return i.transposed().mul(normal.asVector()).normalized().asNormal();
	}
	

}