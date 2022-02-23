package ee.taltech.iti0202.tk1.art;

import java.util.ArrayList;
import java.util.List;

public class Collector {
    private List<Painting> plist = new ArrayList<>();

    public Collector() {

    }

    public boolean addPainting(Painting painting) {
        if (!plist.contains(painting)) {
            plist.add(painting);
            return true;
        } else {
            return false;
        }
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
}
