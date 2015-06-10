/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerContent;

import java.io.IOException;
import java.net.*;
import static java.net.InetAddress.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author jeroen
 */
public class ServerContentOld extends JPanel implements Runnable {

    private ServerSocket sock = null;
    private SocketData s = null;
    private JTextArea textOutput;
    private JTextField textInput;
    private ServerLog logger;

    public ServerContentOld(JTextArea textOutput, JTextField textInput) {
	try {
	    this.sock = new ServerSocket(0);
	    this.textOutput = textOutput;
	    this.textInput = textInput;
	    this.logger = new ServerLog();
	    InetAddress i = getLocalHost();
	    textOutput.append(i + ":" + geefPort() + '\n');
	    this.s = new SocketData(sock);
	    Date date = new Date();
	    logger.addText("Server started on: " + ServerLog.format.format(date) + " on IP: " + i + ":" + geefPort());

	    Thread t = new Thread(this);
	    t.start();
	} catch (IOException e) {
	    e.printStackTrace();
	    System.exit(0);
	}
    }

    private int geefPort() {
	if (!(sock == null)) {
	    return sock.getLocalPort();
	} else {
	    return -1;
	}
    }

    public void sluitLogger() {
	logger.closeFile();
    }

    public void logText(String text) {
	textOutput.append(text + '\n');
	logger.addText(text);
    }

    public void showTextFromCommand(String text) {
	if (text.equalsIgnoreCase("ip")) {
	    try {
		logText("IP: " + getLocalHost() + ':' + geefPort());
	    } catch (IOException e) {
	    }
	} else if (text.startsWith("/say ")) {
	    logText(text);
	}
    }

    @Override
    public void run() {
	while (true) {
	    try {
		for (HandleAClient client : s.sockets) {
		    client.doYourThing();
		}
		Thread.sleep(1000);
	    } catch (InterruptedException ex) {
		Logger.getLogger(ServerContentOld.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }
}
