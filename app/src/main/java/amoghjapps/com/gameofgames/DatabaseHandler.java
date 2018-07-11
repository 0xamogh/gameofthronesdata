package amoghjapps.com.gameofgames;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import amoghjapps.com.gameofgames.Model.ModelClass;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="characterManager";
    private static final String TABLE_CHARACTERS="characters";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_GENDER="gender";
    private static final String KEY_IMAGELINK="imagelink";
    private static final String KEY_TITLE="title";
    private static final String KEY_CULTURE="culture";
    private static final String KEY_DOB="dob";
    private static final String KEY_DOD="dod";
    private static final String KEY_MOM="mom";
    private static final String KEY_DAD="dad";
    private static final String KEY_HEIR="heir";
    private static final String KEY_HOUSE="house";



    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CHARACTERS_TABLE="CREATE TABLE "+ TABLE_CHARACTERS+"("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_NAME+" TEXT,"+KEY_GENDER+
                " TEXT,"+KEY_IMAGELINK+" TEXT,"+KEY_TITLE+" TEXT,"+KEY_CULTURE+" TEXT,"+KEY_DOB+" TEXT,"+KEY_DOD+" TEXT,"+KEY_MOM+" TEXT,"+KEY_DAD+" TEXT,"+KEY_HEIR+" TEXT,"+
                KEY_HOUSE+" TEXT"+")";
        db.execSQL(CREATE_CHARACTERS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHARACTERS);

        // Create tables again
        onCreate(db);
    }

    void addCharacter(ModelClass.Data character){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_NAME,character.getName());
        values.put(KEY_GENDER,character.isMale());
        values.put(KEY_IMAGELINK,character.getImageLink());
        values.put(KEY_CULTURE,character.getCulture());
        values.put(KEY_DOB,character.getDateOfBirth());
        values.put(KEY_DOD,character.getDateOfDeath());
        values.put(KEY_MOM,character.getMother());
        values.put(KEY_DAD,character.getFather());
        values.put(KEY_HEIR,character.getHeir());
        values.put(KEY_HOUSE,character.getHouse());

        db.insert(TABLE_CHARACTERS,null,values);
        db.close();



    }
    ModelClass.Data getData(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_CHARACTERS,new String[]{
                KEY_ID,KEY_NAME,KEY_GENDER,KEY_IMAGELINK,KEY_CULTURE,
                KEY_DOB,KEY_DOD,KEY_MOM,KEY_DAD,KEY_HEIR,KEY_HOUSE},KEY_ID+"=?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        ModelClass.Data data=new ModelClass.Data(cursor.getString(0),
                cursor.getString(1),Boolean.parseBoolean(cursor.getString(2)),cursor.getString(3),cursor.getString(4),
                cursor.getString(5),Integer.parseInt(cursor.getString(6)),Integer.parseInt(cursor.getString(7)),cursor.getString(8),
                cursor.getString(9),cursor.getString(10),cursor.getString(11));
        return data;
        }

    public void deleteCharacter(ModelClass.Data character) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CHARACTERS, KEY_ID + " = ?",
                new String[] { String.valueOf(character.get_id()) });
        db.close();
    }


    public int getCharacterCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CHARACTERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}