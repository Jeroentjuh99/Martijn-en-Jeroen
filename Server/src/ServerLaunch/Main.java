/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerLaunch;

import ServerContent.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author jeroen
 */
public class Main extends JFrame {

    private final Color textColor = Color.GREEN;
    private ServerContent s = null;

    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		Main main = new Main();
	    }
	});

    }

    public Main() {
	super("Chatservice Server");
	setLayout(new BorderLayout());
	setSize(720, 600);
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	JTextArea textOut = setTextArea(new JTextArea());
	JTextField textIn = setTextField(new JTextField());
	JButton button = new JButton();
	button.addActionListener(new buttonListener(textIn));
	getRootPane().setDefaultButton(button);
	this.s = new ServerContent(textOut, textIn);
	JScrollPane pane = new JScrollPane(textOut, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	button.setVisible(false);

	add(pane, BorderLayout.CENTER);
	add(textIn, BorderLayout.SOUTH);
	add(button, BorderLayout.NORTH);

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
	textIn.setCaretColor(textColor);
	textIn.requestFocus();
	return textIn;
    }

    private class buttonListener implements ActionListener {

	private final JTextField textIn;

	public buttonListener(JTextField textIn) {
	    this.textIn = textIn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    String a = textIn.getText();
	    s.logText(a);
	    if (a.equalsIgnoreCase("/shutdown")) {
		s.sluitLogger();
		System.exit(0);
	    } else if (a.equalsIgnoreCase("/ip")) {
		s.showTextFromCommand("ip");
	    }

	    textIn.setText(null);
	}
    }
}
