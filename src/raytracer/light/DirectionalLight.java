package raytracer.light;

import raytracer.geometry.Hit;
import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.raytracer.Color;
import raytracer.raytracer.Ray;
import raytracer.raytracer.World;

/**
 * Die Klasse DirectionalLight repräsentiert das Sonnenlicht, welches überall in
 * der Szene aus der gleichen Richtung kommt.
 * 
 */
public class DirectionalLight extends Light {

	/**
	 * Richtung der Lichtquelle.
	 */
	private Vector3 direction;

	/**
	 * 
	 * @param color
	 *            - Farbe <i>c<sub>l</sub></i> der Lichtquelle.
	 * @param direction
	 *            - Richtung der Lichtquelle.
	 * @param castShadows
	 *            - Schatten der Lichtquelle Ja/Nein.
	 */
	public DirectionalLight(final Color color, final Vector3 direction, final boolean castShadows) {
		super(color, castShadows);
		this.setDirection(direction);
	}

	@Override
	public boolean illuminates(final Point3 point, final World world) {
		// DirectionalLight hat an sich KEINEN Schatten
		final Hit h = world.hit(new Ray(point, directionFrom(point)));
		if(h != null) {
			return false;
		}
		return true;
	}

	@Override
	public Vector3 directionFrom(final Point3 point) {
		return this.direction.mul(-1).normalized();
	}

	/**
	 * @return Richtung der Lichtquelle.
	 */
	public Vector3 getDirection() {
		return direction;
	}

	/**
	 * @param direction
	 *            - Richtung der Lichtquelle.
	 */
	public void setDirection(final Vector3 direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "DirectionalLight [direction = " + direction + ", color = "
				+ getColor() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
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
		DirectionalLight other = (DirectionalLight) obj;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		return true;
	}

}
