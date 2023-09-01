package enums;

public enum SortReference {

    idAsc("id,asc");

    private final String description;

    SortReference(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
