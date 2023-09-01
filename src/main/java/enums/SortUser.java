package enums;

public enum SortUser {

    idAsc("id,asc");

    private final String description;

    SortUser(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
