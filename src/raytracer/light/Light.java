package raytracer.light;

import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.raytracer.Color;
import raytracer.raytracer.World;

/**
 * Abstrakte Basisklasse fuer die Beleuchtung. Speichert die Farbe des Lichts.
 */
public abstract class Light {

	/**
	 * Farbe <i>c<sub>l</sub></i> der Lichtquelle.
	 */
	public final Color color;
	
	/**
	 * Schatten
	 */
	public final boolean castShadows;

	/**
	 * @param color
	 *            - Farbe <i>c<sub>l</sub></i> der Lichtquelle.
	 *            
	 * @param castShadows
	 *            - Schatten der Lichtquelle Ja/Nein.
	 */
	public Light(final Color color, final boolean castShadows) {
		this.color = color;
		this.castShadows = castShadows;
	}

	/**
	 * Ermittelt, ob das übergebene {@link Point3}-Objekt von diesem Licht (
	 * <code>this</code>) angestrahlt wird. <br>
	 * <br>
	 * Diese Methode wird zunächst für die Begrenzung des Winkels bei SpotLight
	 * benötigt. In der nächsten Aufgabe findet hier die Prüfung für den
	 * Schatten statt.
	 * 
	 * @param point
	 *            - Punkt der geprueft werden soll.
	 * @param world
	 *            - World ind der geprueft werden soll.
	 *                       
	 * @return <b>boolean</b> - <code>true</code>, wenn <code>point</code> von diesem Licht
	 *         angestrahlt wird und <code> false</code> wenn nicht.
	 */
	abstract public boolean illuminates(final Point3 point, final World world);

	/**
	 * Gibt für das übergebene {@link Point3}-Objekt den {@link Vector3}
	 * <i>l</i> zurück, welcher zur Lichtquelle zeigt.
	 * 
	 * @param point
	 *            - Punkt von dem der Vektor <i>l</i> ausgeht.
	 * @return {@link Vector3}-Objekt entspricht dem Vektor <i>l</i> und zeigt
	 *         zur Lichtquelle.
	 */
	abstract public Vector3 directionFrom(final Point3 point);

	/**
	 * @return {@link Color} - Farbe <i>c<sub>l</sub></i> der Lichtquelle.
	 */
	public Color getColor() {
		return color;
	}

	@Override
	public String toString() {
		return "Light [color = " + color + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Light other = (Light) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}
	
}
