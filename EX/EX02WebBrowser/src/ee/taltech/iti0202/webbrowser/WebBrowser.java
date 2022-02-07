package ee.taltech.iti0202.webbrowser;


import java.util.Stack;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Objects;
import java.util.Collections;
import java.util.Comparator;


public class WebBrowser {
    private String homePage;

    public String homepage = "google.com";
    public Stack<String> back = new Stack<>();
    public Stack<String> forward = new Stack<>();
    public String currentPage = "google.com";
    public List<String> history = new ArrayList<>();
    public List<String> bookMark = new ArrayList<>();
    public List<Integer> sortedList = new ArrayList<>();
    public HashMap<String, Integer> map = new HashMap<>();
    public List<Integer> top = new ArrayList<>();
    public List<String> topKey = new ArrayList<>();
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
}
