/**
 * This is a simple game built with java
 * To win, toggle all the buttons to yellow
 * 
 * @author Bob Schmitz
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class LightsOut extends JFrame {
	// Create button array and boolean array to track button changes
	private JButton[] button=new JButton[25];
	private boolean[] grid=new boolean[25];

	// Initialize lightsOut
	public static void main(String[] args) {
		int rows = 5;
		int cols = 5;
		LightsOut lo = new LightsOut(rows, cols);
		lo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lo.pack();
		lo.setVisible(true);
	}
	// This is a helper method for toggleLights
	private void checkToggle(int i) {
		// Toggle index light
		if (grid[i] == true) {
			grid[i]=false;
			button[i].setBackground(Color.BLACK);
		} else {
			grid[i]=true;
			button[i].setBackground(Color.YELLOW);
		}
	}
	// Toggle button and all adjacent buttons
	private void toggleLights(int i) {
		// Toggle selected button
		checkToggle(i);
		// Toggle one button up
		if (i > 5) checkToggle(i-5);
		// Toggle one button down
		if (i < 20) checkToggle(i+5);
		// Toggle one button to the left
		if (i%5 > 0) checkToggle(i-1);
		// Toggle one button to the right
		if (i%5 < 4) checkToggle(i+1);
	}
	// Check if game is complete, reset board if complete
	private void checkDone() {
		if (checkDone(0, button.length) == false) return;
		JOptionPane.showMessageDialog(null, "You Win!");
		randomize();
	}
	// checkDone helper
	private boolean checkDone(int i, int length) { 
		if (!grid[i]) return false;
		if (i+1 == length) return true;
		return checkDone(i+1, length);
	}
	// Reset board and randomize it
	private void randomize() {
		for (int i=0; i < button.length; i++) {
			grid[i]=true;
			button[i].setBackground(Color.YELLOW);
		}
		randomize(5);
	}
	// Helper method for randomize
	private void randomize(int count) {
		if (count == 0) return;
		toggleLights((int)(Math.random() * 25));
		randomize(count-1);
	}
	// Construct window and button grid
	private LightsOut(int rows, int cols) {
		// Create action listner to detect button click
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = (int)((JButton)e.getSource()).getClientProperty("index");
				toggleLights(index);
				checkDone();
			}
		};

		Container pane = getContentPane();
		pane.setLayout(new GridLayout(rows, cols));
		for (int i=0; i < button.length; i++) {
			button[i] = new JButton(" ");
			button[i].putClientProperty("index", i);
			button[i].addActionListener(listener);
			pane.add(button[i]);
		}
		randomize();
	}
}
