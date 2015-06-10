package ClientLaunch;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javafx.scene.input.KeyCode;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author jeroen
 */
public class Main extends JFrame {
	private JPanel panel = new JPanel();
	private JTextArea ontvangenBerichten = new JTextArea(41,52); 
	private Menu menu = new Menu(this,ontvangenBerichten);
	public static void main(String[] args) {
		Main m = new Main();

	}

	public Main() {
		super("Windows Live Messenger");
		if(menu.login())
		{
		setSize(600, 800);
		JPanel backgroundSouth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextArea bericht = new JTextArea(5,40); 
		volgendeRegel(bericht);
		ontvangenBerichten.setEditable(false);
		volgendeRegel(ontvangenBerichten);
		JScrollPane scrollen = new JScrollPane(bericht, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane scrollen1 = new JScrollPane(ontvangenBerichten, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollen1);
		backgroundSouth.add(scrollen);
		backgroundSouth.add(menu.Send(bericht));
//		JButton button = menu.Send(bericht);
//		backgroundSouth.add(button);
//		getRootPane().setDefaultButton(button);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		backgroundSouth.setBorder(BorderFactory.createLineBorder(Color.orange,5));
		add(backgroundSouth, BorderLayout.SOUTH);
		getContentPane().add(panel);
		setResizable(false);
		setVisible(true);
		
//		ServerConnection c = new ServerConnection(menu, panel);
		}
		else{
			System.exit(0);
		}
	}
	
	public void volgendeRegel(final JTextArea bericht)
	{
		bericht.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				 if (e.getKeyCode() == KeyEvent.VK_ENTER)  {
					 e.consume();
					 menu.Send(bericht);
				 }
				 System.err.print(e.getKeyCode());
				// System.out.print(KeyEvent.VK_ENTER);
				bericht.setWrapStyleWord(true);
				bericht.setLineWrap(true);
			}
		});
	}


}
