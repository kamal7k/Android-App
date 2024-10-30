package com.example.suitcase.Adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suitcase.Item;
import com.example.suitcase.R;

import java.util.ArrayList;

// managing and binding data to the recycler view
// recycler view is responsible for to display the list of items effectively
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    private final RecyclerItemsClickView recyclerItemsClickView;
    private ArrayList<Item> itemsModels;
    // managing and binding the data in recycler view to give specific action.
    public ItemsAdapter(ArrayList<Item>itemsModels, RecyclerItemsClickView recyclerItemsClickView){
        this.recyclerItemsClickView=recyclerItemsClickView;
        this.itemsModels=itemsModels;
    }
    // on create view holder is needed to represent an item to the list
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items,parent,false);    // Inflate the item layout from the parent's context.
        return new ItemViewHolder(view); // Return a new ItemViewHolder associated with the inflated view.
    }
    // on bind view holder is called ViewHolder with data from a specific item in the items list.
    // It sets the text and image views in the ViewHolder based on the Item object at the given position.
    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemViewHolder holder, int position) {
        Item itemsModel=itemsModels.get(position);
        holder.txt_name.setText(itemsModel.getName());
        if (itemsModel.isPurchased()){
            holder.txt_name.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.baseline_check_24,0);
        }
        holder.txt_price.setText(String.valueOf(itemsModel.getPrice()));
        holder.txt_description.setText(itemsModel.getDescription());
        Uri imageUri=itemsModel.getImage();
        if (imageUri !=null){
            holder.imageView.setImageURI(imageUri);
        }
    }
    //Returns the total number of items in the items list, which determines the size of the RecyclerView.
    @Override
    public int getItemCount() {
        return itemsModels.size();
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txt_name,txt_price,txt_description;

        //This inner class represents the ViewHolder for each item in the RecyclerView.
        //It holds references to the views within the item layout (imageView, textViewName, textViewPrice, textViewDescription).
        //It also sets an OnClickListener on the item view itself.
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.item_image);
            txt_name=itemView.findViewById(R.id.item_name);
            txt_price=itemView.findViewById(R.id.item_Price);
            txt_description=itemView.findViewById(R.id.item_dis);
            itemView.setOnClickListener(this::itemViewOnClick);
        }
        //This method is invoked when an item in the RecyclerView is clicked.
        // It triggers the onItemClick method of the itemClickListener, passing the clicked view and the adapter position.

        private void itemViewOnClick(View view){
            recyclerItemsClickView.onItemClick(view,getAdapterPosition());
        }
    }
}