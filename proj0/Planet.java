public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	// gravitational constant
	public static final double G = 6.67E-11;

	public Planet(double xP,double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double dx = p.xxPos - xxPos;
		double dy = p.yyPos - yyPos;
		double r = Math.sqrt(dx * dx + dy * dy);
		return r;
	}

	public double calcForceExertedBy(Planet p){
		double F = G * mass * p.mass / (calcDistance(p) * calcDistance(p));
		return F;
	}

	public double calcForceExertedByX(Planet p){
		return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
	}

	public double calcForceExertedByY(Planet p){
		return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] allPlanets){
		double F = 0;
		for (Planet p : allPlanets) {
			if(this.equals(p)){
				continue;
			}
			F += calcForceExertedByX(p);
		}
		return F;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets){
		double F = 0;
		for (Planet p : allPlanets) {
			if(this.equals(p)){
				continue;
			}
			F += calcForceExertedByY(p);
		}
		return F;
	}
	//fX:NetForceExertedByY
	//fY:NetForceExertedByX
	//dt:a small period of time 
	public void update(double dt, double fX, double fY){
		double aX = fX / mass;
		double aY = fY / mass;

		xxVel = xxVel + aX * dt;
		yyVel = yyVel + aY * dt;

		xxPos = xxPos + xxVel * dt;
		yyPos = yyPos + yyVel * dt;
	}

	//draw itself at its appropriate position
	public void draw(){
		String pic = "./images/" + imgFileName;
		StdDraw.picture(xxPos,yyPos,pic);
	}

}
