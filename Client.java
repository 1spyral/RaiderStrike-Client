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

    Set<Integer> keysPressed;
    boolean mouseDown;
    int mouseX;
    int mouseY;

    StateMachine stateMachine;
    Thread server;

    Client() throws Exception {
        window = new JFrame("Magic Terrorists");   
        gamePanel = new GamePanel();          
        keyListener = new MyKeyListener();
        mouseListener = new MyMouseListener();
        mouseMotionListener = new MyMouseMotionListener();

        keysPressed = new HashSet<Integer>();

        stateMachine = new StateMachine();        
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
        while (true) {
            window.repaint();
            try  {Thread.sleep(Const.FRAME_PERIOD);} catch(Exception e){}

           // stateMachine.update(keysPressed, mouseDown, mouseX, mouseY);
        }
    }    
    // act upon key and mouse events
    public class MyKeyListener implements KeyListener {   
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            keysPressed.add(key);
        }
        public void keyReleased(KeyEvent e) { 
            int key = e.getKeyCode();
            keysPressed.remove(key);
        }   
        public void keyTyped(KeyEvent e) {
            char keyChar = e.getKeyChar();
        }           
    }    

    public class MyMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();    
        }
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == 1) {
                mouseDown = true;
            }
        }
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() == 1) {
                mouseDown = false;
            }
        }
        public void mouseEntered(MouseEvent e) {
        }
        public void mouseExited(MouseEvent e) {
        }
    }    

    public class MyMouseMotionListener implements MouseMotionListener{
        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
        }
        public void mouseDragged(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
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
    }    }
