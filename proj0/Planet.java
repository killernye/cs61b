public class Planet {
	static final double G = 6.67e-11;

	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/** Calculate the distance between two planets. */
	public double calcDistance(Planet p) {
		double dx = p.xxPos - xxPos;
		double dy = p.yyPos - yyPos;
		double sum_of_squares = dx*dx + dy*dy;
		double r = Math.sqrt(sum_of_squares);
		return r;
	}

	public double calcForceExertedBy(Planet p) {
		double r = this.calcDistance(p);
		return Planet.G*this.mass*p.mass/(r*r);
	}

	public double calcForceExertedByX(Planet p) {
		double f = calcForceExertedBy(p);
		double dx = p.xxPos - xxPos;
		double r = calcDistance(p);
		return f*dx/r;
	}

	public double calcForceExertedByY(Planet p) {
		double f = calcForceExertedBy(p);
		double dy = p.yyPos - yyPos;
		double r = calcDistance(p);
		return f*dy/r;
	}

	public double calcNetForceExertedByX(Planet[] planets) {
		double total = 0;
		for (Planet p: planets) {
			if (this.equals(p)) {
				continue;
			}
			total += calcForceExertedByX(p);
		}
		return total;
	}

	public double calcNetForceExertedByY(Planet[] planets) {
		double total = 0;
		for (Planet p: planets) {
			if (this.equals(p)) {
				continue;
			}
			total += calcForceExertedByY(p);
		}
		return total;
	}

	public void update(double dt, double xF, double yF) {
		double aX = xF/mass;
		double aY = yF/mass;
		xxVel = xxVel +  dt * aX;
		yyVel = yyVel + dt * aY;
		xxPos = xxPos +  xxVel * dt;
		yyPos = yyPos +  yyVel * dt;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, imgFileName);
	}

}