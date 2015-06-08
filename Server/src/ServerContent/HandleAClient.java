/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerContent;

import java.net.*;

/**
 *
 * @author jeroen
 */
public class HandleAClient {

    private Socket socket;
    
    public HandleAClient(Socket socket) {
	this.socket = socket;
	
    }
}
