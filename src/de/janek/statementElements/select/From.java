package de.janek.statementElements.select;

public class From {

    private String table;
    private String alias;

    public From(String table) {
        this(table, null);
    }

    public From(String table, String alias) {
        this.table = table;
        this.alias = alias;
    }

    //in basis klasse
    public String getString() {
        return null;
    }

}
