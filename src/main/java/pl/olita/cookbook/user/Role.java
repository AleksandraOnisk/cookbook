package pl.olita.cookbook.user;


public enum Role {

    ROLE_USER("Użytkownik systemu"), ROLE_ADMIN("Administrator systemu");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}


