package com.example.metroguide;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import mumayank.com.airlocationlibrary.AirLocation;


/** @noinspection ALL*/
public class MainActivity extends AppCompatActivity  implements SeekBar.OnSeekBarChangeListener, AirLocation.Callback  {

    Spinner depStationSpinner, arrStationSpinner;
    SeekBar sortBar;
    int sortBarValue = 0;
    SharedPreferences pref;
    String savedDepStation = "";
    String savedArrStation = "";
    int savedSortType = 0;
    List<String> allStationsDep;
    List<String> allStationsArr;
    Location mylocation = new Location("");
    Location depLocation = new Location("");
    Location arrlocation = new Location("");
    Location arrlocationStaion = new Location("");
    AirLocation airLocation;
    ImageView currentLocationImg;
    ImageView depRouteImg;
    ImageView arrRouteImg;
    TextView destinationText;
    List<String> metroLine1Stations;
    List<String> metroLine2Stations;
    List<String> metroLine3Branch1Stations;
    List<String> metroLine3Branch2Stations;
    List<Location> metroLine1StationsLocation;
    List<Location> metroLine2StationsLocation;
    List<Location> metroLine3Branch1StationsLocation;
    List<Location> metroLine3Branch2StationsLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        depStationSpinner = findViewById(R.id.depStationSpinner);
        arrStationSpinner = findViewById(R.id.arrStationSpinner);
        ImageView imageView = findViewById(R.id.myImageView);
        imageView.setAlpha(0.5f);

        sortBar = findViewById(R.id.sortBar);
        sortBar.setOnSeekBarChangeListener(this);
        sortBar.setProgress(0);

        currentLocationImg = findViewById(R.id.HomePositionImg);
        currentLocationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform the desired action here
                airLocation = new AirLocation( MainActivity.this, MainActivity.this,true,0,"");
                airLocation.start();
            }
        });

        depRouteImg = findViewById(R.id.DepRouteImg);
        depRouteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform the desired action here
                openGoogleMapsDirections(mylocation.getLatitude(), mylocation.getLongitude(), depLocation.getLatitude(), depLocation.getLongitude());
            }
        });

        arrRouteImg = findViewById(R.id.ArrRouteImg);
        arrRouteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform the desired action here
                openGoogleMapsDirectionsr(arrlocationStaion.getLatitude(), arrlocationStaion.getLongitude(), arrlocation.getLatitude(), arrlocation.getLongitude());
            }
        });

        pref = getSharedPreferences("metrodata", MODE_PRIVATE);
        savedDepStation = pref.getString("DepatureStaion", "");
        savedArrStation = pref.getString("ArrivalStaion", "");
        savedSortType  = pref.getInt("SortType", 0);

        destinationText = findViewById(R.id.DestinationText);

        metroLine1Stations = List.of(
                "HELWAN", "AIN HELWAN", "HELWAN UNIVERSITY", "WADI HOF", "HADAYEK HELWAN",
                "EL-MAASARA", "TORA EL-ASMANT", "KOZZIKA", "TORA EL-BALAD", "THAKANAT EL-MAADI",
                "MAADI", "HADAYEK EL-MAADI", "DAR EL-SALAM", "EL-ZAHRAA", "MAR GIRGIS",
                "EL-MALEK EL-SALEH", "SAYEDA ZEINAB", "SAAD ZAGHLOUL", "SADAT", "GAMAL ABDEL NASSER",
                "ORABI", "EL-SHOHADAAH", "GHAMRA", "EL-DEMERDASH", "MANSHIET EL-SADR", "KOBRI EL-QOBBA",
                "HAMMAMAT EL-QOBBA", "SARAY EL-QOBBA", "HADAYEK EL-ZAITOUN", "HELMEYET EL-ZAITOUN",
                "EL-MATARYA", "AIN SHAMS", "EZBET EL-NAKHL", "EL-MARG", "NEW EL-MARG"
        );

        metroLine2Stations = List.of(
                "EL MOUNIB", "SAQYET MAKKI", "OM EL MASRYEEN", "GIZA", "FEISAL",
                "CAIRO UNIVERSITY", "EL BEHOUS", "EL DOKKI", "OPERA", "SADAT",
                "MOHAMED NAGUIB", "ATABA", "EL-SHOHADAAH", "MASARA", "ROUD EL FARAG",
                "SAINT TERESA", "KHALAFAWEY", "MEZALLAT", "KOLLEYET EL ZERA3A",
                "SHOUBRA EL KHEIMA"
        );

        metroLine3Branch1Stations = List.of(
                "ADLY MANSOUR", "EL HAYKSTEP", "OMAR IBN EL KHATTAB", "QOBA", "HESHAM BARAKAT",
                "EL NOZHA", "NADI EL SHAMS", "ALF MASKAN", "HELIOPOLIS", "HAROUN",
                "EL AHRAM", "KOLLEYET EL BANAT", "EL ESTAD", "ARD EL MAARD",
                "ABASIA", "ABDO BASHA", "EL GEISH", "BAB EL SHARIA", "ATABA",
                "GAMAL ABDEL NASSER", "MASPERO", "SAFAA HEGAZI", "KIT KAT",
                "SUDAN", "IMBABA", "EL BOHY", "EL QAWMEYA",
                "EL TARIQ EL DAIRY", "ROD EL FARAG AXIS"
        );

        metroLine3Branch2Stations = List.of(
                "ADLY MANSOUR", "EL HAYKSTEP", "OMAR IBN EL KHATTAB", "QOBA", "HESHAM BARAKAT",
                "EL NOZHA", "NADI EL SHAMS", "ALF MASKAN", "HELIOPOLIS", "HAROUN",
                "EL AHRAM", "KOLLEYET EL BANAT", "EL ESTAD", "ARD EL MAARD",
                "ABASIA", "ABDO BASHA", "EL GEISH", "BAB EL SHARIA", "ATABA",
                "GAMAL ABDEL NASSER", "MASPERO", "SAFAA HEGAZI", "KIT KAT",
                "EL TOUFIQIA", "WADI EL NIL",
                "GAMAET EL DOWL EL ARABIA", "BOLAK EL DAKROUR", "CAIRO UNIVERSITY"
        );



        double[][] metroLine1StationsCoordinates = {
                {29.848982000000003, 31.334230899999998},
                {29.8626524, 31.325052699999997},
                {29.869451400000003, 31.320066899999997},
                {29.897136000000003, 31.303966199999998},
                {29.906078400000002, 31.299515799999998},
                {29.9259651, 31.2875444},
                {29.936259, 31.281820600000003},
                {29.9467633, 31.272979999999997},
                {29.953300900000002, 31.262956},
                {29.9603028, 31.2576431},
                {29.970143200000003, 31.2506075},
                {29.995481700000003, 31.2311768},
                {30.006098800000004, 31.229628799999997},
                {30.016000000000002, 31.230929999999997},
                {30.032345099999997, 31.2416988},
                {30.0370333, 31.238362000000002},
                {30.043622, 31.235860900000002},
                {30.05341, 31.23886},
                {30.056688499999996, 31.2420542},
                {30.606616600000002, 30.8728615},
                {30.069028999999997, 31.2646168},
                {30.076780000000003, 31.27727},
                {30.081985200000002, 31.287534500000003},
                {30.087197000000003, 31.2941041},
                {30.0912364, 31.298911200000003},
                {30.097645999999997, 31.3045631},
                {30.1058896, 31.3104833},
                {30.113254800000004, 31.313964700000003},
                {30.1213363, 31.313722299999995},
                {30.131025700000002, 31.319091299999997},
                {30.139318000000003, 31.324422199999997},
                {30.1520807, 31.335682},
                {30.163648700000003, 31.3383642}
        };


        double[][] metroLine2StationsCoordinates = {
                {29.98139, 31.21194}, // El-Mounib
                {29.99556, 31.20861}, // Sakiat Mekky
                {30.00528, 31.20806}, // Omm El-Masryeen
                {30.01056, 31.20694}, // El Giza
                {30.01722, 31.20389}, // Faisal
                {30.02611, 31.20111}, // Cairo University
                {30.03583, 31.20028}, // El Bohoth
                {30.03833, 31.21194}, // Dokki
                {30.04167, 31.22528}, // Opera
                {30.04444, 31.23556}, // Sadat
                {30.04528, 31.24417}, // Mohamed Naguib
                {30.0525, 31.24694},  // Attaba
                {30.0625, 31.24611},  // Al-Shohadaa
                {30.07111, 31.245},   // Masarra
                {30.08056, 31.24556}, // Road El-Farag
                {30.08833, 31.24556}, // St. Teresa
                {30.09806, 31.24528}, // Khalafawy
                {30.105, 31.24667},   // Mezallat
                {30.11389, 31.24861}, // Kolleyyet El-Zeraa
                {30.1225, 31.24472}   // Shubra El-Kheima
        };


        double[][] metroLine3Branch1StationsCoordinates = {
                {30.14694, 31.42139}, // Adly Mansour
                {30.14389, 31.40472}, // El Haykestep
                {30.14056, 31.39417}, // Omar Ibn El-Khattab
                {30.13472, 31.38389}, // Qobaa
                {30.13111, 31.37278}, // Hesham Barakat
                {30.12833, 31.36000}, // El-Nozha
                {30.12222, 31.34472}, // Nadi El-Shams
                {30.11806, 31.33972}, // Alf Maskan
                {30.10806, 31.33806}, // Heliopolis Square
                {30.10111, 31.33278}, // Haroun
                {30.09139, 31.32639}, // Al-Ahram
                {30.08361, 31.32833}, // Koleyet El-Banat
                {30.07306, 31.31750}, // Stadium
                {30.07333, 31.30111}, // Fair Zone
                {30.06972, 31.28083}, // Abbassia
                {30.06472, 31.27472}, // Abdou Pasha
                {30.06194, 31.26694}, // El Geish
                {30.05389, 31.25611}, // Bab El Shaaria
                {30.05250, 31.24694}, // Attaba
                {30.05361, 31.23889}, // Nasser
                {30.05556, 31.23222}, // Maspero
                {30.06250, 31.22250}, // Safaa Hegazy
                {30.06667, 31.21306}, // Kit Kat
                {30.06972, 31.20528}, // Sudan Street
                {30.07583, 31.20750}, // Imbaba
                {30.08222, 31.21056}, // El-Bohy
                {30.09333, 31.20944}, // Al-Qawmeya Al-Arabiya
                {30.09639, 31.19972}, // Ring Road
                {30.10194, 31.18417}  // Rod al-Farag Axis
        };

        double[][] metroLine3Branch2StationsCoordinates = {
                {30.14694, 31.42139}, // Adly Mansour
                {30.14389, 31.40472}, // El Haykestep
                {30.14056, 31.39417}, // Omar Ibn El-Khattab
                {30.13472, 31.38389}, // Qobaa
                {30.13111, 31.37278}, // Hesham Barakat
                {30.12833, 31.36000}, // El-Nozha
                {30.12222, 31.34472}, // Nadi El-Shams
                {30.11806, 31.33972}, // Alf Maskan
                {30.10806, 31.33806}, // Heliopolis Square
                {30.10111, 31.33278}, // Haroun
                {30.09139, 31.32639}, // Al-Ahram
                {30.08361, 31.32833}, // Koleyet El-Banat
                {30.07306, 31.31750}, // Stadium
                {30.07333, 31.30111}, // Fair Zone
                {30.06972, 31.28083}, // Abbassia
                {30.06472, 31.27472}, // Abdou Pasha
                {30.06194, 31.26694}, // El Geish
                {30.05389, 31.25611}, // Bab El Shaaria
                {30.05250, 31.24694}, // Attaba
                {30.05361, 31.23889}, // Nasser
                {30.05556, 31.23222}, // Maspero
                {30.06250, 31.22250}, // Safaa Hegazy
                {30.06667, 31.21306}, // Kit Kat
                {30.06528, 31.20250}, // El-Tawfikeya
                {30.05833, 31.20111}, // Wadi El-Nil
                {30.05083, 31.19972}, // Gamaat El Dowal Al-Arabiya
                {30.03611, 31.19639}, // Bulaq El-Dakroor
                {30.02611, 31.20111}  // Cairo University
        };



        metroLine1StationsLocation = new ArrayList<>(metroLine1StationsCoordinates.length);
        metroLine2StationsLocation = new ArrayList<>(metroLine2StationsCoordinates.length);
        metroLine3Branch1StationsLocation = new ArrayList<>(metroLine3Branch1StationsCoordinates.length);
        metroLine3Branch2StationsLocation = new ArrayList<>(metroLine3Branch2StationsCoordinates.length);

        setLocations(metroLine1StationsCoordinates,metroLine1StationsLocation);
        setLocations(metroLine2StationsCoordinates,metroLine2StationsLocation);
        setLocations(metroLine3Branch1StationsCoordinates,metroLine3Branch1StationsLocation);
        setLocations(metroLine3Branch2StationsCoordinates,metroLine3Branch2StationsLocation);




        Set<String> uniqueStations = new HashSet<>();
        uniqueStations.addAll(metroLine1Stations);
        uniqueStations.addAll(metroLine2Stations);
        uniqueStations.addAll(metroLine3Branch1Stations);
        uniqueStations.addAll(metroLine3Branch2Stations);

        List<String> allStations = new ArrayList<>(uniqueStations);
        Collections.sort(allStations);

        allStationsDep = new ArrayList<>(allStations.subList(0, allStations.size()));
        allStationsArr = new ArrayList<>(allStations.subList(0, allStations.size()));


        allStationsDep.add(0, "Please select the Departure station"); // Adding "please select" at the beginning
        //items->adapter->spinner
        ArrayAdapter adapter = new ArrayAdapter(this
                , android.R.layout.simple_list_item_1, allStationsDep);
        depStationSpinner.setAdapter(adapter);

        allStationsArr.add(0, "Please select the Arrival station"); // Adding "please select" at the beginning
        //items->adapter->spinner
        ArrayAdapter adapter2 = new ArrayAdapter(this
                , android.R.layout.simple_list_item_1, allStationsArr);
        arrStationSpinner.setAdapter(adapter2);

        if (!(savedDepStation.equals("") || savedArrStation.equals(""))) {
            int depIndex = allStationsDep.indexOf(savedDepStation);
            int arrIndex = allStationsArr.indexOf(savedArrStation);
            if (depIndex != -1) {
                depStationSpinner.setSelection(depIndex);
            }
            if (arrIndex != -1) {
                arrStationSpinner.setSelection(arrIndex);
            }
            sortBar.setProgress(savedSortType);
            Intent intent = new Intent(this, RoutesActivity.class);
            intent.putExtra("DepatureStaion", savedDepStation);
            intent.putExtra("ArrivalStaion", savedArrStation);
            intent.putExtra("SortType", sortBarValue);
            startActivity(intent);
        }


    }

    public void ShowRoutes(View view) {
        String selectedDepStation = (String) depStationSpinner.getSelectedItem();
        String selectedArrStation = (String) arrStationSpinner.getSelectedItem();


        if (selectedDepStation.equalsIgnoreCase(selectedArrStation)) {
            Toast.makeText(this, "Please select different stations.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedDepStation.contains("Please"))
            Toast.makeText(this, "Please select Departure Station.", Toast.LENGTH_SHORT).show();
        if (selectedArrStation.contains("Please"))
            Toast.makeText(this, "Please select Arrival Station.", Toast.LENGTH_SHORT).show();
        if (!(selectedDepStation.contains("Please") || selectedArrStation.contains("Please"))) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("DepatureStaion", selectedDepStation);
            editor.putString("ArrivalStaion", selectedArrStation);
            editor.putInt("SortType", sortBarValue);
            editor.apply();

            Intent intent = new Intent(this, RoutesActivity.class);
            intent.putExtra("DepatureStaion", selectedDepStation);
            intent.putExtra("ArrivalStaion", selectedArrStation);
            intent.putExtra("SortType", sortBarValue);
            startActivity(intent);
        }
    }

    public void ShowReturnRoutes(View view) {
        String selectedArrStation = (String) depStationSpinner.getSelectedItem();
        String selectedDepStation = (String) arrStationSpinner.getSelectedItem();
        if (selectedDepStation.equalsIgnoreCase(selectedArrStation)) {
            Toast.makeText(this, "Please select different stations.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedDepStation.contains("Please"))
            Toast.makeText(this, "Please select Departure Station.", Toast.LENGTH_SHORT).show();
        if (selectedArrStation.contains("Please"))
            Toast.makeText(this, "Please select Arrival Station.", Toast.LENGTH_SHORT).show();
        if (!(selectedDepStation.contains("Please") || selectedArrStation.contains("Please"))) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("DepatureStaion", selectedArrStation);
            editor.putString("ArrivalStaion", selectedDepStation);
            editor.putInt("SortType", sortBarValue);
            editor.apply();

            Intent intent = new Intent(this, RoutesActivity.class);
            intent.putExtra("DepatureStaion", selectedDepStation);
            intent.putExtra("ArrivalStaion", selectedArrStation);
            intent.putExtra("SortType", sortBarValue);
            startActivity(intent);
        }
    }

    public void clearData(View view) {
        depStationSpinner.setSelection(0);
        arrStationSpinner.setSelection(0);

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("DepatureStaion", "");
        editor.putString("ArrivalStaion", "");
        editor.apply();

        savedDepStation = "";
        savedArrStation = "";

        savedSortType = 0;
        sortBar.setProgress(0);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        sortBarValue = seekBar.getProgress();
    }

    public void setLocations(double[][] coordinates, List<Location> metroLineStationsLocation){
        for (double[] coordinate : coordinates) {
            Location location = new Location("");
            location.setLatitude(coordinate[0]);
            location.setLongitude(coordinate[1]);
            metroLineStationsLocation.add(location);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        airLocation.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        airLocation.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onFailure(@NonNull AirLocation.LocationFailedEnum locationFailedEnum) {
        Toast.makeText(this, locationFailedEnum.name(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(@NonNull ArrayList<Location> locations) {
        double lat = locations.get(0).getLatitude();
        double lon = locations.get(0).getLongitude();
        Toast.makeText(this, "Lat" + lat+"  Lon:"+lon, Toast.LENGTH_SHORT).show();
        mylocation.setLatitude(lat);
        mylocation.setLongitude(lon);



        List<Double> metroLine1StationsDistance = new ArrayList<>();
        List<Double> metroLine2StationsDistance = new ArrayList<>();
        List<Double> metroLine3Branch1StationsDistance = new ArrayList<>();
        List<Double> metroLine3Branch2StationsDistance = new ArrayList<>();


        for (Location location : metroLine1StationsLocation) {
            metroLine1StationsDistance.add((double) mylocation.distanceTo(location));
        }
        for (Location location : metroLine2StationsLocation) {
            metroLine2StationsDistance.add((double) mylocation.distanceTo(location));
        }
        for (Location location : metroLine3Branch1StationsLocation) {
            metroLine3Branch1StationsDistance.add((double) mylocation.distanceTo(location));
        }
        for (Location location : metroLine3Branch2StationsLocation) {
            metroLine3Branch2StationsDistance.add((double) mylocation.distanceTo(location));
        }

        List<Double> minDistance=new ArrayList<>();
        minDistance.add(Collections.min(metroLine1StationsDistance));
        minDistance.add(Collections.min(metroLine2StationsDistance));
        minDistance.add(Collections.min(metroLine3Branch1StationsDistance));
        minDistance.add(Collections.min(metroLine3Branch2StationsDistance));

        int indexMin = minDistance.indexOf(Collections.min(minDistance));
        String station="";

        if (indexMin==0) {
            station = metroLine1Stations.get(metroLine1StationsDistance.indexOf(minDistance.get(0)));
            depLocation = metroLine1StationsLocation.get(metroLine1StationsDistance.indexOf(minDistance.get(0)));
        }
        else if (indexMin==1) {
            station = metroLine2Stations.get(metroLine2StationsDistance.indexOf(minDistance.get(1)));
            depLocation = metroLine2StationsLocation.get(metroLine2StationsDistance.indexOf(minDistance.get(1)));
        }
        else if (indexMin==2) {
            station = metroLine3Branch1Stations.get(metroLine3Branch1StationsDistance.indexOf(minDistance.get(2)));
            depLocation = metroLine3Branch1StationsLocation.get(metroLine3Branch1StationsDistance.indexOf(minDistance.get(2)));
        }
        else {
            station = metroLine3Branch2Stations.get(metroLine3Branch2StationsDistance.indexOf(minDistance.get(3)));
            depLocation = metroLine3Branch2StationsLocation.get(metroLine3Branch2StationsDistance.indexOf(minDistance.get(3)));
        }

        int indexDepStationSpinner = allStationsDep.indexOf(station);
        depStationSpinner.setSelection(indexDepStationSpinner);

    }

    // Method to open Google Maps with directions between two locations
    public void openGoogleMapsDirections(double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+endLatitude+"," +endLongitude+"&mode=w"); // or walking, bicycling, transit

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void NearstStation(View view) {
        if(!destinationText.getText().toString().isEmpty()) {
            Geocoder geocoder = new Geocoder(this);
            try {
                List<Address> addresses = geocoder.getFromLocationName(destinationText.getText().toString() + " egypt", 1);
                double lat = addresses.get(0).getLatitude();
                double lon = addresses.get(0).getLongitude();
                getNearestArrStation(lat,lon);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void getNearestArrStation(double lat, double lon){
        Toast.makeText(this, "Lat" + lat+"  Lon:"+lon, Toast.LENGTH_SHORT).show();
        arrlocation.setLatitude(lat);
        arrlocation.setLongitude(lon);



        List<Double> metroLine1StationsDistance = new ArrayList<>();
        List<Double> metroLine2StationsDistance = new ArrayList<>();
        List<Double> metroLine3Branch1StationsDistance = new ArrayList<>();
        List<Double> metroLine3Branch2StationsDistance = new ArrayList<>();


        for (Location location : metroLine1StationsLocation) {
            metroLine1StationsDistance.add((double) arrlocation.distanceTo(location));
        }
        for (Location location : metroLine2StationsLocation) {
            metroLine2StationsDistance.add((double) arrlocation.distanceTo(location));
        }
        for (Location location : metroLine3Branch1StationsLocation) {
            metroLine3Branch1StationsDistance.add((double) arrlocation.distanceTo(location));
        }
        for (Location location : metroLine3Branch2StationsLocation) {
            metroLine3Branch2StationsDistance.add((double) arrlocation.distanceTo(location));
        }

        List<Double> minDistance=new ArrayList<>();
        minDistance.add(Collections.min(metroLine1StationsDistance));
        minDistance.add(Collections.min(metroLine2StationsDistance));
        minDistance.add(Collections.min(metroLine3Branch1StationsDistance));
        minDistance.add(Collections.min(metroLine3Branch2StationsDistance));

        int indexMin = minDistance.indexOf(Collections.min(minDistance));
        String station="";

        if (indexMin==0) {
            station = metroLine1Stations.get(metroLine1StationsDistance.indexOf(minDistance.get(0)));
            arrlocationStaion = metroLine1StationsLocation.get(metroLine1StationsDistance.indexOf(minDistance.get(0)));
        }
        else if (indexMin==1) {
            station = metroLine2Stations.get(metroLine2StationsDistance.indexOf(minDistance.get(1)));
            arrlocationStaion = metroLine2StationsLocation.get(metroLine2StationsDistance.indexOf(minDistance.get(1)));
        }
        else if (indexMin==2) {
            station = metroLine3Branch1Stations.get(metroLine3Branch1StationsDistance.indexOf(minDistance.get(2)));
            arrlocationStaion = metroLine3Branch1StationsLocation.get(metroLine3Branch1StationsDistance.indexOf(minDistance.get(2)));
        }
        else {
            station = metroLine3Branch2Stations.get(metroLine3Branch2StationsDistance.indexOf(minDistance.get(3)));
            arrlocationStaion = metroLine3Branch2StationsLocation.get(metroLine3Branch2StationsDistance.indexOf(minDistance.get(3)));
        }

        int indexArrStationSpinner = allStationsArr.indexOf(station);
        arrStationSpinner.setSelection(indexArrStationSpinner);
    }

    public void openGoogleMapsDirectionsr(double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
        Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin="
                + startLatitude + "," + startLongitude
                + "&destination="
                + endLatitude + "," + endLongitude
                + "&travelmode=walking"); // or walking, bicycling, transit
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

}