/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerContent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.net.*;
import static java.net.InetAddress.*;
import javax.swing.*;

/**
 *
 * @author jeroen
 */
public class ServerContent extends JPanel implements Runnable {

    private ServerSocket sock = null;
    private Color textColor = Color.GREEN;
    private SocketData s = null;
    private JTextArea text;

    public ServerContent(JTextArea text) {
	try {
	    this.sock = new ServerSocket(0);
	    this.text = text;
	    InetAddress i = getLocalHost();
	    System.err.println(i + ":" + geefPort());
	    this.s = new SocketData(sock);

	    for (Socket a : s.sockets) {
		System.err.println(a.getInetAddress());
	    }

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
}
