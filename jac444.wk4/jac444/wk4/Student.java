package jac444.wk4;

import java.io.Serializable;

public class Student implements Serializable {
	private static final long serialVersionUID = 8781946609486113016L;
	private String name;
	private String course;
	private int grade;
	
	public Student(String _name, String _course, int _grade) {
		this.name = _name;
		this.course = _course;
		this.grade = _grade;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getCourse() {
		return this.course;
	}
	
	public int getGrade() {
		return this.grade;
	}
	
	public void setName(String _name) {
		this.name = _name;
	}
	
	public void setCourse (String _course) {
		this.course = _course;
	}
	
	public boolean setGrade(int _grade) {
		if (_grade >=0 && _grade <= 100) {
			this.grade = _grade;
			return true;
		}
		else {
			return false;
		}
	}
}
