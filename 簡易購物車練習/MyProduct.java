package Practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class MyProduct extends Product{
	private double discount;
	
	public void setDiscount(double count) {
		this.discount = 1.0-count;
	}
	public double getDiscount() {
		return discount;
	}


	public MyProduct(String a, String b, int c, double d) { //先轉好再接資料
		super.setBarCode(a);
		super.setName(b);
		super.setPrice(c);
		this.setDiscount(d);
	}
}
