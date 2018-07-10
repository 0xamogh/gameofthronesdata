package amoghjapps.com.gameofgames;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import amoghjapps.com.gameofgames.Adapter.RetrofitAdapter;
import amoghjapps.com.gameofgames.Adapter.urlManager;
import amoghjapps.com.gameofgames.Model.ModelClass;
import amoghjapps.com.gameofgames.RecyclerView.Character;
import amoghjapps.com.gameofgames.RecyclerView.RecyclerViewAdapter;
import amoghjapps.com.gameofgames.SearchHistoryActions.SearchedCharacters;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public SearchView searchView;
    public RecyclerView recyclerView;
    public ImageView imageView;
    public CollapsingToolbarLayout mCollapsingToolbarLayout;
    public String gender;
    Button button;
    public static ArrayList<String> namestring;
    public static ArrayList<String> ids=new ArrayList<>();
    private Context parent;
    private FileInputStream fileIn;
    private FileOutputStream fileOut;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private Object outputObject;
    private String filePath;

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
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SearchedCharacters.class);
                startActivity(intent);
            }
        });

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
                        saveJSONasText(response.body().getData());

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

        ids.add(character.get_id());

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
    public void saveJSONasText(ModelClass.Data data){
        String jsonfile=data.toString();
        FileOutputStream fos=null;
        try {
            fos=openFileOutput(data.get_id().toLowerCase()+".json",MODE_PRIVATE);
            fos.write(jsonfile.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void writeObject(Object inputObject, String fileName){
        try {
            filePath = parent.getApplicationContext().getFilesDir().getAbsolutePath() + "/" + fileName;
            fileOut = new FileOutputStream(filePath);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(inputObject);
            fileOut.getFD().sync();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOut != null) {
                try {
                    objectOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
