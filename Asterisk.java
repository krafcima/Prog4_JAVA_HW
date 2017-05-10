/**
 * 
 * @author Marek Krafcik
 *
 */
public class Asterisk {
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
	 * Returns n- times repeated m string.
	 * @param n
	 * @param m
	 * @return
	 */
	public static String generujString(int n, String m){
		String result =  "";
		int i;
		for (i = 0; i < n; i++){
			result +=  m;
		}
		return result;
	}
	/**
	 * Returns magical ASCII Asterisk in string.
	 * @param n
	 * @return
	 */
    public static String asterisk(int n){
    	String res = "";
    	int pocetMedzier = n -3;
    	for (int i = 0; i < n/2; i++){
    		if (i == 0){
    			for (int j = 0; j < 2; j++){
    				res += "*" + generujString(pocetMedzier/2, " ");
    			}
    			res += "*\n";
    		}
    		else{
    		  res += generujString(i, " ");
  				for (int j = 0; j < 2; j++){
  					res += "*" + generujString(pocetMedzier/2 - i, " ");
  				}
  				res += "*" + generujString(i, " ") + "\n";
    		}
    	}
    	String pom = reverse(res);
    	for (int i = 0; i < n; i++){
    		res += "*";
    	}

    return res+pom + "\n";	
    }
/**
 *  Main fucntion prints some of possible inputs.
 * @param args
 */
    public static void main(String[] args) {
        for (int i = 5; i < 50; i+=2){
        	System.out.println(asterisk(i));
        }
    }
}
