package ee.taltech.iti0202.webbrowser;

import com.sun.jdi.Value;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

public class WebBrowser {
    private String homePage;

    public String homepage = "google.com";
    public Stack<String> back = new Stack<>();
    public Stack<String> forward = new Stack<>();
    public String currentPage = "google.com";
    public List<String> history = new ArrayList<>();
    public List<String> bookMark = new ArrayList<>();
    public List<Integer> sortedList = new ArrayList<>();
    public HashMap<Integer, String> map = new HashMap<>();
    public String ans = "";
    public int top3 = 1;

    public void history() {
        if (history.size() != 0) {
            if (!Objects.equals(currentPage, history.get(history.size() - 1))) {
                history.add(currentPage);
            }
        } else {
            history.add(currentPage);
        }
    }

    /**
     * get Home page
     */
    public WebBrowser() {
        homePage();
    }

    /**
     * Goes to homepage.
     */
    public void homePage() {
        goTo(homepage);
    }

    /**
     * Goes back to previous page.
     */
    public void back() {
        forward.push(currentPage);
        if (back.size() >= 1) {
            currentPage = back.pop();
        }
        history();
    }

    /**
     * Goes forward to next page.
     */
    public void forward() {
        back.push(currentPage);
        if (forward.size() >= 1) {
            currentPage = forward.pop();
        }
        history();
    }

    /**
     * Go to a webpage.
     *
     * @param url url to go to
     */
    public void goTo(String url) {
        back.push(currentPage);
        currentPage = url;
        forward.clear();
        history();
    }

    /**
     * Add a webpage as a bookmark.
     */
    public void addAsBookmark() {
        if (bookMark.size() != 0) {
            if (!bookMark.contains(currentPage)) {
                bookMark.add(currentPage);
            }
        } else {
            bookMark.add(currentPage);
        }
    }

    /**
     * Remove a bookmark.
     *
     * @param bookmark to remove
     */
    public void removeBookmark(String bookmark) {
        bookMark.remove(currentPage);
    }

    public List<String> getBookmarks() {
        return bookMark;
    }

    public void setHomePage(String homePage) {
        homepage = homePage;
    }

    public void sort() {
        List<Integer> top = new ArrayList<>();
        List<String> topKey = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (String page : history) {
            if (!map.containsKey(page)) {
                int amount = Collections.frequency(history, page);
                map.put(page, amount);
                top.add(amount);
                topKey.add(page);
            }
        }
        String k = "";
        sortedList = top.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (Integer amount : sortedList) {
            if (top3 <= 3) {
                for (String key : topKey) {
                    if (amount.equals(map.get(key))) {
                        k = key;
                        break;
                    }
                }
                if (amount == 1) {
                    ans += k + " - " + amount + " visit" + "\n";
                } else {
                    ans += k + " - " + amount + " visits" + "\n";
                }
                map.remove(k);
                top3 += 1;
            } else {
                break;
            }
        }
    }

    /**
     * Get top 3 visited pages.
     *
     * @return a String that contains top three visited pages separated with a newline "\n"
     */
    public String getTop3VisitedPages() {
        sort();
        return ans;
    }

    /**
     * Returns a list of all visited pages.
     * <p>
     * Not to be confused with pages in your back-history.
     * <p>
     * For example, if you visit "facebook.com" and hit back(),
     * then the whole history would be: ["google.com", "facebook.com", "google.com"]
     *
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
        return currentPage;
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
//        System.out.print(mari.getHistory() + "\n");
//        System.out.print("");
//
//        WebBrowser a = new WebBrowser();
//        a.setHomePage("neti.ee");
//        a.goTo("facebook.com");
//        a.forward();
//        a.forward();
//        System.out.print(a.getHistory() + "\n");
//        System.out.print("");
//
//        WebBrowser j = new WebBrowser();
//        j.setHomePage("neti.ee");
//        j.goTo("facebook.com");
//        j.back();
//        j.homePage();
//        j.forward();
//        System.out.print(j.getHistory() + "\n");
//        System.out.print(j.getTop3VisitedPages() + "\n");
//        System.out.print("");
//
//
//        WebBrowser jhon = new WebBrowser();
//        System.out.print(jhon.getCurrentUrl() + "\n");
//        jhon.setHomePage("neti.ee");
//        jhon.goTo("facebook.com");
//        System.out.print(jhon.getCurrentUrl() + "\n");
//        jhon.goTo("google.com");
//        System.out.print(jhon.getCurrentUrl() + "\n");
//        jhon.back();
//        System.out.print(jhon.getCurrentUrl() + "\n");
//        jhon.addAsBookmark();
//        jhon.forward();
//        System.out.print(jhon.getCurrentUrl() + "\n");
//        jhon.homePage();
//        System.out.print(jhon.getCurrentUrl() + "\n");
//        jhon.addAsBookmark();
//        System.out.print(jhon.getBookmarks() + "\n");
//        System.out.print(jhon.getHistory() + "\n");
//        System.out.print(jhon.getTop3VisitedPages() + "\n");
//        System.out.print("");

        WebBrowser g = new WebBrowser();
        g.setHomePage("neti.ee");
        g.goTo("facebook.com");
        g.goTo("mangalib.com");
        g.homePage();
        System.out.print(g.getHistory() + "\n");
        System.out.print(g.getTop3VisitedPages() + "\n");
    }

}
