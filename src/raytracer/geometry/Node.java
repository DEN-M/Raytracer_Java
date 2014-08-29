package raytracer.geometry;

import java.util.List;

import raytracer.material.Material;
import raytracer.math.Transform;
import raytracer.raytracer.Ray;
import raytracer.raytracer.Raytracer;

public class Node extends Geometry {

	final public Transform transform;
	final private List<Geometry> geometryObjects;

	public Node(final Material material, final Transform transform,	final List<Geometry> geometryObjects) {
		super(material);
		this.transform = transform;
		this.geometryObjects = geometryObjects;
	}

	@Override
	public Hit hit(Ray ray) {
		// TODO Muss noch gemaht werden IST NOCH NICHT GETESTET!!!!
		Hit lowestHit = new Hit(1000000, null, null, null);
		Hit erg;
		Ray transformRay = this.transform.mul(ray);

		//alle Schnittpunkte des Rays mit den in den in der Scene enthaltenen Objekte in Array abspeichern
		for(Geometry geo : geometryObjects) {
			Hit hit = geo.hit(transformRay);
			if(hit == null) {
				continue;
			}
			// Bestimmung des Hitpointes mit dem kleinsten t
			if(hit != null && hit.t >= Raytracer.EPSILON && hit.t < lowestHit.t) {
				lowestHit = hit;
			}
		}
		
		//Bestimmung des Hitpointes mit dem kleinsten Abstand zur Kamera
		if(lowestHit.ray == null) {
			return null;
		} else {
			erg = new Hit(lowestHit.t, lowestHit.ray, lowestHit.geo, transform.mul(lowestHit.normal));
			return erg;
		}
	}
	
//	@Override
//	public Hit hit(Ray ray) {
//		// TODO Muss noch gemaht werden IST NOCH NICHT GETESTET!!!!
//		ArrayList<Hit> hits = new ArrayList<Hit>();
//		Hit erg;
//		Ray transformRay = this.transform.mul(ray);
//
////		System.out.println(geometryObjects.size());
//		// alle Schnittpunkte des Rays mit den in den in der Scene enthaltenen
//		// Objekte in Array abspeichern
//		for (Geometry geo : geometryObjects) {
//			// TODO HIER WEITER!!!!!!!!!!!!!!
//
//			Hit hit = geo.hit(transformRay);
////			Hit hit = geo.hit(ray);
//			if (hit != null) {
//				hits.add(hit);
//			}
//		}
//
//		// Prüfung ob Ray keinen Schnittpunkt mit Objekten besitzt
//		if (hits.isEmpty()) {
//			return null;
//		}
//
//		// Bestimmung des Hitpointes mit dem kleinsten t
//		else {		
//			erg = hits.get(0);
//			for (Hit hit : hits) {
//				erg = new Hit(hit.t, ray, hit.geo, transform.mul(hit.normal));
//			}
//			return erg;
//		}
//
//	}

	public List<Geometry> getGeometryObjects() {
		return geometryObjects;
	}

}