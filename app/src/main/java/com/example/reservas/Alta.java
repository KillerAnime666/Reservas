package com.example.reservas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Alta extends AppCompatActivity {

    EditText etnombre, etapellido, etcarnet, ettelefono, etdepartamento, etproducto, etmonto, etsaldo;

    Button btguardar, btsalir;

    Controlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        etnombre = findViewById(R.id.etNombre);
        etapellido = findViewById(R.id.etApellido);
        etcarnet = findViewById(R.id.etCarnet);
        ettelefono = findViewById(R.id.etTelefono);
        etdepartamento = findViewById(R.id.etDepartamento);
        etproducto = findViewById(R.id.etProducto);
        etmonto = findViewById(R.id.etMonto);
        etsaldo = findViewById(R.id.etSaldo);

        btguardar = findViewById(R.id.btGrabaProducto);
        btsalir = findViewById(R.id.btSaleDeProducto);

        controlador = new Controlador(this);

        btguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etnombre.getText().toString().trim();
                String apellido = etapellido.getText().toString().trim();
                String carnet = etcarnet.getText().toString().trim();
                String telefono = ettelefono.getText().toString().trim();
                String departamento = etdepartamento.getText().toString().trim();
                String producto = etproducto.getText().toString().trim();
                String monto = etmonto.getText().toString().toString().trim();
                String saldo = etsaldo.getText().toString().trim();

                if (nombre.isEmpty() || carnet.isEmpty() || telefono.isEmpty()
                        || departamento.isEmpty() || monto.isEmpty() || saldo.isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "error, campos vacios", Toast.LENGTH_LONG).show();
                }else{
                    long res = controlador.altaProducto(new ModeloProducto(nombre, apellido, carnet,
                            Integer.parseInt(telefono), departamento, producto
                            , Float.parseFloat(monto), Float.parseFloat(saldo)));
                    System.out.println("Proceso de alta");
                    if (res <= 0){
                        System.out.println("Fracaso en el proceso de alta");
                        Toast.makeText(getApplicationContext(),
                                "error, fracaso en la grabacion,", Toast.LENGTH_LONG).show();
                    }else{
                        System.out.println("Exito en el Proceso de alta");

                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                        limpiaCampos();
                        Toast.makeText(getApplicationContext(),"succes, exito en la grabacion "+res, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    void limpiaCampos(){
        etnombre.setText(null);
        etapellido.setText(null);
        etcarnet.setText(null);
        ettelefono.setText(null);
        etdepartamento.setText(null);
        etproducto.setText(null);
        etmonto.setText(null);
        etsaldo.setText(null);

        etnombre.requestFocus();
    }

}