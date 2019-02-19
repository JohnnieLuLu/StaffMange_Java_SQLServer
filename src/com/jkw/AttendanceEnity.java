package com.jkw;

public class AttendanceEnity {

	private String ID;
	private int year;
	private int mon;
	private int lateNum;
	private int earlyNum;
	private int abNum;
	private int holiNum;
	private float deduct;
	public AttendanceEnity() {
		// TODO Auto-generated constructor stub
	}
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
	public int getMon() {
		return mon;
	}
	public void setMon(int mon) {
		this.mon = mon;
	}
	public int getLateNum() {
		return lateNum;
	}
	public void setLateNum(int lateNum) {
		this.lateNum = lateNum;
	}
	public int getEarlyNum() {
		return earlyNum;
	}
	public void setEarlyNum(int earlyNum) {
		this.earlyNum = earlyNum;
	}
	public int getAbNum() {
		return abNum;
	}
	public void setAbNum(int abNum) {
		this.abNum = abNum;
	}
	public int getHoliNum() {
		return holiNum;
	}
	public void setHoliNum(int holiNum) {
		this.holiNum = holiNum;
	}
	public float getDeduct() {
		return deduct;
	}
	public void setDeduct(float deduct) {
		this.deduct = deduct;
	}

}
