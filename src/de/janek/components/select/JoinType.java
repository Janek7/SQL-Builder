package de.janek.components.select;

/**
 * enumeration of basic join types in MySQL
 *
 * @author Janek7
 */
public enum JoinType {

    JOIN("JOIN"),
    LEFT_JOIN("LEFT JOIN"),
    RIGHT_JOIN("RIGHT JOIN");

    private String string;

    /**
     * creates a new JoinType
     *
     * @param string MySQL string
     */
    JoinType(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

}
