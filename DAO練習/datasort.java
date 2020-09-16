package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

public class datasort {

	static IDAO<Student> sDAO = new StudentDAO();
	static IDAO<Student> scDAO = new StudentCourseDAO();
	static IDAO<Student> cDAO = new CourseDAO();
	static IDAO<Student> dDAO = new DeptDAO();
	static IDAO<Student> rDAO = new ResultDAO();
	
	public static void courseData(String[] coursedata) {
		for (String line:coursedata) {  //抓檔案的每一行
			System.out.println(line);
			String [] data = line.split(",");
			Student student = new Student();  //建立Object
			student.setcourseID(data[0]) ;
			student.setcoursename(data[1]) ;
			cDAO.insert(student);
		}
	}
	
	public static void deptData(String[] deptdata) {
		for (String line:deptdata) {  //抓檔案的每一行
			System.out.println(line);
			String [] data = line.split(",");
			Student student = new Student();  //建立Object
			student.setdeptID(data[0]);
			student.setdeptName(data[1]);
			
			dDAO.insert(student);
		}
	}
	
	public static void studentCourseData(String[] studentcoursedata) {
		for (String line:studentcoursedata) {  //抓檔案的每一行
			System.out.println(line);
			String [] data = line.split(",");
			Student student = new Student();  //建立Object
			student.setId(data[0]);
			student.setcourseID(data[1]);
			
			scDAO.insert(student);
		}
	}
	
	public static void studentData(String[] studentdata) {
		for (String line:studentdata) {  //抓檔案的每一行
			System.out.println(line);
			String [] data = line.split(",");
			Student student = new Student();  //建立Object
			student.setId(data[0]);
			student.setName(data[1]);
			student.setBirthdate(data[2]);
	        sDAO.insert(student);
		}
	}
	
	public static void result(ResultSet resultSet) {
		try {
			boolean s = resultSet.next(); //直接先取第一行
//			System.out.println(resultSet.getString("courseName"));
			String course = resultSet.getString("courseName");
		    String name = resultSet.getString("name");
		    String deptName = resultSet.getString("deptName");
		    String id = resultSet.getString("studentID");

		    while (resultSet.next()) {
		    	if(resultSet.getString("studentID").contentEquals(id)) {  //用ID來檢查是否同一人
		      		course += "," + resultSet.getString("courseName");
		      		continue;
		      	}
		      	else {
		      		Student student = new Student(id,name);  //建立學生後新增到table
		      		student.setdeptName(deptName);
		      		student.setcourse(course) ;
		      		rDAO.insert(student);
		      		course = resultSet.getString("courseName");
		            name = resultSet.getString("name");
		            deptName = resultSet.getString("deptName");
		            id = resultSet.getString("studentID");
		      	}
			}
		    Student student = new Student(id,name); //新增最後一筆資料
		    student.setdeptName(deptName);
      		student.setcourse(course) ;
			rDAO.insert(student);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	
}
