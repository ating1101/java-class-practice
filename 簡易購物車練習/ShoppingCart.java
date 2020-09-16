package Practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import com.ntc.javaclass.Teacher;

public class ShoppingCart implements ShoppingRule{
	ArrayList<Product> a2 = new ArrayList();
	String remove = "";
	double sum = 0;
	
	
	public ShoppingCart() {
		
	}	
	
	
	public boolean put(Product prod) {
		a2.add(prod);
		return true;
	}
	
	public boolean pop(Product prod) {		
		a2.remove(prod);
		System.out.println(prod.getName()+" 刪除成功");
		return true;
	}
	
	public void listing() {
		Iterator it = a2.iterator();
		System.out.println(" 條碼  	品項	價格  ");
		sum = 0;
		while(it.hasNext()) {
			
			Object m = it.next();
			System.out.print(((Product) m).getBarCode()+"  ");
			System.out.print(((Product) m).getName()+"  ");
			System.out.println(((Product) m).getPrice()+"  ");
			sum += ((MyProduct) m).getPrice()*((MyProduct) m).getDiscount();  //先計算出總額			
		}
	}
	
	public int cost() {
		return (int)sum;
	}
	
	
	
}
