package Practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Readfile {
	static String[] readFile() {
		ArrayList<String> al = new ArrayList<String>();
		String remove ="";		
		String filename = "C:\\Users\\88697\\eclipse-workspace\\20200608\\src\\Practice\\ProductData.txt";
		try(
			FileReader fr = new FileReader(filename);
			) 
		{			
			BufferedReader br = new BufferedReader(fr);	
			while (br.ready()) {
				String str = br.readLine();	
				if(str.charAt(0) != '#') { //去除不要的

					al.add(str);		
				}
			}				
		}catch(Exception e){
			e.printStackTrace();
		}
		return al.toArray(new String[al.size()]);		
	}	
}
