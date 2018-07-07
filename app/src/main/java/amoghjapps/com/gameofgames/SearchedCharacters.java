package amoghjapps.com.gameofgames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import amoghjapps.com.gameofgames.RecyclerView.SearchedRecyclerAdapter;

public class SearchedCharacters extends AppCompatActivity {
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_characters);
        recyclerView=findViewById(R.id.recyclerView);

        SearchedRecyclerAdapter adapter=new SearchedRecyclerAdapter()
    }

}
