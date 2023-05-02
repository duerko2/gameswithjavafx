package com.marcusjac.GalacticFighter;

public class Enemy {

    private double x;
    private double y;
    private Heading heading;

    public Enemy(double x,double y,Heading heading){
        this.x=x;
        this.y=y;
        this.heading=heading;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Heading getHeading() {
        return heading;
    }

    public void setHeading(Heading heading) {
        this.heading = heading;
    }
}
