package amoghjapps.com.gameofgames;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.util.SortedList;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import amoghjapps.com.gameofgames.Adapter.RetrofitAdapter;
import amoghjapps.com.gameofgames.Adapter.urlManager;
import amoghjapps.com.gameofgames.Model.ModelClass;
import amoghjapps.com.gameofgames.RecyclerView.Character;
import amoghjapps.com.gameofgames.RecyclerView.RecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public SearchView searchView;
    public RecyclerView recyclerView;
    public ImageView imageView;
    public CollapsingToolbarLayout mCollapsingToolbarLayout;
    public String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle("Name");
        setSupportActionBar(toolbar);
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
                        ModelClass.Data characterList=response.body().getData();
                        Picasso.get().load(urlManager.imageBase+characterList.getImageLink()).into(imageView);
                        mCollapsingToolbarLayout.setTitle(characterList.getName());
                        if(characterList.isMale()==true){
                            gender="male";
                        }else{
                            gender="female";
                        }
                        //creating data model
                        ArrayList<Character> data=new ArrayList<>();
                        //1
                        if(characterList.getTitle()!=null)
                            data.add(new Character("Title:",characterList.getTitle()));
                        //2
                        //extra
                        data.add(new Character("Gender",gender));
                        if(characterList.getCulture()!=null)
                            data.add(new Character("Culture:",characterList.getCulture()));
                        //3
                        if(Integer.toString(characterList.getDateOfBirth())!=null)
                            data.add(new Character("Date of Birth:",Integer.toString(characterList.getDateOfBirth())));
                        //4
                        if(Integer.toString(characterList.getDateOfDeath())!="0")
                            data.add(new Character("Date of Death:",Integer.toString(characterList.getDateOfDeath())));
                        //5
                        if(characterList.getHeir()!=null)
                            data.add(new Character("Heir:",characterList.getHeir()));
                        //6
                        if(characterList.getFather()!=null)
                            data.add(new Character("Father:",characterList.getFather()));
                        //7
                        if(characterList.getMother()!=null)
                            data.add(new Character("Mother:",characterList.getMother()));
                        //8
                        if(characterList.getHouse()!=null)
                            data.add(new Character("House:",characterList.getHouse()));






                        //initialize recycler view;
                        RecyclerViewAdapter adapter=new RecyclerViewAdapter(data);
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(adapter);

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
