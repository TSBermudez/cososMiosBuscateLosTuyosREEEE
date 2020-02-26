package com.orange.reflex;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import com.alee.laf.WebLookAndFeel;
import com.alee.skin.dark.WebDarkSkin;
import com.orange.myPanel.MyPanel;
import com.orange.util.Constants;

/**
 * Hello warudo!
 *
 */
public class App {

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				WebLookAndFeel.install(WebDarkSkin.class);
				JFrame frame = new JFrame(Constants.PANEL);
				frame.setUndecorated(true);
	            frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
	            frame.setResizable(false);
				frame.setTitle(Constants.PANEL_NAME);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(new MyPanel());
				frame.pack();		
				frame.setVisible(true);				
			}
		});
	}

}
