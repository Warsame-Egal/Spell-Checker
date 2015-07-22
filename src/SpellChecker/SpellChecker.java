package SpellChecker;

/** File Name: SpellChecker.java
 * Course Name: CST8130 - Data Structures
 * Lab Section: Thursday
 * Student Name: Warsame Egal
 * Date: 2015/3/26
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import TextFiles.Book;
import TextFiles.Dictionary;

/**
 * SpellChecker class
 * @author Warsame Egal
 * @since 1.8
 */
public class SpellChecker {

	/**
	 * Instantiates book object to access Book class.
	 */
	private Book book;

	/**
	 * Instantiates dictionary object to access Dictionary class.
	 */
	private Dictionary dictionary;

	/**
	 * Create a misspelledWordsMap type Map<String, Collection<String>>.
	 */
	private Map<String, Collection<String>> misspelledWordsMap;

	/**
	 * Create a concordMap type Map<String, Collection<String>>.
	 */
	private Map<String, Collection<String>> concordMap;

	/**
	 * SpellChecker constructor loads the dictionary and the book to the proper class loads
	 *  the book and the dictionary after the book and dictionary have been created.
	 * @throws FileNotFoundException for SpellChecker method
	 */
	public SpellChecker() throws FileNotFoundException {
		book = new Book("Oliver.txt");
		book.loadBook();
		dictionary = new Dictionary("Main.txt");
		dictionary.loadDictionary();
	}

	/**
	 * getter for dictionary
	 * @return dictionary
	 */
	public Dictionary getDictionary() {
		return dictionary;
	}

	/**
	 * getter for book
	 * @return book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @return collectionParagraphs from book
	 */
	public Collection<String> getCollectionParagraphs() {
		return book.getCollectionParagraphs();
	}

	/**
	 * @return the collectionWords from dictionary
	 */
	public Collection<String> getCollectionWords() {
		return dictionary.getCollectionWords();
	}

	/**
	 * This method will load all misspelled words into an treeMap and splits paragraphs into seperate words then checks 
	 * if the paragraph exists passes the paragraphs into a linkedHashSet then puts the
	 * mispelledWords as the key and paragraph as the value in a treeMap. capture time it took.
	 */
	public void loadSpellChecker() {
		misspelledWordsMap = new TreeMap<String, Collection<String>>(String.CASE_INSENSITIVE_ORDER); // create treeMap called misspelledWordMap

		long startTime = System.currentTimeMillis(); // capture the time

		for (String paragraph : book.getCollectionParagraphs()) { // enhanced for loop to iterate through the collection of paragraphs in book class
			String[] collectionWords = paragraph.split("[^a-zA-Z']+"); // capture array of references to isolated words
			for (String word : collectionWords) { // enhanced for loop to iterate through each word.
				if (!dictionary.lookup(word)) { // if the word is not found
					Collection<String> paragraphThatContainsWord = misspelledWordsMap.get(word); // pass the word into a collection
					if (paragraphThatContainsWord == null) { // if the collection paragraph doesnt exist
						List<String> iList = new ArrayList<String>(); // create an arrayList
						iList.add(paragraph); // add the paragraph into the arrayList
						misspelledWordsMap.put(word, iList); // put word as the key and ilist paragraph as the value.
					}
					if (misspelledWordsMap.containsKey(word)) { // if the treeMap contains a word
						misspelledWordsMap.get(word).add(paragraph); // add a paragraph to the treeMap
					} else {
						paragraphThatContainsWord.add(paragraph); // add the paragraph to a listParagraph ,
					}// inner if
				} // outter if
			} // inner for
		} // outter for

		// capture the elapsed time and print the class type size.
		double elapseTime = (double) (System.currentTimeMillis() - startTime) / 1000.0;
		System.out.printf("Collection Type:%s loadSpellChecker() Word Count:%d Elapse Time:%.3f seconds\n\n", 
		misspelledWordsMap.getClass().getName(), misspelledWordsMap.size(), elapseTime);
	} // loadMisspelledWords

	/**
	 * This method will load all words into an treeMap and splits paragraphs into seperate words then checks 
	 * if the paragraph exists passes the paragraphs into a linkedHashSet then puts the word as the
	 * key and paragraph as the value in a treeMap. capture time it took.
	 */
	public void loadConcordance() {
		concordMap = new TreeMap<String, Collection<String>>(String.CASE_INSENSITIVE_ORDER); // create treeMap called concordMap

		long startTime = System.currentTimeMillis(); // capture the time

		for (String paragraph : book.getCollectionParagraphs()) { // enhanced for loop to iterate through the collection of paragraphs in book class
			String[] collectionWords = paragraph.split("[^a-zA-Z_\u00E0-\u00EF]+"); // capture array of references to isolated words
			for (String word : collectionWords) { // enhanced for loop to iterate through each word.
				if (word.length() == 0) // removes spaces 
					continue; // continue abandons the rest of this cycle of the for-loop, and starts again with the next word to be considered.
				Collection<String> listParagraph = concordMap.get(word); // // create an collection<String>
				if (listParagraph == null) { // checks if a word is present
					List<String> iList = new ArrayList<String>(); // create ArrayList called list
					iList.add(paragraph); // add the paragraph into the linkedhashset
					concordMap.put(word, iList); // put word as the key and ilist paragraph as the value.
				} else {
					listParagraph.add(paragraph); // add the paragraph to a listParagraph
				}// if
			} // inner for
		} // for
		double elapseTime = (double) (System.currentTimeMillis() - startTime) / 1000.0;
		System.out.printf("Collection Type:%s loadConcordance() Word Count:%d Elapse Time:%.3f seconds\n\n",
		concordMap.getClass().getName(), concordMap.size(), elapseTime);
	}

	/**
	 * @return the misspelledWordsMap.
	 */
	public Map<String, Collection<String>> getMisspelledWordsMap() {
		return misspelledWordsMap;
	}

	/**
	 * @return the concordMap.
	 */
	public Map<String, Collection<String>> getConcordMap() {
		return concordMap;
	}

}