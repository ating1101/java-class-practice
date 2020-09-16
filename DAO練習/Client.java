package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.mysql.cj.jdbc.DatabaseMetaData;

import dao.Student;

public class Client {
	static IDAO<Student> sDAO = new StudentDAO();
	static IDAO<Student> scDAO = new StudentCourseDAO();
	static IDAO<Student> cDAO = new CourseDAO();
	static IDAO<Student> dDAO = new DeptDAO();
	static IDAO<Student> rDAO = new ResultDAO();

	public static void main(String[] args) {

		//CourseData
		String [] tempcoursedata = Readfile.readFile("CourseData"); // 讀coursedata
//		datasort.courseData(tempcoursedata);  //寫入coursedata
		
		//DeptData
		String [] tempdeptdata = Readfile.readFile("DeptData"); // 讀deptdata
//		datasort.deptData(tempdeptdata);  //寫入deptdata
		
		//StudentCourseData
		String [] tempstudentcoursedata = Readfile.readFile("StudentCourseData");  //讀studentcoursedata
//		datasort.studentCourseData(tempstudentcoursedata);  //寫入studentcoursedata
     
		//StudentData
        String [] tempstudentdata = Readfile.readFile("StudentData");  //讀studentdata
//        datasort.studentData(tempstudentdata);  //寫入studentdata
		
		//合併表格
		String sql = "SELECT sd.studentID , sd.name , dd.deptName ,  cd.courseName"+
				" FROM courseData cd , departmentData dd , studentCourseData scd , studentData sd"+
				" WHERE substring(sd.studentID, 1, 2) = dd.deptID && sd.studentID=scd.studentID && scd.courseId = cd.courseId"+
				" order by sd.studentID"; // SQL語法
		
		//用GROUP_CONCAT可以直接去除重複的資料，只留不同的
//		String sql = "SELECT studentData.studentID , studentData.name , departmentData.deptName,GROUP_CONCAT(courseData.courseName) as courseName FROM studentData , departmentData , courseData  ,studentCourseData WHERE substring(studentData.studentID, 1, 2) = departmentData.deptID && studentData.studentID=studentCourseData.studentId && studentCourseData.courseId = courseData.courseId group by studentData.studentID;";

		Connection conn = MySQL.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql); 
			ResultSet resultSet = preparedStatement.executeQuery();  //合併表格查詢
//            datasort.result(resultSet); //建立表格
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		//查詢表格
		
//		sDAO.getAll();
//		scDAO.getAll();
//		cDAO.getAll();
//		dDAO.getAll();
//		rDAO.getAll();
		
		//輸入欄位順序或PK查詢
		
//		sDAO.get("MA0006");
//		sDAO.get(3);
		
//		cDAO.get("C003");
//		cDAO.get(3);
		
//		dDAO.get("IE");
//		dDAO.get(3);
		
//		scDAO.get("CS0001");
//		scDAO.get(3);

//		rDAO.get("CS0001");
//		rDAO.get(3);
        
        
        //刪除指定資料(輸入PK)
		
//      sDAO.delete("003");
//      cDAO.delete("dfds");
//		dDAO.delete("d");
//		scDAO.delete("52");
//		rDAO.delete("52");

		//更新資料
		
		//(studentID, newsutdentID, newname, newbirth),不更改輸入""
//      sDAO.update("50", "1" , "name", "1995-05-16");
		
		//(courseID, newcourseID, newcoursename),不更改輸入""
//      cDAO.update("d", "", "555");
		
		//(deptID, newdeptID, newdeptName),不更改輸入""
//		dDAO.update("d", "", "555");
		
		//(id, newid, newsutdentID, newcourseID),不更改輸入""
//		scDAO.update("15","15" ,"s", "555");
		
		//(newsutdentID, newsutdentID, newname, newdeptName, newcourse),不更改輸入""
//		rDAO.update("dsf","15" ,"s", "555");
        
	}

}
