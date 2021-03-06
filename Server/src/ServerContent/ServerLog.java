/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerContent;

import ServerLaunch.Main;
import java.io.*;
import java.util.Date;

/**
 *
 * @author jeroen
 */
public class ServerLog {

    private PrintWriter writer = null;

    public ServerLog() throws FileNotFoundException, IOException {
	File file = new File("Log//ServerLog " + Main.format.format(new Date()) + ".txt");
	file.createNewFile();
	this.writer = new PrintWriter(file);
	addText("Server started on " + Main.format.format(new Date()));
    }

    public void addText(String text) {
	if (!(writer == null)) {
	    writer.println(text + "\n");
	    writer.flush();
	}
    }

    public void closeFile() {
	if (!(writer == null)) {
	    addText("Server shutdown on: " + Main.format.format(new Date()));
	    writer.close();
	}
    }
}
