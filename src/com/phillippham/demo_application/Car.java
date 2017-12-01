package com.phillippham.demo_application;

import java.io.Serializable;

public class Car implements Serializable
{
	// Attributes
	private String make, model;
	private int year, topSpeed;
	
	// Constructors
	public Car()
	{
		this.make = "N/A";
		this.model = "N/A";
		this.year = 0;
		this.topSpeed = 0;
	}

	public Car(String make, String model, int year, int topSpeed)
	{
		this.make = make;
		this.model = model;
		this.year = year;
		this.topSpeed = topSpeed;
	}

	// Accessors
	public String getMake()
	{
		return make;
	}

	public String getModel()
	{
		return model;
	}

	public int getYear()
	{
		return year;
	}

	public int getTopSpeed()
	{
		return topSpeed;
	}

	// Mutators
	public void setMake(String make)
	{
		this.make = make;
	}

	public void setModel(String model)
	{
		this.model = model;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public void setTopSpeed(int topSpeed)
	{
		this.topSpeed = topSpeed;
	}

	// Behaviors
	public String toString()
	{
		return "Car [Make: " + make + ", Model: " + model + ", Year: " + year + ", Top Speed: " + topSpeed + "]";
	}
}