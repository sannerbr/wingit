package learn.wingit.models;

public enum Type {
    COMMERCIAL(1, "commercial"),
    CARGO(2, "cargo"),
    PRIVATE(3, "private");

    private int id;
    private String name;

    Type (int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Type getTypeById(int id) {
        for (Type t : Type.values()) {
            if (t.id == id) {
                return t;
            }
        }
        return null;
    }

    public static Type getTypeByName(String name) {
        for (Type t : Type.values()) {
            if (t.name.equals(name)) {
                return t;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
