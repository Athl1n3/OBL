package entities;

public class Book {
	private int bookID;
	private String name;
	private String author;
	private double edition;
	private int  printYear;
	private String Subject;
	private String description;
	private int catalog;
	private String tableOfContents;
	private String shelf;
	private int copiesNumber;
	
	public Book(int bookID, String name, String author, double edition, int printYear, String subject,
			String description, int catalog, String tableOfContents, String shelf, int copiesNumber) {
		super();
		this.bookID = bookID;
		this.name = name;
		this.author = author;
		this.edition = edition;
		this.printYear = printYear;
		Subject = subject;
		this.description = description;
		this.catalog = catalog;
		this.tableOfContents = tableOfContents;
		this.shelf = shelf;
		this.copiesNumber = copiesNumber;
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
	public double getEdition() {
		return edition;
	}
	/**
	 * Instantiates the edition
	 * @param  edition 
	 */
	public void setEdition(double edition) {
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
		return Subject;
	}
	/**
	 * Instantiates the Subject
	 * @param  Subject 
	 */
	public void setSubject(String subject) {
		Subject = subject;
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

}
