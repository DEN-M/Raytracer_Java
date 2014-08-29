package raytracer.light;

import raytracer.geometry.Hit;
import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.raytracer.Color;
import raytracer.raytracer.Ray;
import raytracer.raytracer.World;

/**
 * Die Klasse <code>PointLight</code> repr‰sentiert eine Punktlichtquelle, die
 * gleichm‰ﬂig in alle Richtungen strahlt.
 */
public class PointLight extends Light {

	/**
	 * Position <i>p<sub>l</sub></i> der Lichtquelle.
	 */
	private final Point3 position;

	/**
	 * 
	 * @param color
	 *            - Farbe <i>c<sub>l</sub></i> der Lichtquelle.
	 * @param position
	 *            - Position <i>p<sub>l</sub></i> der Lichtquelle.
	 * @param castShadows
	 *            - Schatten der Lichtquelle Ja/Nein.
	 */
	public PointLight(final Color color, final Point3 position,
			final boolean castShadows) {
		super(color, castShadows);
		this.position = position;
	}

	@Override
	public boolean illuminates(final Point3 point, final World world) {
		if (castShadows) {
			Ray ray = new Ray(point, position.sub(point).normalized());
			final Hit h = world.hit(ray);
			if(h!= null) {
				return false;				
			}
			else {
				return true;
			}
		} else {
			return true;
		}
	}

	// alte Variante vor 10.06.2013 [DEN]
	// @Override
	// public boolean illuminates(final Point3 point, final World world) {
	// final Hit h = world.hit(new Ray(point,
	// directionFrom(point).normalized()));
	// if(h != null) {
	// return false;
	// } else {
	// return true;
	// }
	// }

	@Override
	public Vector3 directionFrom(final Point3 point) {
		return this.position.sub(point).normalized();
	}

	/**
	 * @return Position <i>p<sub>l</sub></i> der Lichtquelle.
	 */
	public Point3 getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "PointLight [position = " + position + ", color = " + getColor()
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PointLight other = (PointLight) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

}
