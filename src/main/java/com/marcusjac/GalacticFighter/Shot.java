package com.marcusjac.GalacticFighter;

public class Shot {
    private double x;
    private double y;
    private double shotspeed;

    public Shot(double x,double y, double shotspeed){
        this.x=x;
        this.y=y;
        this.shotspeed=shotspeed;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getShotspeed() {
        return shotspeed;
    }
}
