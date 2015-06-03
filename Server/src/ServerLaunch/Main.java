/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerLaunch;

import ServerContent.ServerContent;
import java.awt.*;
import javax.swing.*;


//JTextarea ff naar kijken voor server text
//of: http://docs.oracle.com/javase/tutorial/uiswing/components/editorpane.html

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
	
	JTextArea text = new JTextArea();
	text.setEditable(false);
	text.setWrapStyleWord(true);
	text.setLineWrap(true);
	text.setBackground(Color.PINK);
	text.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
	
	ServerContent s = new ServerContent(text);
	text.setText("Jeroen is supercool :D");
	JScrollPane pane = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	add(pane, BorderLayout.CENTER);
	
	setResizable(false);
	setVisible(true);
    }
}
