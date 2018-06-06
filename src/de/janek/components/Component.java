package de.janek.components;

/**
 * Symbolizes a component / part of a mysql statement
 *
 * @author Janek7
 */
public abstract class Component {

    /**
     * provides the component as a string for use in a sql statement
     *
     * @return string
     */
    public abstract String getString();

}
