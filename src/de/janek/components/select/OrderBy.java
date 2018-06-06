package de.janek.components.select;

import de.janek.components.Component;

/**
 * Symbolizes the order of the dataset in a SELECT statement
 * MySQL: 'ORDER BY attr ORDER_TYPE'
 *
 * @author Janek7
 */
public final class OrderBy extends Component {

    private String attr;
    private OrderType orderType;

    /**
     * creates a new order by component
     *
     * @param attr      attribute to sort by
     * @param orderType order direction
     */
    public OrderBy(String attr, OrderType orderType) {
        this.attr = attr;
        this.orderType = orderType;
    }

    /**
     * @see Component#getString()
     */
    @Override
    public String getString() {
        return "ORDER BY " + attr + " " + orderType;
    }

    public String getAttr() {
        return attr;
    }
}
