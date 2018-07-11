package amoghjapps.com.gameofgames.SearchHistoryActions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import amoghjapps.com.gameofgames.DatabaseHandler;
import amoghjapps.com.gameofgames.LocalDisplayActivity;
import amoghjapps.com.gameofgames.R;
import amoghjapps.com.gameofgames.RecyclerView.Character;

import static amoghjapps.com.gameofgames.MainActivity.ids;

public class SearchedCharacters extends AppCompatActivity {
    public RecyclerView recyclerView;
    public EditText searchView;
    public static ArrayList<SearchedItemModel> array;
    public SearchedRecyclerAdapter adapt;
    private static final Type REVIEW_TYPE = new TypeToken<Character>() {
    }.getType();
    public DatabaseHandler datab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_characters);
        recyclerView=findViewById(R.id.recycler_view);
        searchView=findViewById(R.id.searchView);

        Toast.makeText(getApplicationContext(),String.valueOf(ids.size()), Toast.LENGTH_LONG).show();

        datab=new DatabaseHandler(getApplicationContext());
        final List<SearchedItemModel> list=datab.getAllCharacters();
        initializeRecycler( list,false);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString(),list);
            }
        });







    }

    /*public void loadandparse(String name) throws IOException {
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String path=getFilesDir()+"/"+name+".json";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        ModelClass character=gson.fromJson(bufferedReader ,ModelClass.class);
        array.add(new SearchedItemModel(character.getData().get_id(),character.getData().getName(),character.getData().getImageLink()));
        initializeRecycler(array,false);

    }*/

    public void initializeRecycler(final List<SearchedItemModel> array, boolean filter){

            adapt = new SearchedRecyclerAdapter(array);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapt);

        adapt.setOnItemClickListener(new SearchedRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                SearchedItemModel item=array.get(position);
                String name=item.namestring;
                Intent intent=new Intent(SearchedCharacters.this, LocalDisplayActivity.class);
                intent.putExtra("id",name);
                startActivity(intent);
            }
        });

    }
    private void filter(String s,List<SearchedItemModel> arrayList){

        for( SearchedItemModel item: arrayList){

            if(item.namestring.toLowerCase().toString().contains(s.toLowerCase())){
                arrayList.add(item);
            }

        }


       initializeRecycler(arrayList,true);
    }

}
