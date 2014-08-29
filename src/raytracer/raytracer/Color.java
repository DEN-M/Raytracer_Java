package raytracer.raytracer;


/**
 * Die Klasse {@link Color} stellt eine Farbe im RBG Raum dar.
 * Die einzelnen Werte werden als <b>double</b> Zahlwerte gespeichert.
 * Die Wertegrenze liegt zwischen <b>0.0</b> und <b>1.0</b>.
 */

public class Color {

	/**
	 * Das Attribut <code>r</code> beinhaltet einen <b>double</b> Wert, welcher den Rotwert repräsentiert.
	 *
	 */
	public double r;
	
	/**
	 * Das Attribut <code>g</code> beinhaltet einen <b>double</b> Wert, welcher den Gelbwert repräsentiert.
	 *
	 */
	public double g;
	
	/**
	 * Das Attribut <code>b</code> beinhaltet einen <b>double</b> Wert, welcher den Blauwert repräsentiert.
	 *
	 */
	public double b;
	
	/**
	 * Der Konstruktor erzeugt ein {@link Color} Objekt und überprüft ob die als Paramteter übergebenen Werte in dem vorgebenem Wertebereich von 0.0 - 1.0 liegen.
	 * 
	 * @param r
	 *             Attribut für Rotwert.
	 * @param g
	 *             Attribut für Gelbwert.
	 * @param b
	 *             Attribut für Blauwert.
	 *             
	 *             
	 */
	public Color(final double r, final double g, final double b) {
			this.r = r > 1 ? 1 : r;
			this.g = g > 1 ? 1 : g;
			this.b = b > 1 ? 1 : b;
	}
	
	/**
	 * Die Methode {@link toAwtColor}  konvertiert das Color Objekt in ein <code>java.awt.Color</code> Objekt.
	 * Dabei werden die Paramter des <code>Code</code> Objektes von <code>double</code> in <code>int</code> Werte konvertiert.
	 * Das neu erzeugte <code>java.awt.Color</code> Objekt beinhaltet Werte im bereich 0-255.
	 * 
	 * @return java.awt.Color Objekt.
	 */
	public int toAwtColor() {
		Color c = clip();
		return (toInt(1.0) << 24) | (toInt(c.r) << 16) | (toInt(c.g) << 8) | toInt(c.b);
	}

	private static int toInt(double c) {
		return (int) Math.round(c * 255.0);
	}

	/**
	 * trimmmt die Color Werte auf das Intervall [0.0, 1.0].
	 * 
	 * @return The clipped color.
	 */
	public Color clip() {
		Color c = new Color(Math.min(r, 1), Math.min(g, 1), Math.min(b, 1));
		return new Color(Math.max(c.r, 0), Math.max(c.g, 0), Math.max(c.b, 0));
	}
	
	/**
	 * Addiert das <code>Color</code> Objekt mit dem übergebenem Color Objekt.
	 * Dabei werden die einzelnden Attribute addiert und in einem neuem <code>Color</code> Objekt wiedergegeben.
	 * 
	 * @return neues {@link Color} Objekt.
	 */
	public Color add(final Color c) {
		if (c == null)
			throw new IllegalArgumentException(
					"Der Wert darft nicht null sein!");
		return new Color(r + c.r, g + c.g, b + c.b);
	}

	/**
	 * Subtrahiert das <code>Color</code> Objekt mit dem übergebenem Color Objekt.
	 * Dabei werden die einzelnden Attribute subtrahiert und in einem neuem <code>Color</code> Objekt wiedergegeben.
	 * @return neues {@link Color} Objekt.
	 */
	public Color sup(final Color c) {
		if (c == null)
			throw new IllegalArgumentException(
					"Der Wert darft nicht null sein!");
		return new Color(r - c.r, g - c.g, b - c.b);
	}

	/**
	 * Multipliziert das <code>Color</code> Objekt mit dem übergebenem Color Objekt.
	 * Dabei werden die einzelnden Attribute multipliziert und in einem neuem <code>Color</code> Objekt wiedergegeben.
	 * 
	 * @return neues {@link Color} Objekt.
	 */
	public Color mul(final Color c) {
		if (c == null)
			throw new IllegalArgumentException(
					"Der Wert darft nicht null sein!");
		return new Color(r * c.r, g * c.g, b * c.b);
	}

	/**
	 * Addiert das <code>Color</code> Objekt mit dem übergebenem Skalar.
	 * Dabei werden die einzelnden Attribute mit dem Skalar multipliziert und in einem neuem <code>Color</code> Objekt wiedergegeben.
	 * 
	 * @return neues {@link Color} Objekt.
	 */
	public Color mul(final double v) {
		if (v < 0)
			throw new IllegalArgumentException(
					"Der Wert darft nicht kleiner als 0 sein!");
		return new Color(r * v, g * v, b * v);
	}

	/**
	 * gibt <code>r</code> Wert wieder.
	 * 
	 * @return r als <code>double</code>
	 */
	public double getR() {
		return r;
	}

	/**
	 * gibt <code>g</code> Wert wieder.
	 * 
	 * @return g als <code>double</code>
	 */
	public double getG() {
		return g;
	}

	/**
	 * gibt <code>b</code> Wert von wieder.
	 * 
	 * @return b als <code>double</code>
	 */
	public double getB() {
		return b;
	}

	@Override
	public String toString() {
		return "Color: r = " + r + ", g = " + g + ", b = " + b;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(b);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(g);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(r);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Color other = (Color) obj;
		if (Double.doubleToLongBits(b) != Double.doubleToLongBits(other.b))
			return false;
		if (Double.doubleToLongBits(g) != Double.doubleToLongBits(other.g))
			return false;
		if (Double.doubleToLongBits(r) != Double.doubleToLongBits(other.r))
			return false;
		return true;
	}
		
}