import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static sun.applet.AppletResourceLoader.getImage;

public class Main extends JFrame {

    JFrame frame;
    Image BackgroundImage;
    Image ImageStorage;
    Graphics graphics;

    static  Ball ball=new Ball((short)250,(short)250); // ball
    static Paddle paddle1=new Paddle((short) 15, (short) 140, (short) 1);//  paddle 1
    static Paddle paddle2=new Paddle((short) 770, (short) 140, (short) 2); // paddle 2

    Main() {
                this.setTitle("Pong");
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setFocusable(true);
                this.addKeyListener(new ObslugaKlawiatury());
                this.setLocationRelativeTo(null);//set frame into the center
                this.setSize(800,500);
                this.setVisible(true);
                this.setResizable(false); // NO RESIZE
    }

    @Override
    public void paint(Graphics g) {
        ImageStorage=createImage(getWidth(),getHeight()); // create image
        graphics=ImageStorage.getGraphics();// get graphics from storage
        draw(graphics);
        g.drawImage(ImageStorage, 0, 0, this);// draw image.
    }

    public void draw(Graphics g) {
        java.net.URL urlBackground = getClass().getResource("Background.jpg");
        BackgroundImage = getImage(urlBackground);
        g.drawImage(BackgroundImage, 0, 0, null); // draw background

        ball.draw(g);//draw ball

        paddle1.draw(g);//draw paddle 1
        paddle2.draw(g);//draw paddle 2

        // scoring
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); // set font
        g.drawString("PLAYER 1: "+ ball.p1Score, 135,80); // draw score player 1
        g.drawString("PLAYER 2: "+ ball.p2Score, 545,80); // draw score player 2

        repaint();
    }

    public static void main(String[] args) {
        new Main();

        // watki byly lepszym rozwiazaniem niz tradycyjny timer.

        Thread th=new Thread(ball);//thread for ball
        th.start();

        Thread p1=new Thread(paddle1);//threads for paddle
        Thread p2=new Thread(paddle2);

        p1.start();
        p2.start();
    }
}

