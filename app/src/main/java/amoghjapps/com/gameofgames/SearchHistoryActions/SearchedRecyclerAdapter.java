package amoghjapps.com.gameofgames.SearchHistoryActions;

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

import java.util.ArrayList;
import java.util.List;

import amoghjapps.com.gameofgames.R;

public class SearchedRecyclerAdapter extends RecyclerView.Adapter<SearchedRecyclerAdapter.SearchedMyViewHolder> {

     //random color generator
    public List<SearchedItemModel> listattributes;
    private OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
         this.listener=listener;
    }
    public static class SearchedMyViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public ImageView image;
        public SearchedMyViewHolder(View v, final OnItemClickListener listener1){
            super(v);
            name=v.findViewById(R.id.gmailitem_title);
            image=v.findViewById(R.id.gmailitem_letter);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener1!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener1.onItemClick(position);
                        }
                    }

                }
            });



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
        return new SearchedMyViewHolder(iitemView,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchedMyViewHolder holder, int position) {
        SearchedItemModel item = listattributes.get(position);
        //to be completed
        holder.name.setText(item.getNamestring());
    }

    @Override
    public int getItemCount() {
        return listattributes.size();
    }
    public void filterList(ArrayList<SearchedItemModel> filteredList) {
        SearchedCharacters.array = filteredList;
        notifyDataSetChanged();
    }
}
