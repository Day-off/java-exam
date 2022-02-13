package ee.taltech.iti0202.lotr;


public class Ring {
    private final Type r_type;
    private final Material r_material;

    public enum Type {
        THE_ONE, GOLDEN, NENYA, OTHER
    }

    public enum Material {
        GOLD, SILVER, BRONZE, PLASTIC, DIAMOND
    }

    public Ring(Type type, Material material) {
        r_type = type;
        r_material = material;
    }

    public Type getType() {
        return r_type;
    }

    public Material getMaterial() {
        return r_material;
    }
}
