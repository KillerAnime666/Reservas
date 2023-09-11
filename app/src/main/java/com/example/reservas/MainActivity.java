package com.example.reservas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

public class MainActivity extends AppCompatActivity {

    Button btmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btmenu = findViewById(R.id.btMenu);

        btmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu ppm = new PopupMenu(MainActivity.this, btmenu);

                ppm.getMenuInflater().inflate(R.menu.layout_menu, ppm.getMenu());

                ppm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId() == R.id.mnuAlta){
                            Intent open = new Intent(MainActivity.this, Alta.class);
                            startActivity(open);
                        } else if (menuItem.getItemId() == R.id.mnuLista) {
                            Intent open = new Intent(MainActivity.this, Lista.class);
                            startActivity(open);
                        }

                        return true;

                    }
                });

                ppm.show();

            }
        });

    }
}