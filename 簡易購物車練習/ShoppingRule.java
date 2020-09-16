package Practice;

public interface ShoppingRule {
	public boolean put(Product prod); //買東西
	public boolean pop(Product prod); //不買
	public void listing(); //清單:產品名稱.價格
	public int cost(); //費用
}
