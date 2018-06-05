package de.janek.statementElements.select;

public class OrderBy {

    public enum OrderType {
        ASC, DESC;
    }

    private String attr;
    private OrderType orderType;

    public OrderBy(String attr, OrderType orderType) {
        this.attr = attr;
        this.orderType = orderType;
    }

    public String getAttr() {
        return attr;
    }

    public OrderType getOrderType() {
        return orderType;
    }

}
