package ee.taltech.iti0202.tk1.art;

public class Painting {
    private String nameP;
    private String authorP;

    public Painting(String name, String author) {

    }

    public void setTitle(String nameP) {
        nameP = nameP;
    }

    public void setAuthor(String authorP) {
        authorP = authorP;
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
