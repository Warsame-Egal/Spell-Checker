package Test;

/** File Name: TestCode.java
 * Course Name: CST8130 - Data Structures
 * Lab Section: 
 * Student Name: Warsame Egal
 * Date: 2015/2/26
 */
import java.io.FileNotFoundException;
import SpellChecker.SpellChecker;
import TextFiles.Dictionary;

/**
 * TestCode class
 * @author Warsame Egal
 * @since 1.8
 */
public class TestCode {

	/**
	 * Test if the two files have been read and added into the list and set
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		// Book book = new Book("Oliver.txt");
		Dictionary text = new Dictionary("Main.txt");
		SpellChecker check = new SpellChecker();
		text.loadDictionary();
		check.loadConcordance();
		check.loadSpellChecker();

	}
}
