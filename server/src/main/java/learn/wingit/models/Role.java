package learn.wingit.models;

public enum Role {
    ADMIN(1, "ADMIN"),
    USER(2, "USER");

    private int roleId;
    private String role;

    Role(int roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public static Role getRoleById(int roleId) {
        for(Role role : Role.values()) {
            if(role.roleId == roleId) {
                return role;
            }
        }
        return null;
    }

    public static Role getRoleByName(String name) {
        for(Role role : Role.values()) {
            if(role.role.equalsIgnoreCase(name)) {
                return role;
            }
        }
        return null;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRole() {
        return role;
    }
}


