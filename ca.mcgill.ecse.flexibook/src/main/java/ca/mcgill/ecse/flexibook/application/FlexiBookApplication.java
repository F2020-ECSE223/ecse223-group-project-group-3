/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ca.mcgill.ecse.flexibook.application;

import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.User;
import ca.mcgill.ecse.flexibook.persistence.FlexiBookPersistence;
import ca.mcgill.ecse.flexibook.view.FlexiBookPage;
import javafx.application.Application;
import javafx.stage.Stage;


public class FlexiBookApplication extends Application{
	private static FlexiBook flexibook;
	private static User currentUser = null;


	/*
	 * @author: Eric Chehata
	 * returns the flexibook
	 */
	public static FlexiBook getFlexibook() {
		if (flexibook == null) flexibook = FlexiBookPersistence.load();
		return flexibook;

	}

	/*
	 * @author: Eric Chehata
	 * returns the user that is currently logged in
	 */
	public static User getCurrentUser() {
		return currentUser;
	}

	/*
	 *@author: Eric Chehata
	 *@param: user
	 *sets the logged in user to a specific user
	 */
	public static void setCurrentUser(User user) {
		currentUser = user;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new FlexiBookPage(primaryStage);
	}

	public static void main(String[] args) {
		// start UI
		launch(args);
	}
}
