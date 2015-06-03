package ClientLaunch;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	public static void main(String[] args) {
		Main m = new Main();

	}

	public Main() {
		super("Windows Live Messenger");
		Menu menu = new Menu(this,panel);
		if(menu.gebruikersnaam())
		{
		setSize(600, 800);
		JPanel backgroundSouth = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		JPanel panel = new JPanel();
		//JTextArea chat = new JTextArea();
		//chat.setSize(panel.getWidth(), panel.getHeight());
		System.out.println(panel.getWidth());
//		panel.add(chat);
		JTextArea bericht = new JTextArea(5,40); 
		JScrollPane scrollen = new JScrollPane(bericht, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		backgroundSouth.add(scrollen);
		backgroundSouth.add(menu.Send(bericht));
		backgroundSouth.setBorder(BorderFactory.createLineBorder(Color.orange,5));
		add(backgroundSouth, BorderLayout.SOUTH);
		getContentPane().add(panel);
		//panel.setBackground(Color.black);
		setResizable(false);
		setVisible(true);
		
//		ServerConnection c = new ServerConnection(menu, panel);
		
		System.out.println(panel.getHeight());
		}
	}
	
	public void volgendeRegel(final JTextArea bericht)
	{
		bericht.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				bericht.setWrapStyleWord(true);
				bericht.setLineWrap(true);
			}
		});

	}


}
