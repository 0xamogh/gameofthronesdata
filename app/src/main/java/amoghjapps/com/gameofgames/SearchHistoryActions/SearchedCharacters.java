package amoghjapps.com.gameofgames.SearchHistoryActions;

import android.graphics.ColorSpace;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.JsonReader;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import amoghjapps.com.gameofgames.Model.ModelClass;
import amoghjapps.com.gameofgames.R;
import amoghjapps.com.gameofgames.RecyclerView.Character;
import retrofit2.Call;

import static amoghjapps.com.gameofgames.MainActivity.ids;

public class SearchedCharacters extends AppCompatActivity {
    public RecyclerView recyclerView;
    public EditText searchView;
    public static ArrayList<SearchedItemModel> array;
    public SearchedRecyclerAdapter adapt;
    private static final Type REVIEW_TYPE = new TypeToken<Character>() {
    }.getType();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_characters);
        recyclerView=findViewById(R.id.recyclerView);
        searchView=findViewById(R.id.searchView);

        Toast.makeText(getApplicationContext(),String.valueOf(ids.size()), Toast.LENGTH_LONG).show();
       try {
           loadandparse(ids.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

          array=new ArrayList<>();






    }
    public void loadandparse(String name) throws IOException {
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String path=getFilesDir()+"/"+name+".json";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        ModelClass character=gson.fromJson(bufferedReader ,ModelClass.class);
        array.add(new SearchedItemModel(character.getData().get_id(),character.getData().getName(),character.getData().getImageLink()));
        initializeRecycler(array,false);

    }

    public void initializeRecycler(ArrayList<SearchedItemModel> array,boolean filter){

            adapt = new SearchedRecyclerAdapter(array);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapt);

    }
    private void filter(String s){
        ArrayList<SearchedItemModel> arrayList=new ArrayList<>();
        for( SearchedItemModel item: array){

            if(item.namestring.toLowerCase().toString().contains(s.toLowerCase())){
                arrayList.add(item);
            }

        }


       initializeRecycler(arrayList,true);
    }

}
