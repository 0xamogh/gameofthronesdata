package amoghjapps.com.gameofgames;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import amoghjapps.com.gameofgames.Adapter.RetrofitAdapter;
import amoghjapps.com.gameofgames.Adapter.urlManager;
import amoghjapps.com.gameofgames.Model.ModelClass;
import amoghjapps.com.gameofgames.RecyclerView.Character;
import amoghjapps.com.gameofgames.RecyclerView.RecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public SearchView searchView;
    public RecyclerView recyclerView;
    public ImageView imageView;
    public CollapsingToolbarLayout mCollapsingToolbarLayout;
    public String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle("Name");
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.hasFixedSize();
        LinearLayoutManager llm=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        searchView=findViewById(R.id.seachView);
        imageView=findViewById(R.id.imageView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                RetrofitAdapter.GOTInterface gotInterface=RetrofitAdapter.getClient();
                Call<ModelClass> call=gotInterface.getChracterInfo(query);
                call.enqueue(new Callback<ModelClass>() {
                    @Override
                    public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                        //creating data model
                        ArrayList<Character> characterData= setCharacterAttributes(response.body().getData());
                        //initialize recycler view;
                        initializeRecycler(characterData);

                        Log.d("status","Success");

                    }

                    @Override
                    public void onFailure(Call<ModelClass> call, Throwable t) {
                        Log.d("status","Failure");
                    }
                });
                return false;

            };


            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public ArrayList<Character> setCharacterAttributes(ModelClass.Data character){

        Picasso.get().load(urlManager.imageBase+character.getImageLink()).into(imageView);
        ArrayList<Character> data=new ArrayList<>();

        mCollapsingToolbarLayout.setTitle(character.getName());
        if(character.isMale()==true){
            gender="male";
        }else{
            gender="female";
        }
        //1
        if(character.getTitle()!=null)
            data.add(new Character("Title:",character.getTitle()));
        //2
        //extra
        data.add(new Character("Gender",gender));
        if(character.getCulture()!=null)
            data.add(new Character("Culture:",character.getCulture()));
        //3
        if(character.getDateOfBirth()!=0)
            data.add(new Character("Date of Birth:",Integer.toString(character.getDateOfBirth())));
        //4
        if(character.getDateOfDeath()!=0)
            data.add(new Character("Date of Death:",Integer.toString(character.getDateOfDeath())));
        //5
        if(character.getHeir()!=null)
            data.add(new Character("Heir:",character.getHeir()));
        //6
        if(character.getFather()!=null)
            data.add(new Character("Father:",character.getFather()));
        //7
        if(character.getMother()!=null)
            data.add(new Character("Mother:",character.getMother()));
        //8
        if(character.getHouse()!=null)
            data.add(new Character("House:",character.getHouse()));
        return data;


    }
    public void initializeRecycler(ArrayList<Character> arrayList){
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(arrayList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }
    public void saveJSON(ModelClass.Data data){

    }
}
