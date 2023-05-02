package com.marcusjac.GalacticFighter.Levels;

public class Levels {

    public static Level[] levels = new Level[11];


    public static void createLevels(){
        levels[0]=new Level(3,3,6,6,6,6,
                0.5,300,300);
        levels[1]=new Level(4,3,8,8,6,6,
                1,300,280);
        levels[2]=new Level(4,3,8,8,8,8,
                1,250,260);
        levels[3]=new Level(4,4,8,8,8,8,
                1,250,240);
        levels[4]=new Level(4,4,8,8,8,8,
                1,250,220);
        levels[5]=new Level(5,4,8,8,8,8,
                2,250,200);
        levels[6]=new Level(5,4,8,8,9,9,
                2,225,180);
        levels[7]=new Level(6,5,8,8,9,9,
                2,225,160);
        levels[8]=new Level(6,5,8,8,11,9,
                2,225,140);
        levels[9]=new Level(8,5,8,8,11,9,
                3,200,120);
        levels[10]=new Level(10,6,8,8,12,10,
                4,150,100);



    }

}
