/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerLaunch;

import ServerContent.ServerContent;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author jeroen
 */
public class Main extends JFrame {

    public static void main(String[] args) {
	Main m = new Main();
    }
    
    public Main(){
	super("Chatservice Server");
	setLayout(new BorderLayout());
	setSize(720,600);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	
	ServerContent s = new ServerContent();
	s.setBackground(Color.BLACK);
	JScrollPane pane = new JScrollPane(s, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	add(pane, BorderLayout.CENTER);
	
	setResizable(false);
	setVisible(true);
    }
}
