package com.phillippham.demo_application;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class CarBizarre extends JFrame implements ActionListener
{
	// Class level references
	private ArrayList<Car> list = new ArrayList<Car>();
	private JMenuBar jmbMain;
	private JMenu jmuFile;
	private JMenuItem jmiExit, jmiSave, jmiOpen;
	private JTabbedPane jtpMain;
	private JTextField txfMake, txfModel, txfYear, txfTopSpeed;
	private JButton btnAdd, btnDisplay;
	private JTextArea txaOutput;
	
	// Constructor
	public CarBizarre()
	{
		this.jmbMain = new JMenuBar();
		this.jmuFile = new JMenu("File");
		this.jmiExit = new JMenuItem("Exit");
		this.jmiExit.addActionListener(this);
		this.jmiExit.setMnemonic('x');
		
		this.jmiSave = new JMenuItem("Save");
		this.jmiSave.addActionListener(this);
		this.jmiSave.setMnemonic('s');
		
		this.jmiOpen = new JMenuItem("Open");
		this.jmiOpen.addActionListener(this);
		this.jmiOpen.setMnemonic('o');
		
		this.jmuFile.add(this.jmiSave);
		this.jmuFile.add(jmiOpen);
		this.jmuFile.addSeparator();
		this.jmuFile.add(this.jmiExit);
		
		
		this.jmbMain.add(this.jmuFile);
		this.setJMenuBar(this.jmbMain);
		
		// Tabbed panel
		this.jtpMain = new JTabbedPane();
		
		// First panel
		JPanel pnlFirst = new JPanel(new GridLayout(5,2,10,10));
		JLabel lblMake = new JLabel("Make:");
		lblMake.setHorizontalAlignment(JLabel.CENTER);
		this.txfMake = new JTextField();
		
		JLabel lblModel = new JLabel("Model:");
		lblModel.setHorizontalAlignment(JLabel.CENTER);
		this.txfModel = new JTextField();
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setHorizontalAlignment(JLabel.CENTER);
		this.txfYear = new JTextField();
		
		JLabel lblTopSpeed = new JLabel("Top Speed:");
		lblTopSpeed.setHorizontalAlignment(JLabel.CENTER);
		this.txfTopSpeed = new JTextField();
		
		this.btnAdd = new JButton("Submit");
		this.btnAdd.addActionListener(this);
		
		pnlFirst.add(lblMake);
		pnlFirst.add(this.txfMake);
		pnlFirst.add(lblModel);
		pnlFirst.add(this.txfModel);
		pnlFirst.add(lblYear);
		pnlFirst.add(this.txfYear);
		pnlFirst.add(lblTopSpeed);
		pnlFirst.add(this.txfTopSpeed);
		pnlFirst.add(this.btnAdd);
		
		// Second panel
		JPanel pnlSecond = new JPanel(new BorderLayout());
		btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(this);
		txaOutput = new JTextArea();
		JScrollPane jspScroller = new JScrollPane(txaOutput);
		
		pnlSecond.add(btnDisplay, BorderLayout.NORTH);
		pnlSecond.add(jspScroller);
		
		// Add the panels to the tabbed pane as tabbed items
		this.jtpMain.addTab("Add", pnlFirst);
		this.jtpMain.addTab("Display", pnlSecond);
		
		// Add the tabbed pane to the JFrame
		this.add(this.jtpMain);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		// Run code based on item triggered
		if(e.getSource() == this.jmiExit)
		{
			// Close program
			System.exit(0);
		}
		else if(e.getSource() == this.jmiSave)
		{
			// Save the list to the file
			ObjectOutputStream oo = null;
			
			try
			{
				oo = new ObjectOutputStream(new FileOutputStream("Cars.txt"));
				oo.writeObject(this.list);
				
				JOptionPane.showMessageDialog(this, "Inventory has been saved to the file.");
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				try
				{
					oo.close();
				}
				catch(IOException ex){}
			}
		}
		else if(e.getSource() == this.jmiOpen)
		{
			// Get data from the file
			ObjectInputStream oi = null;
			
			try
			{
				oi = new ObjectInputStream(new FileInputStream("Cars.txt"));
				this.list = (ArrayList<Car>)(oi.readObject());
				
				JOptionPane.showMessageDialog(this, "Inventory loaded to application.");
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			} catch (ClassNotFoundException ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				try
				{
					oi.close();
				}
				catch(IOException ex){}
			}
		}
		else if(e.getSource() == this.btnAdd)
		{
			// Create and load a Car using form input from first panel
			Car c1 = new Car();
			c1.setMake(this.txfMake.getText());
			c1.setModel(this.txfModel.getText());
			c1.setYear(Integer.parseInt(this.txfYear.getText()));
			c1.setTopSpeed(Integer.parseInt(this.txfTopSpeed.getText()));
			
			// Add the Car to our data structure
			this.list.add(c1);
			
			// Clear the list and set up for the next car
			this.txfMake.setText("");
			this.txfModel.setText("");
			this.txfYear.setText("");
			this.txfTopSpeed.setText("");
			this.txfMake.requestFocus();
			
			// Tell user that the Car was added to the data structure
			JOptionPane.showMessageDialog(this, c1.getMake() + " was added to inventory.");
		}
		else if(e.getSource() == this.btnDisplay)
		{
			// Display every car in the car data structure
			txaOutput.setText("Car List: \n\n");
			
			for (Car car : list)
			{
				this.txaOutput.append(car + "\n");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "<ERROR: INVALID EVENT>");
		}
	}
}