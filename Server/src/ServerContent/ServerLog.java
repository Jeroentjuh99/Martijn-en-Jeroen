/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerContent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author jeroen
 */
public class ServerLog {

    private PrintWriter writer = null;

    public ServerLog() throws FileNotFoundException{
	File file = new File("ServerLog " + new Date() + ".txt");
	System.out.println(file);
	this.writer = new PrintWriter(file);
    }

    public void addText(String text) {
	writer.println(text);
    }

    public void closeFile() {
	writer.close();
    }
}
