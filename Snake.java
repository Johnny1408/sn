package Objects;

import SnakeGame.SnakeGame;

public class Snake {

    SnakeGame main;

    public int direction = 0;
    public int length = 2;

    public int snakex[] = new int[main.WIDTH*main.HEIGHT];
    public int snakey[] = new int[main.WIDTH*main.HEIGHT];

    public Snake(int x0, int y0, int x1, int y1){
        snakex[0] = x0;
        snakey[0] = y0;
        snakex[1] = x1;
        snakey[1] = y1;
    }

    public void move(){

        for(int d= length; d>0; d--){
            snakex[d] = snakex[d-1];
            snakey[d] = snakey[d-1];
        }
        if (direction == 0) snakex[0]++;
        if (direction == 1) snakey[0]++;
        if (direction == 2) snakex[0]--;
        if (direction == 3) snakey[0]--;
        for(int d = length-1; d > 0; d--){
            if ((snakex[0] == snakex[d]) & (snakey[0] == snakey[d])) {
                length = d-1;


            }
        }
        if (snakex[0] > main.WIDTH-1) snakex[0] = 0;
        if (snakex[0] < 0) snakex[0] = main.WIDTH-1;
        if (snakey[0] > main.HEIGHT) snakey[0] = 1;
        if (snakey[0] < 1) snakey[0] = main.HEIGHT;

        if (length<2) length = 2;

    }
}