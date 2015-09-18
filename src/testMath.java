
public class testMath {

	public static boolean isPrime(long n) {
	    if (n <= 3) {
	        return n > 1;
	    }
	    if (n % 2 == 0 || n % 3 == 0) {
	        return false;
	    }
	 
	    for (int i = 5; i * i <= n; i += 6) {
	        if (n % i == 0 || n % (i + 2) == 0) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public static void main(String args[]){
		
//		for (int i = 2; i <= 100; i++) {
//			
//			if(isPrime(i))
//			System.out.println(i);
//			
//		}
//		int i = 0;
//		int j = 0;
//		for(i = 2; i <= 100; i++){  // 从2开始循环，一直到100
//			   for(j = 2; j <= i/2; j++){ // 对于从2到100之间的数，每个数都拿来除以从2到它自身一半（i/2）的所有数，看是否能被整除；
//			    if(i % j == 0) // 如果能被整除，说明这个数拥有除了 1 和它自身以外的商，不是质数；
//			     break; // 跳出 j 循环，继续 i 循环
//			   }
//			   if(j > i/2){  // 如果2-100之间的某个数，除了1 和它本身之外，没有其他可以整除的数，则是质数；
//			    System.out.println(i); // 输出这个质数
//			   }
//			  }
//	}
	
	for(int i=2;i<100;i++){
		int j;
		    for(j=2;j<=Math.sqrt(i);j++){//尝试到i的平方根
		        if(i%j==0){//若能整除退出内层循环
		        break;
		        }
		    }
		    //若j大余i的平方根(正)则表明内层循环走完一趟都没能被整除.i为质数
		    if(j>Math.sqrt(i))System.out.println(i);
		}
	}
}
