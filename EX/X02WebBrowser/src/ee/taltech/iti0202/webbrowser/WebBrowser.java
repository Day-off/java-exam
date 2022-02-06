package ee.taltech.iti0202.webbrowser;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class WebBrowser {
    public static Stack<String> back_stack = new Stack<>();
    public static Stack<String> forward_stack = new Stack<>();
    public static String current_web = "google.com";
    public static String home_page = "google.com";
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
        addHistory(current_web);
        current_web = home_page;
    }

    /**
     * Goes back to previous page.
     */
    public void back() {
        addHistory(current_web);
        forward_stack.push(current_web);
        if (back_stack.size() >= 1){
            current_web  = back_stack.pop();
        }
    }

    /**
     * Goes forward to next page.
     */
    public void forward() {
        addHistory(current_web);
        back_stack.push(current_web);
        if (forward_stack.size() >= 1){
            current_web  = forward_stack.pop();
        }
    }

    /**
     * Go to a webpage.
     *
     * @param url url to go to
     */
    public void goTo(String url) {
        addHistory(current_web);
        back_stack.push(current_web);
        current_web = url;
    }

    /**
     * Add a webpage as a bookmark.
     */
    public void addAsBookmark() {
        bookmakrs.add(current_web);
    }

    /**
     * Remove a bookmark.
     *
     * @param bookmark to remove
     */
    public void removeBookmark(String bookmark) {
        bookmakrs.remove(current_web);
    }

    public List<String> getBookmarks() {
        return bookmakrs;
    }

    public void setHomePage(String homePage) {
        home_page = homePage;
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
        return current_web;
    }

}
