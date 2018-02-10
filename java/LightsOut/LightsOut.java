/**
 * This is a simple game built with java
 * Toggle all the yellow buttons to black to solve the puzzle
 * 
 * @author Bob Schmitz
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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
		lo.setTitle("Lights Out!");
		lo.pack();
		lo.setVisible(true);
		JOptionPane.showMessageDialog(null, "Toggle all the yellow buttons to black to solve the puzzle");
	}
	// This is a helper method for toggleLights
	private void checkToggle(int i) {
		// Toggle index light
		if (grid[i]) {
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
		if (i > 4) checkToggle(i-5);
		// Toggle one button down
		if (i < 20) checkToggle(i+5);
		// Toggle one button to the left
		if (i%5 > 0) checkToggle(i-1);
		// Toggle one button to the right
		if (i%5 < 4) checkToggle(i+1);
	}
	// Check if game is complete, reset board if complete
	private void checkDone() {
		if (!checkDone(0)) return;
		JOptionPane.showMessageDialog(null, "You Win!");
		randomize();
	}
	// checkDone helper
	private boolean checkDone(int i) { 
		if (grid[i]) return false;
		if (i+1 == grid.length) return true;
		return checkDone(i+1);
	}
	// Reset board and randomize it
	private void randomize() {
		for (int i=0; i < button.length; i++) {
			grid[i]=false;
			button[i].setBackground(Color.BLACK);
		}
		// Toggle random buttons
		randomize(6);
		// If puzzle solved after randomizing, redo
		if (checkDone(0)) randomize();
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
			button[i] = new JButton();
			button[i].setPreferredSize(new Dimension(50, 50));
			button[i].putClientProperty("index", i);
			button[i].addActionListener(listener);
			pane.add(button[i]);
		}
		randomize();
	}
}
