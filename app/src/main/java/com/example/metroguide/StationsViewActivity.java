package com.example.metroguide;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/** @noinspection unchecked*/
public class StationsViewActivity extends AppCompatActivity {

    ImageView metroStationsImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations_view);

        List<List<int[]>> routesCoordinates = new ArrayList<>();
        Intent intent = getIntent();
        if (intent != null) {
            // Retrieve the serialized data
            ArrayList<ArrayList<ArrayList<Integer>>> serializedData = (ArrayList<ArrayList<ArrayList<Integer>>>) intent.getSerializableExtra("RouteCoordinates");

            assert serializedData != null;
            for (ArrayList<ArrayList<Integer>> route : serializedData) {

                List<int[]> routeCoordinates = new ArrayList<>();
                for (ArrayList<Integer> point : route) {
                    int[] intArray = new int[2];
                    for (int i = 0; i < point.size(); i++) {
                        intArray[i] = point.get(i);
                    }
                    routeCoordinates.add(intArray);
                }
                routesCoordinates.add(routeCoordinates);
            }




        }


        metroStationsImage = findViewById(R.id.MetroStationsimageView);
        ViewTreeObserver vto = metroStationsImage.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                metroStationsImage.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int width = metroStationsImage.getWidth();
                int height = metroStationsImage.getHeight();


                MetroLineView metroLineView = findViewById(R.id.metroLineView);
                metroLineView.setCoordinates(routesCoordinates, width, height);
            }
        });
    }
}