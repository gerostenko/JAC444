package jac444.wk5;

import java.io.Serializable;

public class Student implements Serializable {
	private static final long serialVersionUID = 8781946609486113016L;
	private static Integer idGenerator = 99;
	private Integer id;
	private String name;
	private String course;
	private Integer grade;
	
	/**
	 * Constructor
	 * @param _name
	 * @param _course
	 * @param _grade
	 */
	public Student(String _name, String _course, Integer _grade) {
		this.id = idGenerator++;
		this.name = _name;
		this.course = _course;
		this.grade = _grade;
	}
	
	/**
	 * Method to update the idGenerator value
	 * @param lastIdUsed
	 */
	public static void seedIdGenerator(Integer lastIdUsed) {
		idGenerator = lastIdUsed+1;
	}
	
	/**
	 * @return id
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return course
	 */
	public String getCourse() {
		return this.course;
	}
	
	/**
	 * @return grade
	 */
	public Integer getGrade() {
		return this.grade;
	}
	
	/**
	 * @param _name
	 * set name
	 */
	public void setName(String _name) {
		this.name = _name;
	}
	
	/**
	 * @param _course
	 * set course
	 */
	public void setCourse (String _course) {
		this.course = _course;
	}
	
	/**
	 * @param _grade
	 * @return
	 * set grade and return response if grade was set
	 */
	public boolean setGrade(Integer _grade) {
		if (_grade >=0 && _grade <= 100) {
			this.grade = _grade;
			return true;
		}
		else {
			return false;
		}
	}
}
