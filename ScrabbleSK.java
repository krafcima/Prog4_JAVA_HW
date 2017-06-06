import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
/**
 * 
 * @author Marek Krafcik
 *
 */
public class ScrabbleSK {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		System.out.println(najlepsieVolby("XEOTSMG"));
        System.out.println(najlepsieVolby("šakuèke"));
        System.out.println(najlepsieVolby("abcdefg"));
        System.out.println(najlepsieVolby("hogofog"));
        System.out.println(najlepsieVolby("kolopic"));
        System.out.println(najlepsieVolby("kolopiè"));
	}
	
	static HashMap<String, Integer> values = new HashMap<String, Integer>();
	static String stringFile2 = "";
	static String stringFile3 = "";
	static String stringFile4 = "";
	static String stringFile5 = "";
	static TreeSet<String> all = new TreeSet<String>();
	
	/**
	 * 
	 * @param word
	 * @return
	 */
	public static int hodnota(String word){
		if(values.isEmpty()){
			try {
				Scanner scanner = new Scanner(new File("Pismenka.txt"), "cp1250");
				String[] arrayLine;
				while (scanner.hasNext()) {
					
					   arrayLine = scanner.next().split(" ");
					   
					   if(arrayLine[0].length() != 1){
						   values.put(arrayLine[0].substring(0, 1), Integer.parseInt(arrayLine[0].substring(1)));
					   }
					   
					   
				}
				scanner.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		word = word.toUpperCase(); 
		String[] chars = word.split("");
		int k = 0;
		if(chars[0].isEmpty()){
			k = 1;
		}
		int value = 0;
		for(int i = k; i < chars.length; i++){
			
			value += values.get(chars[i]);
		}
		return value;
		
	}
	/**
	 * 
	 * @param word
	 * @return
	 * @throws FileNotFoundException
	 */
	
	public static TreeSet<String> najlepsieVolby(String word) throws FileNotFoundException{
		TreeSet<String> best = new TreeSet<String>();
		word = word.toUpperCase();
		try {
			FileInputStream f = new FileInputStream("Zoznam2.txt");
	        Scanner scanner = new Scanner(f, "cp1250");
			//Scanner scanner = new Scanner(new File("Zoznam2.txt"), "cp1250");
			scanner.useDelimiter("\\Z");
			stringFile2 = scanner.next();
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileInputStream f = new FileInputStream("Zoznam3.txt");
	        Scanner scanner = new Scanner(f, "cp1250");
			//Scanner scanner = new Scanner(new File("Zoznam3.txt"), "cp1250");
			scanner.useDelimiter("\\Z");
			stringFile3 = scanner.next();
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileInputStream f = new FileInputStream("Zoznam4.txt");
	        Scanner scanner = new Scanner(f, "cp1250");
			//Scanner scanner = new Scanner(new File("Zoznam4.txt"), "cp1250");
			scanner.useDelimiter("\\Z");
			stringFile4 = scanner.next();
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileInputStream f = new FileInputStream("Zoznam5.txt");
	        Scanner scanner = new Scanner(f, "cp1250");
			//Scanner scanner = new Scanner(new File("Zoznam5.txt"), "cp1250");
			scanner.useDelimiter("\\Z");
			stringFile5 = scanner.next();
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int max = 0;
		all.addAll(words2(word));
		all.addAll(words3(word));
		all.addAll(words4(word));
		all.addAll(words5(word));
		
		int val = 0;
		for(String w : all){
			
				val = hodnota(w);
				
				if(val > max){
					best.clear();
					best.add(w);
					max = val;
					
				}
				else if(val == max){
					best.add(w);
				}
			
		}
		
		stringFile2 = "";
		stringFile3 = "";
		stringFile4 = "";
		stringFile5 = "";
		all.clear();
		return best;
	}
	/**
	 * 
	 * @param word
	 * @return
	 */
	public static TreeSet<String> words2(String word){
		TreeSet<String> ret = new TreeSet<String>();
		String[] chars = word.split("");
		int k = 0;
		if(chars[0].isEmpty()){
			k = 1;
		}
		for(int i = k; i < chars.length; i++){
			for(int j = k; j < chars.length; j++){
				if(i != j){
					String s = (chars[i]+chars[j]).toLowerCase();
					if(stringFile2.contains(s)){
						ret.add(s);
					}
				}
			}
			
		}
		return ret;
	}
	/**
	 * 
	 * @param word
	 * @return
	 */
	public static TreeSet<String> words3(String word){
		TreeSet<String> ret = new TreeSet<String>();
		String[] chars = word.split("");
		int k = 0;
		if(chars[0].isEmpty()){
			k = 1;
		}
		for(int i = k; i < chars.length; i++){
			for(int j = k; j < chars.length; j++){
				for(int l = k; l< chars.length; l++){
					if(i != j && j != l && i != l){
						String s = (chars[i]+chars[j]+chars[l]).toLowerCase();
						if(stringFile3.contains(s)){
							ret.add(s);
						}
					}
				}
				
			}
			
		}
		return ret;
	}
	/**
	 * 
	 * @param word
	 * @return
	 */
	public static TreeSet<String> words4(String word){
		TreeSet<String> ret = new TreeSet<String>();
		String[] chars = word.split("");
		int k = 0;
		if(chars[0].isEmpty()){
			k = 1;
		}
		for(int i = k; i < chars.length; i++){
			for(int j = k; j < chars.length; j++){
				for(int l = k; l< chars.length; l++){
					for(int m = k; m < chars.length; m++){
						if(i != j && j != l && i != l && i != m && j != m && l != m){
							String s = (chars[i]+chars[j]+chars[l]+chars[m]).toLowerCase();
							if(stringFile4.contains(s)){
								ret.add(s);
							}
						}
					}
					
				}
				
			}
			
		}
		return ret;
	}
	/**
	 * 
	 * @param word
	 * @return
	 */
	public static TreeSet<String> words5(String word){
		TreeSet<String> ret = new TreeSet<String>();
		String[] chars = word.split("");
		int k = 0;
		if(chars[0].isEmpty()){
			k = 1;
		}
		for(int i = k; i < chars.length; i++){
			for(int j = k; j < chars.length; j++){
				for(int l = k; l< chars.length; l++){
					for(int m = k; m < chars.length; m++){
						for(int n = k; n < chars.length; n++){
							if(i != j && j != l && i != l && i != m && j != m && l != m && i != n && j != n && l != n && n != m){
								String s = (chars[i]+chars[j]+chars[l]+chars[m]+chars[n]).toLowerCase();
								if(stringFile5.contains(s)){
									ret.add(s);
								}
								
							}
						}
						
					}
					
				}
				
			}
			
		}
		return ret;
	}

}
