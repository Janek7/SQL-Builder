package de.janek.components.select;

import de.janek.components.Component;

public class From extends Component{

    private String table;
    private String alias;

    public From(String table) {
        this(table, null);
    }

    public From(String table, String alias) {
        this.table = table;
        this.alias = alias;
    }

    @Override
    public String getString() {
        return "FROM " + table + (alias != null ? " " + alias : "");
    }
}
