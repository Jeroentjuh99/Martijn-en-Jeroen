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

    private Recursiegame game;
    private Main main;
    private ServerConnection server;
    protected String gebruikersnaam1;
    protected String textveldGebruikersnaam;
    protected String textveldIp;

    public Menu(Main main, JTextArea panel) {
	this.main = main;
	game = new Recursiegame();
	server = new ServerConnection(this, panel);
    }

    public JButton Send(final JTextArea bericht) {
	JButton Send = new JButton("Send");
	Send.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		if (!(bericht.getText().isEmpty())) {
		    if (bericht.getText().startsWith("/quit")) {
			server.sendMessage("/quit");
			System.exit(0);
		    } else if (bericht.getText().startsWith("/startgame")) {
			String[] a = {"a"};
			game.main(a);
		    } else {
			server.sendMessage("/say " + gebruikersnaam1 + ": " + bericht.getText());
		    }

		    bericht.setText(null);
		}
	    }
	});
	return Send;
    }

    public void Sendbericht(JTextArea bericht1) {
	if (bericht1.getText().startsWith("/quit")) {
	    server.sendMessage("/quit");
	    System.exit(0);
	} else if (bericht1.getText().startsWith("/startgame")) {
	    String[] a = {"a"};
	    game.main(a);
	} else {
	    server.sendMessage("/say " + gebruikersnaam1 + ": " + bericht1.getText());
	}
	bericht1.setText(null);
    }

    public boolean login() {
	JTextField gebruikersnaam = new JTextField();
	JTextField ipadres = new JTextField();
	gebruikersnaam.setText(textveldGebruikersnaam);
	ipadres.setText(textveldIp);
	ipadres.addKeyListener(new KeyAdapter() {
	    public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (!((c >= '0' && c <= '9')
			|| c == KeyEvent.VK_BACK_SPACE
			|| c == KeyEvent.VK_DELETE || c == KeyEvent.VK_ENTER || c == KeyEvent.VK_PERIOD || c == KeyEvent.VK_SEMICOLON)) {
		    JOptionPane
			    .showMessageDialog(null,
				    "Het adress moet bestaan uit een ipadress en een poortnummer VB: 123.23.320.78;89900");
		    e.consume();
		}
	    }
	});
	Object[] optionItems = {"Gebruikersnaam ", gebruikersnaam, "ServerAdres als volgt 000.00.000.00;000 ", ipadres};
	int option = JOptionPane.showConfirmDialog(null, optionItems,
		"Gebruikersnaam", JOptionPane.OK_CANCEL_OPTION);
	if (option == JOptionPane.OK_OPTION) {
	    if (!(gebruikersnaam.getText().trim().equalsIgnoreCase("Server"))) {
		if (!gebruikersnaam.getText().isEmpty() && !ipadres.getText().isEmpty()) {
		    this.gebruikersnaam1 = gebruikersnaam.getText();
		    if (server.serverConnection(gebruikersnaam.getText(), ipadres.getText())) {
			main.setappName(gebruikersnaam1);
			server.startThread();
			return true;
		    } else {
			JOptionPane.showMessageDialog(null, "Het serveradres is niet berijkbaar");
			textveldGebruikersnaam = gebruikersnaam.getText();
			textveldIp = ipadres.getText();
			return false;
		    }
		} else {
		    JOptionPane.showMessageDialog(null, "Vul een gebruikersnaam in en een ipadres + poortnummer");
		    textveldGebruikersnaam = gebruikersnaam.getText();
		    textveldIp = ipadres.getText();
		    return false;
		}
	    } else {
		JOptionPane.showMessageDialog(null, "De gebruikersnaam " + gebruikersnaam.getText() + " mag niet gebruikt worden.");
		textveldIp = ipadres.getText();
		return false;
	    }
	} else {
	    System.exit(0);
	    return false;
	}

    }
}
