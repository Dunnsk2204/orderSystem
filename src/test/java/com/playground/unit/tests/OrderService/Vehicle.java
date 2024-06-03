package com.playground.unit.tests.OrderService;

public abstract class Vehicle {
	
	public void turnKey() {
		System.out.println("Key Turned.");
	}
	
	public void hasWheels() {
		System.out.println("YES");
	}
	
	public void drivesMoreThan1Mph() {
		System.out.println("Driving more than 1 mph");
	}
	
	protected abstract void FuelType();
	
	protected abstract void EngineSize();
}
