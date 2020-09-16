package homework;

import java.util.ArrayList;
import java.util.Date;

public class Student {
	private String id;
	private String name;
	private String department;
	private String departmentId;
	private ArrayList<String> course;
	
	public Student(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getname() {
		return name;
	}
	
	public String getid() {
		return  id;
	}
	
	public void setdepartment(String department) {
		this.department = department;
	}
	
	public String getdepartment() {
		return department;
	}
	public void setdepartmentId(String department) {
		this.departmentId = departmentId;
	}
	
	public String getdepartmentId() {
		return  department;
	}
	
	public void setcourse(ArrayList<String> course) {
		this.course = course;
	}
	
	public ArrayList<String> getcourse() {
		return  course;
	}
	public String toString() {
		String ans = "{";
		
		for (int i = 0; i < course.size(); i++) {
			ans += course.get(i);				
			if (i == course.size() - 1) {
				ans += "}";
				break;
			}
			ans += ",";			
		}		
		return ans;
	}
}
