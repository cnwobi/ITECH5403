import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    @NotNull ArrayList<Member> members = new ArrayList<>();
    @NotNull ArrayList<Book> books=new ArrayList<>();

    public void displayUserMenu() {
        // Control the menu navigation.  Includes display of menu, acceptance and processing of user input and
        // exiting the menu based on the user's selections.
        boolean blnContinue = true;
        Scanner scanInput = null;

        header();

        try {		//try-catch-finally to ensure compatibility with all java versions.
            scanInput = new Scanner(System.in);
            while (blnContinue) {
                displayMenu();

                if (scanInput.hasNextInt()) {
                    blnContinue = processMenuSelection(scanInput.nextInt());
                }
                else {
                    scanInput.nextLine();
                    blnContinue = processMenuSelection(0);
                }
            }
        }
        catch (Exception e){
            // Generic error catch - this can be made more specific to the expected errors.
            System.out.println("An unexpected exception has occurred with input");
        }
        finally {
            // Close the scanner
            if (scanInput != null) {
                scanInput.close();
            }
        }

    }

    private void header() {
        // Display program header information

        System.out.println("--------------------------------------------------------");
        System.out.println("---Welcome To ITECH 5403 Library Management System---");
        System.out.println("--------------------------------------------------------");
        System.out.println();
    }

    private void displayMenu() {
        // Display the menu for user to select which function to run
        System.out.println("Main Menu - please select an option:");
        System.out.println("1. Add new member");
        System.out.println("2. Add a new book");
        System.out.println("3. Process borrowing");
        System.out.println("4.Process returning");
        System.out.println("5. View member status");
        System.out.println("6. View book status");
        System.out.println("7. Quit");
        System.out.print("\nEnter your option: ");

    }

    private boolean processMenuSelection(int menuSelection) {
        // Use the input provided by the user to activate the appropriate code function

        boolean blnContinue = true;

        switch (menuSelection){
            case 1:
                // call method to add a new member here
                System.out.println("-- Adding a new member--");
                addNewMember();
                break;
            case 2:
                // call method to add a new book here

                System.out.println("--Adding a new book--");
                addNewBook();
                break;
            case 3:
                // call method to process borrowing here
                System.out.println("--Processing borrowing--");
                processBorrowing();
                break;
            case 4:
                // call method to  process return here
                System.out.println("--Processing return--");
                processReturn();
                break;
            case 5:
                // call method to view member status here
                System.out.println("--Viewing a member status--");
                viewMemberStatus();
                break;
            case 6:
                System.out.println("Viewing a book status");
                viewBookStatus();
                break;
            case 7:
                System.out.println("-- Exiting ITECH5403 Library System --\n ....\n  -- Goodbye! --\n");
                blnContinue = false;
                break;
            default:
                // no valid selection made
                System.out.println("Invalid selection! A number between 1 and 7 was expected.");
        }
        return blnContinue;
    }

    public void addNewMember(){
        boolean processing = true;
        while (processing) {
            Scanner scanInput = new Scanner(System.in);
            System.out.println("Please Enter the name of the new member: ");

            String name = scanInput.nextLine();
            members.add(new Member(name));
            Member newMember = members.get(members.size() - 1);

            System.out.println("Member [" + newMember.getName() + "] has been created with member ID: [" + newMember.getId() + "]");
            boolean invalidSelection=true;
            while (invalidSelection) {
                System.out.println("Would you like to [a]dd a new member or go-[b]ack to the previous menu?");
                String choice = scanInput.next();
                switch (choice) {
                    case "a":
                        processing = true;
                        invalidSelection=false;
                        break;

                    case "b":
                        processing = false;
                        invalidSelection=false;
                        break;


                    default:
                        System.out.println("Invalid selection please enter [a] to add new member or [b] to go back to menu");
                        invalidSelection=true;
                }
            }
           }

    }
    public void addNewBook(){
        //this method adds a new book
        String title=null;
        boolean processing= true;
        boolean validNoCopies= true;
        Scanner scanInput = new Scanner(System.in);
        while (processing) {
            boolean bookisUnique = false;
            while (!bookisUnique) {
                System.out.println("Please Enter the name of the new book: ");

                title = scanInput.nextLine();
                if (books.size() >= 0) {
                    bookisUnique=true;
                    for (Book book : books) {
                        if (title.equals(book.getTitle())) {
                            System.out.println("Book Already exists");
                            System.out.println("Book ID: " + book.getBookId() + "\nBook Title: " + book.getTitle() + "\nNumber of Copies: " + book.getNumberOfCopies());
                            bookisUnique=false;
                        }
                        else {
                            bookisUnique=true;


                        }
                    }
                }
            }


            // validates that the number of copies is a positive integer
            while(validNoCopies) {
                try {

                    while (validNoCopies) {
                        System.out.println("Please enter number of copies");
                       int  numberOfCopies = scanInput.nextInt();
                        if (numberOfCopies >= 0) {
                            books.add(new Book(title, numberOfCopies));
                            validNoCopies = false;
                            processing = false;

                        } else {
                            System.out.println("Number of copies must be Zero or a positive integer");
                            validNoCopies = true;
                        }
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Number of copies must be zero or a positive integer");
                    processing = true;
                    System.out.println();
                    scanInput.next();
                    validNoCopies = true;
                }
            }

        }
        Book newBook = books.get(books.size() - 1);

        System.out.println("New Book Added: " + "\nBook ID: "+ newBook.getBookId() +"\nBook Title: " + newBook.getTitle() + "\nNumber of Copies: "+newBook.getNumberOfCopies());
        boolean invalidSelection=true;
        while (invalidSelection) {
            System.out.println("Would you like to [a]dd a new book or go-[b]ack to the previous menu?");
            String choice = scanInput.next();
            switch (choice) {
                case "a":
                    // recursive call to the same method to add a new book
                   addNewBook();
                    invalidSelection=false;
                    break;
                case "A":
                    // recursive call to the same method case insensitive
                    addNewBook();
                    invalidSelection=false;
                    break;
                case "b":
                    invalidSelection=false;
                    break;

                case "B":

                    invalidSelection=false;
                    break;
                default:
                    System.out.println("Invalid selection please enter [a] to add new member or [b] to go back to menu");
                    invalidSelection=true;
            }
        }


    }//end of addNewBook()


    public void  processBorrowing(){

        // this method processes borrowing
        Book bookBorrowed;
        boolean error=true;
        int id=0;
        Scanner scanInput = new Scanner(System.in);
do {
    try {
        System.out.println("Please enter a valid member ID:");

        id = scanInput.nextInt();
        if (id>0){
            error=false;
        }
        else {
            System.out.println("A valid member ID must be a positive integer");
            error=true;
        }
    }
    catch (InputMismatchException e){
        System.out.println("A valid member ID must be a positive integer");
        scanInput.next();
    }
}
while (error); // do while with try catch



    if (members.size() > 0) {
        boolean memberExists = false;
        boolean bookIDvalid=false;
        int bookId=0;
        for (Member member : members) {
            if (id == member.getId()) {
                boolean bookExists = false;
                while (!bookExists) {
                   do {
                       try {
                           System.out.println("Please enter a valid book ID for borrowing by member [" + member.getName() + "]:");
                          bookId = scanInput.nextInt();
                          if (bookId>0){
                              bookIDvalid=true;
                          }
                          else {
                              System.out.println("A valid book Id must be a positive integer");
                              bookIDvalid=false;
                          }
                       }
                       catch (InputMismatchException e){
                           System.out.println("A valid book Id must be a positive integer");
                           bookIDvalid=false;
                           scanInput.next();
                       }
                   }
                   while (!bookIDvalid);
                    if (books.size() > 0) {

                        for (Book book : books) {
                            if (bookId == book.getBookId() && book.getNumberOfCopies() > 0) {
                                bookBorrowed = book;
                                member.addBook(book);
                                bookBorrowed.membersHire(member);
                                // get the number of copies of a book subtract one and set the number of copies to this new number
                                book.setNumberOfCopies(book.getNumberOfCopies() - 1);
                                bookExists = true;
                                System.out.println("*** Borrowing processed successfully***");
                                System.out.println("Member:" + member.getName());
                                System.out.println("Borrowed Book title: " + bookBorrowed.getTitle());
                                System.out.println("Number of books remaining: " + bookBorrowed.getNumberOfCopies());

                                break;

                            } else if (bookId == book.getBookId() && book.getNumberOfCopies() == 0) {
                                System.out.println("Sorry!!! Book titled [" + book.getTitle() + "] currently is not available for borrowing.");
                                bookExists = true;

                            } else if (bookId != book.getBookId()) {
                                bookExists = false;
                            }
                        }
                        if (!bookExists) {
                                /*print book exist only once if flag evaluates as true
                                  Instead of printing in with every iteration */
                            System.out.println("Book does not exist");
                        }
                    } // end of if(book.size>0)
                    else {
                        System.out.println("No book exits in the Library. Please add new books");
                        break;
                    }


                }
                //if member exists execute the code block above , set memberExists flag according and break the loop...
                memberExists = true;

                break;
            }// end of if(id == member.getId())
            else {
                // if id does not match any member in array list flag remains false
                memberExists = false;

            }

        }// end of for(Member member: members)
        if (!memberExists) {
            System.out.println("User with Id [" + id + "] does not exist");
        }
    }// if (members.size()>0)
    else {
        System.out.println("Please add new members before borrowing");
    }

        // begin of control statement to ask for further user action
        boolean invalidSelection=true;
        while (invalidSelection) {
            System.out.println("Would you like to [p]rocess a new borrowing or go-[b]ack to the previous menu?" );
            String choice = scanInput.next();
            switch (choice) {
                case "p":
                    // recursive call to the same method to process borrowing
                    processBorrowing();
                    invalidSelection=false;
                    break;

                case "b":
                    invalidSelection=false;
                    break;

                default:
                    System.out.println("Invalid selection please enter [a] to add new member or [b] to go back to menu");
                    invalidSelection=true;
            }
        }

    }//end of processBorrowing()



    public void viewMemberStatus(){
// this method is use to view member status
        boolean memberExist=false;
        Scanner scanInput = new Scanner(System.in);

        System.out.println("Please Enter a valid Member Id");

        int memberId= scanInput.nextInt();
        if (members.size()>0){
            for (Member member:members) {
                if (memberId == member.getId()) {
                    System.out.println("Member [" + member.getId() + "] : " + member.getName());
                    ArrayList<Book> books = member.getBooksBorrowed();
                    if (books.size() > 0) {
                        System.out.print("Borrowed Books : ");

                        books.forEach(book -> System.out.print(book.getTitle() + ","));
                        System.out.println();
                    } else {
                        System.out.println("Borrowed Books: None");
                    }
                    memberExist=true;
                    break;
                }
                else {
                   memberExist=false;
                }
            }
            if (!memberExist){
                System.out.println("Member with ID [" +memberId+"] does not exist");
            }
        }
        else{
System.out.println("Please Add Members first before checking status");
System.out.println();
        }
        boolean invalidSelection=true;
        while (invalidSelection) {
            System.out.println(" Would you like to view a new member’s [s]tatus or go-[b]ack to the previous menu?" );
            String choice = scanInput.next();
            switch (choice) {
                case "s":
                    // recursive call to the same method to view book status
                   viewMemberStatus();
                    invalidSelection=false;
                    break;

                case "b":
                    invalidSelection=false;
                    break;

                default:
                    System.out.println("Invalid selection please enter [s] to view a new member's status or [b] to go back to menu");
                    System.out.println();
                    invalidSelection=true;
            }
        }
    } //end of viewMemberStatus


    public void viewBookStatus(){
        // this method is used to view the status of a book

        Scanner scanInput = new Scanner(System.in);
        boolean error= true;
        while (error) {
            try{
            System.out.println("Please Enter a valid Book Id");
            int bookId = scanInput.nextInt();
            if (bookId > 0) {
                if (books.size() > 0) {
                    for (Book book : books) {
                        if (bookId == book.getBookId()) {
                            System.out.println("Book [" + book.getBookId() + "] : " + book.getTitle());
                            System.out.println("Available Number Of Copies: " + book.getNumberOfCopies());
                            ArrayList<Member> members = book.getMembers();
                            error= false;
                            if (members.size() > 0) {
                                System.out.println("List of Members borrowing: ");
                                members.forEach(member -> System.out.print(member.getName() + ","));
                                System.out.println();

                            } else {
                                System.out.println("List of Members borrowing: None");

                            }
                        }
                        else {
                            System.out.println("Book with Id [" +bookId+"], does not exist in the library");
                            error=false;
                        }
                    }
                } else {
                    System.out.println("No books in the library yet! Add books first before checking status");
                    error=false;
                }
            }
            else {
                System.out.println("A valid book Id must be a positive integer");
            }
            }
            catch (InputMismatchException e){
                System.out.println("A valid book Id must be a positive integer");
                scanInput.next();
            }

        }//end of while (!error)


        boolean invalidSelection=true;
        while (invalidSelection) {
            System.out.println("Would you like to view a new book [s]tatus or go-[b]ack to the previous menu?");
            String choice = scanInput.next();
            switch (choice) {
                case "s":
                    // recursive call to the same method to view book status
                    viewBookStatus();
                    invalidSelection=false;
                    break;

                case "b":
                    invalidSelection=false;
                    break;

                default:
                    System.out.println("Invalid selection please enter [s] to view book status or [b] to go back to menu");
                    System.out.println();
                    invalidSelection=true;
            }
        }
    }//end of viewBookStatus

    public void processReturn(){
        Scanner scanInput = new Scanner(System.in);
        boolean error=true;
        boolean memberNotFound=true;
        ArrayList<Book> borrowedBooks;

        do {
            try {
                System.out.println("Please enter a valid member ID:");
                int id = scanInput.nextInt();
                if(id>0){
                if (members.size() > 0) {
                    for (Member member : members) {
                        if (id == member.getId()) {
                            memberNotFound=false;
                            boolean returnBookIDValid;

                            do {
                                System.out.println("Please enter a valid book ID for returning by member [" + member.getName() + "]:");
                                try {
                                    int returnBookID = scanInput.nextInt();
                                    if (returnBookID>0){
                                        returnBookIDValid=true;
                                       borrowedBooks= member.getBooksBorrowed();
                                       boolean bookNotInBorrowedBooks=false;
                                       if(borrowedBooks.size()>0) {
                                           for (Book book : borrowedBooks) {
                                               if (returnBookID == book.getBookId()) {
                                                   book.setNumberOfCopies(book.getNumberOfCopies() + 1);
                                                   borrowedBooks.removeIf(book1 -> book1.getBookId() == book.getBookId());
                                                   member.setBooksBorrowed(borrowedBooks);
                                                   book.removeMemberFromHire(member);
                                                   bookNotInBorrowedBooks = false;
                                                   System.out.println("*** Returning processed successfully***");
                                                   System.out.println("Member:" + member.getName());
                                                   System.out.println("Book returned: " + book.getTitle());
                                                   System.out.println("After returning Number of books [" + book.getTitle() + "] available in stock: " + book.getNumberOfCopies());
                                                   break;

                                               } else {
                                                   bookNotInBorrowedBooks = true;
                                               }
                                           }
                                       }
                                       else{

                                           System.out.println("Member has no borrowed books");
                                           System.out.println();

                                        }
                                       if(bookNotInBorrowedBooks){
                                           System.out.println("Book Not in Member's Borrowed List");
                                       }

                                    }
                                    else {
                                        System.out.println("A valid book ID must be a positive integer number");
                                        returnBookIDValid=false;
                                    }


                                } catch (InputMismatchException e) {
                                    System.out.println("A valid book ID must be a positive integer number");
                                    returnBookIDValid = false;
                                    scanInput.next();

                                }
                            }
                            while (!returnBookIDValid);
                            break; // break out of for loop if member found...
                        }// if (id == member.getId)
                        else {
                            memberNotFound=true;
                        }


                    }
                    if (memberNotFound){
                        System.out.println("Member with ID ["+id+"] does not exist");
                    }
                    error=false;

                }// end of  if (members.size()>0)
                else {
                    System.out.println("No member exist in the library yet!");
                    error=false;
                }
            }
            else {
                    System.out.println("Member ID must be a positive integer number");
                    error=true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Member ID must be a positive integer number");
                scanInput.next();
                error=true;
            }
        }
        while (error);


        boolean invalidSelection=true;
        while (invalidSelection) {
            System.out.println(" Would you like to [p]rocess a new returning or go-[b]ack to the previous menu?");
            String choice = scanInput.next();
            switch (choice) {
                case "p":
                    // recursive call to the same method to view book status
                   processReturn();
                    invalidSelection=false;
                    break;

                case "b":
                    invalidSelection=false;
                    break;

                default:
                    System.out.println("Invalid selection please enter [p] to process a new returning or [b] to go back to menu");
                    System.out.println();
                    invalidSelection=true;
            }
        }

    }

}//public class Menu
