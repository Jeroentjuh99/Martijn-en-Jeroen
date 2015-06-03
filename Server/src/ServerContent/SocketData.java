/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerContent;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author jeroen
 */
public class SocketData implements Runnable {

    protected ArrayList<Socket> sockets;
    private ServerSocket sock = null;
    protected ArrayList<DataInputStream> inputstreams = null;
    protected ArrayList<DataOutputStream> outputstreams = null;

    public SocketData(ServerSocket sock) {
	this.sockets = new ArrayList();
	this.inputstreams = new ArrayList();
	this.outputstreams = new ArrayList();
	this.sock = sock;
	Thread tr = new Thread(this);
	tr.start();
    }

    @Override
    public void run() {
	while (true) {

	    try {
		Socket socket = sock.accept();
		sockets.add(socket);
		inputstreams.add(new DataInputStream(socket.getInputStream()));
		outputstreams.add(new DataOutputStream(socket.getOutputStream()));

		Thread.sleep((long) 1000);
	    } catch (Exception ex) {
		Logger.getLogger(SocketData.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }

}
