package TextFiles;

/** File Name: Dictionary.java
 * Course Name: CST8130 - Data Structures
 * Lab Section: Thursday
 * Student Name: Warsame Egal
 * Date: 2015/3/26
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Dictionary class
 * @author Warsame Egal
 * @since 1.8
 */
public class Dictionary {

	/**
	 * collectionWords type Collection<String>.
	 */
	private Collection<String> collectionWords;

	/**
	 * Filename reference to be used in parentheses to call each file
	 */
	private String fileName;

	/**
	 * Constructor that sets fileName
	 * @param fileName
	 */
	public Dictionary(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * getter that returns the collection words
	 * @return collectionWords
	 * */
	public Collection<String> getCollectionWords() {
		return collectionWords;
	}

	/**
	 * loads the word from the document into a treeSet then passes the TreeSet. Record the time it took
	 * @throws FileNotFoundException for loadDictionary method
	 */
	public void loadDictionary() throws FileNotFoundException {
		long startTime = System.currentTimeMillis();
		collectionWords = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER); 
		try (Scanner input = new Scanner(new File(fileName))) {
			while (input.hasNext()) {
				String word = input.nextLine();
				collectionWords.add(word); // virtual method to add another paragraph to the collection
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		double elapseTime = (double) (System.currentTimeMillis() - startTime) / 1000.0;
		System.out.printf("Collection Type:%s loadDictionary word Count:%d Elapse Time:%.3f seconds\n", collectionWords.getClass().getName(), collectionWords.size(), elapseTime);
		System.out.println();
	}

	/**
	 * returns a boolean indicating whether the ccollectionWords contains the word (key) that is being searched for
	 * @param word
	 */
	public boolean lookup(String word) {
		return collectionWords.contains(word);
	}

}