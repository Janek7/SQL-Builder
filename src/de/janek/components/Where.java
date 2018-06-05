package de.janek.components;

public class Where extends Component {

    private String attr;
    private Object value;

    public Where(String attr, Object value) {
        this.attr = attr;
        this.value = value;
    }

    @Override
    public String getString() {
        return attr + " = '" + value + "'";
    }
}
