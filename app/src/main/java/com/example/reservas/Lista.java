package com.example.reservas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    ArrayList<ModeloProducto> lista;

    AdaptadorProducto adaptador;

    RecyclerView rvProducto;

    Controlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lista = new ArrayList<>();
        adaptador = new AdaptadorProducto(lista);
        rvProducto = findViewById(R.id.rvLista);

        controlador = new Controlador(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvProducto.setLayoutManager(mLayoutManager);
        rvProducto.setItemAnimator(new DefaultItemAnimator());
        rvProducto.setAdapter(adaptador);

        refrescarListaDeProductos();

        rvProducto.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                rvProducto, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ModeloProducto prodElegido = lista.get(position);
                baja(prodElegido);
            }

            @Override
            public void onLongClick(View view, int position) {
                ModeloProducto prodElegido = lista.get(position);
                cambio(prodElegido);
            }
        }));
    }

    public void refrescarListaDeProductos() {

        System.out.println("100");
        if (adaptador == null) return;
        System.out.println("200");
        lista = controlador.obtenerProductos();
        adaptador.setListaDeProductos(lista);
        adaptador.notifyDataSetChanged();
        System.out.println("300");
    }

    void baja(final ModeloProducto objProd){
        AlertDialog.Builder alertaBaja = new AlertDialog.Builder(this);
        alertaBaja.setTitle("Baja de productos");
        alertaBaja.setMessage(objProd.toString());
        alertaBaja.setCancelable(false);
        alertaBaja.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                long res = controlador.bajaProducto(objProd);
                if(res < 0){
                    Toast.makeText(getApplicationContext(),"Error en la baja",
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"succes, exito enla baja " + res,
                            Toast.LENGTH_LONG).show();
                    refrescarListaDeProductos();
                }
            }
        });

        alertaBaja.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertaBaja.show();
    }

    void cambio(final ModeloProducto objProd){
        View subView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_cambio_producto, null);

        final EditText etNombre, etCarnet, etTelefono, etDepartamento, etMonto, etSaldo;

        etNombre = subView.findViewById(R.id.etNuevoNombre);
        etCarnet = subView.findViewById(R.id.etNuevoCarnet);
        etTelefono = subView.findViewById(R.id.etNuevoTelefono);
        etDepartamento = subView.findViewById(R.id.etNuevoDepartamento);
        etMonto = subView.findViewById(R.id.etNuevoMonto);
        etSaldo = subView.findViewById(R.id.etNuevoSaldo);

        etNombre.setText(objProd.getNombre());
        etCarnet.setText(objProd.getCarnet());
        etTelefono.setText(String.valueOf(objProd.getTelefono()));
        etDepartamento.setText(objProd.getDepartamento());
        etMonto.setText(String.valueOf(objProd.getMonto()));
        etSaldo.setText(String.valueOf(objProd.getSaldo()));

        AlertDialog.Builder ale = new AlertDialog.Builder(this);

        ale.setTitle("Cambios en producto");
        ale.setView(subView);
        ale.setCancelable(false);
        ale.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                objProd.setNombre(etNombre.getText().toString());
                objProd.setCarnet(etCarnet.getText().toString());
                objProd.setTelefono(Integer.parseInt(etTelefono.getText().toString()));
                objProd.setDepartamento(etDepartamento.getText().toString());
                objProd.setMonto(Float.parseFloat(etMonto.getText().toString()));
                objProd.setSaldo(Float.parseFloat(etSaldo.getText().toString()));

                long res = controlador.cambioProducto(objProd);

                if (res < 0){
                    Toast.makeText(getApplicationContext(),"Error en el cambio",
                            Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"succes, exito en  e cambio "+res, Toast.LENGTH_LONG).show();
                    refrescarListaDeProductos();
                }
            }
        });

        ale.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        ale.show();

    }

}