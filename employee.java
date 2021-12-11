package baitapOOP;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class employee extends person {
	protected double salary;
	protected double rate;
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public employee() {
		
	}
	public employee(String name, Date age, double salary, double rate) {
		super(name, age);
		this.salary = salary;
		this.rate = rate;
	}
	public double calsalary() {
		return this.rate*this.salary;
	}
	public void inputemployee() throws ParseException {
		Scanner sc = new Scanner(System.in);
		super.input();
	
		System.out.println(" enter salary: ");
		this.salary = Double.parseDouble(sc.nextLine());
		System.out.println(" enter rate: ");
		this.rate = Double.parseDouble(sc.nextLine());
	}
	public void outputemployee() {
		super.output();
		System.out.println( "has salary: "+this.getSalary() + " has rate: "+this.getRate()+ " has calsalary :"+this.calsalary());
	}


}
