package Hw1;
import java.util.*;

public class Caesar {
	//simple solution for Caesar cipher
	public static String Caesar(String str,int offset){
		str = str.toLowerCase();
		String ret = null;
		char[] chars = str.toCharArray();
		for(char c:chars){
		int x = (int)c;
		if(c!=' '){
			x = (int)c + offset;
			if(x>122){x-=26;}
		}
		ret += (char)x;
		}
		return ret;
	}
	
	// solves the equation ax+by = gcb
	//x<y and both positive
	public static void findvalue(int x, int y,int gcd){
		int currx = x;
		int curry = y;
		int inc = (y-y%x)/x;
		while(true){
			if(currx == gcd - curry || currx == gcd + curry){
			if(currx == gcd + curry)
				curry = -curry;
			else
				currx = -currx;
				break;
			}
		if(currx>curry){
			curry+=y;
			continue;
		}else{
			currx+=(x*inc);
		}
		}
		System.out.println(currx + " +" + curry + " = " + gcd);
		System.out.println(currx/x + "*"+x+" + " + curry/y+"*" + y + " = "+gcd);
	}
	//123 21
	//123 = 21*5+18
	//21 = 18*1 + 3
	//18 = 3*6 + 0
	//Run the Euclidean algorithm for finding gcd
	//returns a stack of the number to be used to solve ax+by = gcd
	public static Stack<Integer> Euclidean(int x, int y){
		Stack<Integer> stack = new Stack<Integer>();

		int q = x;
		int r = y;
		int temp = -1;
		while(r!=0){
			
			stack.push(r);
			stack.push(((q-(q%r))/r));
			stack.push(q);
			
			System.out.println(q + " = " +((q-(q%r))/r) + "*" + r + " + " + q%r);
			temp = r;
			r = q%r;
			q = temp;
			
		}
		System.out.println("gcd = " + q);
		stack.pop();
		stack.pop();
		return stack;
	}
	//123 = 21*5+18
	//21 = 18*1 + 3
	//18 = 3*6 + 0
	//3 = 21 - 1*18
	//3 = 21 - 1*(123-21*2)
	public static void solvegcd(Stack<Integer> stack){
		System.out.println(stack);
		Queue<Integer> q = new LinkedList<Integer>();
		//initial equation
		int gcd = stack.pop();
			int a = 1;
			int b = stack.pop();
			int c = -stack.pop();
			int d = stack.pop();
			q.add(a);
			q.add(b);
			q.add(c);
			q.add(d);
			System.out.println(gcd + " = " + a+"*"+b + " + " + c + "*" + d);
			
		while(!stack.isEmpty()){
			//gcd = a*b + c *(e + -f*g)
			//gcd = (a-(c*f))*b + (c*d)
			a = q.remove();
			b = q.remove();
			c = q.remove();
			d = q.remove();
			int e = stack.pop();
			int f = stack.pop();
			int g = stack.pop();
			System.out.println(gcd + " = " + a+"*"+b + " + " + c + "*(" + + e + " + " + -f + "*" + g + ")");
			q.add(c);
			q.add(e);
			q.add(a-(c*f));
			q.add(a);
			
			System.out.println(gcd + " = " + (a-(c*f)) + "*" + b + " + " + c + "*" + e);
			
		}
		
		
	}

	public static void main(String[] args) {
		//findvalue(21,123,3);
		//Euclidean(47,30);
		Stack<Integer> stack = Euclidean(47,30);
		solvegcd(stack);
		
	}
}
