package ee.taltech.iti0202.tk1.art;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Collector {
    private List<Painting> plist = new ArrayList<>();

    public Collector() {

    }

    public boolean addPainting(Painting painting) {
        for (Painting p : plist) {
            if (Objects.equals(p.getTitle(), painting.getTitle())) {
                return false;
            }
        }
        plist.add(painting);
        return true;
    }

    public boolean sellPainting(Painting painting, Collector fellowCollector) {
        if (fellowCollector != this && fellowCollector.addPainting(painting) && this.plist.contains(painting)) {
            plist.remove(painting);
            return true;
        } else {
            return false;
        }
    }

    public List<Painting> getPaintings() {
        return plist;
    }

    public static void main(String[] args) {

        Painting painting1 = new Painting("The last supper", "Leonardo");
        System.out.println(painting1);  // This is a painting named The last supper and made by Leonardo.
        Painting painting2 = new Painting("Woman with Red Head Scarf");
        System.out.println(painting2);  // This is a painting named Woman with Red Head Scarf and made by an unknown artist.

        Painting forgery = new Painting("The last supper", "Leonerdo");
        System.out.println(forgery);  // This is a painting named The last supper and made by Leonerdo.
        System.out.println();

        Collector collector1 = new Collector();
        Collector collector2 = new Collector();

        System.out.println(collector1.addPainting(painting1)); // true
        System.out.println(collector1.addPainting(painting1)); // false
        System.out.println(collector1.addPainting(painting2)); // true
        System.out.println();
        System.out.println(collector2.getPaintings()); // []
        System.out.println(collector1.getPaintings());
// [This is a painting named The last supper and made by Leonardo.,
// This is a painting named Woman with Red Head Scarf and made by an unknown artist.]
        System.out.println();
        System.out.println(collector1.sellPainting(painting1, collector2)); // true
        System.out.println(collector2.sellPainting(painting2, collector1)); // false
        System.out.println(collector1.sellPainting(painting2, collector1)); // false
        System.out.println();
        System.out.println(collector1.getPaintings());
// [This is a painting named Woman with Red Head Scarf and made by an unknown artist.]
        System.out.println(collector2.getPaintings());
// [This is a painting named The last supper and made by Leonardo.]
        System.out.println(collector2.addPainting(forgery)); // false

    }
}
