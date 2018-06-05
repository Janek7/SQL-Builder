package de.janek.components.select;

import de.janek.components.Component;

public class Select extends Component {

    private String attr;
    private String as;

    public Select(String attr, String as) {
        this.attr = attr;
        this.as = as;
    }

    @Override
    public String getString() {
        return attr + (as != null ? " as " + as : "");
    }
}
