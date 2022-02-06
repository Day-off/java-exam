package ee.taltech.iti0202.webbrowser;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class WebBrowser {
    public static Stack<String> backstack = new Stack<>();
    public static Stack<String> forwardstack = new Stack<>();
    public static String currentweb = "google.com";
    public static String homepage = "google.com";
    public static List<String> bookmakrs = new ArrayList<>();
    public static List<String> history = new ArrayList<>();



    private String homePage;

    public void addHistory(String page){
        if (history.size() != 0){
            if (!Objects.equals(page, history.get(history.size() - 1))){
                history.add(page);
            }
        }else {
            history.add(page);
        }
    }

    /**
     * Goes to homepage.
     */
    public void homePage() {
        addHistory(currentweb);
        currentweb = homepage;
    }

    /**
     * Goes back to previous page.
     */
    public void back() {
        addHistory(currentweb);
        forwardstack.push(currentweb);
        if (backstack.size() >= 1){
            currentweb  = backstack.pop();
        }
    }

    /**
     * Goes forward to next page.
     */
    public void forward() {
        addHistory(currentweb);
        backstack.push(currentweb);
        if (forwardstack.size() >= 1){
            currentweb  = forwardstack.pop();
        }
    }

    /**
     * Go to a webpage.
     *
     * @param url url to go to
     */
    public void goTo(String url) {
        addHistory(currentweb);
        backstack.push(currentweb);
        currentweb = url;
    }

    /**
     * Add a webpage as a bookmark.
     */
    public void addAsBookmark() {
        bookmakrs.add(currentweb);
        addHistory(currentweb);
    }

    /**
     * Remove a bookmark.
     *
     * @param bookmark to remove
     */
    public void removeBookmark(String bookmark) {
        bookmakrs.remove(currentweb);
        addHistory(currentweb);
    }

    public List<String> getBookmarks() {
        return bookmakrs;
    }

    public void setHomePage(String homePage) {
        homepage = homePage;
    }


    /**
     * Get top 3 visited pageоs.
     *
     * @return a String that contains top three visited pages separated with a newline "\n"
     */
    public String getTop3VisitedPages() {
        return null;
    }

    /**
     * Returns a list of all visited pages.
     *
     * Not to be confused with pages in your back-history.
     *
     * For example, if you visit "facebook.com" and hit back(),
     * then the whole history would be: ["google.com", "facebook.com", "google.com"]
     * @return list of all visited pages
     */
    public List<String> getHistory() {
        return history;
    }


    /**
     * Returns the active web page (string).
     *
     * @return active web page
     */
    public String getCurrentUrl() {
        return currentweb;
    }
    /**
     * Run tests.
     *
     * @param args info.
     */
    public static void main(String[] args) {
//        WebBrowser mari = new WebBrowser();
//        mari.goTo("Facr.com");
//        mari.back();
//        mari.back();
//        System.out.print(mari.getHistory());
//        System.out.print("");

//        WebBrowser jhon = new WebBrowser();
//        jhon.setHomePage("neti.ee");
//        jhon.goTo("facebook.com");
//        jhon.forward();
//        jhon.forward();
//        System.out.print(jhon.getHistory());
//
//        WebBrowser jhon = new WebBrowser();
//        jhon.setHomePage("neti.ee");
//        jhon.goTo("facebook.com");
//        jhon.back();
//        jhon.homePage();
//        jhon.forward();
//        System.out.print(jhon.getHistory());

        WebBrowser jhon = new WebBrowser();
        System.out.print(jhon.getCurrentUrl()+"\n");
        jhon.setHomePage("neti.ee");
        jhon.goTo("facebook.com");
        System.out.print(jhon.getCurrentUrl()+"\n");
        jhon.goTo("google.com");
        System.out.print(jhon.getCurrentUrl()+"\n");
        jhon.back();
        System.out.print(jhon.getCurrentUrl()+"\n");
        jhon.addAsBookmark();
        jhon.forward();
        System.out.print(jhon.getCurrentUrl()+"\n");
        jhon.homePage();
        System.out.print(jhon.getCurrentUrl()+"\n");
        jhon.addAsBookmark();
        System.out.print(jhon.getBookmarks()+"\n");
        System.out.print(jhon.getHistory());


    }

}
