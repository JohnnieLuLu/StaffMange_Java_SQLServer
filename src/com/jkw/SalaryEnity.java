package com.jkw;

public class SalaryEnity {

	private String ID;
	private int year;
	private int month;
	private int baseSalary;
	private int bonus;
	private int attendance_deduct;
	private double tax_deduct;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(int baseSalary) {
		this.baseSalary = baseSalary;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public int getAttendance_deduct() {
		return attendance_deduct;
	}
	public void setAttendance_deduct(int attendance_deduct) {
		this.attendance_deduct = attendance_deduct;
	}
	public double getTax_deduct() {
		return tax_deduct;
	}
	public void setTax_deduct(double tax_deduct) {
		this.tax_deduct = tax_deduct;
	}
	public double getActual_salary() {
		return actual_salary;
	}
	public void setActual_salary(double actual_salary) {
		this.actual_salary = actual_salary;
	}
	private double actual_salary;

}
