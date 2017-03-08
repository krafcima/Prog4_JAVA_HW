/**
 * 
 * @author Marek Krafcik
 *
 */
public class JNumbers {
	/**
	 * Returns number of numbs which digit sum is equal to 5 in interval between a, b.
	 * @param a
	 * @param b
	 * @return
	 */
	public static long pocetCisel(long a, long b){
		while (b % 9 == 5 ){
			b += 1;
		}
		while (a % 9 == 5){
			a += 1;
		}
		if (a == b){
			return 1;
		}
		if (b < a){
			return 0;
		}
		long dlzkaIntervalu = b + 2 - a + 1;
		return  dlzkaIntervalu / 9;
	}
	public static void main(String[] args) {
		System.out.println(pocetCisel(6831489146557L, 581175832846222L));

	}

}
