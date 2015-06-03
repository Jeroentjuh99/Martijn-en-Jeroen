package ClientLaunch;

import java.io.*;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServerConnection implements Runnable{

	private Menu menu;
	private JPanel panel;
	private String[] ip;
	private DataOutputStream toServer;
	private DataInputStream fromServer;

	public ServerConnection(Menu menu, JPanel p) {
		this.menu = menu;
		this.panel = p;
		
		
		Thread t = new Thread(this);
		t.start();
	}

	public void sendMessage(String message) {
		
	}

	public void getMessage() {
		String text=null;
		try {
			text = fromServer.readUTF();
			if(text.equals("leef je nog woord"))
			{
				sendMessage("ik leef nog");
			}
			else if(!text.isEmpty())
			{
			JLabel message = new JLabel();
			message.setText(text);
			panel.add(message);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean serverConnection(String gebruikersnaam, String ipadres) {
		boolean connectie = false;
		try {
			ip = ipadres.split(";");
			Socket socket = new Socket(ip[0], Integer.parseInt(ip[1]));
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
