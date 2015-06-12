package ClientLaunch;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author 
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
		String naam = "";
		String ipadress = "";
		if(menu.login(naam, ipadress))
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		backgroundSouth.setBorder(BorderFactory.createLineBorder(Color.orange,5));
		add(backgroundSouth, BorderLayout.SOUTH);
		getContentPane().add(panel);
		setResizable(false);
		setVisible(true);
		
		}
		else{
			System.exit(0);
		}
	}
	
	public void volgendeRegel(final JTextArea bericht)
	{
		bericht.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				bericht.setWrapStyleWord(true);
				bericht.setLineWrap(true);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)  {
					 menu.Sendbericht(bericht);
					 e.consume();
				 }
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}
		});
	}
	public void volgendeRegelB(final JTextArea ontvangenBerichten)
	{
		ontvangenBerichten.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ontvangenBerichten.setWrapStyleWord(true);
				ontvangenBerichten.setLineWrap(true);
			}
		
	});
	}


}
