import java.awt.*;
import java.util.Random;

import static sun.applet.AppletResourceLoader.getImage;

public class Ball implements Runnable{

    short x,y,xDirection,yDirection;

    Rectangle ball;

    short p1Score=0,p2Score=0; // scoring variables

    Image BallImage;

    public Ball(short x,short y)
    {
        this.x=x;
        this.y=y;

        Random r=new Random();
        short rDir= (short) r.nextInt(1);

        if(rDir==0) {
            rDir--;
        }
        setXDirection (rDir);

        int yrDir=r.nextInt(1);
        if(yrDir==0) {
            yrDir--;
        }
        setYDirection ((short) yrDir);
        ball=new Rectangle(this.x,this.y , 15,15); // instance of ball
    }

    // set the x direction of the ball
    public void setXDirection(short xdir)
    {
        xDirection=xdir;
    }
    // set the y Direction of the ball
    public void setYDirection(short ydir)
    {
        yDirection=ydir;
    }

    public void draw(Graphics g)
    {
        java.net.URL urlBall = getClass().getResource("ball.png");
        BallImage = getImage(urlBall);
        g.drawImage(BallImage, this.ball.x, this.ball.y,null); // draw ball
    }

    public void contact()
    {
        if(ball.intersects(Main.paddle1.paddle)) { // paddle1 contact with ball
            setXDirection((short) +1);
        }

        if(ball.intersects(Main.paddle2.paddle)){ // paddle2 contact with ball
            setXDirection((short) -1);
        }
    }

    public void move()
    {
        contact();

        ball.x+=xDirection;
        ball.y+=yDirection;

        if(ball.x<=0) { // contact with frame
            setXDirection((short) +1);
            p2Score++;
        }
        if(ball.x>=785)  { // contact with frame
            setXDirection((short) -1);
            p1Score++;
        }
        if(ball.y<=0) {
            setYDirection((short) +1);
        }
        if(ball.y>=485) {
            setYDirection((short) -1);
        }
    }

    public void run()  {
        try{
            while(true) {
                move();
                Thread.sleep(2);
            }
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
}