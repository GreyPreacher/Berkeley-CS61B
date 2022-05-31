import static java.lang.Long.valueOf;

public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        int planet_sum = in.readInt();
        double universe_radius = in.readDouble();
        return universe_radius;
    }

    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int planet_sum = in.readInt();
        double universe_radius = in.readDouble();
        Planet[] planets=new Planet[planet_sum];
        for(int i=0;i<planet_sum;i++){
            double xp=in.readDouble();
            double yp=in.readDouble();
            double xv=in.readDouble();
            double yv=in.readDouble();
            double mass=in.readDouble();
            String img=in.readString();
            planets[i]=new Planet(xp,yp,xv,yv,mass,img);
        }
        return planets;
    }

    private static String imageToDraw = "images/starfield.jpg";
    public static void main(String[] args){
        double T=Double.valueOf(args[0]).doubleValue();
        double dt=Double.valueOf(args[1]).doubleValue();
        String filename=args[2];
        double radius=readRadius(filename);
        Planet[] planets=readPlanets(filename);
        int planets_num=planets.length;

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius,radius);

        StdDraw.picture(0, 0, imageToDraw);
        for (Planet planet : planets) {
            planet.draw();
        }
        StdDraw.show();

        double t=0.0;
        while ( t<=T ){

            //get forces in x- and y- direction
            double[] xForces=new double[planets_num];
            double[] yForces=new double[planets_num];
            for(int i=0;i<planets_num;i++){
                xForces[i]=planets[i].calcNetForceExertedByX(planets);
                yForces[i]=planets[i].calcNetForceExertedByY(planets);
            }

            //update the position of the planets
            for(int i=0;i<planets_num;i++){
                planets[i].update(dt,xForces[i],yForces[i]);
            }

            //draw the universe background
            StdDraw.picture(0,0,imageToDraw);

            //draw the updated planets
            for(Planet planet : planets){
                planet.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            t+=dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", planets[i].xxPos, planets[i].yyPos,
                    planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
