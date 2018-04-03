import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Vernam {
	
	public static String alphabet = "abcdefghijklmnoprstuvwxyz";

	public static void main(String[] args) {
		String plainTxt;
		String key;
		String cryptogram;
		int[] plainTxtNmbs;
		int[] keyNmbs;
		
		System.out.println("Podaj slowo do zaszyfrowania: ");
		
		//Odczytywanie slowa do zaszyfrowania ze standardowego wejscia
		Scanner reader = new Scanner(System.in);
		plainTxt = reader.nextLine();
		
		//Zmienna przechowujaca dlugosc slowa do zaszyfrowania
		int inputLng = plainTxt.length();
		
		plainTxtNmbs = new int[inputLng];
		plainTxtNmbs = getStringNmbs(plainTxt);
		
		for(int i=0; i < inputLng; i++) {
			System.out.print(plainTxtNmbs[i]+ " ");
		}
		
		//Generowanie klucza (z uzyciem klasy generujacej losowy obiekt String o zadanej dlugosci, za https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
		Random random = new Random();
		RandomString rnd = new RandomString(inputLng, random);
		key = rnd.nextString();
		
		keyNmbs = new int[inputLng];
		keyNmbs = getStringNmbs(key);
		
		System.out.println("\n");
		
		System.out.println("Klucz: ");
		System.out.println(key);
		
		for(int i=0; i < inputLng; i++) {
			System.out.print(keyNmbs[i]+ " ");
		}
		System.out.println("\n");
		
		//tablica przechowujaca indeksy szyfrogramu
		int[] cryptogramIndexes = new int[inputLng];
		
		//wyznaczanie szyfrogramu
		for(int i = 0; i < inputLng; i++) {
			if(plainTxtNmbs[i]+keyNmbs[i] >= alphabet.length()) {
				cryptogramIndexes[i] = (plainTxtNmbs[i]+keyNmbs[i]) - (alphabet.length());
			}
			else
				cryptogramIndexes[i] = plainTxtNmbs[i]+keyNmbs[i];
		}
		
		cryptogram = getCryptogram(cryptogramIndexes);
		
		for(int i=0; i < inputLng; i++) {
			System.out.print(cryptogramIndexes[i]+ " ");
		}
		
		System.out.println("");
		
		System.out.println(cryptogram);
		
	}
	
	//metoda zwracajaca tablice indeksow znakow slowa
	public static int[] getStringNmbs(String txt) {
		
		int[] output;
		
		output = new int[txt.length()];
		
		for(int i = 0; i< txt.length(); i++) {
			for(int j=0; j<alphabet.length(); j++) {
				if(txt.charAt(i) == alphabet.charAt(j)) {
					output[i] = j;
				}
			}
		}
		
		return output;
		
	}
	
	//metoda zwracajaca ciag znakow na podstawie tablicy indeksow
	public static String getCryptogram(int[] indexes) {
		String output;
		char[] chars;
		chars = new char[indexes.length];
		
		for(int i = 0; i< indexes.length; i++) {
			chars[i] = alphabet.charAt(indexes[i]);
		}
		
		output = new String(chars);
		
		return output;
	}
	
}
