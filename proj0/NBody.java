public class NBody {

	public static void main(String[] args) {
		//Collecting All Needed Input
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] allPlanets = readPlanets(filename);

		//Drawing the Background
		String bgPic = "./images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0,0,bgPic);

		//Drawing All Planets
		for (Planet p : allPlanets) {
			p.draw();
		}

		//Add audio
		String audioFile = "./audio/2001.mid";
		StdAudio.loop(audioFile);
		StdDraw.show(4000);

		//Creating an Animation
		double time = 0;
		int num = allPlanets.length;
		while(time < T){
			//Create an xForces array and yForces array
			double[] xForces = new double[num];
			double[] yForces = new double[num];
			//Calculate the net x and y forces for each planet, 
			//storing these in the xForces and yForces arrays respectively
			for (int i = 0; i < num; i++) {
				xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
				yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
			}
			//Call update on each of the planets. 
			//This will update each planet's position, velocity, and acceleration.
			for (int i = 0; i < num; i++) {
				allPlanets[i].update(dt, xForces[i], yForces[i]);
			}

			//Drawing the Background
			StdDraw.picture(0,0,bgPic);

			//Drawing All Planets
			for (Planet p : allPlanets) {
				p.draw();
			}

			//Pause the animation for 10 milliseconds 
			StdDraw.show(10);

			//Increase your time variable by dt
			time += dt;	

		}

		//print output
		StdOut.printf("%d\n", allPlanets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < allPlanets.length; i++) {
		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   			allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
   			allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);	
		}	



	}

	public static double readRadius(String path){
		In in = new In(path);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String path){
		In in = new In(path);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] allPlanets = new Planet[num];
		for (int i = 0; i < allPlanets.length; i++) {
			double xPos = in.readDouble();
			double yPos = in.readDouble();
			double xVel = in.readDouble();
			double yVel = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();

			allPlanets[i] = new Planet(xPos, yPos, xVel, yVel, m, img);
		}

		return allPlanets;
	}
}
