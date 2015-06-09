/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerContent;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeroen
 */
public class HandleAClient {

    private final Socket socket;
    private final DataInputStream inputfromClient;
    private final DataOutputStream outputToClient;

    public HandleAClient(Socket socket) throws IOException {
	this.socket = socket;
	socket.setSoTimeout(5000);
	this.inputfromClient = new DataInputStream(socket.getInputStream());
	this.outputToClient = new DataOutputStream(socket.getOutputStream());
    }

    public void checkAlive() {
	try {
	    outputToClient.writeUTF("/isAlive");
	    System.out.println("hallo");
	    new Thread(new Runnable() {
		@Override
		public void run() {
		    try {
			boolean b = inputfromClient.readBoolean();
			System.out.println(b);
		    } catch (IOException ex) {
			Logger.getLogger(HandleAClient.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
	    }).start();
	} catch (IOException ex) {
	    Logger.getLogger(HandleAClient.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}
