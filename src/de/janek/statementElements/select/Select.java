package de.janek.statementElements.select;

public class Select {

    private String attr;
    private String as;

    public Select(String attr) {
        this(attr, null);
    }

    public Select(String attr, String as) {
        this.attr = attr;
        this.as = as;
    }

    @Override
    public String toString() {
        return attr + (as != null ? " as " + as : "");
    }

}
