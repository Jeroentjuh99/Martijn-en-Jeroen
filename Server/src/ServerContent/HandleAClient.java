/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerContent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author jeroen
 */
public class HandleAClient implements ActionListener {

    private final Socket socket;
    private final DataInputStream inputfromClient;
    private final DataOutputStream outputToClient;
    private Timer t = null;
    private byte ticks = 0;

    public HandleAClient(Socket socket) throws IOException {
	this.socket = socket;
	this.inputfromClient = new DataInputStream(socket.getInputStream());
	this.outputToClient = new DataOutputStream(socket.getOutputStream());
	this.t = new Timer(1000, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	try {
	    boolean alive = false;
	    if (ticks <= 5 && !alive) {
		alive = readIsAlive();
		ticks++;
	    } else {
		t.stop();
		this.t = new Timer(1000, this);
	    }
	} catch (IOException ex) {
	    ticks = 0;
	    ex.printStackTrace();
	}
    }

    public void checkAlive() throws IOException {
	outputToClient.writeUTF("/isAlive");
	ticks = 0;
	t.start();
    }

    private boolean readIsAlive() throws IOException {
	return inputfromClient.readBoolean();
    }
}
