package com.example.project_ecommerce.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.project_ecommerce.ProductActivity;
import com.example.project_ecommerce.R;
import com.example.project_ecommerce.UserActivity;
import com.example.project_ecommerce.model.Item;
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private Context context;
    private List<Item> list;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public ItemAdapter(Context context, List<Item> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context instanceof ProductActivity){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_user, parent, false);
            return new MyViewHolder(itemView);
        }else{
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
            return new MyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            if(context instanceof ProductActivity){
                holder.userItemName.setText(list.get(position).getName());
                holder.userItemPrice.setText(list.get(position).getId());
                holder.userItemStock.setText(list.get(position).getQuantity());

                Glide.with(context)
                        .load(list.get(position).getPicture())
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                holder.progressBarUser.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                holder.progressBarUser.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .error(R.drawable.ic_baseline_error_24)
                        .into(holder.userItemPicture);
            }else{
                holder.idItem.setText(list.get(position).getId());
                holder.nameItem.setText(list.get(position).getName());
                holder.quantityItem.setText(list.get(position).getQuantity());

                Glide.with(context)
                        .load(list.get(position).getPicture())
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                holder.progressBar.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                holder.progressBar.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .error(R.drawable.ic_baseline_error_24)
                        .into(holder.pictureItem);
            }
        }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView idItem, nameItem, quantityItem;
        ImageView pictureItem;
        ProgressBar progressBar;

        TextView userItemName, userItemPrice, userItemStock;
        ImageView userItemPicture;
        ProgressBar progressBarUser;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idItem = (TextView) itemView.findViewById(R.id.itemId);
            nameItem = (TextView) itemView.findViewById(R.id.itemName);
            quantityItem = (TextView) itemView.findViewById(R.id.itemQuantity);
            pictureItem = (ImageView) itemView.findViewById(R.id.itemImage);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress);

            userItemName = (TextView) itemView.findViewById(R.id.userItemName);
            userItemPrice = (TextView) itemView.findViewById(R.id.userItemPrice);
            userItemStock = (TextView) itemView.findViewById(R.id.userItemStock);
            userItemPicture = (ImageView) itemView.findViewById(R.id.userItemPict);
            progressBarUser = (ProgressBar) itemView.findViewById(R.id.progressBarUser);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
