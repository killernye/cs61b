public class NBody {
	
	public static int readNum(String fileName) {
		In in = new In(fileName);
		int num_of_planets = in.readInt();
		return num_of_planets;
	}

	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int num_of_planets = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int num_of_planets = in.readInt();
		in.readDouble();
		Planet[] planets = new Planet[num_of_planets];
		for (int i = 0; i < num_of_planets; i++) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double mass = in.readDouble();
			String name = "./images/" + in.readString();
			planets[i] = new Planet(xP, yP, xV, yV, mass, name);
		}
		return planets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		int num_of_planets = readNum(filename);
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		String background = "./images/starfield.jpg";
		String music = "./audio/2001.mid";

		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, background);
		
		for (Planet p: planets) {
			p.draw();
		}
		
		StdDraw.enableDoubleBuffering();

		double time = 0;
		while (time < T) {
			//StdAudio.play(music);
			double[] xForces = new double[num_of_planets];
			double[] yForces = new double[num_of_planets];
			for (int i = 0; i < num_of_planets; i++) {
				Planet p = planets[i];
				xForces[i] = p.calcNetForceExertedByX(planets);
				yForces[i] = p.calcNetForceExertedByY(planets); 
			}
			int k = 0;
			for (Planet p: planets) {
				p.update(dt, xForces[k], yForces[k]);
				k++;
			}
			StdDraw.picture(0, 0, background);
			for (Planet p: planets) {
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}
	}
}