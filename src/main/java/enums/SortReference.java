package enums;

public enum SortReference {

    idDesc("id,desc"),
    idAsc("id,asc"),
    titleDesc("title,desc"),
    titleAsc("title,asc"),
    yearAsc("year,asc"),
    yearDesc("year,desc"),
    monthAsc("month,asc"),
    monthDesc("month,desc"),
    noteDesc("note,desc"),
    noteAsc("note,asc");

    private final String description;

    SortReference(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
