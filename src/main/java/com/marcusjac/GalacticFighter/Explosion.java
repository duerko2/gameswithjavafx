package com.marcusjac.GalacticFighter;

public class Explosion {
    double x;
    double y;
    int timeToDie;
    int size;

    public Explosion(double x, double y,int timeToDie,int size){
        this.x=x;
        this.y=y;
        this.timeToDie=timeToDie;
        this.size=size;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getTimeToDie() {
        return timeToDie;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setTimeToDie(int timeToDie) {
        this.timeToDie = timeToDie;
    }

    public int getSize() {
        return size;
    }
}
