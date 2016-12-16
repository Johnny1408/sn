package SnakeGame;

import Objects.Apple;
import Objects.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGame extends JPanel implements ActionListener {

    public static final int SCALE = 32;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    public static final int SPEED = 5;

    Apple a = new Apple((int)(Math.random()*WIDTH), (int) (Math.random()*HEIGHT));
    Snake s =new Snake(0, 0, 1, 0);
    Timer t = new Timer(1000/SPEED, this);

    public SnakeGame(){
        t.start();
        addKeyListener(new Keyboard());
        setFocusable(true);
    }

    public void paint(Graphics g){
        g.setColor(color(255, 255, 255));
        g.fillRect(0,0, WIDTH*SCALE, HEIGHT*SCALE);
        g.setColor(color(255, 216, 0));

        for(int xx = 0; xx<=WIDTH*SCALE; xx+=SCALE) {
            g.drawLine(xx, 0, xx, HEIGHT * SCALE);
        }
        for (int yy = 0; yy<=HEIGHT*SCALE; yy+=SCALE){
            g.drawLine(0, yy, WIDTH*SCALE, yy);
                }

        for (int d = 0; d<s.length; d++){
            g.setColor(color(20, 150, 150));
            g.fillRect(s.snakex[d]*SCALE+1, s.snakey[d]*SCALE+1, SCALE-1, SCALE-1);
        }

        g.setColor(color(255, 0 ,0));
        g.fillRect(a.posX*SCALE+1, a.posY*SCALE+1, SCALE-1, SCALE-1);
    }

    public Color color(int red, int green, int blue){
        return new Color(red, green, blue);
    }

    public static void main(String[] args){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setSize(WIDTH*SCALE+7, HEIGHT*SCALE+30);
        f.setLocationRelativeTo(null);
        f.add(new SnakeGame());
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent arg0){
        s.move();
        if((s.snakex[0] == a.posX) & (s.snakey[0] == a.posY)){
            a.setRandomPosition();
            s.length++;
        }

        for (int k=1; k<s.length; k++)
        if((s.snakex[k] == a.posX) & (s.snakey[k] == a.posY)){
            a.setRandomPosition();
        }

        if((s.snakex[0] == a.posX) & (s.snakey[0] == a.posY)){
            a.setRandomPosition();
            s.length++;
        }

        repaint();
    }

    private class Keyboard extends KeyAdapter{
        public void keyPressed(KeyEvent kEvt){
            int key =kEvt.getKeyCode();

            if((key == KeyEvent.VK_D) & s.direction!= 2) s.direction = 0;
            if((key == KeyEvent.VK_S) & s. direction !=3) s.direction = 1;
            if((key == KeyEvent.VK_A)& s.direction !=0) s.direction = 2;
            if((key == KeyEvent.VK_W) & s.direction !=1) s.direction = 3;
        }
    }
}