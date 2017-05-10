import java.io.File;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Marek Krafcik
 *
 */
public class Upratovac {
	static String rootFilePath;
	static Set<String> files = new HashSet<>();

	/**
	 * Set up filePath for cleaning.
	 */		
	public static void poupratuj(File dir){
		rootFilePath = dir.getAbsolutePath();
		Set<File> fS = getDirs(dir);
		presunSuborov(dir);
		System.out.println("Succes ---> " + dir.getAbsolutePath());
		for(File x : fS){
			presunSuborov(x);
			System.out.println("Succes ---> " + x.getAbsolutePath());
			x.delete();
		}
	}

	/**
	 * @param dir
	 * @return
	 */
	public static Set<File> getDirs(File dir) {
		Set<File> m = new HashSet<>();
		for (File k : dir.listFiles()){
			if (k.isDirectory()){
				m.add(k.getAbsoluteFile());
				m.addAll(getDirs(k));
			}
		}
		return m;
	}

	/**
	 * Doing cleaning and moving files to new location.
	 * This algorithm i found at : https://www.mkyong.com/java/how-to-move-file-to-another-directory-in-java/ 
	 * @param dir
	 */
	private static void presunSuborov(File dir) {
		for (File f : dir.listFiles()){
			if (!f.isDirectory()){
				int kde = f.getName().lastIndexOf('.');
				files.add(f.getName().substring(kde));
				File n = new File((rootFilePath + File.separator)+ f.getName().substring(kde));
				if (n.mkdir()){
					if (kontrola(f.getName(), n)){
						f.renameTo( new File((n.getAbsolutePath()+ File.separator) + f.getName()));
					}
				}
				else{
					if (kontrola(f.getName(), n)){
						f.renameTo( new File((n.getAbsolutePath()+ File.separator) + f.getName()));
					}
				}
			}
		}
	}
public static boolean kontrola(String fName, File n){
	for (File k : n.listFiles()){
		if (fName.equals(k.getName())){
			return false;
		}
	}
	return true;
}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File mainDir =  new File("path to directory you want to clean up");
		//System.out.println(mainDir);
		Upratovac.poupratuj(mainDir);
	}

}
