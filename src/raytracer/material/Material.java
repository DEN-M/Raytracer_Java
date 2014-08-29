package raytracer.material;
import raytracer.raytracer.Color;
import raytracer.geometry.Hit;
import raytracer.raytracer.World;

public abstract class Material {

    public abstract Color colorFor(Hit hit, World world, Tracer tracer);

}