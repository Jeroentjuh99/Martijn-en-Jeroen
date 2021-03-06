/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerContent;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Collections;
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
    private String[] validCommands = {"/shutdown, /quit, /exit", "/ip", "/say your_text_here", "/help", "/showUsers", "/showIPs", "/kick username_here"};

    public ServerContent(JTextArea textOut) {
	this.textOut = textOut;
	try {
	    this.server = new ServerSocket(0);
	    String ipString = InetAddress.getLocalHost() + ":" + server.getLocalPort();
	    writeToScreen(ipString);
	    writeToScreen("Type /help for all available commands");
	    this.logger = new ServerLog();
	    logger.addText("IP: " + ipString);

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
	try {
	    if (text.equalsIgnoreCase(validCommands[0].substring(0, 9)) || text.equalsIgnoreCase(validCommands[0].substring(11, 16)) || text.equalsIgnoreCase(validCommands[0].substring(18, 23))) {
		validCommand(text);
		for (int i = 0; i < maxClients; i++) {
		    if (!(clients[i] == null)) {
			clients[i].messageFromServer(validCommands[0].substring(0, 9));
		    }
		}
		closeLog();
		System.exit(0);

	    } else if (text.equalsIgnoreCase(validCommands[1])) {
		validCommand(text);
		writeToScreen("IP: " + InetAddress.getLocalHost() + ":" + server.getLocalPort());
		logText(InetAddress.getLocalHost() + ":" + server.getLocalPort());

	    } else if (text.startsWith(validCommands[2].substring(0, 5))) {
		String[] textArray = text.split(" ", 2);
		text = textArray[0] + " Server: " + textArray[1];
		validCommand(text);
		for (int i = 0; i < maxClients; i++) {
		    if (!(clients[i] == null)) {
			clients[i].messageFromServer(text);
		    }
		}
	    } else if (text.equalsIgnoreCase(validCommands[3])) {
		validCommand(text);
		Help();
	    } else if (text.equalsIgnoreCase(validCommands[4])) {
		validCommand(text);
		showConnectedClients();
	    } else if (text.equalsIgnoreCase(validCommands[5])) {
		validCommand(text);
		showConnectedIPs();
	    } else if (text.startsWith(validCommands[6].substring(0, 6))) {
		validCommand(text);
		kickUser(text.substring(6));
	    } else {
		Help();
	    }
	} catch (Exception e) {
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
			clients[i] = new Client(socket, clients, this);
			break;
		    }
		}
		if (i == maxClients) {
		    DataOutputStream d = new DataOutputStream(socket.getOutputStream());
		    d.writeUTF("/say Server is too busy, please try again later");
		    d.writeUTF(validCommands[0].substring(0, 9));
		    d.close();
		    socket.close();
		}
	    } catch (IOException ex) {
		Logger.getLogger(ServerContent.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }

    public void messageFromClient(String text) {
	text = text.substring(5);
	validCommand(text);
    }

    private void Help() {
	String t = "The valid commands are: \n";
	int a = 0;
	int i = 0;
	for (i = 0; i < validCommands.length; i++) {
	    a = i + 1;
	    t += a + ". \"" + validCommands[i] + "\" \n";
	}
	t += "without the \" ";
	validCommand(t);
    }

    private void showConnectedClients() {
	List<Client> clientsList = new ArrayList();
	for (int i = 0; i < clients.length; i++) {
	    if (!(clients[i] == null)) {
		clientsList.add(clients[i]);
	    }
	}
	Collections.sort(clientsList);
	String t = "The connected users are: \n";
	int i = 1;
	for (Client c : clientsList) {
	    t += i + ". " + c.getClientName() + " \n";
	    i++;
	}
	if (t.equals("The connected users are: \n")) {
	    t = "There are no clients connected";
	}
	validCommand(t);
    }

    private void showConnectedIPs() {
	List<Client> clientsList = new ArrayList();
	for (int i = 0; i < clients.length; i++) {
	    if (!(clients[i] == null)) {
		clientsList.add(clients[i]);
	    }
	}
	Collections.sort(clientsList, Client.SortOnIp());
	String t = "The connected IPs are: \n";
	int i = 1;
	for (Client c : clientsList) {
	    t += i + ". " + c.getIP() + " \n";
	    i++;
	}
	if (t.equals("The connected IPs are: \n")) {
	    t = "There are no clients connected";
	}
	validCommand(t);
    }

    private void kickUser(String user) {
	String banHammer = null;
	for (int i = 0; i < clients.length; i++) {
	    if (!(clients[i] == null)) {
		if (clients[i].getClientName().equalsIgnoreCase(user)) {
		    banHammer = clients[i].getIP();
		}
	    }
	}

	for (int i = 0; i < clients.length; i++) {
	    if (!(clients[i] == null)) {
		if (compare(clients[i].getIP(), banHammer)) {
		    try {
			clients[i].messageFromServer("/say The BanHammer has spoken!");
			clients[i].messageFromServer(validCommands[0].substring(0, 9));
			validCommand(clients[i].getClientName() + " has been kicked");
			clients[i] = null;
		    } catch (IOException e) {
		    }
		}
	    }
	}
    }
    
    private static <E> boolean compare(E o1, E o2){
	return o1.equals(o2);
    }
}
