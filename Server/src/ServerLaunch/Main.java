/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerLaunch;

import ServerContent.*;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author jeroen
 */
public class Main extends JFrame {

    private final Color textColor = Color.GREEN;

    public static void main(String[] args) {
	Main m = new Main();
    }

    public Main() {
	super("Chatservice Server");
	setLayout(new BorderLayout());
	setSize(720, 600);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JTextArea textOut = setTextArea(new JTextArea());
	JTextField textIn = setTextField(new JTextField());

	ServerContent s = new ServerContent(textOut, textIn);
	JScrollPane pane = new JScrollPane(textOut, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	add(pane, BorderLayout.CENTER);

	setResizable(false);
	setVisible(true);
    }

    private JTextArea setTextArea(JTextArea textOut) {
	textOut.setEditable(false);
	textOut.setWrapStyleWord(true);
	textOut.setLineWrap(true);
	textOut.setBackground(Color.BLACK);
	textOut.setFont(new Font("Lucida Console", Font.BOLD, 13));
	textOut.setForeground(textColor);
	return textOut;
    }
    
    private JTextField setTextField(JTextField textIn) {
	textIn.setBackground(Color.BLACK);
	textIn.setFont(new Font("Lucida Console", Font.BOLD, 13));
	textIn.setForeground(textColor);
	return textIn;
    }
}
