package com.example.reservas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Controlador {

    Helper helper;

    public Controlador(Context context) {
        helper = new Helper(context);
    }

    public long altaProducto(ModeloProducto objProd){
        SQLiteDatabase sql = helper.getWritableDatabase();

        ContentValues valInsert = new ContentValues();
        valInsert.put("nombre", objProd.getNombre());
        valInsert.put("carnet", objProd.getCarnet());
        valInsert.put("telefono", objProd.getTelefono());
        valInsert.put("departamento", objProd.getDepartamento());
        valInsert.put("monto", objProd.getMonto());
        valInsert.put("saldo", objProd.getSaldo());

        return sql.insert("reserva", null, valInsert);

    }

    public ArrayList<ModeloProducto> obtenerProductos(){
        System.out.println("controlador 1");

        ArrayList<ModeloProducto> listaProductos= new ArrayList<>();

        SQLiteDatabase baseDeDatos = helper.getReadableDatabase();

        System.out.println("controlador 2");

        String[] columnasConsultadas = {"id", "nombre", "carnet", "telefono",
        "departamento", "monto", "saldo"};

        System.out.println("controlador 3");

        Cursor cursor = baseDeDatos.query(
                "reserva",
                columnasConsultadas,
                null,
                null,
                null,
                null,
                "nombre"
        );

        System.out.println("controlador 4");

        if (cursor == null){
            return listaProductos;
        }

        System.out.println("controlador 5");

        if (!cursor.moveToFirst()){
            return listaProductos;
        }

        System.out.println("controlador 6");
        System.out.println("Empezar recorrido");

        do{
            int cod = cursor.getInt(0);
            String name = cursor.getString(1);
            String ci = cursor.getString(2);
            int tel = cursor.getInt(3);
            String dep = cursor.getString(4);
            float mont = cursor.getFloat(5);
            float sal = cursor.getFloat(6);

            ModeloProducto objProd = new ModeloProducto(cod, name, ci, tel, dep, mont, sal);
            listaProductos.add(objProd);

        }while(cursor.moveToNext());

        cursor.close();
        return listaProductos;

    }

    public int bajaProducto(ModeloProducto productoEditado){
        SQLiteDatabase baseDeDatos = helper.getWritableDatabase();

        String campoParaActualizar = "id = ?";
        String[] argumentoParaActualizar = {String.valueOf(productoEditado.getId())};

        return baseDeDatos.delete("reserva", campoParaActualizar, argumentoParaActualizar);
    }

    public int cambioProducto(ModeloProducto objProd){
        SQLiteDatabase baseDeDatos = helper.getWritableDatabase();

        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", objProd.getNombre());
        valoresParaActualizar.put("carnet", objProd.getCarnet());
        valoresParaActualizar.put("telefono", objProd.getTelefono());
        valoresParaActualizar.put("departamento", objProd.getDepartamento());
        valoresParaActualizar.put("monto", objProd.getMonto());
        valoresParaActualizar.put("saldo", objProd.getSaldo());

        String campoParaActualizar = "id = ?";

        String[] argumentosParaActualizar = {String.valueOf(objProd.getId())};

        return baseDeDatos.update("reserva", valoresParaActualizar,
                campoParaActualizar, argumentosParaActualizar);

    }
}
