package raytracer.raytracer;

import java.util.ArrayList;
import java.util.List;

import raytracer.geometry.Geometry;
import raytracer.geometry.Hit;
import raytracer.light.Light;

public class World {
	
	/**
	 * <code>backgroundColor</code> setzt die Farbe des Hintergrundes fest.
	 */
	public final Color backgroundColor;
	
	/**
	 * <code>ambientColor</code> setzt die Farbe des ambienten Licht fest.
	 */
	public final Color ambientColor;
	
	/**
	 * <code>reflectionIndex</code> setzt die Anzahl der Refraktionen fest.
	 */
	public final double refractionIndex;

    /**
     * <code>geometryObjects</code> Liste von Objekten in der Welt.
     */
	private final List<Geometry> geometryObjects;
    /**
     * <code>geometryObjects</code> Liste von Lichtern in der Welt
     */
    public final List<Light> lights;
	
	/**
	 * Konstruktor der {@link World} Klasse, prï¿½ft den ï¿½bergebenen backgroundColor auf seinen Zustand.
	 * Falls dieser <code>null</code> ist, wird eine <code>IllegalArgumentException</code> geworfen.
	 * 
	 * @param backgroundColor
	 * 							backgroundColor ein Objekt der {@link Color} Klasse, setzt die Hintergrundfarbe.
	 */
	public World(final Color backgroundColor, final Color ambientColor, final double reflectionIndex) {
		if(backgroundColor != null && ambientColor != null) {
			this.backgroundColor = backgroundColor;	
			this.ambientColor = ambientColor;
			this.refractionIndex = reflectionIndex;
		} else {
			throw new IllegalArgumentException("Die BackgroundFarbe ist null");
		}

		this.geometryObjects = new ArrayList<Geometry>();
        this.lights = new ArrayList<Light>();
	}
	
	/**
	 * Fügt geoObjects zu einer Liste von geoObjetcs hinzu
	 * @param geometryObject
	 * @return Rückgabe wert ob hinzugefügt wurde
	 */
	public boolean add(final Geometry geometryObject) {
		return geometryObjects.add(geometryObject);
	}
	
	/**
	 * Fügt Licht zu einer Liste von Lichtern hinzu
	 * @param geometryObject eine geometrischen Object
	 * @return Rückgabe wert ob hinzugefügt wurde
	 */
	public boolean add(final Light  light) {
		return lights.add(light);
	}
	
	/**
	 * 
	 * @param ray  Ein Strahl, der in die Szene gesendet wird.
	 * @return Schnittpunkt mit einem Objekt der am dichtesten an der Kamera liegt (der der sichtbar ist, also nicht vor der Kamera)
	 */
	public Hit hit(final Ray ray) {
		Hit lowestHit = new Hit(1000000, null, null, null);
		
		//alle Schnittpunkte des Rays mit den in den in der Scene enthaltenen Objekte in Array abspeichern
		for(Geometry geo : geometryObjects) {
			Hit hit = geo.hit(ray);
			if(hit == null) {
				continue;
			}
			if(hit != null && hit.t >= Raytracer.EPSILON && hit.t < lowestHit.t) {
				lowestHit = hit;
			}
		}
		
		//Bestimmung des Hitpointes mit dem kleinsten Abstand zur Kamera
//		double laenge = (ray.tOf(hits.get(0)));
		if(lowestHit.ray == null) {
			return null;
		} else {
			return lowestHit;
		}
		
		
	}
	
	/**
	 * Gibt den Hintergrund wieder.
	 * @return backgroundColor
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	
	/**
	 * Gibt den ambienete Farbe wieder.
	 * @return ambientColor
	 */
	public Color getAmbientColor() {
		return ambientColor;
	}

	/**
	 * Gibt eine Liste der geometrischen Objekte wieder.
	 * @return geometryObjects
	 */
	public List<Geometry> getGeometryObjects() {
		return geometryObjects;
	}
	
	/**
	 * Gibt eine Liste der Lichter wider
	 * @return Lichter
	 */
	public List<Light> getLights() {
		return lights;
	}

	@Override
	public String toString() {
		return "World [backgroundColor=" + backgroundColor
				+ ", geometryObjects=" + geometryObjects + "]";
	}
	
}