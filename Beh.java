import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 
 * @author Marek Krafcik
 *
 */
public class Beh {


	public static void main(String[] args) {
		odstartujBeh(200000);

	}
final static AtomicBoolean p = new AtomicBoolean(true);
	/**
	 * vrati boolenovsku hodnotu true alebo false
	 * @return
	 */
	public static AtomicBoolean moze(){
		return p;
	}
	/**
	 * nastavi hodnotu na true alebo false
	 * @param hodnota
	 */
	public static void setP(boolean hodnota){
		p.set(hodnota);
	}
	public static void odstartujBeh(long ciel){
		Bezec jeden = new Bezec(ciel, "Jedna");
		Bezec dva = new Bezec(ciel, "Dva");
		Bezec tri = new Bezec(ciel, "Tri");
		Bezec styri = new Bezec(ciel, "ätyri");
		Bezec pat = new Bezec(ciel, "P‰ù");
		
		ArrayList<Bezec> bezci = new ArrayList<Bezec>();

		bezci.add(jeden); // Bezci zaradeni na startovaciu ciaru, vytvori sa zoznam bezcov pre rozhodcu.
		bezci.add(dva);
		bezci.add(tri);
		bezci.add(styri);
		bezci.add(pat);
	
		Rozhodca rozhodca = new Rozhodca(bezci); //rozhodca dostane zoznam bezcov
				
		jeden.start(); //pretek startuje
		dva.start();
		tri.start();
		styri.start();
		pat.start();
		rozhodca.start();
	}

}

class Bezec extends Thread {
	long ciel;
	String name; 
	long pocitaj = 0; 
	
	public Bezec (long ciel, String meno){
		this.ciel = ciel;
		this.name = meno;
	}
	
	public void run(){
		Random r = new Random();
		
		while(Beh.moze().get()){
			
			long pocet = r.nextInt(10000 - 5000) + 5000;
			for (int i = 0; i < pocet; i++){// k˝m toto zr·ta
			// zapotÌ sa ...
				double nepodstatnyVysledok = Math.exp(Math.pow(Math.random(), Math.random()));
				if (!Beh.moze().get()) return;
				pocitaj++;
			}
			if (pocitaj >= ciel){
				Beh.setP(false);
				break;
			}
			yield(); //daj mozmost aj inym 
		}
	}
}

class Rozhodca extends Thread{
	ArrayList<Bezec> zoznamBezcov = new ArrayList<Bezec>();
	
	public Rozhodca(ArrayList<Bezec> zoznamBezcovPreRozhodcu){
		this.zoznamBezcov.addAll(zoznamBezcovPreRozhodcu);
	}
	
	@SuppressWarnings("deprecation")
	public void run(){
		long max = 0;
		long min = 0; 
		while (true){
			if (Beh.moze().get() == false){
				for (Bezec i : zoznamBezcov){
					i.stop();
				}
				for (int i = 0; i < 6; i++){
					if(daj() != null){
						if (i == 0){
							max = daj().pocitaj;
						}
						if ( i == 5){
							min = daj().pocitaj;
						}
						// vypis do peknej tabulky
						System.out.println("|"+ (i+1) +".| skonËilo vl·kno\t|\t" + daj().name + "\t|\tnar·talo >>>\t" + daj().pocitaj + ".|");
						System.out.println("|----------------------------------------------------------------------|");
					}
					zoznamBezcov.remove(daj());
				}
				System.out.println((max - min)/max);
				System.out.println(((max - min) / max ) * 100 + " %");
				break;
			}
		}
	}
	
	public Bezec daj(){
		long max = 0;
		Bezec bezec = null;
		for (Bezec i : zoznamBezcov){
			if (i.pocitaj > max){
				max = i.pocitaj;
				bezec = i;
			}
		}
		return bezec;
	}
}
