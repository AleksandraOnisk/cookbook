package pl.olita.cookbook;

public enum Category {

    BREAKFAST("ŚNIADANIE"), LUNCH("LUNCH"), DINNER("OBIAD"), DESSERT("DESER"), SUPPER("KOLACJA"), OTHER("INNE");

    private String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
