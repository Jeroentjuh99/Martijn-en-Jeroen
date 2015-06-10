/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerContent;

import java.io.*;
import java.net.*;

/**
 *
 * @author jeroen
 */
public class Client implements Runnable {

    private String clientName = null;
    private DataInputStream input;
    private DataOutputStream output;
    private final Socket socket;
    private final Client[] clients;
    private int maxClients;

    public Client(Socket socket, Client[] clients) {
	this.socket = socket;
	this.clients = clients;
	this.maxClients = clients.length;
	Thread t = new Thread(this);
    }

    @Override
    public void run() {
	try {
	    this.input = new DataInputStream(socket.getInputStream());
	    this.output = new DataOutputStream(socket.getOutputStream());
	    
	    output.writeUTF("/gebruikersnaam");
	    this.clientName = input.readUTF().substring(16);
	    
	    while(true){
		String text = input.readUTF();
		synchronized(this){
		    for(int i = 0; i < maxClients; i++){
			if(!(clients[i] == null)){
			    clients[i].output.writeUTF(text);
			}
		    }
		}
	    }
	    
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }    
}
