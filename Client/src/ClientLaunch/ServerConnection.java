package ClientLaunch;

import java.io.*;
import java.net.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ServerConnection implements Runnable{

	private Menu menu;
	private JTextArea berichtenVenster;
	private String[] ip;
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	private int niksontvangen;
//	private InetAddress Localip = getLocalHost();
	public ServerConnection(Menu menu, JTextArea p) {
		this.menu = menu;
		this.berichtenVenster = p;
		
		
		Thread t = new Thread(this);
		t.start();
	}
	

	public void sendMessage(String message) {
		try {
			toServer.writeUTF(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getMessage() {
		String text=null;
		System.out.println("Check");
		try {
			text = fromServer.readUTF();
			System.err.println(text);
			if(text.isEmpty())
			{
				System.out.println("niks gekregen");
				return;
			}
			else if(text.startsWith("/say "))
			{
//				JTextArea message = new JTextArea(5,50);
//				message.setText(text.substring(4, text.length()-1));
//				message.setWrapStyleWord(true);
//				message.setLineWrap(true);
//				//message.
				berichtenVenster.setText(text.substring(4, text.length()-1));
				berichtenVenster.setText("/n");
			}
			else if(text.startsWith("/isAlive"))
			{
				System.out.println("ik leef nog");
				ikLeefNog();
				toServer.flush();
			}
			else if(text.startsWith("/gebruikersnaam "))
			{
				sendMessage("/gebruikersnaam "+menu.gebruikersnaam1);
				toServer.flush();
			}
			else if(text.startsWith("/hasNewMessage"))
			{
				if(menu.berichten.isEmpty())
				{
					System.out.println("geenBerichten");
					aantalBerichten(0);
					toServer.flush();
				}
				else
				{
					aantalBerichten(menu.berichten.size());
					for(int i=0;i<menu.berichten.size();i++)
					{
						sendMessage("/say "+menu.gebruikersnaam1+": "+menu.berichten.get(i).getText());
						toServer.flush();
						System.out.println("bericht verstuurd");
					}
					menu.berichten.clear();
				}
			}
			else if(text.startsWith("/ip "))
			{
				sendMessage("/ip "+ InetAddress.getLocalHost().getHostAddress());
				toServer.flush();
			}
			else if(text.startsWith("/shutdown"))
			{
				JOptionPane
				.showMessageDialog(null,
						"De server heeft de connectie verbroken");
			}
		} catch (SocketTimeoutException e){
			JOptionPane.showMessageDialog(null, "Het serveradres is niet meer berijkbaar");
			System.exit(0);
		} catch (Exception ex) {
//			ex.printStackTrace();
		}
	}
	
	public void ikLeefNog(){
		try {
			toServer.writeBoolean(true);;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void aantalBerichten(int hoeveelheid){
	try {
		toServer.writeInt(hoeveelheid);;;
	} catch (IOException e) {
		e.printStackTrace();
	}
}

	public boolean serverConnection(String gebruikersnaam, String ipadres) {
		boolean connectie = false;
		try {
			ip = ipadres.split(";");
			Socket socket = new Socket(ip[0], Integer.parseInt(ip[1]));
			socket.setSoTimeout(10000);
			// Create an input stream to receive data from the server
			 fromServer = new DataInputStream(
			 socket.getInputStream());

			// Create an output stream to send data to the server
			toServer =
			new DataOutputStream(socket.getOutputStream());
			connectie = true;
			return connectie;
//			System.out.println("sdfsdfhaopisfdhafdpoahogfs");
		} catch (Exception ex) {
			// jta.append(ex.toString() + '\n');
			connectie = false;
			return connectie;
//		} finally {
//			System.out.println("finaly");
//			return connectie;
		}

		// if()
		// ServerConnection c = new ServerConnection(menu, panel);
	}

	@Override
	public void run() {
		while(true){	
			try {
				getMessage();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
