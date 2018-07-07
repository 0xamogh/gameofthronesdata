package amoghjapps.com.gameofgames.RecyclerView;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import amoghjapps.com.gameofgames.Adapter.urlManager;
import amoghjapps.com.gameofgames.R;
import amoghjapps.com.gameofgames.SearchedItemModel;

public class SearchedRecyclerAdapter extends RecyclerView.Adapter<SearchedRecyclerAdapter.SearchedMyViewHolder> {

     //random color generator
    ColorGenerator generator = ColorGenerator.MATERIAL;
    public List<SearchedItemModel> listattributes;
    public class SearchedMyViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public ImageView image;
        public SearchedMyViewHolder(View v){
            super(v);
            name=v.findViewById(R.id.gmailitem_title);
            image=v.findViewById(R.id.gmailitem_letter);

        }
    }

    public SearchedRecyclerAdapter(List<SearchedItemModel> listattributes) {
        this.listattributes = listattributes;
    }

    @NonNull
    @Override
    public SearchedMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View iitemView= LayoutInflater.from(parent.getContext())
              .inflate(R.layout.searchedcharactersitemview,parent,false);
        return new SearchedMyViewHolder(iitemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchedMyViewHolder holder, int position) {
        SearchedItemModel item=listattributes.get(position);
        //to be completed
        holder.name.setText(item.getNamestring());
        if(item.getImageuri()==null){
            String letter=String.valueOf(item.getNamestring().charAt(0));
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(letter, generator.getRandomColor());
            holder.image.setImageDrawable(drawable);
        }else{
        holder.image.setImageDrawable(Drawable.createFromPath(item.getImageuri()));
    }}

    @Override
    public int getItemCount() {
        return listattributes.size();
    }
}
