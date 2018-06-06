package de.janek.components.select;

import de.janek.components.Component;

/**
 * Symbolizes the selection of a attribute in a SELECT statement
 * MySQL: 'SELECT attr as alias'
 *
 * Note: {@link Select#as} is optional
 *
 * @author Janek7
 */
public final class Select extends Component {

    private String attr;
    private String as;

    /**
     * creates a new select component
     *
     * @param attr selected attribute
     * @param as   name of the attribute in resultset
     */
    public Select(String attr, String as) {
        this.attr = attr;
        this.as = as;
    }

    /**
     * @see Component#getString()
     */
    @Override
    public String getString() {
        return attr + (as != null ? " as " + as : "");
    }

    public String getAttr() {
        return attr;
    }

}
