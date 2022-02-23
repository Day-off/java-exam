package ee.taltech.iti0202.tk1.art;

public class Painting {
    private String nameP;
    private String authorP;

    public Painting(String name, String author){
        nameP = name;
        authorP = author;
    }

    public String getNameP() {
        return nameP;
    }

    public String getAuthorP() {
        return authorP;
    }
}
