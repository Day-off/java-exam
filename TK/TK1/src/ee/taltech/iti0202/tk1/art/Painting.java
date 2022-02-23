package ee.taltech.iti0202.tk1.art;

public class Painting {
    private String namepainting;
    private String authorpain = "an unknown artist";

    public Painting(String name, String author) {
        setTitle(name);
        setAuthor(author);
    }

    public Painting(String name) {
        setTitle(name);
    }

    public void setTitle(String name) {
        namepainting = name;
    }

    public void setAuthor(String author) {
        authorpain = author;
    }

    public String getTitle() {
        return namepainting;
    }

    public String getAuthor() {
        return authorpain;
    }

    @Override
    public String toString() {
        return "This is a painting named " + namepainting + " and made by " + authorpain + ".";
    }
}
