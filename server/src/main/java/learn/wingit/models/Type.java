package learn.wingit.models;

public enum Type {
    COMMERCIAL(1, "Commercial"),
    CARGO(2, "Cargo"),
    PRIVATE(3, "Private");

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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
