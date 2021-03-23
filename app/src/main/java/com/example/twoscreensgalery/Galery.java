package com.example.twoscreensgalery;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Galery extends AppCompatActivity {

    private Button bttNext;
    private Button bttPrevious;
    private TextView titleText;
    private TextView indexText;
    private ImageView imageView;

    Map<Integer, String> imageListNames = new HashMap<Integer, String>();
    private List<Integer> imageList = new ArrayList<>();
    private int imageCount;
    private int imageIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);
        setupUIViews();
        imageList.add(R.drawable.animal);
        imageListNames.put(R.drawable.animal, "animal");
        imageList.add(R.drawable.cat);
        imageListNames.put(R.drawable.cat, "cat");
        imageList.add(R.drawable.monster);
        imageListNames.put(R.drawable.monster, "monster");
        imageList.add(R.drawable.red);
        imageListNames.put(R.drawable.red, "red");
        imageCount = imageList.size();
        SetImageInfo(0);
    }

    private void setupUIViews() {
        bttNext = (Button) findViewById(R.id.button_NextImage);
        bttPrevious = (Button) findViewById(R.id.button_LastImage);
        titleText = (TextView) findViewById(R.id.textView_Title);
        indexText = (TextView) findViewById(R.id.textView_Index);
        imageView = (ImageView) findViewById(R.id.imageView_GaleryImage);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Galery.this);
        builder.setTitle("Atençao!");
        builder.setMessage("Voce realmente deseja sair de sua conta?");
        builder.setPositiveButton(android.R.string.no, null);
        builder.setNegativeButton(android.R.string.yes,
        new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                finish();
            }
        });

        builder.create().show();
    }

    public void onNextImagePressed(View view){
        if (imageIndex + 1 < imageCount)
        SetImageInfo(imageIndex + 1);
    }
    public void onPreviousImagePressed(View view){
        if (imageIndex - 1 >= 0)
        SetImageInfo(imageIndex - 1);
    }
    private void SetImageInfo(int index) {
        imageIndex = index;
        imageView.setImageResource(imageList.get(imageIndex));
        titleText.setText(imageListNames.get(imageList.get(imageIndex)));
        indexText.setText((imageIndex + 1) + "/" + imageCount);
    }
}