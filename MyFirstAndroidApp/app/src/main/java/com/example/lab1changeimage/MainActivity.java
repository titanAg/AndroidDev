package com.example.lab1changeimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeImage(View v) {
        TextView tview = (TextView) findViewById(R.id.profileLabel);
        ImageView imgView = (ImageView) findViewById(R.id.profileImage);
        if (tview.getText().equals("Kyle Orcutt")) {
            imgView.setImageResource(R.drawable.img2);
            tview.setText("Our cat KC ");
        }
        else {
            imgView.setImageResource(R.drawable.img1);
            tview.setText("Kyle Orcutt");
        }
    }

    public void showMessage(View v) {
        int yPos = (int) findViewById(R.id.ChangeBtn).getY();
        Toast toast = Toast.makeText(this, "Hello, my name is Kyle Orcutt!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, yPos);
        toast.show();
    }
}