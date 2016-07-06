package com.android.pilar.myshoppinglistwear;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<Articulo>         mProductos;
    private TextView                    mHeader;

    private Articulo pan,leche,naranjas,huevos,tomates,papel_higienico,gel,queso,embutido,zumo;

    private Context                     contexto = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lista de productos
        mProductos = new ArrayList<Articulo>();

        pan = new Articulo("Pan",false);
        leche = new Articulo("Leche",false);
        naranjas = new Articulo("Naranjas",false);
        huevos = new Articulo("Huevos",false);
        tomates = new Articulo("Tomates",false);
        papel_higienico = new Articulo("Papel higienico",false);
        gel = new Articulo("Gel",false);
        queso = new Articulo("Queso",false);
        embutido = new Articulo("Embutido",false);
        zumo = new Articulo("Zumo",false);

        mProductos.add(pan);
        mProductos.add(leche);
        mProductos.add(naranjas);
        mProductos.add(huevos);
        mProductos.add(tomates);
        mProductos.add(papel_higienico);
        mProductos.add(gel);
        mProductos.add(queso);
        mProductos.add(embutido);
        mProductos.add(zumo);

        // titulo de la lista
        mHeader = (TextView) findViewById(R.id.tvTitulo);

        WearableListView wearableListView = (WearableListView) findViewById(R.id.wearable_List);
        wearableListView.setAdapter(new WearableAdapter(contexto, mProductos));
        wearableListView.setClickListener(mClickListener);
        wearableListView.addOnScrollListener(mOnScrollListener);
    }

    // eventos items de la lista
    private WearableListView.ClickListener mClickListener = new WearableListView.ClickListener() {
        @Override
        public void onClick(WearableListView.ViewHolder viewHolder) {

            if (mProductos.get(viewHolder.getLayoutPosition()).isComprado()){
                mProductos.get(viewHolder.getLayoutPosition()).setComprado(false);
                Toast.makeText(getApplicationContext(), "No has comprado '"  +
                        mProductos.get(viewHolder.getLayoutPosition()).getNombre() + "'", Toast.LENGTH_SHORT).show();
            }
            else {
                mProductos.get(viewHolder.getLayoutPosition()).setComprado(true);
                Toast.makeText(getApplicationContext(), "Has comprado '" +
                        mProductos.get(viewHolder.getLayoutPosition()).getNombre() + "'", Toast.LENGTH_SHORT).show();

                // Borro el producto que acabo de comprar
                mProductos.remove(viewHolder.getLayoutPosition());
                // Actualizo la lista
                WearableListView wearableListView = (WearableListView) findViewById(R.id.wearable_List);
                wearableListView.setAdapter(new WearableAdapter(contexto, mProductos));
            }
        }

        @Override
        public void onTopEmptyRegionClick() {
            Toast.makeText(MainActivity.this,"No has seleccionado ningún artículo", Toast.LENGTH_SHORT).show();
        }
    };


    // Evento de la lista que permite el movimiento hacia arriba de la misma.
    private WearableListView.OnScrollListener mOnScrollListener = new WearableListView.OnScrollListener() {
        @Override
        public void onAbsoluteScrollChange(int i) {
            // Sólo se mueve el título para que se mantenga siempre arriba del todo.
            if (i > 0) {
                mHeader.setY(-i);
            }
        }

        @Override
        public void onScroll(int i) {
            // Placeholder
        }

        @Override
        public void onScrollStateChanged(int i) {
            // Placeholder
        }

        @Override
        public void onCentralPositionChanged(int i) {
            // Placeholder
        }
    };
}
