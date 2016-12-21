package Objects;

import SnakeGame.SnakeGame;

public class Apple {

    SnakeGame main;

    public int posX;
    public int posY;

    public Apple(int startX, int startY){
        posX = startX;
        posY = startY;
    }

    @SuppressWarnings("static-access")
    public void setRandomPosition(){
        posX = (int)(Math.random()*(main.WIDTH-1)+1);
        posY = (int)(Math.random()*(main.HEIGHT-1)+1);
    }
}