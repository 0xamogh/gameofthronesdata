package amoghjapps.com.gameofgames.Model;
//this class will contain all the variables which i want to extract from the api host

import retrofit2.http.GET;

public class ModelClass {
    final Data data;
    final String message;

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public ModelClass(Data data, String message) {

        this.data = data;
        this.message = message;
    }

    public static class Data{
        final String _id;
        final String name;
        final boolean male;
        final String imageLink;
        final String title;
        final String culture;
        final int dateOfBirth;
        final int dateOfDeath;
        final String mother;
        final String father;
        final String heir;
        final String house;

        public String get_id() {
            return _id;
        }

        public String getName() {
            return name;
        }

        public boolean isMale() {
            return male;
        }

        public String getImageLink() {
            return imageLink;
        }

        public String getTitle() {
            return title;
        }

        public String getCulture() {
            return culture;
        }

        public int getDateOfBirth() {
            return dateOfBirth;
        }

        public int getDateOfDeath() {
            return dateOfDeath;
        }

        public String getMother() {
            return mother;
        }

        public String getFather() {
            return father;
        }

        public String getHeir() {
            return heir;
        }

        public String getHouse() {
            return house;
        }

        public Data(String _id, String name, boolean male, String imageLink, String title, String culture, int dateOfBirth, int dateOfDeath, String mother, String father, String heir, String house) {
            this._id = _id;
            this.name = name;
            this.male = male;
            this.imageLink = imageLink;
            this.title = title;
            this.culture = culture;
            this.dateOfBirth = dateOfBirth;
            this.dateOfDeath = dateOfDeath;
            this.mother = mother;
            this.father = father;
            this.heir = heir;
            this.house = house;
        }
    }
}
