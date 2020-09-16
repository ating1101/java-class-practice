package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class StudentCourseDAO implements IDAO<Student>{
	Connection conn = MySQL.getConnection();
	PreparedStatement pst = null;
	ResultSet resultSet = null;

	//新增資料
	public boolean insert(Student t) {
		try {
//			存studentcoursedata
			pst = conn.prepareStatement("Insert into studentCourseData(studentID, courseID) values(?,?)");
			pst.setString(1, t.getId());
			pst.setString(2, t.getcourseID());
			pst.execute();		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//刪除資料
	@Override
	public boolean delete(String id) { 
		Connection conn = MySQL.getConnection();

		try{
			Connection connect = MySQL.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement("delete from studentCourseData where studentID=?");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            System.out.println("刪除成功");

		} catch (SQLException se) {
            System.out.println("刪除失敗");
			se.printStackTrace();
			
		} 
		
		return true;
	}

	//更新資料
	@Override
	public boolean update(String id, String ... newdata) { //不定長度引數
		Connection conn = MySQL.getConnection();
		try{
			Connection connect = MySQL.getConnection();

			PreparedStatement preparedStatement = connect.prepareStatement("UPDATE `studentCourseData` SET `id`=? , `studentID`=? , `courseID`=? WHERE `id`=?");
			preparedStatement.setString(4,id);
            preparedStatement.setString(1,newdata[0]);
            preparedStatement.setString(2,newdata[1]);
            preparedStatement.setString(3,newdata[2]);
            preparedStatement.execute();
            System.out.println("更新成功");

		} catch (SQLException se) {
            System.out.println("更新失敗");
			se.printStackTrace();
		} 
		return true;
	}

	//用PK取資料
	@Override
	public Student get(String id) {
		try{
			Connection connect = MySQL.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement("select * from studentCourseData where studentID=?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // It is possible to get the columns via name
                // also possible to get the columns via the column number
                // which starts at 1. e.g. resultSet.getString(2);
                String sid = resultSet.getString("studentID");
                String cid = resultSet.getString("courseID");
                System.out.print(sid + "  ");
                System.out.println(cid);
            }
            if (resultSet!=null)
            	resultSet.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} 
		
		return null;
	}

	//用欄位順序取資料
	@Override
	public Student get(int id) {
		try{
			Connection connect = MySQL.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement("select * from studentCourseData where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // It is possible to get the columns via name
                // also possible to get the columns via the column number
                // which starts at 1. e.g. resultSet.getString(2);
                String sid = resultSet.getString("studentID");
                String cid = resultSet.getString("courseID");
                System.out.print(sid + "  ");
                System.out.println(cid);
            }
            if (resultSet!=null)
            	resultSet.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} 
		
		return null;
	}

	//顯示全部資料
	@Override
	public List<Student> getAll() {
		Connection conn = MySQL.getConnection();
		Statement stmt;
		String str = "select * from studentCourseData";
		
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(str);
			ResultSetMetaData rsmd = rs.getMetaData();
//			System.out.println(rsmd.getColumnCount());

			while (rs.next()) {
				for (int i = 1; i <= rsmd.getColumnCount() ; i++) {
					System.out.print(rs.getString(i) + "   ");
				}
				System.out.println("");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
		return null;
	}
}
