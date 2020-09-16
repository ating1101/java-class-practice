package dao;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialClob;

public class StudentDAO implements IDAO<Student> {
	Connection conn = MySQL.getConnection();
	PreparedStatement pst = null;
	ResultSet resultSet = null;
	
	//新增資料
	@Override
	public boolean insert(Student t) {
		try {
			//存studentdata
			pst = conn.prepareStatement("Insert into studentData(studentID, name, birth) values(?,?,?)");
			pst.setString(1, t.getId());
			pst.setString(2, t.getName());
			pst.setString(3, t.getBirthdate());
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
			PreparedStatement preparedStatement = connect.prepareStatement("delete from studentData where studentID=?");
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
//		System.out.println(newdata[0]);
		try{
			Connection connect = MySQL.getConnection();

			PreparedStatement preparedStatement = connect.prepareStatement("UPDATE `studentData` SET `studentID`=? , `name`=?, `birth`=? WHERE `studentID`=?");
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
			PreparedStatement preparedStatement = connect.prepareStatement("select * from studentData where studentID=?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // It is possible to get the columns via name
                // also possible to get the columns via the column number
                // which starts at 1. e.g. resultSet.getString(2);
                String sid = resultSet.getString("studentID");
                String name = resultSet.getString("name");
                String birth = resultSet.getString("birth");
                System.out.print(sid + "  ");
                System.out.print(name + "  ");
                System.out.println(birth);
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
		Connection conn = MySQL.getConnection();
		Statement stmt;
		String str = "select * from studentData" ;
		
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(str);
			ResultSetMetaData rsmd = rs.getMetaData();
//			System.out.println(rsmd.getColumnCount());

			if (rs.absolute(id)) {
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
	
	//顯示全部資料
	public List<Student> getAll() {
		Connection conn = MySQL.getConnection();
		Statement stmt;
		String str = "select * from studentData" ;
		
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
