package raytracer.material;

import raytracer.geometry.Hit;
import raytracer.light.Light;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.raytracer.Color;
import raytracer.raytracer.Ray;
import raytracer.raytracer.World;

/**
 * Realisiert eine reflektierenden Obeflaeche
 */
public class ReflectiveMaterial extends Material {

	private Color difusse;
	private Color specular;
	private int exponent;
	private Color reflection;
	
	public ReflectiveMaterial(final Color difusse, final Color specular, final int exponent, final Color reflection) {
		this.difusse = difusse;
		this.specular = specular;
		this.exponent = exponent;
		this.reflection = reflection;
	}
	
	public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
		Normal3 n = hit.normal;
		Point3 p = hit.ray.at(hit.t);
		Color c = world.ambientColor.mul(difusse);
		Vector3 d = hit.ray.d;
		Vector3 e = (d.mul(-1.0)).normalized(); 
		
		for (Light light : world.lights) {
			if(light.illuminates(p, world)) {
				Vector3 l = light.directionFrom(p);
				Vector3 r = l.reflectedOn(n);
				c = c.add(light.color.mul(difusse).mul(Math.max(0, n.dot(l))).add(light.color.mul(specular).mul(Math.pow(Math.max(0, r.dot(e)), exponent))));
			}
		}
		Ray ray = new Ray(p, (d.normalized().mul(-1).reflectedOn(n)));
		c = c.add(reflection.mul(tracer.fr(world, ray)));
		return c;
	}
	
	
	public Color getDifusse() {
		return difusse;
	}
	public Color getSpecular() {
		return specular;
	}
	public int getExponent() {
		return exponent;
	}
	public Color getReflection() {
		return reflection;
	}
	
}