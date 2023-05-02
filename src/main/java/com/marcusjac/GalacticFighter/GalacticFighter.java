package com.marcusjac.GalacticFighter;

import com.marcusjac.GalacticFighter.*;
import com.marcusjac.GalacticFighter.Levels.Levels;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

import static com.marcusjac.GalacticFighter.Levels.Levels.levels;


public class GalacticFighter {

    //variable
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int PLAYER_HEIGHT = 60;
    private static final int PLAYER_WIDTH = 60;
    private static final int ENEMY_HEIGHT = 75;
    private static final int ENEMY_WIDTH = 65;
    private double playerOneYPos = HEIGHT -PLAYER_HEIGHT;
    private int score = 0;
    private double playerOneXPos = WIDTH /2;
    private int shotSpeed=3;
    private int enemyShotSpeed=3;

    private int enemyShotWidth=8;
    private int enemyShotHeight=8;
    private int playerShotHeight =6;
    private int playerShotWidth =6;
    private double enemySpeed=1;
    private int shotFrequency=300;
    private int enemyFrequency=300;
    private int lives=3;

    private int level=999;
    private Phase phase = Phase.STARTPHASE;

    private int starFrequency=10;

    Image explotion = new Image("Explosion.png");
    Image enemyImage = new Image("Enemy1.png");
    Image playerImage = new Image("Player.png");

    // Array of player shots
    private ArrayList<Shot> playerShots = new ArrayList<>();

    // Array of enemy shots
    private ArrayList<Shot> enemyShots = new ArrayList<>();

    // Array of enemies
    private ArrayList<Enemy> enemies = new ArrayList<>();

    // Array of explosions
    private ArrayList<Explosion> explosions = new ArrayList<>();

    private ArrayList<Star> stars = new ArrayList<>();


    public void start(Stage stage) throws Exception {

        Levels.createLevels();
        nextLevel();
        stage.setTitle("Galactic Fighter");
        //background size
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //JavaFX Timeline = free form animation defined by KeyFrames and their duration
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        //number of cycles in animation INDEFINITE = repeat indefinitely
        tl.setCycleCount(Timeline.INDEFINITE);

        //mouse control (move and click)
        canvas.setOnMouseMoved(e-> playerOneXPos = e.getX()-PLAYER_WIDTH/2);

        Scene scene = new Scene(new StackPane(canvas));

        stage.setScene(scene);
        stage.show();
        tl.play();
    }

    private void shoot(){
        playerShots.add(new Shot(playerOneXPos+PLAYER_WIDTH/2-playerShotWidth/4, HEIGHT -PLAYER_HEIGHT,shotSpeed));
    }

    private void enemyShoot(Enemy enemy){
        enemyShots.add(new Shot(enemy.getX()+ENEMY_WIDTH/2-enemyShotWidth/2,enemy.getY()+ENEMY_WIDTH,enemyShotSpeed));
    }
    private void nextLevel(){
        if(level==999){
            level=0;
        } else{
            level++;
        }
        shotSpeed=levels[level].getShotSpeed();
        enemyShotSpeed=levels[level].getEnemyShotSpeed();
        enemyShotWidth=levels[level].getEnemyShotWidth();
        enemyShotHeight=levels[level].getEnemyShotHeight();
        playerShotHeight=levels[level].getPlayerShotHeight();
        playerShotWidth=levels[level].getPlayerShotWidth();
        enemySpeed=levels[level].getEnemySpeed();
        shotFrequency=levels[level].getShotFrequency();
        enemyFrequency=levels[level].getEnemyFrequency();

    }

    private void run(GraphicsContext gc) {
        if(score%10==0&&score!=0&&!(score>100)){
            nextLevel();
            score++;
        }
        //set graphics
        //set background color
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        //set text
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));


        if(phase==Phase.GAME) {
            gc.getCanvas().setOnMouseClicked(e->shoot());

            // Creates a random enemy.
            int rando = (int) (Math.random()*enemyFrequency)+1;
            if(rando==enemyFrequency){
                double randX= Math.random()*(WIDTH -ENEMY_WIDTH-1)+0.1;
                Heading enemyHeading=Heading.RIGHT;
                if(rando<enemyFrequency/2){
                    enemyHeading=Heading.LEFT;
                }
                Enemy enemy = new Enemy(randX,0,enemyHeading);
                enemies.add(enemy);
                enemyFrequency--;
            }

            // Draws the enemies and moves them.

            for(int i=0;i<enemies.size();i++){

                // Creates random integer between 0 and the shotfrequency.
                int rand = (int) (Math.random()*shotFrequency+1);
                Enemy enemy= enemies.get(i);

                //Moves the enemy
                if(enemy.getHeading()==Heading.RIGHT){
                    enemy.setX(enemy.getX()+enemySpeed/2);
                } else{
                    enemy.setX(enemy.getX()-enemySpeed/2);
                }
                enemy.setY(enemy.getY()+enemySpeed);
                if(enemy.getX()<0||enemy.getX()+ENEMY_WIDTH> WIDTH){
                    enemy.setHeading(enemy.getHeading().next());
                }

                // Enemey dissapears if they reach the bottom.
                if (enemy.getY()>= HEIGHT){
                    enemies.remove(enemy);
                }

                // Checks if the enemy has collided with the player.
                if(enemy.getX()>playerOneXPos&&enemy.getX()<playerOneXPos+PLAYER_WIDTH&&enemy.getY()+ENEMY_HEIGHT>playerOneYPos&&enemy.getY()< HEIGHT){
                    lives--;
                    explosions.add(new Explosion(enemy.getX()+ENEMY_WIDTH/2,enemy.getY()+ENEMY_HEIGHT/2,20,100));
                    enemies.remove(enemy);
                }


                //If random integer is the same as shotfrequency, the enemy shoots a shot.
                if(rand==shotFrequency){
                    enemyShoot(enemy);
                }
                gc.drawImage(enemyImage,enemy.getX(),enemy.getY(), ENEMY_WIDTH, ENEMY_HEIGHT);
            }


            // Draws the shots from the player and moves them
            for(int i=0;i<playerShots.size();i++){
                Shot shot=playerShots.get(i);
                shot.setY(shot.getY()-shotSpeed);

                // Checks if the shot has collided with an enemy
                for(int j=0;j<enemies.size();j++){
                    Enemy enemy= enemies.get(j);
                    if(shot.getY()<enemy.getY()+ENEMY_HEIGHT&&shot.getY()+ playerShotWidth >enemy.getY()&&shot.getX()>enemy.getX()- playerShotHeight +1&&shot.getX()<enemy.getX()+ENEMY_WIDTH-1){
                        enemies.remove(enemy);
                        playerShots.remove(shot);
                        score++;
                        explosions.add(new Explosion(shot.getX(),shot.getY(),20,150));
                    }
                }
                gc.setFill(Color.GREEN);
               gc.fillRect(shot.getX(),shot.getY(), playerShotWidth, playerShotHeight);
                gc.setFill(Color.WHITE);
            }



            // Draws the shots from the enemies and moves them
            for(int i=0;i<enemyShots.size();i++){
                Shot shot=enemyShots.get(i);
                shot.setY(shot.getY()+enemyShotSpeed);

                // Checks if the shot has collided with the player.
                if(shot.getY()< HEIGHT &&shot.getY()> HEIGHT -PLAYER_HEIGHT&&shot.getX()+enemyShotWidth>playerOneXPos+1&&shot.getX()<playerOneXPos+PLAYER_WIDTH-1){
                    enemyShots.remove(shot);
                    lives--;
                    explosions.add(new Explosion(shot.getX(),shot.getY(),20,100));
                }

                gc.setFill(Color.RED);
                gc.fillRect(shot.getX(),shot.getY(),enemyShotHeight,enemyShotWidth);
                gc.setFill(Color.WHITE);
            }



            // Draws explosions
            for(int i=0; i<explosions.size();i++) {
                Explosion explosion=explosions.get(i);
                gc.drawImage(explotion, explosion.getX()-explosion.getSize()/2, explosion.getY() - explosion.getSize()/1.5, explosion.getSize(), explosion.getSize());
                explosion.setTimeToDie(explosion.getTimeToDie()-1);
                if(explosion.getTimeToDie()<0){
                    explosions.remove(explosion);
                }
            }



            // End the game
            if(lives<=0){
                phase=Phase.END;
                enemies=new ArrayList<>();
                enemyShots=new ArrayList<>();
                playerShots=new ArrayList<>();
                enemyShotSpeed=3;
                enemySpeed=1;
                shotFrequency=300;
                enemyFrequency=300;
                level=999;
                nextLevel();
            }



            //draw score
            gc.fillText("Level: "+level,WIDTH/2+300,HEIGHT-150);
            gc.fillText("Score: "+String.valueOf(score), WIDTH / 2+300, HEIGHT -100);
            gc.fillText("Lives: "+String.valueOf(lives), WIDTH / 2+300, HEIGHT -50);

        } else if(phase==Phase.STARTPHASE) {
            lives=3;
            score=0;
            //set the start text
            gc.setStroke(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("Click", WIDTH / 2, HEIGHT / 2);
            gc.getCanvas().setOnMouseClicked(e->phase=Phase.GAME);
        } else if(phase==Phase.END){
            gc.fillText("GAME OVER", WIDTH /2, HEIGHT /2);
            gc.fillText("SCORE: "+score, WIDTH /2, HEIGHT /2+50);
            gc.getCanvas().setOnMouseClicked(e-> phase=Phase.STARTPHASE);
        }



        //draw player 1
        gc.drawImage(playerImage,playerOneXPos, playerOneYPos, PLAYER_WIDTH+10, PLAYER_HEIGHT+10);




        //Generate a star randomly and adds it to the array of stars
        int randStar = (int) (Math.random()*starFrequency)+1;
        if(randStar==starFrequency){
            int randStarSize = (int) (Math.random()*5);
            int randStarSpeed = (int)(Math.random()*5+10);
            int randX= (int)(Math.random()* WIDTH);
            stars.add(new Star(randStarSize,randStarSpeed,randX,0));
        }

        // Draws the stars and moves them
        for(int i=1;i<stars.size();i++){
            Star star= stars.get(i);
            gc.fillOval(star.getX(),star.getY(),star.getSize(),star.getSize());
            star.setY(star.getY()+star.getSpeed());
            if(star.getY()> HEIGHT){
                stars.remove(star);
            }
        }



    }


    // start the application
    public void startGame() throws Exception {
        start(new Stage());
    }
}