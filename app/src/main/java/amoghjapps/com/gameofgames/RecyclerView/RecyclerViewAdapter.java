package amoghjapps.com.gameofgames.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.Character;
import java.util.List;

import amoghjapps.com.gameofgames.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<amoghjapps.com.gameofgames.RecyclerView.Character> attributes;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView attribute,attribute_value;
        public MyViewHolder(View v){
            super(v);
            attribute=v.findViewById(R.id.attribute);
            attribute_value=v.findViewById(R.id.attribute_value);

        }

    }

    public RecyclerViewAdapter(List<amoghjapps.com.gameofgames.RecyclerView.Character> attributes) {
        this.attributes = attributes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View itemView= LayoutInflater.from(parent.getContext())
              .inflate(R.layout.listiem,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        amoghjapps.com.gameofgames.RecyclerView.Character character=attributes.get(position);
        holder.attribute_value.setText(character.getAttribute_value());
        holder.attribute.setText(character.getAttribute());
    }

    @Override
    public int getItemCount() {
        return attributes.size();
    }

}
