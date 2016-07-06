package com.android.pilar.myshoppinglistwear;

import android.content.Context;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class WearableAdapter extends WearableListView.Adapter{

    private ArrayList<Articulo>     mItems;
    private final LayoutInflater    mInflater;

    // Constructor de la clase
    public WearableAdapter(Context context, ArrayList<Articulo> items) {
        mInflater = LayoutInflater.from(context);
        mItems = items;
    }

    // Método que se encarga de dibujar el layout
    @Override
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(mInflater.inflate(R.layout.list_articulo, null));
    }

    // Método que escribe los elementos del ListView
    @Override
    public void onBindViewHolder(WearableListView.ViewHolder viewHolder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        TextView textView = itemViewHolder.mItemTextView;

        // Escribe los elementos del Array
            textView.setText(mItems.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private static class ItemViewHolder extends WearableListView.ViewHolder {
        private TextView mItemTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mItemTextView = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
