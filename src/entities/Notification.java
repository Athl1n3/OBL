package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notification {
	
	private int userID;
    private String	date;//!!
    private String message;
    private String userType;
    
         public Notification(int userID, String date, String message, String userType) {
		super();
		this.userID = userID;
		this.date = date;
		this.message = message;
		this.userType = userType;
	}
	
         
     	SimpleDateFormat dateForm=new SimpleDateFormat("MM/dd/YY");

     	/**
     	 * Gets the user ID.
     	 * 
     	 * @return  userID
     	 */
		public int getUserID() {
			return userID;
		}
		/**
		 * Instantiates the userID
		 * @param  set the userID
		 */
		public void setUserID(int userID) {
			this.userID = userID;
		}
     	/**
     	 * Gets the date
     	 * 
     	 * @return  date
     	 * @throws ParseException 
     	 */
		public Date getDate() throws ParseException {
			//date.getDate();
			return dateForm.parse(date);
		}
		/**
		 * Instantiates the data
		 * @param  set the date
		 */
		public void setDate(String date) {
			this.date = date;
		}
	 	/**
     	 * Gets the message
     	 * 
     	 * @return  message
     	 */
		public String getMessage() {
			return message;
		}
		/**
		 * Instantiates the message
		 * @param  set the message
		 */
		public void setMessage(String message) {
			this.message = message;
		}
	 	/**
     	 * Gets the user Type
     	 * 
     	 * @return  userType
     	 */
		public String getUserType() {
			return userType;
		}
		/**
		 * Instantiates the user Type
		 * @param  set the userType
		 */
		public void setUserType(String userType) {
			this.userType = userType;
		}
}
