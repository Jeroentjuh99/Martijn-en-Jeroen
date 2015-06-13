package ClientLaunch;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Recursiegame {

    public static void main(String[] a) {
	new recursiegame1();
    }
}

class recursiegame1 extends JFrame {

    private int p;
    private boolean foundPath;
    ArrayList<JButton> buttons = new ArrayList<>();
    private int clicks;

    public recursiegame1() {
	super("Maze");
	clicks = 0;
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setSize(500, 500);

	JPanel contentPane = new JPanel(new BorderLayout());
	JPanel centerPane = new JPanel(new GridLayout(8, 8));
	JPanel bottomPane = new JPanel(new FlowLayout());
	setContentPane(contentPane);
	contentPane.add(bottomPane, BorderLayout.SOUTH);
	contentPane.add(centerPane, BorderLayout.CENTER);
	JLabel amountOfClicks;
	amountOfClicks = new JLabel(clicks + " Clicks ");
	JButton path = new JButton();
	path.setText("Find Path");
	JButton clear = new JButton();
	clear.setText("Clear Path");
	bottomPane.add(path);
	bottomPane.add(clear);
	path.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		foundPath = false;
		if (pepaalPath(0, 0, 7, 7)) {
		    JOptionPane.showMessageDialog(null, "Path Found");
		} else {
		    JOptionPane.showMessageDialog(null, "Path not Found");
		}
	    }
	});
	clear.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		for (int i = 0; i < buttons.size(); i++) {
		    buttons.get(i).setBackground(null);
		    buttons.get(i).setText(null);
		    p = 0;
		}
	    }
	});
	for (int y = 0; y < 8; y++) {
	    ArrayList<JButton> row = new ArrayList<JButton>();
	    for (int x = 0; x < 8; x++) {
		JButton b = new JButton();
		b.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
			JButton c = (JButton) event.getSource();
			if (c.getText() == "x") {
			    c.setText("");
			} else {
			    c.setText("x");
			}

		    }
		});
		centerPane.add(b);
		buttons.add(b);
	    }

	}
	setVisible(true);
    }

    public Boolean pepaalPath(int bCol, int bRow, int eCol, int eRow) {
	p++;
	buttons.get((bRow * 8) + bCol).setBackground(Color.BLACK);
	buttons.get((bRow * 8) + bCol).setText(String.valueOf(p));
	if (bCol == eCol && bRow == eRow) {
	    foundPath = true;
	    return foundPath;
	} else if (bRow < 7 && (buttons.get(((bRow + 1) * 8) + bCol)).getText() != "x" && buttons.get(((bRow + 1) * 8) + bCol).getBackground() != Color.BLACK) {
	    pepaalPath(bCol, bRow + 1, eCol, eRow);
	} else if (bCol < 7 && (buttons.get((bRow * 8) + bCol + 1)).getText() != "x" && buttons.get((bRow * 8) + bCol + 1).getBackground() != Color.BLACK) {
	    pepaalPath(bCol + 1, bRow, eCol, eRow);
	} else if (bRow > 0 && (buttons.get(((bRow - 1) * 8) + bCol)).getText() != "x" && buttons.get(((bRow - 1) * 8) + bCol).getBackground() != Color.BLACK) {
	    pepaalPath(bCol, bRow - 1, eCol, eRow);
	} else if (bCol > 0 && (buttons.get((bRow * 8) + bCol - 1)).getText() != "x" && buttons.get((bRow * 8) + bCol - 1).getBackground() != Color.BLACK) {
	    pepaalPath(bCol - 1, bRow, eCol, eRow);
	} else if (bCol > 0 && buttons.get(bCol - 1 + (bRow * 8)).getBackground() == Color.BLACK) {
	    buttons.get(bCol - 1 + (bRow * 8)).setBackground(null);
	    pepaalPath(bCol - 1, bRow, eCol, eRow);
	} else if (bRow > 0 && buttons.get(bCol + (bRow - 1) * 8).getBackground() == Color.BLACK) {
	    buttons.get(bCol + (bRow - 1) * 8).setBackground(null);
	    pepaalPath(bCol, bRow - 1, eCol, eRow);
	} else {
	    foundPath = false;
	}
	return foundPath;

    }
}
