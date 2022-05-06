package com.example.dling.webclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button insertButton;
    private Button displayButton, updateButton, deleteButton;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;
        displayButton = (Button)findViewById(R.id.displaybutton);
        insertButton = (Button) findViewById(R.id.insertbutton);
        updateButton = (Button) findViewById(R.id.updatebutton);
        deleteButton = (Button) findViewById(R.id.deletebutton);

        displayButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                url = "http://192.168.1.75/WebApp/display.php";
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }

        });
        insertButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                url = "http://192.168.1.75/WebApp/insert.php";
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }

        });

        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                url = "http://192.168.1.75/WebApp/update.php";
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }

        });

        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                url = "http://192.168.1.75/WebApp/delete.php";
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }

        });

    }
}