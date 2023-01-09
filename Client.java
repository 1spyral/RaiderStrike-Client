import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client {
    JFrame window;
    GamePanel gamePanel;

    Keyboard keyboard;
    Mouse mouse;
    Messenger messenger;

    Server server;
    StateMachine stateMachine;

    Client() throws Exception {
        window = new JFrame("Magic Terrorists");   
        gamePanel = new GamePanel();          

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
        gamePanel.addKeyListener(keyboard);
        gamePanel.addMouseListener(mouse);
        gamePanel.addMouseMotionListener(mouse);        
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