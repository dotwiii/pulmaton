
import java.util.Scanner;

/**
 * Luokka salakirjoituksen koodaamiseen ja purkamiseen sivulla 
 * http://www.pulmaton.fi/wp-content/uploads/2014/01/hakemustehtava.pdf 
 * maaritellylla salakielella.
 * 
 * Kysymyksen vastaus: 42
 * 
 * @author Viivi Livio
 */
public class Salakirjoitus {

	private char[] pienetAakkoset;
	private char[] isotAakkoset;
	private char[] valimerkit;
	private int[] pienetAakkosetLukuina;
	private int[] isotAakkosetLukuina;
	private String[] valimerkitHeksalukuina = {"01", "05", "09", "13", "00"};
	
	public Salakirjoitus(String pAakkoset, String iAakkoset, String vMerkit) {
		this.pienetAakkoset = new char[pAakkoset.length()];
		this.isotAakkoset = new char[iAakkoset.length()];
		this.valimerkit = new char[vMerkit.length()];
		
		for (int i = 0; i < pAakkoset.length(); i++) {
			pienetAakkoset[i] = pAakkoset.charAt(i);
		}
		for (int i = 0; i < iAakkoset.length(); i++) {
			isotAakkoset[i] = iAakkoset.charAt(i);
		}
		for (int i = 0; i < vMerkit.length(); i++) {
			valimerkit[i] = vMerkit.charAt(i);
		}
		this.pienetAakkosetLukuina = this.luoKoodit(this.pienetAakkoset);
		this.isotAakkosetLukuina = this.luoKoodit(this.isotAakkoset);
	}
	
	private int[] luoKoodit(char[] aakkoset) {
		int[] taulukko = new int[aakkoset.length];
		int luku = 0;
		for (int i = 1; i < aakkoset.length + 1; i++) {
			if (i % 2 == 1) { // A, C, E, G...
				luku = i * 2;
			} else { // B, D, F, H...
				luku = ((32 - i) * 4) - 1;
			}
			taulukko[i-1] = luku;
		}
		return taulukko;
	}
	
	private String muunnaHeksaluvuksi(int luku) {
		String heksa = Integer.toHexString(luku);
		if (heksa.length() == 1) {
			heksa = "0" + heksa;
		}
		return heksa;
	}
	
	private int muunnaDesimaaliluvuksi(String heksa) {
		int luku = Integer.parseInt(heksa, 16);
		return luku;
	}
	
	private String koodaa(String teksti) {
		String koodattu = "";
		String koodi = "";
		for (int i = 0; i < teksti.length(); i++) {
			char c = teksti.charAt(i);
			for (int j = 0; j < this.pienetAakkoset.length; j++) {
				if (c == this.pienetAakkoset[j]) {
					koodi = this.muunnaHeksaluvuksi(this.pienetAakkosetLukuina[j]);
				}
			}
			for (int j = 0; j < this.isotAakkoset.length; j++) {
				if (c == this.isotAakkoset[j]) {
					koodi = this.muunnaHeksaluvuksi(this.isotAakkosetLukuina[j]);
				}
			}
			for (int j = 0; j < this.valimerkit.length; j++) {
				if (c == this.valimerkit[j]) {
					koodi = this.valimerkitHeksalukuina[j];
				}
			}
			koodattu += koodi;
		}
		return koodattu;
	}
	
	private String pura(String teksti) {
		String purettu = "";
		char merkki = ' ';
		int i = 0;
		
		while (i+1 < teksti.length()) {
			char a = teksti.charAt(i);
			char b = teksti.charAt(i+1);
			String s = ""+a+b;
			int d = this.muunnaDesimaaliluvuksi(s);
			
			for (int j = 0; j < this.pienetAakkoset.length; j++) {
				if (d == this.pienetAakkosetLukuina[j]) {
					merkki = this.pienetAakkoset[j];
				}
			}
			for (int j = 0; j < this.valimerkit.length; j++) {
				if (s.equals(this.valimerkitHeksalukuina[j])) {
					merkki = this.valimerkit[j];
				}
			}
			i += 2;
			purettu += merkki;
		}
		return purettu;
	}
	
	public static void main(String args[]) {
		
		try {
			System.out.println("Mika on vastaus suureen kysymykseen elamasta, " 
					+ "maailmankaikkeudesta ja muusta sellaisesta?");
			Scanner syote = new Scanner(System.in);
			String vastaus = syote.nextLine();
			
			if (!vastaus.equals("42")) {
				System.out.println("Vaara vastaus.");
				System.exit(0);
			}
			System.out.println("Oikea vastaus. Tervetuloa kayttamaan " 
					+ "salakielen koodaus- ja purkuohjelmaa.");
			
			Salakirjoitus s = new Salakirjoitus("abcdefghijklmnopqrstuvwxyz", 
					"ABCDEFGHIJKLMNOPQRSTUVWXYZ", ",.!? ");
			
			int toiminto = 0;
			String teksti = "";
			while (toiminto != 3) {
				System.out.println("Valitse toiminto antamalla numero:");
				System.out.println("1 Koodaa");
				System.out.println("2 Pura");
				System.out.println("3 Lopeta");
				toiminto = Integer.parseInt(syote.nextLine());
				
				if (toiminto == 1) {
					System.out.println("Anna koodattava teksti:");
					teksti = syote.nextLine();
					System.out.println("Koodattu teksti:");
					System.out.println(s.koodaa(teksti));
				} else if (toiminto == 2) {
					System.out.println("Anna purettava teksti:");
					teksti = syote.nextLine();
					System.out.println("Purettu teksti:");
					System.out.println(s.pura(teksti));
				} else if (toiminto == 3) {
					System.exit(0);
				}
			}
			
		} catch (NumberFormatException n) {
			System.err.println("Toiminnon on oltava numero.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
