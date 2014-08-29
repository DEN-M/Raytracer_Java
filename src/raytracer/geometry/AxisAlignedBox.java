package raytracer.geometry;

import java.util.ArrayList;

import raytracer.material.Material;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.raytracer.Ray;
import raytracer.raytracer.Raytracer;

/**
 * 
 * Stellt eine {@link AxisAlignedBox} dar.
 * 
 */
public class AxisAlignedBox extends Geometry {

	public Point3 lbf; // hinten links unten (a)
	public Point3 run; // vorne rechts oben (b)
	private ArrayList<Plane> planes = new ArrayList<Plane>();
	private ArrayList<Plane> visibalePlanes = new ArrayList<Plane>();

	public AxisAlignedBox(Point3 lbf, Point3 run, Material material) {
		super(material);
		if (lbf.x < run.x && lbf.y < run.y && lbf.z > run.z) {
			this.lbf = lbf;
			this.run = run;
			planes.add(new Plane(lbf, Normal3.X.mul(-1.0), material));
			planes.add(new Plane(lbf, Normal3.Y.mul(-1.0), material));
			planes.add(new Plane(lbf, Normal3.Z, material));
			planes.add(new Plane(run, Normal3.X, material));
			planes.add(new Plane(run, Normal3.Y, material));
			planes.add(new Plane(run, Normal3.Z.mul(-1.0), material));
		} else {
			throw new IllegalArgumentException(
					"Geben Sie zwei gültige Ortsvektoren ein! Der erste Vektor muss links vorne unten, und der zweite Vektor rechts oben hinten liegen.");
		}

	}

	/**
	 * @param <code>ray</code> Strahl, der in die Welt gesendet wird
	 * 
	 * @return Schnittpunkt <code>hit</code> der mit der Box a, dichtesten an
	 *         der Kamera liegt
	 */
	public Hit hit(Ray ray) {
		Point3 o = ray.o;
		Vector3 d = ray.d.normalized();
		double t = -1;
		Plane geschnitteneEbene = null;
		visibalePlanes = new ArrayList<Plane>();

		// visible Planes herrausfinden
		for (int i = 0; i < planes.size(); i++) {
			if (planes.get(i).n.dot(o.sub(planes.get(i).a)) > 0) {
				visibalePlanes.add(planes.get(i));
			}
		}

		// kleinstes t herrausfinden
		for (Plane plane : visibalePlanes) {
			if (plane.n.dot(d) != 0) {
				double schnittpunkt = ((plane.a.dot(plane.n) - (o.dot(plane.n))) / (plane.n.dot(d)));
				if (schnittpunkt > t) {
					t = schnittpunkt;
					geschnitteneEbene = plane;
				}
			}
		}

		if (t >= 0) {
			Point3 hitpoint = ray.at(t);
			// liegt der hitpoint innerhalb der Box?
			if (lbf.x - Raytracer.EPSILON <= hitpoint.x
					&& run.x + Raytracer.EPSILON >= hitpoint.x
					&& lbf.y - Raytracer.EPSILON <= hitpoint.y
					&& run.y + Raytracer.EPSILON >= hitpoint.y
					&& lbf.z + Raytracer.EPSILON >= hitpoint.z
					&& run.z - Raytracer.EPSILON <= hitpoint.z) {
				return new Hit(t, ray, this, geschnitteneEbene.n);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}

// Funktioniert aber die Z-Koordinaten von der box sind verkehrt rum
// public class AxisAlignedBox extends Geometry {
//
// public Point3 lbf; // hinten links unten (a)
// public Point3 run; // vorne rechts oben (b)
// private ArrayList<Plane> planes = new ArrayList<Plane>();
// private ArrayList<Plane> visibalePlanes = new ArrayList<Plane>();
//
// public AxisAlignedBox(Point3 lbf, Point3 run, Material material) {
// super(material);
// if (lbf.x < run.x && lbf.y < run.y && lbf.z > run.z) {
// this.lbf = lbf;
// this.run = run;
// planes.add(new Plane(lbf, Normal3.X.mul(-1.0), material));
// planes.add(new Plane(lbf, Normal3.Y.mul(-1.0), material));
// planes.add(new Plane(lbf, Normal3.Z, material));
// planes.add(new Plane(run, Normal3.X, material));
// planes.add(new Plane(run, Normal3.Y, material));
// planes.add(new Plane(run, Normal3.Z.mul(-1.0), material));
// } else {
// throw new IllegalArgumentException(
// "Geben Sie zwei gültige Ortsvektoren ein! Der erste Vektor muss links vorne unten, und der zweite Vektor rechts oben hinten liegen.");
// }
//
// }
//
// /**
// * @param <code>ray</code> Strahl, der in die Welt gesendet wird
// *
// * @return Schnittpunkt <code>hit</code> der mit der Box a, dichtesten an der
// Kamera liegt
// */
// public Hit hit(Ray ray) {
// Point3 o = ray.o;
// Vector3 d = ray.d.normalized();
// double t = -1;
// Plane geschnitteneEbene = null;
// visibalePlanes = new ArrayList<Plane>();
//
// // visible Planes herrausfinden
// for(int i = 0; i < planes.size(); i++) {
// if(planes.get(i).n.dot(o.sub(planes.get(i).a)) > 0) {
// visibalePlanes.add(planes.get(i));
// }
// }
//
// // kleinstes t herrausfinden
// for(Plane plane : visibalePlanes) {
// if (plane.n.dot(d) != 0) {
// double schnittpunkt = ((plane.a.dot(plane.n) - (o.dot(plane.n))) /
// (plane.n.dot(d)));
// if(schnittpunkt > t) {
// t = schnittpunkt;
// geschnitteneEbene = plane;
// }
// }
// }
//
// if(t >= 0) {
// Point3 hitpoint = ray.at(t);
// // liegt der hitpoint innerhalb der Box?
// if( lbf.x - Raytracer.EPSILON <= hitpoint.x
// && run.x + Raytracer.EPSILON >= hitpoint.x
// && lbf.y - Raytracer.EPSILON <= hitpoint.y
// && run.y + Raytracer.EPSILON >= hitpoint.y
// && lbf.z + Raytracer.EPSILON >= hitpoint.z
// && run.z - Raytracer.EPSILON <= hitpoint.z
// ) {
// return new Hit(t, ray, this, geschnitteneEbene.n);
// } else {
// return null;
// }
// } else {
// return null;
// }
// }
//
// }



// richtige Koordinaten der Box aber die Falsche ausgabe von Plans
// public class AxisAlignedBox extends Geometry {
//
// public Point3 lbf; // hinten links unten (a)
// public Point3 run; // vorne rechts oben (b)
// private ArrayList<Plane> planes = new ArrayList<Plane>();
// private ArrayList<Plane> visibalePlanes = new ArrayList<Plane>();
//
// public AxisAlignedBox(Point3 lbf, Point3 run, Material material) {
// super(material);
// if (lbf.x < run.x && lbf.y < run.y && lbf.z < run.z) {
// this.lbf = lbf;
// this.run = run;
// planes.add(new Plane(lbf, new Normal3(0, -1, 0), material));
// planes.add(new Plane(lbf, new Normal3(-1, 0, 0), material));
// planes.add(new Plane(lbf, new Normal3(0, 0, 1), material));
// planes.add(new Plane(run, new Normal3(0, 1, 0), material));
// planes.add(new Plane(run, new Normal3(1, 0, 0), material));
// planes.add(new Plane(run, new Normal3(0, 0, -1), material));
//
// // planes.add(new Plane(lbf, new Normal3(0, -1, 0), material));
// // planes.add(new Plane(lbf, new Normal3(-1, 0, 0), material));
// // planes.add(new Plane(lbf, new Normal3(0, 0, 1), material));
// // planes.add(new Plane(run, new Normal3(0, 1, 0), material));
// // planes.add(new Plane(run, new Normal3(1, 0, 0), material));
// // planes.add(new Plane(run, new Normal3(0, 0, -1), material));
// } else {
// throw new IllegalArgumentException(
// "Geben Sie zwei gültige Ortsvektoren ein! Der erste Vektor muss links vorne unten, und der zweite Vektor rechts oben hinten liegen.");
// }
//
// }
//
// public Hit hit(Ray ray) {
// Point3 o = ray.o;
// Vector3 d = ray.d.normalized();
// double t = -1;
// Plane geschnitteneEbene = null;
// visibalePlanes = new ArrayList<Plane>();
//
// for(int i = 0; i < planes.size(); i++) {
// if(planes.get(i).a.dot(o.sub(planes.get(i).n)) > 0) {
// visibalePlanes.add(planes.get(i));
// }
// }
//
// for(Plane plane : visibalePlanes) {
// if (plane.a.dot(d) != 0) {
// double schnittpunkt = ((plane.a.dot(plane.n) - (o.dot(plane.n))) /
// (plane.n.dot(d)));
// if(schnittpunkt > t) {
// t = schnittpunkt;
// geschnitteneEbene = plane;
// }
// }
// }
//
// if(t >= 0) {
// Point3 hitpoint = ray.at(t);
// // Point3 hitpoint = ray.o.add(ray.d.mul(t));
// if(
// lbf.x - Raytracer.EPSILON <= hitpoint.x
// && run.x + Raytracer.EPSILON >= hitpoint.x
// && lbf.y - Raytracer.EPSILON <= hitpoint.y
// && run.y + Raytracer.EPSILON >= hitpoint.y
// && lbf.z - Raytracer.EPSILON <= hitpoint.z
// && run.z + Raytracer.EPSILON >= hitpoint.z
// ) {
// return new Hit(t, ray, this, geschnitteneEbene.n);
// } else {
// return null;
// }
// } else {
// return null;
// }
// }
//
// }