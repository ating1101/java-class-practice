package dao;

import java.util.Date;

public class Student {
	String id;
	String name;
	String birthdate;
	String courseID;
	String coursename;
	String deptID;
	String deptName;
	String course;
	
	public Student() {
	}	
	public Student(String id, String name) {
		this.id = id;
		this.name = name;
	}	
	public Student(String id, String name, String birthdate, String courseName) {
		//super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
		this.coursename = courseName;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setcourseID(String courseID) {
		this.courseID = courseID; 
	}
	public String getcourseID() {
		return courseID;
	}
	public void setcoursename(String coursename) {
		this.coursename = coursename; 
	}
	public String getcoursename() {
		return coursename;
	}
	public void setdeptID(String deptID) {
		this.deptID = deptID; 
	}
	public 	String getdeptID() {
		return deptID;
	}
	public void setdeptName(String deptName) {
		this.deptName = deptName; 
	}
	public String getdeptName() {
		return deptName;
	}
	public void setcourse(String course) {
		this.course = course;
	};
	public String getcourse() {
		return course;
	};
}
