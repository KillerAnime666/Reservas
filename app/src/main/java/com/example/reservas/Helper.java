package com.example.reservas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {

    static final String dataBase = "db1t";

    static final int verDB = 1;

    public Helper(@Nullable Context context) {
        super(context, dataBase, null, verDB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE reserva(id integer primary key autoincrement, " +
                        "nombre text, apellido text, carnet text, telefono integer," +
                        "departamento text, producto text, monto float, saldo float)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS reserva");
    }
}
