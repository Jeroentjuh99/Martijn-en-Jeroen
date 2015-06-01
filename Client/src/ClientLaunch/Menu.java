package ClientLaunch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Menu extends JPanel {


	private Main main;

	public Menu(Main main) {
		this.main = main;
	}
	public JButton Send(final JTextArea bericht) {
		JButton Send = new JButton("Send");
		Send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println(bericht.getText());
				bericht.setText(null);
			}
		});
		return Send;
	}
	
	public boolean gebruikersnaam()
	{
		JTextField gebruikersnaam = new JTextField();
		JTextField poort = new JTextField();
		poort.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0' && c <= '9')
						|| c == KeyEvent.VK_BACK_SPACE
						|| c == KeyEvent.VK_DELETE || c == KeyEvent.VK_ENTER)) {
					JOptionPane
							.showMessageDialog(null,
									"Alleen cijfers zijn mogelijk");
					e.consume();
				}
			}
		});
		Object[] optionItems = {"Gebruikersnaam ", gebruikersnaam,"Poort ", poort};
		int option = JOptionPane.showConfirmDialog(null, optionItems,
				"Gebruikersnaam", JOptionPane.OK_CANCEL_OPTION);
		if(option==JOptionPane.OK_OPTION)
		{
			if(!gebruikersnaam.getText().isEmpty()&&!poort.getText().isEmpty())
			{
				return true;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Vul een gebruikersnaam in en een poortnummer");
				gebruikersnaam();
				return false;
			}
		}
		else return false;
		
	}
}