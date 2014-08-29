package raytracer.math;

import raytracer.camera.OrthographicCamera;
import raytracer.camera.PerspectiveCamera;

public class MainTest {

	public static void main(String[] args) {
		
		// Test für Schnittberechnung
		// Kamera
		final Point3 pe = new Point3(4, 4, 4);
		final Vector3 e = new Vector3(4, 4, 4);
		final Vector3 g = new Vector3(-4, -4, -4);
		final Vector3 t = new Vector3(0, 1, 0);
		final double s = 3.0;
		
		// Bildschirm
		final int w = 1920;
		final int h = 1200;
		final int x = 1000;
		final int y = 800;
		
		// Test fuer Kamera Methoden toString(), equals(), hashCode()
		OrthographicCamera oc1 = new OrthographicCamera(pe, g, t, s);
		OrthographicCamera oc2 = new OrthographicCamera(pe, g, t, s);
		//		OrthographicCamera oc2 = oc1;
		System.out.println(oc1.toString() + "\n");
		System.out.println("equals OC: " + oc2.equals(oc1) + "\n");
		System.out.println("hashCode OC: " + oc1.hashCode());
		System.out.println("hashCode OC: " + oc2.hashCode());
		System.out.println("hashCode OC: " + oc2.hashCode() + "\n");
		PerspectiveCamera pc1 = new PerspectiveCamera(pe, g, t, 30.0);
		PerspectiveCamera pc2 = new PerspectiveCamera(pe, g, t, 30.0);
		System.out.println(pc1.toString() + "\n");
		System.out.println("equals PC: " + pc2.equals(pc1) + "\n");
		System.out.println("hashCode PC: " + pc1.hashCode());
		System.out.println("hashCode PC: " + pc2.hashCode());
		System.out.println("hashCode PC: " + pc2.hashCode() + "\n");
		
//		// Figur
		final Vector3 v_C = new Vector3(-1, -1, -1);
//		final Point3 c = new Point3(-1, -1, -1);
		final double r = 3;
//		
//		// Test Rechenzettel
//		System.out.println("Rechenzettel\nOC: " + oc1.rayFor(w, h, x, y).toString());
//		System.out.println("PC: " + pc1.rayFor(w, h, x, y).toString() + "\n");
//		
//		Sphere kugel1 = new Sphere(c, r, new Color(0, 1, 0));
//		System.out.println("OC:\n" + kugel1.hit(oc1.rayFor(w, h, x, y)).toString() + "\n");
//		System.out.println("PC:\n" + kugel1.hit(pc1.rayFor(w, h, x, y)).toString() + "\n");
		
		
		// Berechung für Vector_W ( W = -(g/|g|) )
		final Vector3 v_W = (g.mul(-1/g.length()));
		System.out.println("Vector W = " + v_W);
		
		// Berechung für Vector_U
		final Vector3 v_U = (t.x(v_W).normalized());
		System.out.println("\nVector U = " + v_U);
		
		// Berechung für Vector_U
		final Vector3 v_V = v_W.x(v_U);
		System.out.println("\nVector V = " + v_V);
		
		// Berechnung für den Strahl RAY
		final Vector3 v_O = e;
		final Vector3 v_R1 = v_W.mul(-1*((h/2)/Math.tan(Math.toRadians(30))));
		final Vector3 v_R2 = v_U.mul(x-((w-1)/2));
		final Vector3 v_R3 = v_V.mul(y-((h-1)/2));
		final Vector3 v_R = v_R1.add(v_R2).add(v_R3);
		System.out.println("\nVector R = " + v_R);
		System.out.println("\tv_R1 = " + v_R1);
		System.out.println("\tv_R2 = " + v_R2);
		System.out.println("\tv_R3 = " + v_R3);
		
		// Lengä von v_R
		v_R.length();
		System.out.println("\nLänge von Vector R = " + v_R.length());
		
		// Berechnung für Vector v_D = v_R / |v_R|
		final Vector3 v_D = v_R.normalized();
		System.out.println("\nVector D = " + v_D);
		
		// Berechnung von t für Kugel
		// Berechne b
		final double a = v_D.dot(v_D);
		final double b = v_D.dot(v_O.sub(v_C).mul(2));
		final double c = (v_O.sub(v_C)).dot(v_O.sub(v_C)) - (r*r);
		final double d = (b*b)-4*a*c;
		// Ausgabe
		System.out.println("\nBerechnung von t");
		System.out.println("\ta = " + a);
		System.out.println("\tb = " + b);
		System.out.println("\tc = " + c);
		System.out.println("\td = " + d);
		final double t1;
		final double t2;
		if(d > 0) {
			t1 = (-b+Math.sqrt((b*b)-4*a*c))/(2*a);
			t2 = (-b-Math.sqrt((b*b)-4*a*c))/(2*a);
			System.out.println("\tt1 = " + t1);
			System.out.println("\tt2 = " + t2);
		} else if(d == 0) {
			t1 = (-b+Math.sqrt((b*b)-4*a*c))/(2*a);
			System.out.println("\tt1 = " + t1);
		} else if(d < 0)  {
			System.out.println("Es gitb keinen Schnittpunkt!");
		}
		
		
	}

}