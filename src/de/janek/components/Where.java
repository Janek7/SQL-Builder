package de.janek.components;

/**
 * Symbolizes a filter of the dataset
 * MySQL: 'WHERE attr = value'
 *
 * @author Janek7
 */
public class Where extends Component {

    private String attr;
    private Object value;

    /**
     * creates a new where component
     *
     * @param attr  filter attribute
     * @param value filter value
     */
    public Where(String attr, Object value) {
        this.attr = attr;
        this.value = value;
    }

    /**
     * @see Component#getString()
     */
    @Override
    public String getString() {
        return attr;
    }

    public Object getValue() {
        return value;
    }

}
