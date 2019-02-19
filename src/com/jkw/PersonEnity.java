package com.jkw;

import java.util.Date;

public class PersonEnity {
	private String ID;
	private String name;
	private String sex;
	private Date birthDay;
	private int hireDay_year;
	private int hireDay_mon;
	private String professor;
	private String deptName;
	
	public int getHireDay_year() {
		return hireDay_year;
	}
	public void setHireDay_year(int hireDay_year) {
		this.hireDay_year = hireDay_year;
	}
	public int getHireDay_mon() {
		return hireDay_mon;
	}
	public void setHireDay_mon(int hireDay_mon) {
		this.hireDay_mon = hireDay_mon;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
