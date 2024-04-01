import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlappyPanel extends JPanel implements KeyListener,ActionListener
{
    final int WIDTH=525, HEIGHT=550;
    final int wallxV=5;
    final int wallwidth=50;
    int flappyheight =HEIGHT/4;
    int flappyV=0, flappyA=8, flappyimpulse= 2;
    int[] barrier = {WIDTH,WIDTH+WIDTH/2};
    int[] gapx={(int)(Math.random()*(HEIGHT-150)),(int)(Math.random()*(HEIGHT-150))};
    int score=0;
    boolean gameEnd=false;
    Timer time=  new Timer(40,this);
    public FlappyPanel(){

        setSize(WIDTH,HEIGHT);
        setFocusable(true);
        addKeyListener(this);

        setBackground(Color.GREEN);
    }

    public static void main(String[] args) {
        new FlappyPanel();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!gameEnd) {
            g.setColor(Color.BLACK);
            g.drawString("Score: "+score,WIDTH/2,10);
            drawbarrier(g);
            logic();
            drawflappy(g);

        }else {
            g.setColor(Color.black);
            g.drawString("Score: "+score,WIDTH/2,10);

        }
    }

    private void drawflappy(Graphics g){
        g.setColor(Color.red);
        g.fillRect(75, flappyheight +flappyV,25,25);
    }

    private void drawbarrier(Graphics g) {
        for (int i = 0; i < 2; i++) {
            g.setColor(Color.BLUE);
            g.fillRect(barrier[i], 0, wallwidth, HEIGHT);

            g.setColor(Color.green);
            g.fillRect(barrier[i], gapx[i], wallwidth, 100);
        }
    }
    public void logic(){
        for(int i=0;i<2;i++){
            if(barrier[i]<=100 && barrier[i]+wallwidth>=100 || barrier[i]<=75 && barrier[i]+wallwidth>=75){
                if((flappyheight +flappyV)>=0 && (flappyheight +flappyV)<=gapx[i] || (flappyheight +flappyV+25)>=gapx[i]+100 && (flappyheight +flappyV+25)<=HEIGHT){
                    gameEnd=true;
                }
        }
            if(flappyheight+flappyV<=0 || flappyheight+flappyV+25>=HEIGHT){
                gameEnd=true;

            }

            if(75>barrier[i]+wallwidth){
                score++;
            }

            if(barrier[i]+wallwidth<=0){
                    barrier[i]=WIDTH;
                gapx[i]=(int)(Math.random()*(HEIGHT-150));
            }
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        flappyA=flappyA+flappyimpulse;
        flappyV=flappyV+flappyA;
        barrier[0]=barrier[0]-wallxV;
        barrier[1]=barrier[1]-wallxV;
        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code=e.getKeyCode();

        if (code==e.VK_SPACE){
            flappyA=-10;

        }
        if (code==e.VK_S){
            time.start();
        }
        if(code==e.VK_R){
            time.stop();
            flappyheight =HEIGHT/4;
            flappyV=0;
            flappyA=8;
            flappyimpulse=1;
            score=0;
            barrier[0]=WIDTH;
            barrier[1]=WIDTH+WIDTH/2;
            gapx[0]=(int)(Math.random()*(HEIGHT-150));
            gapx[1]=(int)(Math.random()*(HEIGHT-150));
            gameEnd=false;

            repaint();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
