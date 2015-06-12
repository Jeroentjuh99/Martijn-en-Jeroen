/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientLaunch;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author jeroen
 */
public class Main2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		Main2 main = new Main2();
	    }
	});
    }
    private String gebruikersnaam;
    
    public void Main2(){
	JTextField gebruikersnaam = new JTextField();
	JTextField ipadres = new JTextField();
	Object[] objects = {"Gebruikersnaam: ", gebruikersnaam, "IP: ", ipadres};
	int option = JOptionPane.showConfirmDialog(null, objects, "Login", JOptionPane.OK_CANCEL_OPTION);
	if(option == JOptionPane.OK_OPTION){
	    this.gebruikersnaam = gebruikersnaam.getText();
	    
	}
    }	
}
