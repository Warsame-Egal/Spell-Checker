package FXLauncher;

/* File Name: FXLauncher.java
 * Course Name: CST8130 - Data Structures
 * Lab Section: Thursday
 * Student Name: Warsame Egal
 * Date: 2015/3/26
 */
import SpellChecker.SpellChecker;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXLauncher class
 * @author Warsame Egal
 * @since 1.8
 */
public class FXLauncher extends Application {
	private Scene scene;
	private SpellChecker spellCheck;
	private HBox hbox;
	private static final int PREFWIDTH = 120;
	private static final int PREFHEIGHT = 600;

	@Override
	public void start(Stage primaryStage) throws Exception {

		spellCheck = new SpellChecker();
		spellCheck.loadConcordance(); // loads concord words
		spellCheck.loadSpellChecker(); // loads misspelled words

		primaryStage.setTitle("Condcordance "); // set the title of the stage

		/**
		 * set the title for dictionaryTitle, concordWords, misspelledConcordWords and paragraphTitle. set the title effects.
		 */
		Text dictionaryTitle = new Text("Dictionary: " + spellCheck.getDictionary().getCollectionWords().size() + 
		" " + spellCheck.getCollectionWords().getClass().getName());
		dictionaryTitle.setFont(Font.font("Serif", FontWeight.EXTRA_BOLD, 20));
		dictionaryTitle.setFill(Color.WHITE);

		Text concordWords = new Text("  Concordance: " + spellCheck.getConcordMap().size() + 
		" " + spellCheck.getConcordMap().getClass().getName());
		concordWords.setFont(Font.font("Serif", FontWeight.BOLD, 20));
		concordWords.setFill(Color.WHITE);

		Text misspelledConcordWords = new Text("  Mispelled : " + spellCheck.getMisspelledWordsMap().size() +
		" " + spellCheck.getMisspelledWordsMap().getClass().getName());
		misspelledConcordWords.setFont(Font.font("Serif", FontWeight.BOLD, 20));
		misspelledConcordWords.setFill(Color.WHITE);

		Text paragraphTitle = new Text("  Paragraph:" + spellCheck.getBook().getCollectionParagraphs().size() + 
		" " + spellCheck.getCollectionParagraphs().getClass().getName());
		paragraphTitle.setFont(Font.font("Serif", FontWeight.BOLD, 20));
		paragraphTitle.setFill(Color.WHITE);

		/**
		 * WebView is a Node that manages a WebEngine and displays its content
		 */
		final WebView paragraphWebView = new WebView();

		/**
		 * create HBox object
		 */
		hbox = new HBox();

		/**
		 * Initialize all the VBox boxes contained in the scene
		 */
		VBox vBoxDictionary = new VBox();
		VBox vBoxConcordance = new VBox();
		VBox vBoxMisspelledConcordance = new VBox();
		VBox vBoxParagraph = new VBox();
		vBoxParagraph.getStyleClass().add("paragraph"); // css content
		VBox vDictionary = new VBox();
		VBox vMisspelledDictionary = new VBox();
		VBox vConcordance = new VBox();
		VBox vMisspelledConcordance = new VBox();

		/**
		 * Initialize the scrollpanes with the predefined width and height
		 */
		ScrollPane dictionary = new ScrollPane();
		ScrollPane concordance = new ScrollPane();
		ScrollPane misspelled = new ScrollPane();
		dictionary.setPrefSize(PREFWIDTH, PREFHEIGHT);
		dictionary.setContent(vBoxDictionary);
		dictionary.getStyleClass().add("dictionary");
		concordance.setPrefSize(PREFWIDTH, PREFHEIGHT);
		concordance.setContent(vBoxConcordance);
		concordance.getStyleClass().add("concord");
		misspelled.setPrefSize(PREFWIDTH, PREFHEIGHT);
		misspelled.setContent(vBoxMisspelledConcordance);
		misspelled.getStyleClass().add("misspelled");

		/**
		 * concord words map
		 */
		for (String sSearchWord : spellCheck.getConcordMap().keySet()) { // sSearchWord now holds every concord
			Text text = new Text(sSearchWord); // all words from concord passed to text
			text.setOnMouseEntered(new EventHandler<Event>() { // Sets the value of the property onMouseEntered.
				public void handle(Event args0) {
					final String HTMLBoundingBlueBoxCSSstyle = "<style>.border{border-style:dashed; border-color:#287EC7;}</style>"; // CSS style tags passed to a string
					StringBuilder stringBuilder = new StringBuilder(HTMLBoundingBlueBoxCSSstyle); // pass the style to a stringbuilder
					String sRegExSearch = "\\b(?i:" + sSearchWord + ")\\b"; // The following regular expression isolates a searchword:
					for (String paragraph : spellCheck.getConcordMap().get(sSearchWord)) { // loop through word
						String highlightedString = paragraph.toString().replaceAll(sRegExSearch, "<span class=\"border\">$0</span>"); // store the isolated word thats highlited into a string
						stringBuilder.append(highlightedString).append("<br><br>"); // append the highlighted word, two blank lines between each paragraph in the WebView
					} // end for(processing all paragraphs in collection)
					paragraphWebView.getEngine().loadContent(stringBuilder.toString()); // Returns the WebEngine object with string builder content
				}
			}); // end setOnMouseEntered
			vBoxConcordance.getChildren().add(text); // add the text into vBoxConcordance that has everything
		} // for

		/**
		 * misspelled words map
		 */
		for (final String sSearchWord : spellCheck.getMisspelledWordsMap().keySet()) { // sSearchWord now holds every concord
			Text text = new Text(sSearchWord); // all words from concord passed to text
			text.setOnMouseEntered(new EventHandler<Event>() { // Sets the value of the property onMouseEntered.
				public void handle(Event args0) {
					final String HTMLBoundingRedBoxCSSstyle = "<style>.border{border-style:inset; border-color:green;}</style>"; // CSS style tags passed to a string
					StringBuilder stringBuilder = new StringBuilder(HTMLBoundingRedBoxCSSstyle); // pass the style to a stringbuilder
					String sRegExSearch = "\\b(?i:" + sSearchWord + ")\\b"; // The following regular expression isolates a searchword:
					for (String paragraph : spellCheck.getMisspelledWordsMap().get(sSearchWord)) { // loop through word
						String highlightedString = paragraph.toString().replaceAll(sRegExSearch, "<span class=\"border\">$0</span>"); // store the isolated word thats highlited into a string
						stringBuilder.append(highlightedString).append("<br><br>"); // append the highlighted word, two blank lines between each paragraph in the WebView
					} // end for(processing all paragraphs in collection)
					paragraphWebView.getEngine().loadContent(stringBuilder.toString()); // //Returns the WebEngine object with string builder content
				}
			}); // end setOnMouseEntered
			vBoxMisspelledConcordance.getChildren().add(text); // add the text into vBoxMisspelledConcordance that has everything
		}

		/**
		 * Loads dictionary words pass the words into a text then pass the text into a vbox
		 */
		for (String word : spellCheck.getDictionary().getCollectionWords()) {
			Text text = new Text(word); // pass the words into a text object
			vBoxDictionary.getChildren().add(text);// add the text into vBoxDictionary that has words
		}

		/**
		 * Loads all the objects into the correct VBoxes
		 */
		vDictionary.getChildren().addAll(dictionaryTitle, dictionary);
		vConcordance.getChildren().addAll(concordWords, concordance);
		vMisspelledConcordance.getChildren().addAll(misspelledConcordWords, misspelled);
		vBoxParagraph.getChildren().addAll(paragraphTitle, paragraphWebView);
		hbox.getChildren().addAll(vDictionary, vMisspelledDictionary, vConcordance, vMisspelledConcordance, vBoxParagraph);

		scene = new Scene(hbox); // add the hbox into the scene
		scene.getStylesheets().add("Stylesheets.css"); // css content
		primaryStage.setScene(scene);// add the scene into the primary stage
		primaryStage.getIcons().add(new Image("http://img1.wikia.nocookie.net/__cb20111228065136/simpsons/images/1/11/Twitter_bird_icon.png")); // creates logo
		primaryStage.show();// display everything
	}// start

	/**
	 * Here is where the program starts
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
