package com.marcusjac.GalacticFighter.Levels;

public class Level {

    private int shotSpeed;
    private int enemyShotSpeed;
    private int enemyShotWidth;
    private int enemyShotHeight;
    private int playerShotHeight;
    private int playerShotWidth;
    private double enemySpeed;
    private int shotFrequency;
    private int enemyFrequency;

    public int getShotSpeed() {
        return shotSpeed;
    }

    public void setShotSpeed(int shotSpeed) {
        this.shotSpeed = shotSpeed;
    }

    public int getEnemyShotSpeed() {
        return enemyShotSpeed;
    }

    public void setEnemyShotSpeed(int enemyShotSpeed) {
        this.enemyShotSpeed = enemyShotSpeed;
    }

    public int getEnemyShotWidth() {
        return enemyShotWidth;
    }

    public void setEnemyShotWidth(int enemyShotWidth) {
        this.enemyShotWidth = enemyShotWidth;
    }

    public int getEnemyShotHeight() {
        return enemyShotHeight;
    }

    public void setEnemyShotHeight(int enemyShotHeight) {
        this.enemyShotHeight = enemyShotHeight;
    }

    public int getPlayerShotHeight() {
        return playerShotHeight;
    }

    public void setPlayerShotHeight(int playerShotHeight) {
        this.playerShotHeight = playerShotHeight;
    }

    public int getPlayerShotWidth() {
        return playerShotWidth;
    }

    public void setPlayerShotWidth(int playerShotWidth) {
        this.playerShotWidth = playerShotWidth;
    }

    public double getEnemySpeed() {
        return enemySpeed;
    }

    public void setEnemySpeed(double enemySpeed) {
        this.enemySpeed = enemySpeed;
    }

    public int getShotFrequency() {
        return shotFrequency;
    }

    public void setShotFrequency(int shotFrequency) {
        this.shotFrequency = shotFrequency;
    }

    public int getEnemyFrequency() {
        return enemyFrequency;
    }

    public void setEnemyFrequency(int enemyFrequency) {
        this.enemyFrequency = enemyFrequency;
    }

    public Level(int shotSpeed, int enemyShotSpeed, int enemyShotWidth, int enemyShotHeight, int playerShotHeight, int playerShotWidth, double enemySpeed, int shotFrequency, int enemyFrequency) {
        this.shotSpeed = shotSpeed;
        this.enemyShotSpeed = enemyShotSpeed;
        this.enemyShotWidth = enemyShotWidth;
        this.enemyShotHeight = enemyShotHeight;
        this.playerShotHeight = playerShotHeight;
        this.playerShotWidth = playerShotWidth;
        this.enemySpeed = enemySpeed;
        this.shotFrequency = shotFrequency;
        this.enemyFrequency = enemyFrequency;
    }
}
