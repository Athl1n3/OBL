package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserActivity {
	
	
	private int userID;
	private String activityName;
	private String date;//!!!!!!!!!!
	
	SimpleDateFormat dateForm=new SimpleDateFormat("MM/dd/YY");
	
	public UserActivity(int userID, String activityName, String date) {
		super();
		this.userID = userID;
		this.activityName = activityName;
		this.date = date;
	}

	
	
	/**
	 * Gets the user ID.
	 * 
	 * @return  userID
	 */
	public int getUserID() {
		return userID;
	}
	/**
	 * Instantiates user ID
	 * @param  userID 
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	/**
	 * Gets the activity Name.
	 * 
	 * @return  activityName
	 */
	public String getActivityName() {
		return activityName;
	}
	/**
	 * Instantiates activity Name
	 * @param  activityName 
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	/**
	 * Gets the date.
	 * 
	 * @return  date
	 * @throws ParseException 
	 */
	public Date getDate() throws ParseException {
		return dateForm.parse(date);
	}
	/**
	 * Instantiates date
	 * @param  date 
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	

}
