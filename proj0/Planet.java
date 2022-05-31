public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }
    public Planet(Planet p){
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }
    public double calcDistance(Planet p){
        return Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos)
                +(yyPos-p.yyPos)*(yyPos-p.yyPos));
    }

    public double calcForceExertedBy(Planet p){
        double distance=calcDistance(p);
        double F=(G*mass*p.mass)/(distance*distance);
        return F;
    }

    public double calcForceExertedByX(Planet p){
        double distance=calcDistance(p);
        double F1x=(calcForceExertedBy(p)*(p.xxPos-xxPos))/distance;
        return F1x;
    }

    public double calcForceExertedByY(Planet p){
        double distance=calcDistance(p);
        double F1y=(calcForceExertedBy(p)*(p.yyPos-yyPos))/distance;
        return F1y;
    }

    public double calcNetForceExertedByX(Planet[] all_planets){
        int len=all_planets.length;
        double sum=0;
        for (int i=0;i<len;i++){
            if (this.equals(all_planets[i])==true){
                continue;
            }
            sum+=calcForceExertedByX(all_planets[i]);
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] all_planets){
        int len=all_planets.length;
        double sum=0;
        for (int i=0;i<len;i++){
            if (this.equals(all_planets[i])==true){
                continue;
            }
            sum+=calcForceExertedByY(all_planets[i]);
        }
        return sum;
    }

    public void update(double dt, double fX, double fY) {
        double a_netX=fX/mass;
        double a_netY=fY/mass;
        xxVel+=dt*a_netX;
        yyVel+=dt*a_netY;
        xxPos+=dt*xxVel;
        yyPos+=dt*yyVel;
    }


    public void draw(){
        String imageToDraw="images/"+imgFileName;
        StdDraw.picture(xxPos,yyPos,imageToDraw);
    }
}
