package raytracer.light;

import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.raytracer.Color;
import raytracer.raytracer.World;

/**
 * Die Klasse <code>SpotLight</code> ist eine Lichtquelle die von einem
 * bestimmten Punkt aus in eine gegebene Richtung innerhalb eines festgelegten
 * Winkels abstrahlt.
 * 
 */
public class SpotLight extends Light {

	/**
	 * Position <i>p<sub>l</sub></i> der Lichtquelle.
	 */
	private Point3 position;

	/**
	 * Hauptrichtung der Lichtquelle.
	 */
	private Vector3 direction;

	/**
	 * Strahlwinkel der Lichtquelle.<br>
	 * Die Lichtquelle strahlt innerhalb des Winkels <code>halfAngle</code>
	 * (x2).
	 */
	private double halfAngle;

	/**
	 * 
	 * @param color
	 *            - Farbe <i>c<sub>l</sub></i> der Lichtquelle.
	 * @param position
	 *            - Position <i>p<sub>l</sub></i> der Lichtquelle.
	 * @param direction
	 *            - Richtung der Lichtquelle.
	 * @param halfAngle
	 *            - Halbe Öffnungswinkel der Lichtquelle.
	 * @param castShadows
	 *            - Schatten der Lichtquelle Ja/Nein. 
	 */
	public SpotLight(final Color color, final Point3 position, final Vector3 direction, final double halfAngle, final boolean castShadows) {
		super(color,castShadows);
		if (halfAngle <= 0 && halfAngle >= 180) {
			throw new IllegalArgumentException(
					"Der Winkel muss einen Wert zwischen 0 und 180");
		}

		this.setPosition(position);
		this.setDirection(direction);
		this.setHalfAngle(halfAngle);
	}

	@Override
	public boolean illuminates(final Point3 point, final World world) {
		// TODO Shcatten muss noch angepasst werden. Bist jetzt habe ich zu der Übung-3 nicht geändert!
		
		// Vektor l
		final Vector3 l = directionFrom(point).mul(-1);

		// beta = arccos ( (a * b) / (|a| * |b|) )
		// Winkel zwischen Hauptrichtung des Lichtes (direction) und Vektor zur
		// Lichtquelle (l)
//		final double beta = Math.acos(Math.toRadians((direction.dot(l))
//				/ (direction.getMagnitude() * l.getMagnitude())));
		
		final double beta = Math.acos((direction.dot(l)
				/ (direction.getMagnitude() * l.getMagnitude())));

		if (this.halfAngle <= beta) {
			return false;
		}
		return true;
	}

	@Override
	public Vector3 directionFrom(final Point3 point) {
		// Vektor von Punkt A nach Punkt B --> (AB) = (B)-(A)
		return (this.position.sub(point)).normalized();
		
//		Vector3 o = new Vector3(point.getX(), point.getY(), point.getZ());
//		Vector3 l = new Vector3(position.getX(), position.getY(), position.getZ());
//		return o.add(l).normalized();
	}

	/**
	 * @return Position <i>p<sub>l</sub></i> der Lichtquelle.
	 */
	public Point3 getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            - Position <i>p<sub>l</sub></i> der Lichtquelle.
	 */
	public void setPosition(final Point3 position) {
		this.position = position;
	}

	/**
	 * @return Hauptrichtung der Lichtquelle.
	 */
	public Vector3 getDirection() {
		return direction;
	}

	/**
	 * @param direction
	 *            - Hauptrichtung der Lichtquelle.
	 */
	public void setDirection(final Vector3 direction) {
		this.direction = direction;
	}

	/**
	 * @return Strahlwinkel der Lichtquelle.
	 */
	public double getHalfAngle() {
		return halfAngle;
	}

	/**
	 * @param halfAngle
	 *            - Halbe Öffnungswinkel der Lichtquelle.
	 */
	public void setHalfAngle(final double halfAngle) {
		this.halfAngle = halfAngle;
	}

	@Override
	public String toString() {
		return "SpotLight [position = " + position + ", direction = "
				+ direction + ", halfAngle = " + halfAngle + ", color = "
				+ getColor() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		long temp;
		temp = Double.doubleToLongBits(halfAngle);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		SpotLight other = (SpotLight) obj;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (Double.doubleToLongBits(halfAngle) != Double
				.doubleToLongBits(other.halfAngle))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

}
