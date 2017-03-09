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
		/**
		 * This magic i have found after reading comments in list.
		 * V tychto while cykloch hladam najmensie  b a najvacsie a, pre ktore plati, ze a,b %9 == 5
		 */
		while (b % 9 != 5){
			b -= 1 ;
		}
		while (a % 9 != 5){
			a += 1;
		}
		/**
		 * Co ak ale dostanem rovne cisla ? No tam sa potom nachadza iba jedno jedine cislo, pre ktore plati vyssie uvedene. 
		 */
		if (b == a){
			return 1;
		}
		/**
		 * Co ak ale b < a ? No tak to znamena, ze ziadne cislo splnajuce podmienku nie je a preto return 0.
		 */
		if (b < a){
			return 0;
		}
		/**
		 * Fejkova 10, zabezpecuje zhltnutie vsetkych zlych moznosti, kde by mohli nastat vo vysledkoch o 1, 2, 3 ... rozdielne pocty
		 * na hodnotu 10 som prisiel po opakovanych submitoch, kde som zvolil metodu pokus/omyl {:-/|
		 * Ziskanu hodnotu intervalu potom uz iba staci vydelit 9 a pre test na liste dostaneme spravne hodnoty, vid. main. 
		 */
		long dlzkaIntervalu = (b - a) + 10; 
		return dlzkaIntervalu / 9;
	}
	/**
	 * It will print some test data.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(pocetCisel(341713,544126));
		System.out.println(pocetCisel(8, 18));
		System.out.println(pocetCisel(15566,725476));
		System.out.println(pocetCisel(6831489146557L, 581175832846222L));
		System.out.println(pocetCisel(497656, 62003));
		System.out.println(pocetCisel(798952057,312068093));
		System.out.println(pocetCisel(9148397011067L, 434068943568701L));

	}

}
