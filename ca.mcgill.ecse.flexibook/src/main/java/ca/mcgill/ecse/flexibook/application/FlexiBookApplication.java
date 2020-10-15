/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ca.mcgill.ecse.flexibook.application;

import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.User;

public class FlexiBookApplication {
   private static FlexiBook flexibook;
   private User currentUser;
   
   public static void main(String args) {
	   
   }
    
    public static FlexiBook getFlexibook() {
    	if (flexibook == null) flexibook = new FlexiBook();
    	return flexibook;
    			
    }
    
    public User getCurrentUser() {
    	return this.currentUser;
    }
    
    public void setCurrentUser(User user) {
    	this.currentUser = user;
    }
}
