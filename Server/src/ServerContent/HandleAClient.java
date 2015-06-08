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

/**
 *
 * @author jeroen
 */
public class HandleAClient implements ActionListener{

    private final Socket socket;
    private final DataInputStream inputfromClient;
    private final DataOutputStream outputToClient;

    public HandleAClient(Socket socket) throws IOException {
	this.socket = socket;
	this.inputfromClient = new DataInputStream(socket.getInputStream());
	this.outputToClient = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	
    }
    
    public void checkAlive(){
	
    }
}
