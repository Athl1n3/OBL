package entities;

public class Librarian extends Account {
	   
	private int workerID;

	
	       public Librarian(int iD, float firstName, float lastName, float eMail, float mobileNum, int userID, String userName,
			String password, String userType, int workerID) {
		super(iD, firstName, lastName, eMail, mobileNum, userID, userName, password, userType);
		this.workerID = workerID;
	}

	    	/**
	    	 * Gets the Worker ID.
	    	 * 
	    	 * @return  workerID
	    	 */
		    public int getWorkerID() {
			return workerID;
		    }
		    
		/**
		 * Instantiates the worker ID
		 * @param  set the worker ID
		 */
		public void setWorkerID(int workerID) {
			this.workerID = workerID;
		}

	       
	

}
