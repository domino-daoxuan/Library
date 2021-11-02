public class App {

    private static final String LIBRARY_NAME = "Bach Khoa Library";
    private static Library library = new Library(LIBRARY_NAME);

    private static int option;

    private static boolean cont = true;

    public static void main(String[] args) throws Exception {

        // ...

        do {

            showMenu();
            askOption();
        } while (cont);

        // ...
    }

    public static void showMenu() {
        System.out.println("========== Main menu ==========");
        System.out.println("0. Get a lucky book!");
        System.out.println("1. Show library information");
        System.out.println("2. Add new book");
        System.out.println("3. Find book");
        System.out.println("4. Borrow a book");
        System.out.println("5. Return a book");
        System.out.println("6. Exit");
        System.out.println("===============================");
        System.out.println("Enter menu ID (1-6) : ");
    }

    public static void askOption() {

        option = enterNum();
        switch (option) {
            case 0:
                getALuckyBook();
                break;
            case 1:
                showLibraryInfo();
                break;
            case 2:
                addNewBook();
                break;
            case 3:
                showMenuFindBook();
                findBook();
                break;
            case 4:
                borrowBook();
                break;
            case 5:
                returnBook();
                break;
            case 6:
                System.out.println("Goodbye, See you next time!");
                cont = false;
                break;
            default:
                System.out.println("please pick another number in this menu");
                break;
        }
    }

    // ========================================================================

    private static void getALuckyBook() {
        Book aLuckyBook = library.getALuckyBook();
        System.out.println("Happy 30/4 - 1/5!" + aLuckyBook.getBookTitle());
        library.showInforBook(aLuckyBook.getBookID());
    }

    private static void showLibraryInfo() {
        library.showLibraryInfo();
    }

    private static void addNewBook() {
        library.addNewBook();
    }

    // --------------------------------------
    private static void showMenuFindBook() {
        System.out.println("---------------------------");
        System.out.println("1. Find by Book's ID: ");
        System.out.println("2. Find by Book's Tittle: ");
        System.out.println("---------------------------");
        System.out.println("Enter menu ID (1-2) : ");
    }

    private static void findBook() {
        option = enterNum();
        switch (option) {
            case 1:
                findBookByID();
                break;
            case 2:
                findBookByTittle();
                break;
            default:
                break;
        }
    }

    private static void findBookByID() {
        int bookID;
        boolean check;
        System.out.println("Enter Book's ID: ");
        bookID = enterNum();
        check = library.findBook(bookID);
        if (check) {
            library.showInforBook(bookID);
        } else
            System.out.println("Doesn't have book !");
    }

    private static void findBookByTittle() {
        String keyword;
        boolean check;
        System.out.println("Enter Book's Tittle: ");
        keyword = enterString();
        check = library.findBook(keyword);
        if (check) {
            library.showInforBook(keyword);
        } else
            System.out.println("Doesn't have book !");
    }

    // ------------------------------------------------

    private static void borrowBook() {
        int bookID;
        System.out.println("------------------------------\n" + "Enter book's ID: ");
        bookID = enterNum();
        library.borrowBook(bookID);
    }

    private static void returnBook() {
        int bookID;
        System.out.println("------------------------------\n" + "Enter book's ID: ");
        bookID = enterNum();
        library.returnBook(bookID);
    }

    // ====================================================

    private static int enterNum() {
        return library.enterNum();
    }

    private static String enterString() {
        return library.enterString();
    }
}
