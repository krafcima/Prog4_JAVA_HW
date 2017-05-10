public class BiLandia {
/**
 * Returns number of x < n. 
 * @param n
 * @return
 */
	public static int pocetMensich(int n){
		int i;
		int pocet = 0;
		for (i = 0; i < n; i++){
			int pom = (int)Math.pow(2,i);
			if (pom < n){
				pocet += 1;
			}
		}
	return pocet;
	}
	/**
	 *  Returns true.
	 * @param n
	 * @return
	 */
    
    public static boolean daSa(int n){
    	return true;
    }
    /**
     *  Returns number of options.
     * @param n
     * @return
     */
    public static int pocetMoznosti(int n){
    	return 1;
    }
    /**
     * Main fucntion prints some of possible inputs.
     * @param args
     */
 	public static void main(String[] args) {
 		for (int i = 0; i < 20; i++){
 			System.out.println("Pocet Mensich("+i+") =  \t" + pocetMensich(i));
 		}
    }
}