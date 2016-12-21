
package SnakeGame;

import Objects.Apple;
import Objects.Snake;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGame extends JPanel implements ActionListener {

    public static final int SCALE = 32;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    public static final int SPEED = 200;
    public static final int[] show1 = {2, 3, 4};


    Apple a = new Apple((int)(Math.random()*(WIDTH-1))+1, (int)((Math.random()*(HEIGHT-1))+1));
    Snake s = new Snake(0, 1, 1, 1);
    Timer t = new Timer(SPEED, this);

    public SnakeGame() {
        t.start();
        addKeyListener(new Keyboard());
        setFocusable(true);
    }

    public void paint(Graphics g){


        g.setColor(Color.WHITE);
        g.fillRect(0,0, WIDTH*SCALE, (HEIGHT+1)*SCALE);

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, WIDTH*SCALE+1, SCALE);

        {
            Font font = new Font("TimesRoman", Font.BOLD, 10);
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString("Длина змейки: ", 2, 18);
            g.drawString("Скорость змейки: ", 162, 18);
            String speed = Integer.toString(1000/SPEED);
            String r = (" кадров/сек");
            String q = Integer.toString(s.length);
            if ((s.length == 2) | (s.length == 3) | (s.length == 4)){
                g.drawString(q + " единицы", 75, 18);
            }
            else
            {
                g.drawString(q + " единиц", 75, 18);
            }
            //g.drawString("sd", 130-150, 18);

            g.drawString(speed, 250, 18);
            g.drawString(r, 255, 18);
        }

        g.setColor(Color.YELLOW);
        for(int xx = SCALE; xx<=WIDTH*SCALE; xx+=SCALE) {
            g.drawLine(xx, SCALE, xx, (HEIGHT +1) * SCALE);
        }
        for (int yy = SCALE; yy<=(HEIGHT+1)*SCALE; yy+=SCALE){
            g.drawLine(0, yy, WIDTH*SCALE, yy);
                }

        for (int d = 0; d < s.length; d++){
            g.setColor(Color.green);
            g.fillRect(s.snakex[d]*SCALE+1, s.snakey[d]*SCALE+1, SCALE-1, SCALE-1);
        }

        g.setColor(Color.RED);
        g.fillRect(a.posX*SCALE+1, a.posY*SCALE+1, SCALE-1, SCALE-1);
    }


    public static void main(String[] args){
        final JFrame f = new JFrame("Snake");
        JPanel panel = new JPanel();
        //JPanel panel1 = new JPanel();
        SnakeGame m = new SnakeGame();
        // panel1.setLayout(new BorderLayout());
        f.getContentPane().add(panel);
        panel.setLayout(new FlowLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        panel.setSize(WIDTH*SCALE+7, (HEIGHT + 1)*SCALE+30);
        f.setSize(WIDTH*SCALE+7, (HEIGHT + 1)*SCALE+56);
        f.setLocationRelativeTo(null);
        JButton exit = new JButton("Exit");
        exit.setSize(100,100);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                System.exit(0);
            }
        });
        JButton press = new JButton("New Game");
        //press.setBounds(164, 204, 100, 100);
        panel.add(press);
        panel.add(exit);
        //panel.add(exitButton);
        press.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.add(new SnakeGame());
                panel.setVisible(false);
                f.add(exit, BorderLayout.NORTH);
                //panel1.setSize(WIDTH*SCALE, SCALE);
                //f.add(panel1);

            }
        });



        press.setVisible(true);
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