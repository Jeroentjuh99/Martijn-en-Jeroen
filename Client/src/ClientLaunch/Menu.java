package ClientLaunch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Menu extends JPanel {


	private Main main;
	private ServerConnection server;
	protected String gebruikersnaam1;
	protected ArrayList<JTextArea> berichten = new ArrayList();
//	private JPanel panel;

	public Menu(Main main, JPanel panel) {
		this.main = main;
//		this.panel = panel;
		server= new ServerConnection(this, panel);
	}
	public JButton Send(final JTextArea bericht) {
		JButton Send = new JButton("Send");
		Send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!(bericht.getText().isEmpty()))
				{
					System.out.println(bericht.getText());
					bericht.setText(null);
					berichten.add(bericht);
				}
			}
		});
		return Send;
	}
	
	
	public boolean login()
	{
		JTextField gebruikersnaam = new JTextField();
		JTextField ipadres = new JTextField();
		ipadres.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0' && c <= '9')
						|| c == KeyEvent.VK_BACK_SPACE
						|| c == KeyEvent.VK_DELETE || c == KeyEvent.VK_ENTER || c== KeyEvent.VK_PERIOD || c== KeyEvent.VK_SEMICOLON )) {
					JOptionPane
							.showMessageDialog(null,
									"Het adress moet bestaan uit een ipadress en een poortnummer VB: 123.23.320.78;89900");
					e.consume();
				}
			}
		});
		Object[] optionItems = {"Gebruikersnaam ", gebruikersnaam,"ServerAdres als volgt 000.00.000.00;000 ", ipadres};
		int option = JOptionPane.showConfirmDialog(null, optionItems,
				"Gebruikersnaam", JOptionPane.OK_CANCEL_OPTION);
		if(option==JOptionPane.OK_OPTION)
		{
			if(!gebruikersnaam.getText().isEmpty()&&!ipadres.getText().isEmpty())
			{
//				server= new ServerConnection(this, panel);
				this.gebruikersnaam1= gebruikersnaam.getText();
				if(server.serverConnection(gebruikersnaam.getText(),ipadres.getText()))
				{
//					server= new ServerConnection(this, panel);
					return true;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Het serveradres is niet berijkbaar");
					login();
					return false;
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Vul een gebruikersnaam in en een ipadres + poortnummer");
				login();
				return false;
			}
		}
		else return false;
		
	}
}