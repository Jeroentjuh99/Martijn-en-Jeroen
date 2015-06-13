package ClientLaunch;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ServerConnection implements Runnable{

	private Menu menu;
	private JTextArea berichtenVenster;
	private String[] ip;
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	public ServerConnection(Menu menu, JTextArea p) {
		this.menu = menu;
		this.berichtenVenster = p;
	}
	
	public void startThread()
	{
		Thread t = new Thread(this);
		t.start();
	}

	public void sendMessage(String message) {
		try {
			toServer.writeUTF(message);
			toServer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void getMessage() {
		String text=null;
		try {
			text = fromServer.readUTF();
			if(text.isEmpty())
			{
				System.out.println("niks gekregen");
				return;
			}
			else if(text.startsWith("/say "))
			{
				berichtenVenster.append(text.substring(4, text.length()));
				berichtenVenster.append("\n\n");
				berichtenVenster.setWrapStyleWord(true);
				berichtenVenster.setLineWrap(true);
			}
			else if(text.equals("/gebruikersnaam"))
			{
				sendMessage("/gebruikersnaam "+menu.gebruikersnaam1);
				toServer.flush();
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
				System.exit(0);
			}
		} catch (SocketTimeoutException e){
			JOptionPane.showMessageDialog(null, "Het serveradres is niet meer berijkbaar");
			System.exit(0);
		} catch (NullPointerException e) {
		} catch (SocketException e) {
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

	
	public void aantalBerichten(int hoeveelheid){
	try {
		toServer.writeInt(hoeveelheid);
	} catch (IOException e) {
		e.printStackTrace();
	}
}

	public boolean serverConnection(String gebruikersnaam, String ipadres) {
		boolean connectie = false;
		try {
			ip = ipadres.split(";");
			Socket socket = new Socket(ip[0], Integer.parseInt(ip[1]));
			 fromServer = new DataInputStream(
			 socket.getInputStream());

			toServer =
			new DataOutputStream(socket.getOutputStream());
			connectie = true;
			return connectie;
		} catch (Exception ex) {
			connectie = false;
			return connectie;
		}

	}

	@Override
	public void run() {
		while(true){	
			try {
				getMessage();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
