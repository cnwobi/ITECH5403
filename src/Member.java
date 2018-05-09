import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Member {
    private String name;
    private static int count =1;
    private int id;
    @NotNull
    private ArrayList<Book> booksBorrowed = new ArrayList<>();

    public int getId() {
        return id;
    }

    public  void setId(int id) {
       id = id;
    }

    public String getName() {
        return name;
    }

    public Member(String name) {
        id=count++;

        this.name = name;
    }
    public void addBook(Book book){
        booksBorrowed.add(book);

    }

    @NotNull
    public ArrayList<Book> getBooksBorrowed() {
        return booksBorrowed;
    }

    public void setBooksBorrowed(ArrayList<Book> booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }
}
