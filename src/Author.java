public class Author {
    private String name;
    private int yearActive;
    private int yearDie;

    public Author(String name, int yearActive, int yearDie) {
        this.name = name;
        this.yearActive = yearActive;
        this.yearDie = yearDie;
    }

    public String getName() {
        return name;
    }

    public int getYearActive() {
        return yearActive;
    }

    public String getYearDie() {
        return (yearDie == -1) ? "N/A" : String.valueOf(yearDie);
    }
}

