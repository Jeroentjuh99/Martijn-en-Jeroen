/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerContent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.net.*;
import static java.net.InetAddress.*;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author jeroen
 */
public class ServerContent extends JPanel implements Runnable {
    
    private ServerSocket sock = null;
    private SocketData s = null;
    private JTextArea textOutput;
    private JTextField textInput;
    private ServerLog logger;
    
    public ServerContent(JTextArea textOutput, JTextField textInput) {
	try {
	    this.sock = new ServerSocket(0);
	    this.textOutput = textOutput;
	    this.textInput = textInput;
	    this.logger = new ServerLog();
	    InetAddress i = getLocalHost();
	    textOutput.append(i + ":" + geefPort() + '\n');
	    this.s = new SocketData(sock);
	    Date date = new Date();
	    logger.addText("Server started on: " + ServerLog.format.format(date) + " on IP:" + i + ":" + geefPort());
	    
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
    
    @Override
    public void run() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
    }
    
    public void sluitLogger() {
	logger.closeFile();
    }
    
    public void logText(String text) {
	textOutput.append(text + '\n');
	logger.addText(text);
    }
    
    public void showTextFromCommand(String text){
	if(text.equalsIgnoreCase("ip")){
	    try{
	    logText("IP: " + getLocalHost() + ':' + geefPort());
	    } catch (IOException e){
	    }
	}
    }
}
