package TextFiles;

/** File Name: Book.java
 * Course Name: CST8130 - Data Structures
 * Lab Section: Thursday
 * Student Name: Warsame Egal
 * Date: 2015/3/26
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * TextFile class
 * @author Warsame Egal
 * @since 1.8
 */
public class Book {

	/**
	 * Filename reference to be used in parentheses to call each file
	 */
	private String fileName;
	
	/**
	 * collectionBook type Collection<String>.
	 */
	private Collection<String> collectionBook;

	/**
	 * Constructor that sets fileName
	 * @param fileName
	 */
	public Book(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the collectionBook
	 */
	public Collection<String> getCollectionParagraphs() {
		return collectionBook;
	}

	/**
	 * loads the paragraphs from the document into an ArrayList. Each line is considered to be a separate paragraph. Record the time it took.
	 * @throws FileNotFoundException for loadDictionary method
	 */
	public void loadBook() throws FileNotFoundException {
		long startTime = System.currentTimeMillis();
		try (Scanner input = new Scanner(new File(fileName))) {
			collectionBook = new ArrayList<String>();
			while (input.hasNext()) {
				String paragraphs = input.nextLine();
				collectionBook.add(paragraphs);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		double elapseTime = (double) (System.currentTimeMillis() - startTime) / 1000.0;
		System.out.printf("Collection Type:%s loadBook paragraph Count:%d Elapse Time:%.3f seconds\n", collectionBook.getClass().getName(), collectionBook.size(), elapseTime);
		System.out.println();
	}
}
