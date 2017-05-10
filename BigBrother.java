/**
 * 
 * @author Marek Krafcik
 *
 */
public class BigBrother {
	/**
	 * Returns reversed string.
	 * @param retazec
	 * @return
	 */
	public static String reverse(String retazec){
		if (retazec == null || retazec.isEmpty()){
			return retazec;
		}
		String result = "";
		for (int i = retazec.length() - 1; i >= 0; i--){
			result += retazec.charAt(i);
		}
		return result;
	}
	/**
	 * Returns dec number in binary. 
	 * @param n
	 * @return
	 */
    public static String doBinarneho(int n){
        String res = "";
        if (n != 0){
	        while (n != 0){
	        	if ((n % 2) == 0){
	        		res += "0";
	        		n /= 2;
	        	}
	        	else{
	        		res += "1";
	        		n /= 2;
	        	}
	        }
        }
        else{
        	res = "0";
        }
        return reverse(res); 
    }
    /**
     * Returns true in case numbers are brothers and false if they are not.
     * @param n
     * @param m
     * @return
     */
    public static boolean suBratia(int n, int m){
    	if ((n < 0) | (m < 0)){
    		return false;
    	}
    	if ( n < m ){
    		return false;
    	}
    	int index = 0;
    	String res1 = doBinarneho(n);
    	String res2 = doBinarneho(m);
    	for (int i = 0; i < res1.length(); i++){
    		if (res1.charAt(i) == res2.charAt(index)){
    			index += 1;
    		}    		
    		if (index == res2.length()){
    			return true;
    		}
    	}
    	return false;
    }
/**
 * Main fucntion prints some of possible inputs.
 * @param args
 */
    public static void main(String[] args) {
        System.out.println("Su Bratia(23,0)   == " + suBratia(23, 0)); //== true  // velky = 10111, maly = 0
        System.out.println("Su Bratia(23,1)    == " + suBratia(23, 1)); // == true  // velky = 10111, maly = 1
        System.out.println("Su Bratia(23,2)    == " + suBratia(23, 2)); //== true  // velky = 10111, maly = 10
        System.out.println("Su Bratia(23,3)    == " + suBratia(23, 3)); //== true  // velky = 10111, maly = 11
        System.out.println("Su Bratia(23,4)    == " + suBratia(23, 4)); //== false // velky = 10111, maly = 100
        System.out.println("Su Bratia(23,5)    == " + suBratia(23, 5)); //== true  // velky = 10111, maly = 101
        System.out.println("Su Bratia(23,6)    == " + suBratia(23, 6)); //== false // velky = 10111, maly = 110
        System.out.println("Su Bratia(23,7)    == " + suBratia(23, 7)); //== true  // velky = 10111, maly = 111
        System.out.println("Su Bratia(23,8)    == " + suBratia(23, 8)); //== false // velky = 10111, maly = 1000
        System.out.println("Su Bratia(23,9)    == " + suBratia(23, 9)); //== false // velky = 10111, maly = 1001
        System.out.println("Su Bratia(7,0)     == " + suBratia(7, 0)); //== false  // velky = 111, maly = 0
        System.out.println("suBratia(8,-4)     == " + suBratia(8,-4));
        System.out.println("Su Bratia(-4,-8)   == " + suBratia(-4,-8));
    }
}
