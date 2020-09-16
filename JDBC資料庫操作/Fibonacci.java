package homework;

public class Fibonacci {
	public int Fibonacci(int n) {

		if (n == 0) {
			return 0;
		}else if (n == 1) {
			return 1;
		}		
		int ans = Fibonacci(n-1) + Fibonacci(n-2);
		return ans;			
	}
}