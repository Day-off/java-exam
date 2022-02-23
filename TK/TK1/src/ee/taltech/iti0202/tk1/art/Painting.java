package ee.taltech.iti0202.tk1.art;

public class Painting {
    private String namepainting;
    private String authorpain;

    public Painting(String name, String author) {
        setTitle(name);
        setAuthor(author);
    }

    public Painting(String name) {
        setTitle(name);
        setAuthor("an unknown artist");
    }

    /***
     * Setter
     */
    public void setTitle(String name) {
        namepainting = name;
    }

    /***
     * Setter
     */
    public void setAuthor(String author) {
        authorpain = author;
    }

    /***
     * Getter
     * @return String
     */
    public String getTitle() {
        return namepainting;
    }

    /***
     * Getter
     * @return String
     */
    public String getAuthor() {
        return authorpain;
    }

    @Override
    public String toString() {
        return "This is a painting named " + namepainting + " and made by " + authorpain + ".";
    }
}
