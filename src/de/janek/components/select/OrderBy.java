package de.janek.components.select;

import de.janek.components.Component;

public class OrderBy extends Component{

    private String attr;
    private OrderType orderType;

    public OrderBy(String attr, OrderType orderType) {
        this.attr = attr;
        this.orderType = orderType;
    }

    @Override
    public String getString() {
        return "ORDER BY " + attr + " " + orderType;
    }

}
