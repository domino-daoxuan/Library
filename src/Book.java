public class Book {
    private int bookID;

    private String bookTitle;

    private int amount;
    
    private int available;

    public Book(){
    }

    public Book(int bookID, String bookTitle, int amount){
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.amount = amount;
        this.available = amount;
    }

    public int getBookID(){
        return this.bookID;
    }

    public void setBookID(int bookID){
        this.bookID = bookID;
    }

    public String getBookTitle(){
        return this.bookTitle;
    }

    public void setBookTitle(String bookTitle){
        this.bookTitle = bookTitle;
    }

    public int getAmount(){
        return this.amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public int getAvailable(){
        return this.available;
    }

    public void setAvailable(int available){
        this.available = available;
    }

    public void showBookInfo(){
        System.out.println("-----------------------------");
        System.out.println("Book ID: " + this.bookID);
        System.out.println("Book Title: " + this.bookTitle);
        System.out.println("Total Amount: " + this.amount);
        System.out.println("Left Amount: " + this.available);
    }
}
