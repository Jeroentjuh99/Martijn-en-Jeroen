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
	t.start();
    }

    @Override
    public void run() {
	int maxClients = this.maxClients;
	Client[] clients = this.clients;
	try {
	    this.input = new DataInputStream(socket.getInputStream());
	    this.output = new DataOutputStream(socket.getOutputStream());

	    output.writeUTF("/gebruikersnaam");
	    this.clientName = input.readUTF().substring(16);
	    output.writeUTF("/say Welcome to the server, " + clientName + ". \nType /quit to quit the client.");
	    System.err.println(clientName);

	    synchronized (this) {
		for (int i = 0; i < maxClients; i++) {
		    if (clients[i] != null && clients[i] == this) {
			clientName = clientName;
			break;
		    }
		}
		for (int i = 0; i < maxClients; i++) {
		    if (clients[i] != null && clients[i] != this) {
			clients[i].output.writeUTF("/say " + clientName + " just joined the chat!");
		    }
		}
	    }

	    while (true) {
		synchronized (this) {
		    String text = input.readUTF();
		    for (int i = 0; i < maxClients; i++) {
			if (!(clients[i] == null)) {
			    clients[i].output.writeUTF(text);
			    System.out.println(text);
			}
		    }
		}
	    }
	    
	    synchronized (this) {
		for (int i = 0; i < maxClients; i++) {
		    if (clients[i] != null && clients[i] != this
			    && clients[i].clientName != null) {
			clients[i].output.writeUTF("*** The user " + clientName
				+ " is leaving the chat room !!! ***");
		    }
		}
	    }
	    output.writeUTF("*** Bye " + clientName + " ***");

	    /*
	     * Clean up. Set the current thread variable to null so that a new client
	     * could be accepted by the server.
	     */
	    synchronized (this) {
		for (int i = 0; i < maxClients; i++) {
		    if (clients[i] == this) {
			clients[i] = null;
		    }
		}
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
