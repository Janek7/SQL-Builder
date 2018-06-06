package de.janek.components.select;

import de.janek.components.Component;

/**
 * Symbolizes a table join
 *
 * @author Janek7
 */
public class Join extends Component {

    private JoinType joinType;
    private From otherTable;
    private String joinCondition;

    /**
     * creates a new join
     *
     * @param joinType      join tyoe
     * @param otherTable    other table
     * @param joinCondition join condition
     */
    public Join(JoinType joinType, From otherTable, String joinCondition) {

        this.joinType = joinType;
        this.otherTable = otherTable;
        this.joinCondition = joinCondition;

    }

    /**
     * @see Component#getString()
     */
    @Override
    public String getString() {
        return joinType.getString() + " " + otherTable.getTable() + " "
                + (otherTable.getAlias() != null ? otherTable.getAlias() + " " : "") + "ON " + joinCondition;
    }

}
