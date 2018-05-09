import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Book {
    private String title;
    private int numberOfCopies;
    private int bookId;
    private static int count=1;
    @NotNull
    private ArrayList<Member> members= new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public int getBookId() {
        return bookId;
    }

    public Book(String title, int numberOfCopies) {
        this.title = title;
        this.numberOfCopies = numberOfCopies;
        bookId=count++;
    }
    public void membersHire(Member member){
        members.add(member);

    }
    public void removeMemberFromHire(Member member){
        members.removeIf(member1 -> member1.getId()== member.getId());
    }
    public ArrayList<Member> getMembers() {
        return members;
    }
}
