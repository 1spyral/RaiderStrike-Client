import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client {
    JFrame window;
    GamePanel gamePanel;
    KeyListener keyListener; 
    MouseListener mouseListener;
    MouseMotionListener mouseMotionListener;

    Keyboard keyboard;
    Mouse mouse;
    Messenger messenger;

    Server server;
    StateMachine stateMachine;

    Client() throws Exception {
        window = new JFrame("Magic Terrorists");   
        gamePanel = new GamePanel();          
        keyListener = new MyKeyListener();
        mouseListener = new MyMouseListener();
        mouseMotionListener = new MyMouseMotionListener();

        keyboard = new Keyboard();
        mouse = new Mouse();
        messenger = new Messenger();

        server = new Server(messenger);
        messenger.setServer(server);

        stateMachine = new StateMachine(keyboard, mouse, messenger);
    }
    // set up the game window
    public void setup() {
        window.setSize(Const.WIDTH, Const.HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        gamePanel.addKeyListener(keyListener);
        gamePanel.addMouseListener(mouseListener);
        gamePanel.addMouseMotionListener(mouseMotionListener);        
        window.add(gamePanel); 
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    // main game loop
    public void start() {
        server.start();
        while (true) {
            window.repaint();
            try  {Thread.sleep(Const.FRAME_PERIOD);} catch(Exception e){}

            stateMachine.update();
        }
    }    
    // act upon key and mouse events
    public class MyKeyListener implements KeyListener {   
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            keyboard.keyPressed(key);
        }
        public void keyReleased(KeyEvent e) { 
            int key = e.getKeyCode();
            keyboard.keyReleased(key);
        }   
        public void keyTyped(KeyEvent e) {
            char keyChar = e.getKeyChar();
            keyboard.keyTyped(keyChar);
        }           
    }    

    public class MyMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent e) {
             
        }
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == 1) {
                mouse.rightMousePressed();
            } else if (e.getButton() == 2) {
                mouse.leftMousePressed();
            }
        }
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() == 1) {
                mouse.rightMouseReleased();
            } else if (e.getButton() == 2) {
                mouse.leftMouseReleased();
            }
        }
        public void mouseEntered(MouseEvent e) {
        }
        public void mouseExited(MouseEvent e) {
        }
    }    

    public class MyMouseMotionListener implements MouseMotionListener{
        public void mouseMoved(MouseEvent e) {
            mouse.setX(e.getX());
            mouse.setY(e.getY());
        }
        public void mouseDragged(MouseEvent e) {
            mouse.setX(e.getX());
            mouse.setY(e.getY());
        }         
    }    
    //draw everything
    public class GamePanel extends JPanel {
        GamePanel() {
            setFocusable(true);
            requestFocusInWindow();
        }
        
        @Override
        public void paintComponent(Graphics g) { 
            super.paintComponent(g); //required
            
            stateMachine.draw(g);
        }    
    }    
}