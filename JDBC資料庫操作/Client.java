package homework;


import java.util.ArrayList;
import java.util.Iterator;

import Practice.Product;
import homework.Readfile;

public class Client {
	public static void main(String[] args) {
		String [] StudentData = Readfile.readFile("StudentData");
		String [] StudentCourseData = Readfile.readFile("StudentCourseData");
		String [] DeptData = Readfile.readFile("DeptData");
		String [] CourseData = Readfile.readFile("CourseData");
		ArrayList<Student> tempStudentData = new ArrayList();
		
		for (String line:StudentData) {  //學生基本資料
//			System.out.println(line);
			String [] data = line.split(",");
			Student student = new Student(data[0],data[1]);  //建立Object
			tempStudentData.add(student);  // 把每一行加到暫存ArrayList
		}
		
		
		Iterator it = tempStudentData.iterator(); // 抓學生id
		while(it.hasNext()) {
			ArrayList<String> temp = new ArrayList<String>(); //暫時存學生修那些課
			Student student = (Student)it.next();
			String sname = student.getid();   //現在這個學生的id
			for (String line:StudentCourseData) { //學生修課資料				
				String [] data = line.split(",");
				if (sname.contentEquals(data[0])) {  //比對學生id跟修課id
					
					for (String line1:CourseData) {  //比對課程id跟課程名稱
						String [] data1 = line1.split(",");
						if (data[1].contentEquals(data1[0])) {
							temp.add(data1[1]);  //暫存學生所修課程名稱
						}
					}
				}				
			}
			student.setcourse(temp);	//學生加入修課id
			
			for (String line:DeptData) {				
				String [] data = line.split(",");				
				if (sname.substring(0,2).contentEquals(data[0])) {
					student.setdepartment(data[1]);  //設定學生系別
					break;
				}
			}			
		}

		Iterator it1 = tempStudentData.iterator();
		while(it1.hasNext()) {
			Student student = (Student)it1.next();
			if (student.getname().contentEquals("張一正") | student.getname().contentEquals("林路基")) {
				System.out.print(student.getid() + ",");
				System.out.print(student.getname() + ",");
				System.out.print(student.getdepartment() + ",");				
				System.out.println(student.toString());
			}
		}
		
		
		//第二題
		Fibonacci f = new Fibonacci();
		System.out.println(f.Fibonacci(3));
	}
}

	

