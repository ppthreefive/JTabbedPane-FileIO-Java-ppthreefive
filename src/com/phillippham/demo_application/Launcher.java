package com.phillippham.demo_application;

import javax.swing.*;

public class Launcher
{
	public static void main(String[] args)
	{
		// Launch application thread
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				// Launch the application
				CarBizarre app = new CarBizarre();
				app.setTitle("Car Bizzare Application");
				app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				app.setSize(800,600);
				app.setLocationRelativeTo(null);
				app.setVisible(true);
			}
		});
	}
}