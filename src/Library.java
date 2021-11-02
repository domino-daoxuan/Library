import java.util.Scanner;

public class Library {
    private String libraryName;

    private Book[] bookList;

    private static int numberOfBooks;

    public static final int MAX_NUMBER_BOOKS = 100;

    private static int indexOfBook;

    public Library(String name) {
        this.libraryName = name;
        this.bookList = new Book[MAX_NUMBER_BOOKS];
    }

    public Book[] getBookList() {
        return this.bookList;
    }

    public void setBookList(Book[] bookList) {
        this.bookList = bookList;
    }

    // Thêm 1 quyển sách mới
    public void addNewBook() {

        // kiểm tra giới hạn đầu sách
        if (checkLimitBook()) {
            System.out.println("Library has limited !");
        } else {
            int bookID;
            String bookTitle;
            int amount;
            boolean cont;
            System.out.println("-----------------------------");
            System.out.println("Enter book's information:");

            // nhập và kiểm tra bookID
            do {
                System.out.println("(1)Book ID: ");
                bookID = enterNum();
                cont = checkExistBookID(bookID);
                if (cont) {
                    System.out.println("This book has been in library," + " please enter another bookID !");
                }
                if (bookID < 0) {
                    System.out.println("This bookID is illegal" + " please enter another bookID !");
                }
            } while (cont || bookID < 0);

            // nhập title, amount
            System.out.println("(2)Book Title: ");
            bookTitle = enterString();
            System.out.println("(3)Amount: ");
            amount = enterNum();    

            // tạo sách mới và thêm vào danh sách
            Book book = new Book(bookID, bookTitle, amount);
            bookList[numberOfBooks] = book;
            numberOfBooks++;
            book.showBookInfo();
        }

    }

    // Tìm kiếm sách

    public boolean findBook(int bookID) {
        if (checkExistBookID(bookID)) {
            for (indexOfBook = 0; indexOfBook < numberOfBooks; indexOfBook++) {
                if (bookID == bookList[indexOfBook].getBookID()) {
                    return true;
                }
            }
        }
        return false;
    }

    // Mượn sách

    public void borrowBook(int bookID) {
        if (checkExistBookID(bookID)) {
            showInforBook(bookID);
            int available = 0;
            int index = 0;
            for (indexOfBook = 0; indexOfBook < numberOfBooks; indexOfBook++) {
                if (bookList[indexOfBook].getBookID() == bookID) {
                    index = indexOfBook;
                    available = bookList[indexOfBook].getAvailable();
                    break;
                }
            }
            if (available > 0) {
                bookList[index].setAvailable(available - 1);
                System.out.println("\nThe book is borrowed successfully!");
                showInforBook(bookID);
            } else
                System.out.println("Not enough amount to borrowed !");
        } else {
            System.out.println("Doesn't have this bookID in our library!");
        }
    }

    // Trả sách

    public void returnBook(int bookID) {
        if (checkExistBookID(bookID)) {
            int available = 0;
            int index = 0;
            for (indexOfBook = 0; indexOfBook < numberOfBooks; indexOfBook++) {
                if (bookList[indexOfBook].getBookID() == bookID) {
                    index = indexOfBook;
                    available = bookList[indexOfBook].getAvailable();
                    break;
                }
            }
            if (bookList[index].getAvailable() == bookList[index].getAmount()) {
                System.out.println("The book was returned successfully!");
            } else {

                bookList[index].setAvailable(available + 1);
                System.out.println("\nThe book is returned successfully!");
            }
            showInforBook(bookID);
        } else {
            System.out.println("Doesn't have this bookID in our library!");
        }
    }

    // Hàm hiển thị thông tin thư viện
    public void showLibraryInfo() {
        System.out.println("---------------------------------------------");
        System.out.println(libraryName);
        System.out.print("Library has been " + numberOfBooks + " book\n");
        if (numberOfBooks > 0) {
            for (indexOfBook = 0; indexOfBook < numberOfBooks; indexOfBook++) {
                bookList[indexOfBook].showBookInfo();
            }
        }
        System.out.println();
    }

    // phương thức findbook bằng String
    public boolean findBook(String keyword) {
        String data = "";
        String[] arrData;

        // Duyet tat ca cac cuon sach co trong thu vien
        for (indexOfBook = 0; indexOfBook < numberOfBooks; indexOfBook++) {
            data = bookList[indexOfBook].getBookTitle();

            // so sanh toan bo tieu de
            if (keyword.equalsIgnoreCase(data)) {
                return true;
            }

            // so sanh tung chu
            arrData = data.split("\\s");
            for (String string : arrData) {
                if (keyword.equalsIgnoreCase(string)) {
                    return true;
                }
            }
        }
        return false;
    }

    // lay sach ngau nhien
    public Book getALuckyBook() {
        Book book = new Book();
        int availableBook = availableBook();
        int available;
        if (numberOfBooks == 0 || availableBook == 0) {
            book.setBookTitle("No book in library");
            return book;
        } else {
            // tao so nguyen ngau nhien
            double randomDouble = Math.random();
            randomDouble = randomDouble * numberOfBooks;
            int randomInt = (int) randomDouble;

            // lay sach
            book = bookList[randomInt];

            // dat lai so luong sach
            available = bookList[randomInt].getAvailable();
            if (available == 0) {
                return getALuckyBook();
            } else {
                bookList[randomInt].setAvailable(available - 1);
                bookList[randomInt].setAmount(available - 1);
                return book;
            }
        }
    }

    /*
     * ===============================================================
     * 
     * Ham viet them
     *
     */

    // Hàm hiển thị thông tin 1 cuốn sách
    public void showInforBook(int bookID) {
        for (indexOfBook = 0; indexOfBook < numberOfBooks; indexOfBook++) {
            if (bookList[indexOfBook].getBookID() == bookID) {
                bookList[indexOfBook].showBookInfo();
                break;
            }
        }
    }

    public void showInforBook(String keyword) {
        String data;
        String[] arrData;
        for (indexOfBook = 0; indexOfBook < numberOfBooks; indexOfBook++) {
            data = bookList[indexOfBook].getBookTitle();

            if (keyword.equalsIgnoreCase(data)) {
                bookList[indexOfBook].showBookInfo();
                break;
            }

            arrData = data.split("\\s");
            for (String string : arrData) {
                if (keyword.equalsIgnoreCase(string)) {
                    bookList[indexOfBook].showBookInfo();
                    break;    
                }
            }
        }
    }

    // Hàm kiểm tra giới hạn đầu sách
    private boolean checkLimitBook() {
        if (numberOfBooks == MAX_NUMBER_BOOKS)
            return true;
        return false;
    }

    // Hàm kiểm tra tồn tại bookID
    private boolean checkExistBookID(int bookID) {
        if (numberOfBooks == 0)
            return false;
        for (indexOfBook = 0; indexOfBook < numberOfBooks; indexOfBook++) {
            if (bookList[indexOfBook].getBookID() == bookID)
                return true;
        }
        return false;
    }


    // tong so luong cac dau sach hien co trong thu vien
    private int availableBook() {
        int availableSum = 0;
        for (indexOfBook = 0; indexOfBook < numberOfBooks; indexOfBook++) {
            availableSum += bookList[indexOfBook].getAvailable();
        }
        return availableSum;
    }

    // nhap lieu

    Scanner input = new Scanner(System.in);

    // nhap so
    public int enterNum() {
        int number;
        while (true) {
            String str = input.nextLine();
            if (str.trim().equals("")) {
                System.out.println("String is empty, please enter again!");
                continue;
            }
            try {
                number = Integer.parseInt(str);
            } catch (NumberFormatException ex) {
                System.out.println("String is illegal, please enter only number!");
                continue;
            }
            return number;
        }
    }

    // nhap chuoi
    public String enterString() {
        String keyword;
        keyword = input.nextLine();
        return keyword;
    }
}
