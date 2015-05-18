package ClientLaunch;

import javax.swing.JFrame;

/**
 *
 * @author jeroen
 */
public class Main extends JFrame {

    public static void main(String[] args) {
	JFrame frame = new JFrame("Windows Live Messenger");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	frame.setSize(600, 800);
	frame.setResizable(false);
	frame.setVisible(true);
    }
} 
