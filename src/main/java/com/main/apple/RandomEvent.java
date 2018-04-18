package com.main.apple;

import java.awt.*;
import java.awt.event.*;

/**
 * @author ashay
 *
 */
class RandomEvent extends Frame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Event event;

	RandomEvent() {

		// button to trigger events
		Button b = new Button("Generate event");
		b.setBounds(100, 100, 120, 40);

		// register listener
		b.addActionListener(this);

		// add button and set size, layout and visibility
		add(b);
		setSize(300, 300);
		setLayout(null);
		setVisible(true);

		event = new Event();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		int eventValue = (int) (Math.random() * 10);
		event.registerEvent(eventValue);

	}

}