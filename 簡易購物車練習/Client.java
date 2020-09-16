package Practice;

import java.util.ArrayList;
import java.util.Iterator;

public class Client {
	
	public static void main(String[] args) {
		ShoppingCart cart = new ShoppingCart();
		ArrayList<Product> prodlist = new ArrayList();
		//讀檔案然後採買..
		String [] list = Readfile.readFile();
		for (String line:list) {
			if (line.charAt(0)!='-') {
				String [] data = line.split(",");
				MyProduct product = new MyProduct(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]));  //���隞�
				prodlist.add(product);	
			}
		}
		Iterator it = prodlist.iterator();
		while(it.hasNext()) {
			Product myproduct = (Product)it.next();
			cart.put(myproduct);
			System.out.println(myproduct.getName() + " 加入成功");
		}		
//		
		cart.listing(); //印出清單
		Iterator it1 = prodlist.iterator();    
		for(String line : list) {  		// 抓到要刪除的物品
			if (line.charAt(0)=='-') {
				while(it1.hasNext()) {  // 從購物車裡面取得物件比對
				Product myproduct = (Product)it1.next();
				if (myproduct.getBarCode().equals(line.substring(1)))
				cart.pop(myproduct);
				}
			}
		}
		cart.listing();
		int sum = cart.cost();
		System.out.print(sum);
	}
}
