package ee.taltech.iti0202.tk1.art;

public class Painting {
    private String nameP;
    private String authorP;

    public Painting(String name, String author) {
        setTitle(name);
        setAuthor(author);
    }

    public void setTitle(String nameP) {
        this.nameP = nameP;
    }

    public void setAuthor(String authorP) {
        this.authorP = authorP;
    }

    public String getTitle() {
        return nameP;
    }

    public String getAuthor() {
        return authorP;
    }

    @Override
    public String toString() {
        return nameP + " " + authorP;
    }
}
