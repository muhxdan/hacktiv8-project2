package com.example.project_ecommerce.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_ecommerce.R;
import com.example.project_ecommerce.model.Item;

import org.w3c.dom.Text;

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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.idItem.setText(list.get(position).getId());
        holder.nameItem.setText(list.get(position).getName());
        holder.quantityItem.setText(list.get(position).getQuantity());
        holder.pictureItem.setText(list.get(position).getPicture());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView idItem, nameItem, quantityItem, pictureItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idItem = (TextView) itemView.findViewById(R.id.itemId);
            nameItem = (TextView) itemView.findViewById(R.id.itemName);
            quantityItem = (TextView) itemView.findViewById(R.id.itemQuantity);
            pictureItem = (TextView) itemView.findViewById(R.id.itemPicture);

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
