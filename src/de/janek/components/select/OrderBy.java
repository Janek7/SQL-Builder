package de.janek.components.select;

import de.janek.components.Component;

/**
 * Symbolizes the order of the dataset in a SELECT statement
 * MySQL: 'ORDER BY attr ORDER_TYPE'
 *
 * @author Janek7
 */
public final class OrderBy extends Component {

    private String column;
    private OrderType orderType;

    /**
     * creates a new order by component
     *
     * @param column      attribute to sort by
     * @param orderType order direction
     */
    public OrderBy(String column, OrderType orderType) {
        this.column = column;
        this.orderType = orderType;
    }

    /**
     * @see Component#getString()
     */
    @Override
    public String getString() {
        return "ORDER BY " + column + " " + orderType;
    }

    public String getColumn() {
        return column;
    }
}
