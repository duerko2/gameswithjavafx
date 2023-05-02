package com.marcusjac.GalacticFighter;

public class Star {
    int size;
    int speed;
    int x;
    int y;
    public Star(int size,int speed,int x, int y){
        this.size=size;
        this.speed=speed;
        this.x=x;
        this.y=y;
    }

    public int getSize() {
        return size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
