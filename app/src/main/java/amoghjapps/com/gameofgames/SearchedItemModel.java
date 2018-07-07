package amoghjapps.com.gameofgames;

public class SearchedItemModel {
    String namestring;
    String imageuri;

    public String getNamestring() {
        return namestring;
    }

    public void setNamestring(String namestring) {
        this.namestring = namestring;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

    public SearchedItemModel(String namestring, String imageuri) {
        this.namestring = namestring;
        this.imageuri = imageuri;
    }
}
