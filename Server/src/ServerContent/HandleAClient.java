/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerContent;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.*;

/**
 *
 * @author jeroen
 */
public class HandleAClient implements Runnable{

    private final Socket socket;
    private final DataInputStream inputfromClient;
    private final DataOutputStream outputToClient;
    protected ArrayList<String> messages = new ArrayList();

    public HandleAClient(Socket socket) throws IOException {
	this.socket = socket;
	socket.setSoTimeout(5000);
	this.inputfromClient = new DataInputStream(socket.getInputStream());
	this.outputToClient = new DataOutputStream(socket.getOutputStream());
    }

    public void doYourThing(){
	Thread t = new Thread(this);
	t.start();
    }
    
    @Override
    public void run(){
	try {
	    outputToClient.writeUTF("/hasNewMessage");
	    int i = inputfromClient.readInt();
	    while (i > 0){
		messages.add(inputfromClient.readUTF());
	    }
	    
	} catch (IOException ex) {
	    Logger.getLogger(HandleAClient.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}
