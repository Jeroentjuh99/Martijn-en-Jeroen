/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerContent;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author jeroen
 */
public class ServerContent implements Runnable {

    private final JTextArea textOut;
    private ServerSocket server = null;
    private int maxClients = 10;
    private Client[] clients = new Client[maxClients];
    private ServerLog logger;

    public ServerContent(JTextArea textOut) {
	this.textOut = textOut;
	try {
	    this.server = new ServerSocket(0);
	    writeToScreen(InetAddress.getLocalHost() + ":" + server.getLocalPort());
	    this.logger = new ServerLog();
	    logger.addText("IP: " + InetAddress.getLocalHost() + ":" + server.getLocalPort());

	    Thread t = new Thread(this);
	    t.start();
	} catch (IOException e) {
	    System.err.println("Server could not be opened on this port");
	    System.exit(0);
	}
    }

    public void writeToScreen(String text) {
	textOut.append(text + '\n');
    }

    public void logText(String text) {
	logger.addText(text);
    }

    public void handleCommand(String text) {
	if (text.equalsIgnoreCase("/shutdown")) {
	    validCommand(text);
	    closeLog();
	    System.exit(0);
	} else if (text.equalsIgnoreCase("/ip")) {
	    try {
		validCommand(text);
		writeToScreen("IP: " + InetAddress.getLocalHost() + ":" + server.getLocalPort());
		logText(InetAddress.getLocalHost() + ":" + server.getLocalPort());
	    } catch (UnknownHostException ex) {
		Logger.getLogger(ServerContent.class.getName()).log(Level.SEVERE, null, ex);
	    }
	} else if(text.startsWith("/say ")){
	    validCommand(text);
	    
	}
    }

    public void closeLog() {
	logger.closeFile();
    }

    private void validCommand(String t) {
	writeToScreen(t);
	logText(t);
    }

    @Override
    public void run() {
	while (true) {
	    try {
		int i = 0;
		Socket socket = server.accept();
		for (i = 0; i < maxClients; i++) {
		    if (clients[i] == null) {
			clients[i] = new Client(socket, clients);
			break;
		    }
		}
		if (i == maxClients) {
		    DataOutputStream d = new DataOutputStream(socket.getOutputStream());
		    d.writeUTF("/say Server is too busy, please try again later");
		    d.writeUTF("/shutdown");
		    d.close();
		    socket.close();
		}
	    } catch (IOException ex) {
		Logger.getLogger(ServerContent.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }
}
