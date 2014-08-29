package raytracer.material;

import raytracer.geometry.Hit;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.raytracer.Color;
import raytracer.raytracer.Ray;
import raytracer.raytracer.World;

public class TransparentMaterial extends Material {
	
	private final double refractionIndex;  

	public TransparentMaterial(double refractionIndex) {
		this.refractionIndex = refractionIndex;
	}
	
	
	@Override
	public Color colorFor(Hit hit, World world, Tracer tracer) {
		Normal3 normal = hit.normal;
		Point3 p = hit.ray.at(hit.t);
		Vector3 e = hit.ray.d.mul(-1.0);
		Vector3 r = e.reflectedOn(normal);
		
		Vector3 ot = refracted(e, normal, world.refractionIndex, refractionIndex);
		if(ot == null) {
			tracer.rekusionIndexUP();
			Color c = tracer.fr(world, new Ray(p, r));
			tracer.rekusionIndexDOWN();
			return c;
		} else {
			tracer.rekusionIndexUP();
			double [] ab = schlick(e, normal, world.refractionIndex, refractionIndex);
			Color c = tracer.fr(world, new Ray(p, r));
			if(c != null) {
				c = c.mul(ab[0]);
			} else {
				c = world.backgroundColor;
			}
			
			Color d = tracer.fr(world, new Ray(p, ot));
			tracer.rekusionIndexDOWN();
			
			if(d != null) {
				c = c.add(d.mul(ab[1]));
			}
			return c;
		}
	}
	
	private Vector3 refracted(final Vector3 e, final Normal3 normal, double etaOut, double etaIn) {
		boolean inside = (normal.dot(e)) < 0;
		double eta = etaOut / etaIn;
		Normal3 cn = normal;
		
		if(inside) {
			eta = etaIn / etaOut;
			cn = normal.mul(-1.0);
		}
		
		double costhetai = cn.dot(e);
		double h = 1 - (eta * eta) * (1 - costhetai * costhetai);
		if(h > 0) {
			double costhetat = Math.sqrt(h);
			return e.mul(-1.0).mul(eta).sub(cn.mul(costhetat - eta * costhetai));
		}
		return null;
	}
	
	private double[] schlick(final Vector3 e, final Normal3 normal, final double etaOut, final double etaIn) {
		boolean inside = (normal.dot(e)) < 0;
		Normal3 cn = normal;
		if(inside) {
			cn = normal.mul(-1.0);
		}
		double cosThetaI = cn.dot(e);
		double r0 = Math.pow((etaOut - etaIn) / (etaOut + etaIn), 2);
		double r = r0 + (1 - r0) * Math.pow((1 - cosThetaI * cosThetaI), 5);
		double t = 1 - r;
		return new double []{r, t};
	}

}