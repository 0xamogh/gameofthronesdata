package amoghjapps.com.gameofgames.RecyclerView;

public class Character {
    String attribute;
    String attribute_value;

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setAttribute_value(String attribute_value) {
        this.attribute_value = attribute_value;
    }

    public String getAttribute() {

        return attribute;
    }

    public String getAttribute_value() {
        return attribute_value;
    }

    public Character(String attribute, String attribute_value) {

        this.attribute = attribute;
        this.attribute_value = attribute_value;
    }

}
