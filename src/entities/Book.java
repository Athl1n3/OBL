package entities;

public class Book {
	private int bookID;
	private String name;
	private String author;
	private String edition;
	private int  printYear;
	private String subject;
	private String description;
	private int catalog;
	private String tableOfContents;
	private String shelf;
	private int copiesNumber;
	private String type;
	private int availableCopies;
	private int bookOrders;
	
	public Book(int bookID, String name, String author, String edition, int printYear, String subject,
			String description, int catalog, String tableOfContents, String shelf, int copiesNumber, String type, int availableCopies) {
		super();
		this.bookID = bookID;
		this.name = name;
		this.author = author;
		this.edition = edition;
		this.printYear = printYear;
		this.subject = subject;
		this.description = description;
		this.catalog = catalog;
		this.tableOfContents = tableOfContents;
		this.shelf = shelf;
		this.copiesNumber = copiesNumber;
		this.type = type;
		this.availableCopies = availableCopies;
		this.bookOrders = bookOrders;
	}
	
  	/**
  	 * Gets the book ID.
  	 * 
  	 * @return  bookID
  	 */
	public int getBookID() {
		return bookID;
	}
	
	/**
	 * Instantiates book ID
	 * @param  bookID 
	 */
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	
  	/**
  	 * Gets the name.
  	 * 
  	 * @return  name
  	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Instantiates name
	 * @param  name 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
  	/**
  	 * Gets the author.
  	 * 
  	 * @return  author.
  	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Instantiates the author
	 * @param  author 
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
  	/**
  	 * Gets the edition.
  	 * 
  	 * @return  edition.
  	 */
	public String getEdition() {
		return edition;
	}
	/**
	 * Instantiates the edition
	 * @param  edition 
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}

  	/**
	 * @return the printYear
	 */
	public int getPrintYear() {
		return printYear;
	}
	
	/**
	 * @param printYear the printYear to set
	 */
	public void setPrintYear(int printYear) {
		this.printYear = printYear;
	}
	/**
  	 * Gets the Subject.
  	 * 
  	 * @return  Subject.
  	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * Instantiates the Subject
	 * @param  Subject 
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
  	/**
  	 * Gets   description.
  	 * 
  	 * @return  description.
  	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Instantiates the description
	 * @param  description 
	 */
	public void setDescription(String description) {
		this.description = description;
	}
  	/**
  	 * Gets   catalog.
  	 * 
  	 * @return  catalog.
  	 */
	public int getCatalog() {
		return catalog;
	}
	/**
	 * Instantiates the catalog
	 * @param  catalog 
	 */
	public void setCatalog(int catalog) {
		this.catalog = catalog;
	}
	/**
  	 * Gets   table Of Contents.
  	 * 
  	 * @return  tableOfContents.
  	 */
	public String getTableOfContents() {
		return tableOfContents;
	}
	/**
	 * Instantiates the table Of Contents
	 * @param  tableOfContents 
	 */
	public void setTableOfContents(String tableOfContents) {
		this.tableOfContents = tableOfContents;
	}
	/**
  	 * Gets   shelf.
  	 * 
  	 * @return  shelf.
  	 */
	public String getShelf() {
		return shelf;
	}
	/**
	 * Instantiates the shelf
	 * @param  shelf 
	 */
	public void setShelf(String shelf) {
		this.shelf = shelf;
	}
	/**
  	 * Gets   copies Number.
  	 * 
  	 * @return  copiesNumber.
  	 */
	public int getCopiesNumber() {
		return copiesNumber;
	}
	/**
	 * Instantiates the copies Number
	 * @param  copiesNumber 
	 */
	public void setCopiesNumber(int copiesNumber) {
		this.copiesNumber = copiesNumber;
	}
	
	public String getBookType() {
		return type;
	}
	
	public void setBookType(String type) {
		this.type = type;
	}
	
	public int getAvailableCopies() {
		return availableCopies;
	}
	
	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}
	
	public int getBookOrders() {
		return bookOrders;
	}
	
	public void setBookOrders(int bookOrders) {
		this.bookOrders = bookOrders;
	}

}
