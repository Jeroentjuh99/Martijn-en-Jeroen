/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerContent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jeroen
 */
public class ServerLogOld {

    private PrintWriter writer = null;
    public static SimpleDateFormat format = new SimpleDateFormat("d-M-yy HH.mm.ss");

    public ServerLogOld() throws FileNotFoundException, IOException {
	Date date = new Date();
	File file = new File("Log//ServerLog " + format.format(date) + ".txt");
	file.createNewFile();
	this.writer = new PrintWriter(file);
    }

    public void addText(String text) {
	if (!(writer == null)) {
	    writer.println(text);
	    writer.flush();
	}
    }

    public void closeFile() {
	if (!(writer == null)) {
	    Date d = new Date();
	    addText("Server shutdown on: " + format.format(d));
	    writer.close();
	}
    }
}
