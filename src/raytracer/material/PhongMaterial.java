package raytracer.material;

import raytracer.geometry.Hit;
import raytracer.light.Light;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.raytracer.Color;
import raytracer.raytracer.Raytracer;
import raytracer.raytracer.World;

public class PhongMaterial extends Material {
	private Color diffuseColor;
	private Color specularColor;
	private int phongExponent;

	public PhongMaterial(Color diffuseColor, Color specularColor, int phongExponent) {
		super();
		this.diffuseColor = diffuseColor;
		this.specularColor = specularColor;
		this.phongExponent = phongExponent;
	}

	public Color colorFor(Hit hit, World world, Tracer tracer) {
		
		Normal3 n = hit.normal; 						// Normalvektor
		Point3  p = hit.ray.at(hit.t - Raytracer.EPSILON); 					// Schnittpunkt
		Vector3 e = (hit.ray.d.mul(-1.0)).normalized(); 	// Vektor zur Kamera

		Color cd = this.diffuseColor; 		// Farbe diffuse Reflektion (Skript:cr)
		Color ca = world.getAmbientColor(); // Farbe ambientes Licht
		Color cs = this.specularColor; 		// Farbe Glanzpunkt
		
		Color cl; 							// Farbe Licht
		Color lam;							// Farbe Lambert
		Color pho;							// Farbe Phong
		
		// Formel Aufgabe
		Color c  = cd.mul(ca); 				// Farbe Ergebnis
		
		for (Light light : world.getLights()) {		
			if (light.illuminates(p, world)) {
								
				Vector3 l = light.directionFrom(p).normalized(); // Vektor zur Lichtquelle
				Vector3 r = l.reflectedOn(n);		// l reflektiert an n
				cl = light.getColor();
				
				// Formel Aufgabe
				lam = cd.mul(cl).mul(Math.max(0, n.dot(l)));
				pho = cs.mul(cl).mul(Math.pow(Math.max(0, e.dot(r)), this.phongExponent));
				c = c.add(lam).add(pho);
			}			
		}
		return c;
	}
}


//// Farbe die ausgegeben wird
//Color c = world.ambientColor.mul(diffuseColor);
//
//// Vektor zum Augenpunkt des Betrachtesrs (der Kamera)
//Vector3 e = (hit.ray.d.mul(-1.0)).normalized();
//
//// Normlavektor auf der Objekoberlfäche
//Normal3 normal = hit.normal;
//
//// Schnittpunkt
//Point3 point = hit.ray.at(hit.t);
//
//for (Light light : world.getLights()) {
//
//	// Vektor in Richtung der Lichtquelle
//	Vector3 l = light.directionFrom(point).normalized();
//
//	// reflektierter Vektor
//	Vector3 r = l.reflectedOn(normal);
//
//	// Lambert an Phong angepasst
//	Color cLambert = 
//			c.add(light.color.mul(diffuseColor).mul(Math.max(0, normal.dot(l))));
//	// Phong-Berechnung
//	c = cLambert.add((light.color.mul(specularColor)).mul(light.color)
//			.mul(Math.pow(Math.max(0, r.dot(e)), phongExponent)));
//
//	// Funktionit nicht so wie es sein sollte
//	// c = c.add(light.color.mul(diffuseColor).mul(Math.max(0,
//	// normal.dot(l))).add(light.color.mul(specularColor).mul(Math.pow(Math.max(0,
//	// r.dot(e)), phongExponent))));
//}
//// System.out.println("Phong c = " + c);
//return c;