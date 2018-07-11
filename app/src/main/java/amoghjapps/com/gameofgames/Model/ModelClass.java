package amoghjapps.com.gameofgames.Model;
//this class will contain all the variables which i want to extract from the api host

import retrofit2.http.GET;

public class ModelClass {
     Data data;

    public void setData(Data data) {
        this.data = data;
    }

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
         String _id;
        String name;
        boolean male;
        String imageLink;
        String title;
        String culture;
        int dateOfBirth;
        int dateOfDeath;
        String mother;
        String father;
        String heir;
        String house;

        public void setName(String name) {
            this.name = name;
        }

        public void setMale(boolean male) {
            this.male = male;
        }

        public void setImageLink(String imageLink) {
            this.imageLink = imageLink;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setCulture(String culture) {
            this.culture = culture;
        }

        public void setDateOfBirth(int dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public void setDateOfDeath(int dateOfDeath) {
            this.dateOfDeath = dateOfDeath;
        }

        public void setMother(String mother) {
            this.mother = mother;
        }

        public void setFather(String father) {
            this.father = father;
        }

        public void setHeir(String heir) {
            this.heir = heir;
        }

        public void setHouse(String house) {
            this.house = house;
        }



        public void set_id(String _id){
          _id=this._id;
        }
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
