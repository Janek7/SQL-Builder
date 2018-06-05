package de.janek.statementElements;

public class Where {

    private String attr;
    private Object value;

    public Where(String attr, Object value) {
        this.attr = attr;
        this.value = value;
    }

    public String getAttr() {
        return attr;
    }

    public Object getValue() {
        return value;
    }

}
