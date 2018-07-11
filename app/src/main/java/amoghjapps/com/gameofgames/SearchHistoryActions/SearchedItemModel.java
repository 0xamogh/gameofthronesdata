package amoghjapps.com.gameofgames.SearchHistoryActions;

import amoghjapps.com.gameofgames.DatabaseHandler;
import amoghjapps.com.gameofgames.Model.ModelClass;

public class SearchedItemModel {
    String id;
    String namestring;
    String imageuri;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public SearchedItemModel(String id, String namestring, String imageuri) {
        this.id = id;
        this.namestring = namestring;
        this.imageuri = imageuri;
    }

}
