import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ObslugaKlawiatury implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            Main.paddle1.keyPressed(e); // paddle 1
            Main.paddle2.keyPressed(e);   //paddle 2
        }
        @Override
        public void keyReleased(KeyEvent e) {
            Main.paddle1.keyReleased(e); // paddle 1
            Main.paddle2.keyReleased(e); // paddle 2
        }
        @Override
        public void keyTyped(KeyEvent e) {
        }
    }
