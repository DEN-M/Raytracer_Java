package raytracer.material;


import raytracer.geometry.Hit;
import raytracer.raytracer.Color;
import raytracer.raytracer.World;

public class SingleColorMaterial extends Material{

	public final Color color;

    public SingleColorMaterial(Color color){
        this.color = color;
    }

    public Color colorFor(Hit hit, World world, Tracer tracer){
        return color;
    }
}
