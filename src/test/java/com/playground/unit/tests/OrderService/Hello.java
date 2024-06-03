package com.playground.unit.tests.OrderService;

public class Hello {

	public static void main(String[] args) {
//		System.out.println(reverseString("Hello World"));
//		System.out.println(isPalindrome("racecar"));
//		System.out.println(isPalindrome("infotools"));
		
		Vehicle vehicle = new Car();
		vehicle.turnKey();
		vehicle.FuelType();
		vehicle.EngineSize();
		vehicle.drivesMoreThan1Mph();
		vehicle.hasWheels();
	}
}
