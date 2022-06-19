import java.awt.*;
import java.awt.event.KeyEvent;

import static sun.applet.AppletResourceLoader.getImage;

public class Paddle implements Runnable {

    short x,y,yDirection,id;
    Rectangle paddle;
    Image PaddleImage1;
    Image PaddleImage2;

    public Paddle(short x,short y,short id)
    {
        this.x=x;
        this.y=y;
        this.id=id;
        paddle=new Rectangle(x,y,10,80);
    }

    public void keyPressed(KeyEvent e)
    {
        switch(id)  {
            //Paddle 1
            case 1:
                if(e.getKeyCode()== e.VK_W) {
                    setYDirection((short) -1);
                }
                if(e.getKeyCode()== e.VK_S) {
                    setYDirection((short) 1);
                }
                break;
            //Paddle 2
            case 2:
                if(e.getKeyCode()== e.VK_UP) {
                    setYDirection((short) -1);
                }
                if(e.getKeyCode()== e.VK_DOWN) {
                    setYDirection((short) +1);
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e)
    {
        switch(id)
        {
            //for Paddle 1
            case 1:
                if(e.getKeyCode()== e.VK_W)
                {
                    setYDirection((short) 0);
                }
                if(e.getKeyCode()== e.VK_S)
                {
                    setYDirection((short) 0);
                }
                break;
            //for Paddle 2
            case 2:
                if(e.getKeyCode()== e.VK_UP)
                {
                    setYDirection((short) 0);
                }
                if(e.getKeyCode()== e.VK_DOWN)
                {
                    setYDirection((short) 0);
                }
                break;
            default:
                System.out.println("Enter correct id");
                break;


        }
    }
    public void setYDirection(short ydir) {
        yDirection=ydir;
    }

    public void move() {
        paddle.y+=yDirection;
        if(paddle.y<=30) {
            paddle.y=30;
        }
        if(paddle.y>=405){
            paddle.y=405;
        }
    }

    public void draw(Graphics g)
    {
        java.net.URL url1 = getClass().getResource("paddle.png");
        PaddleImage1 = getImage(url1);
        java.net.URL url2 = getClass().getResource("paddle2.png");
        PaddleImage2 = getImage(url2);

        switch(id)
        {
            //Paddle 1
            case 1:
                g.drawImage(PaddleImage1, this.paddle.x, this.paddle.y, null);
                break;
            //Paddle 2
            case 2:
                g.drawImage(PaddleImage2, this.paddle.x, this.paddle.y, null);
                break;
        }
    }

    @Override
    public void run() {
        try {
            while(true) {
                move();
                Thread.sleep(4);
            }
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
