package com.example.metroguide;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RoutesActivity extends AppCompatActivity {


    HashMap<List<Byte>, List<String>> commonStationsMap = new HashMap<>();
    {
        commonStationsMap.put(Arrays.asList((byte) 1, (byte) 2), List.of("SADAT", "EL-SHOHADAAH"));
        commonStationsMap.put(Arrays.asList((byte) 1, (byte) 3), List.of("GAMAL ABDEL NASSER"));
        commonStationsMap.put(Arrays.asList((byte) 1, (byte) 4), List.of("GAMAL ABDEL NASSER"));
        commonStationsMap.put(Arrays.asList((byte) 2, (byte) 3), List.of("ATABA"));
        commonStationsMap.put(Arrays.asList((byte) 2, (byte) 4), List.of("ATABA", "CAIRO UNIVERSITY"));
        commonStationsMap.put(Arrays.asList((byte) 3, (byte) 4), List.of("KIT KAT"));
    }



    List<String> metroLine1Stations = Arrays.asList(
            "HELWAN", "AIN HELWAN", "HELWAN UNIVERSITY", "WADI HOF", "HADAYEK HELWAN",
            "EL-MAASARA", "TORA EL-ASMANT", "KOZZIKA", "TORA EL-BALAD", "THAKANAT EL-MAADI",
            "MAADI", "HADAYEK EL-MAADI", "DAR EL-SALAM", "EL-ZAHRAA", "MAR GIRGIS",
            "EL-MALEK EL-SALEH", "SAYEDA ZEINAB", "SAAD ZAGHLOUL", "SADAT", "GAMAL ABDEL NASSER",
            "ORABI", "EL-SHOHADAAH", "GHAMRA", "EL-DEMERDASH", "MANSHIET EL-SADR", "KOBRI EL-QOBBA",
            "HAMMAMAT EL-QOBBA", "SARAY EL-QOBBA", "HADAYEK EL-ZAITOUN", "HELMEYET EL-ZAITOUN",
            "EL-MATARYA", "AIN SHAMS", "EZBET EL-NAKHL", "EL-MARG", "NEW EL-MARG"
    );

    List<String> metroLine2Stations = Arrays.asList(
            "EL MOUNIB", "SAQYET MAKKI", "OM EL MASRYEEN", "GIZA", "FEISAL",
            "CAIRO UNIVERSITY", "EL BEHOUS", "EL DOKKI", "OPERA", "SADAT",
            "MOHAMED NAGUIB", "ATABA", "EL-SHOHADAAH", "MASARA", "ROUD EL FARAG",
            "SAINT TERESA", "KHALAFAWEY", "MEZALLAT", "KOLLEYET EL ZERA3A",
            "SHOUBRA EL KHEIMA"
    );

    List<String> metroLine3Branch1Stations = Arrays.asList(
            "ADLY MANSOUR", "EL HAYKSTEP", "OMAR IBN EL KHATTAB", "QOBA", "HESHAM BARAKAT",
            "EL NOZHA", "NADI EL SHAMS", "ALF MASKAN", "HELIOPOLIS", "HAROUN",
            "EL AHRAM", "KOLLEYET EL BANAT", "EL ESTAD", "ARD EL MAARD",
            "ABASIA", "ABDO BASHA", "EL GEISH", "BAB EL SHARIA", "ATABA",
            "GAMAL ABDEL NASSER", "MASPERO", "SAFAA HEGAZI","KIT KAT",
            "SUDAN", "IMBABA", "EL BOHY", "EL QAWMEYA",
            "EL TARIQ EL DAIRY", "ROD EL FARAG AXIS"
    );

    List<String> metroLine3Branch2Stations = Arrays.asList(
            "ADLY MANSOUR", "EL HAYKSTEP", "OMAR IBN EL KHATTAB", "QOBA", "HESHAM BARAKAT",
            "EL NOZHA", "NADI EL SHAMS", "ALF MASKAN", "HELIOPOLIS", "HAROUN",
            "EL AHRAM", "KOLLEYET EL BANAT", "EL ESTAD", "ARD EL MAARD",
            "ABASIA", "ABDO BASHA", "EL GEISH", "BAB EL SHARIA", "ATABA",
            "GAMAL ABDEL NASSER", "MASPERO", "SAFAA HEGAZI","KIT KAT",
            "EL TOUFIQIA", "WADI EL NIL",
            "GAMAET EL DOWL EL ARABIA", "BOLAK EL DAKROUR", "CAIRO UNIVERSITY"
    );

    List<int[]> metroLine1Coordinates = Arrays.asList(
            new int[]{632, 903}, new int[]{613, 873}, new int[]{596, 848}, new int[]{584, 823}, new int[]{566, 798}, new int[]{554, 774},
            new int[]{538, 749}, new int[]{523, 724}, new int[]{509, 699}, new int[]{493, 674}, new int[]{481, 650}, new int[]{468, 624},
            new int[]{467, 599}, new int[]{467, 576}, new int[]{467, 551}, new int[]{467, 526}, new int[]{467, 501}, new int[]{467, 476},
            new int[]{467, 448}, new int[]{467, 359}, new int[]{482, 337}, new int[]{498, 316}, new int[]{520, 289}, new int[]{535, 269},
            new int[]{551, 249}, new int[]{565, 228}, new int[]{580, 210}, new int[]{596, 190}, new int[]{612, 169}, new int[]{627, 151},
            new int[]{640, 130}, new int[]{655, 110}, new int[]{671, 91}, new int[]{685, 71}, new int[]{705, 48}
    );

    List<int[]> metroLine2Coordinates = Arrays.asList(
            new int[]{309, 649}, new int[]{309, 613}, new int[]{311, 585}, new int[]{311, 557}, new int[]{311, 529}, new int[]{309, 501},
            new int[]{337, 450}, new int[]{375, 450}, new int[]{412, 450}, new int[]{467, 448}, new int[]{524, 420}, new int[]{537, 358},
            new int[]{498, 316}, new int[]{496, 275}, new int[]{498, 252}, new int[]{498, 228}, new int[]{498, 204}, new int[]{496, 180},
            new int[]{487, 154}, new int[]{470, 121}
    );

    List<int[]> metroLine3Branch1Coordinates = Arrays.asList(
            new int[]{873, 288}, new int[]{872, 246}, new int[]{873, 211}, new int[]{853, 172}, new int[]{830, 172}, new int[]{809, 175}, new int[]{789, 188},
            new int[]{769, 202}, new int[]{750, 214}, new int[]{730, 228}, new int[]{711, 241}, new int[]{691, 253}, new int[]{672, 267}, new int[]{652, 280},
            new int[]{632, 294}, new int[]{613, 306}, new int[]{594, 319}, new int[]{574, 333}, new int[]{537, 358}, new int[]{467, 359}, new int[]{404, 330},
            new int[]{364, 313}, new int[]{334, 297}, new int[]{325, 261}, new int[]{325, 225}, new int[]{325, 188}, new int[]{325, 152}, new int[]{325, 121},
            new int[]{327, 77}
    );

    List<int[]> metroLine3Branch2Coordinates = Arrays.asList(
            new int[]{873, 288}, new int[]{872, 246}, new int[]{873, 211}, new int[]{853, 172}, new int[]{830, 172}, new int[]{809, 175}, new int[]{789, 188},
            new int[]{769, 202}, new int[]{750, 214}, new int[]{730, 228}, new int[]{711, 241}, new int[]{691, 253}, new int[]{672, 267}, new int[]{652, 280},
            new int[]{632, 294}, new int[]{613, 306}, new int[]{594, 319}, new int[]{574, 333}, new int[]{537, 358}, new int[]{467, 359}, new int[]{404, 330},
            new int[]{364, 313}, new int[]{334, 297}, new int[]{280, 341}, new int[]{272, 370}, new int[]{275, 428}, new int[]{286, 464}, new int[]{311, 501}
    );



    String departureStation = "";
    String arrivalStation = "";

    int sortValue = 0;

    TextView routesText;
    LinearLayout routesContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);
        departureStation = getIntent().getStringExtra("DepatureStaion");
        arrivalStation = getIntent().getStringExtra("ArrivalStaion");
        sortValue = getIntent().getIntExtra("SortType",0);
        routesContainer = findViewById(R.id.routesContainer);
        routesContainer.removeAllViews();  // Clear any existing views

//        routesText = findViewById(R.id.possibleRoutesText);
//        routesText.setMovementMethod(new ScrollingMovementMethod());

        getRoutes();

    }

    private void getRoutes(){
        List<Byte> departureStationLine = new ArrayList<>();
        List<Byte> arrivalStationLine = new ArrayList<>();
        List<Byte> departureStationIndexList = new ArrayList<>();
        List<Byte> arrivalStationIndexList = new ArrayList<>();

        addStationInfo(departureStation, metroLine1Stations, departureStationLine, departureStationIndexList, (byte) 1);
        addStationInfo(departureStation, metroLine2Stations, departureStationLine, departureStationIndexList, (byte) 2);
        addStationInfo(departureStation, metroLine3Branch1Stations, departureStationLine, departureStationIndexList, (byte) 3);
        addStationInfo(departureStation, metroLine3Branch2Stations, departureStationLine, departureStationIndexList, (byte) 4);

        addStationInfo(arrivalStation, metroLine1Stations, arrivalStationLine, arrivalStationIndexList, (byte) 1);
        addStationInfo(arrivalStation, metroLine2Stations, arrivalStationLine, arrivalStationIndexList, (byte) 2);
        addStationInfo(arrivalStation, metroLine3Branch1Stations, arrivalStationLine, arrivalStationIndexList, (byte) 3);
        addStationInfo(arrivalStation, metroLine3Branch2Stations, arrivalStationLine, arrivalStationIndexList, (byte) 4);

        // Check if both lists have common stations and update them
        updateCommonStations(departureStationLine, arrivalStationLine, departureStationIndexList, arrivalStationIndexList, departureStation, arrivalStation);


        if (departureStationLine.isEmpty()) {
            System.out.println("You entered wrong departure station");
            return;
        }
        if (arrivalStationLine.isEmpty()) {
            System.out.println("You entered wrong arrival station");
            return;
        }

        List<List<List<String>>> possibleRouteData = new ArrayList<>();

        for (int i = 0; i < departureStationLine.size(); i++) {
            for (int j = 0; j < arrivalStationLine.size(); j++) {
                possibleRoutes(possibleRouteData, metroLine1Stations, metroLine2Stations, metroLine3Branch1Stations, metroLine3Branch2Stations,
                        departureStationLine.get(i), arrivalStationLine.get(j), departureStationIndexList.get(i), arrivalStationIndexList.get(j));
            }
        }
        printRoutes(possibleRouteData);
    }


    private void addStationInfo(String station, List<String> metroLineStations, List<Byte> stationLine, List<Byte> stationIndexList, byte lineNum) {
        byte stationIndex = (byte) metroLineStations.indexOf(station);
        if (stationIndex != -1) {
            stationLine.add(lineNum);
            stationIndexList.add(stationIndex);
        }
    }

    void updateCommonStations(List<Byte> departureStationLine, List<Byte> arrivalStationLine, List<Byte> departureStationIndexList, List<Byte> arrivalStationIndexList, String departureStation, String arrivalStation) {

        boolean departureStationFound = false;
        boolean arrivalStationFound = false;
        List<String> stations = null;
        List<Byte> lines = null;
        for (Map.Entry<List<Byte>, List<String>> entry : commonStationsMap.entrySet()) {
            stations = entry.getValue();
            if(stations.contains(departureStation)) departureStationFound = true;
            if(stations.contains(arrivalStation)) arrivalStationFound = true;
            if (departureStationFound && arrivalStationFound) return;
        }

        for (Map.Entry<List<Byte>, List<String>> entry : commonStationsMap.entrySet()) {
            stations = entry.getValue();
//            List<String> lineStations = getLineStations(departureStationLine, metroLine1Stations, metroLine2Stations, metroLine3Branch1Stations, metroLine3Branch2Stations);
            if(stations.contains(departureStation)){
                for (Byte line : arrivalStationLine) {
                    if (departureStationLine.contains(line)){
                        int depLineIndex = departureStationLine.indexOf(line);
                        int arrLineIndex = arrivalStationLine.indexOf(line);
                        byte depStationIndex = departureStationIndexList.get(depLineIndex);
                        byte arrStationIndex = arrivalStationIndexList.get(arrLineIndex);
                        departureStationIndexList.clear();
                        arrivalStationIndexList.clear();
                        departureStationIndexList.add(depStationIndex);
                        arrivalStationIndexList.add(arrStationIndex);
                        departureStationLine.clear();
                        arrivalStationLine.clear();
                        departureStationLine.add(line);
                        arrivalStationLine.add(line);
                        break;
                    }
                }
            }
            if(stations.contains(arrivalStation)){
                for (Byte line : departureStationLine) {
                    if (arrivalStationLine.contains(line)){
                        int depLineIndex = departureStationLine.indexOf(line);
                        int arrLineIndex = arrivalStationLine.indexOf(line);
                        byte depStationIndex = departureStationIndexList.get(depLineIndex);
                        byte arrStationIndex = arrivalStationIndexList.get(arrLineIndex);
                        departureStationIndexList.clear();
                        arrivalStationIndexList.clear();
                        departureStationIndexList.add(depStationIndex);
                        arrivalStationIndexList.add(arrStationIndex);
                        departureStationLine.clear();
                        arrivalStationLine.clear();
                        departureStationLine.add(line);
                        arrivalStationLine.add(line);
                        break;
                    }
                }
            }
        }
    }


    public void possibleRoutes(List<List<List<String>>> routeData, List<String> metroLine1Stations, List<String> metroLine2Stations,
                               List<String> metroLine3Branch1Stations, List<String> metroLine3Branch2Stations,
                               byte departureStationLine, byte arrivalStationLine, byte departureStationIndex, byte arrivalStationIndex) {
        if (departureStationLine == arrivalStationLine) {
            List<String> lineStations = getLineStations(departureStationLine, metroLine1Stations, metroLine2Stations, metroLine3Branch1Stations, metroLine3Branch2Stations);
            String lineStartDirection = getLineStartDirection(departureStationLine);
            String lineEndDirection = getLineEndDirection(departureStationLine);
            String lineName = lineName(departureStationLine);

            routeData.add(findRoute1(lineStations, departureStationIndex, arrivalStationIndex, lineStartDirection, lineEndDirection, lineName, true));
        }
        else {
            List<String> commonStations = findCommonStation(departureStationLine, arrivalStationLine);
            if (!commonStations.isEmpty()) {
                for (String commonStation : commonStations) {
                    routeData.add(findRoute2(departureStationLine, arrivalStationLine, departureStationIndex, arrivalStationIndex, metroLine1Stations, metroLine2Stations, metroLine3Branch1Stations, metroLine3Branch2Stations,commonStation));
                }
            }
            List<List<String>> route3 = findRoute3(departureStationLine, arrivalStationLine, departureStationIndex, arrivalStationIndex, metroLine1Stations, metroLine2Stations, metroLine3Branch1Stations, metroLine3Branch2Stations, arrivalStation);
            for (List<String> route : route3) {
                if(route!=null) routeData.add(Collections.singletonList(route));
            }
        }



    }

    private List<String> findCommonStation(byte departureStationLine, byte arrivalStationLine) {
        List<Byte> key = Arrays.asList(departureStationLine, arrivalStationLine);
        if (commonStationsMap.containsKey(key)) {
            return commonStationsMap.get(key);
        }
        List<Byte> reverseKey = Arrays.asList(arrivalStationLine, departureStationLine);
        if (commonStationsMap.containsKey(reverseKey)) {
            return commonStationsMap.get(reverseKey);
        }
        return new ArrayList<>();
    }



    private List<String> getLineStations(byte line, List<String> metroLine1Stations, List<String> metroLine2Stations, List<String> metroLine3Branch1Stations, List<String> metroLine3Branch2Stations) {
        switch (line) {
            case 1:
                return metroLine1Stations;
            case 2:
                return metroLine2Stations;
            case 3:
                return metroLine3Branch1Stations;
            case 4:
                return metroLine3Branch2Stations;
            default:
                return new ArrayList<>();
        }
    }

    private List<int[]> getLineCoordinates(byte line, List<int[]> metroLine1Coordinates, List<int[]> metroLine2Coordinates, List<int[]> metroLine3Branch1Coordinates, List<int[]> metroLine3Branch2Coordinates) {
        switch (line) {
            case 1:
                return metroLine1Coordinates;
            case 2:
                return metroLine2Coordinates;
            case 3:
                return metroLine3Branch1Coordinates;
            case 4:
                return metroLine3Branch2Coordinates;
            default:
                return new ArrayList<>();
        }
    }

    private String getLineStartDirection(byte line) {
        switch (line) {
            case 1:
                return "HELWAN";
            case 2:
                return "EL MOUNIB";
            case 3:
            case 4:
                return "ADLY MANSOUR";
            default:
                return "";
        }
    }

    private String getLineEndDirection(byte line) {
        switch (line) {
            case 1:
                return "EL MARG";
            case 2:
                return "SHOUBRA EL KHEIMA";
            case 3:
                return "ROD EL FARAG AXIS";
            case 4:
                return "CAIRO UNIVERSITY";
            default:
                return "";
        }
    }

    public List<List<String>> findRoute1(List<String> stations, int departureStationIndex, int arrivalStationIndex,
                                         String lineStartDirection, String lineEndDirection, String metroName, boolean onlyOneline) {
        List<List<String>> route = new ArrayList<>();
        List<String> segment = new ArrayList<>();

        int numStations = Math.abs(arrivalStationIndex - departureStationIndex);
        String direction = arrivalStationIndex > departureStationIndex ? lineEndDirection : lineStartDirection;

        for (int i = Math.min(departureStationIndex, arrivalStationIndex); i <= Math.max(departureStationIndex, arrivalStationIndex); i++) {
            segment.add(stations.get(i));
        }

        // Reverse the segment list if the departure station index is greater than the arrival station index
        if (departureStationIndex > arrivalStationIndex) {
            Collections.reverse(segment);
        }

        if (onlyOneline) {
            route.add(Collections.singletonList("No. of stations: " + numStations));
            int estimatedMinutes = numStations * 3;
            route.add(Collections.singletonList("Estimated travel time: " + convertMinutesToHoursAndMinutes(estimatedMinutes)));
            route.add(Collections.singletonList("Ticket Price: " + ticketPrice(numStations)));
            route.add(Collections.singletonList("Take: " + metroName));
            route.add(Collections.singletonList("Direction: " + direction));
            route.add(Collections.singletonList("Departure: " + stations.get(departureStationIndex)));
            route.add(Collections.singletonList("Arrival: " + stations.get(arrivalStationIndex)));
            route.add(Collections.singletonList("Intermediate Stations: " + String.join(" → ", segment)));
        }
        else{
            route.add(Collections.singletonList(String.valueOf(numStations)));
            route.add(Collections.singletonList(metroName)); //The name of the line
            route.add(Collections.singletonList(direction)); //Line direction
            route.add(Collections.singletonList(stations.get(departureStationIndex))); //Departure Station
            route.add(Collections.singletonList(stations.get(arrivalStationIndex))); //ArrivalStation
            route.add(Collections.singletonList(String.join(" → ", segment))); //Intermediate Stations
        }

        return route;
    }

    public List<List<String>> findRoute2(byte departureStationLine, byte arrivalStationLine, byte departureStationIndex, byte arrivalStationIndex,
                                         List<String> metroLine1Stations, List<String> metroLine2Stations, List<String> metroLine3Branch1Stations, List<String> metroLine3Branch2Stations, String commonStation) {
        List<List<String>> route = new ArrayList<>();
        List<String> segment1 = new ArrayList<>();
        List<String> segment2 = new ArrayList<>();

        List<String> departureLineStations = getLineStations(departureStationLine, metroLine1Stations, metroLine2Stations, metroLine3Branch1Stations, metroLine3Branch2Stations);
        List<String> arrivalLineStations = getLineStations(arrivalStationLine, metroLine1Stations, metroLine2Stations, metroLine3Branch1Stations, metroLine3Branch2Stations);


        int departureToCommonIndex = departureLineStations.indexOf(commonStation);
        int arrivalFromCommonIndex = arrivalLineStations.indexOf(commonStation);

        int numStations1 = Math.abs(departureStationIndex - departureToCommonIndex);
        int numStations2 = Math.abs(arrivalStationIndex - arrivalFromCommonIndex);

        for (int i = Math.min(departureStationIndex, departureToCommonIndex); i <= Math.max(departureStationIndex, departureToCommonIndex); i++) {
            segment1.add(departureLineStations.get(i));
        }

        for (int i = Math.min(arrivalStationIndex, arrivalFromCommonIndex); i <= Math.max(arrivalStationIndex, arrivalFromCommonIndex); i++) {
            segment2.add(arrivalLineStations.get(i));
        }

        // Reverse the segment lists if needed
        if (departureStationIndex > departureToCommonIndex) {
            Collections.reverse(segment1);
        }
        if (arrivalStationIndex < arrivalFromCommonIndex) {
            Collections.reverse(segment2);
        }
        if((departureStationLine==3 && arrivalStationLine==4 && arrivalStationIndex<arrivalFromCommonIndex)
                || (departureStationLine==4 && arrivalStationLine==3 && departureStationIndex<departureToCommonIndex)
                || numStations1<1
                || numStations2<1)   // (arrivalStationLine==2 && arrivalStationIndex==9)
            return null;

        route.add(Collections.singletonList("No. of stations: " + (numStations1 + numStations2)));
        int estimatedMinutes = (numStations1 + numStations2) * 3;
        route.add(Collections.singletonList("Estimated travel time: " + convertMinutesToHoursAndMinutes(estimatedMinutes)));
        route.add(Collections.singletonList("Estimated travel time for changing lines: 5 minutes"));
        route.add(Collections.singletonList("Estimated total travel time: " + convertMinutesToHoursAndMinutes(estimatedMinutes+5)));
        route.add(Collections.singletonList("Ticket Price: " + ticketPrice(numStations1 + numStations2)));
        route.add(Collections.singletonList("You will change at: " + commonStation));
        route.add(Collections.singletonList("First take: " + lineName(departureStationLine)));
        // Corrected direction based on departure station's index relative to common station's index
        if (departureStationIndex > departureToCommonIndex) {
            route.add(Collections.singletonList("Direction: " + getLineStartDirection(departureStationLine)));
        } else {
            route.add(Collections.singletonList("Direction: " + getLineEndDirection(departureStationLine)));
        }        route.add(Collections.singletonList("Departure: " + departureLineStations.get(departureStationIndex)));
        route.add(Collections.singletonList("Arrival: " + commonStation));
        route.add(Collections.singletonList("Intermediate Stations: " + String.join(" → ", segment1)));

        route.add(Collections.singletonList("Second take: " + lineName(arrivalStationLine)));
        // Corrected direction based on arrival station's index relative to common station's index
        if (arrivalStationIndex < arrivalFromCommonIndex) {
            route.add(Collections.singletonList("Direction: " + getLineStartDirection(arrivalStationLine)));
        } else {
            route.add(Collections.singletonList("Direction: " + getLineEndDirection(arrivalStationLine)));
        }
        route.add(Collections.singletonList("Departure: " + commonStation));
        route.add(Collections.singletonList("Arrival: " + arrivalLineStations.get(arrivalStationIndex)));
        route.add(Collections.singletonList("Intermediate Stations: " + String.join(" → ", segment2)));

        return route;
    }
    public List<List<String>> findRoute3(byte departureStationLine, byte arrivalStationLine, byte departureStationIndex, byte arrivalStationIndex,
                                         List<String> metroLine1Stations, List<String> metroLine2Stations, List<String> metroLine3Branch1Stations, List<String> metroLine3Branch2Stations, String arrivalStation) {
        List<List<String>> allRoutes = new ArrayList<>();


        List<String> departureLineStations = getLineStations(departureStationLine, metroLine1Stations, metroLine2Stations, metroLine3Branch1Stations, metroLine3Branch2Stations);
        List<String> arrivalLineStations = getLineStations(arrivalStationLine, metroLine1Stations, metroLine2Stations, metroLine3Branch1Stations, metroLine3Branch2Stations);
        for (int i = 0; i < 4; i++) {
            List<String> midLine = getLineStations((byte) (i + 1), metroLine1Stations, metroLine2Stations, metroLine3Branch1Stations, metroLine3Branch2Stations);
            byte midLineIndex = (byte) (i + 1);
            if (!((departureLineStations == midLine) || (arrivalLineStations == midLine))) {
                List<String> commonStationsDepMed = findCommonStation(departureStationLine, midLineIndex);
                List<String> commonStationsMedArr = findCommonStation(midLineIndex, arrivalStationLine);
                if (!commonStationsDepMed.isEmpty()) {
                    for (String commonStation1 : commonStationsDepMed) {
                        for (String commonStation2 : commonStationsMedArr) {

                            List<List<String>> route1 ;
                            List<List<String>> route2 ;
                            List<List<String>> route3 ;
                            List<String> tempRoute = new ArrayList<>();


                            if ( (departureStationLine==arrivalStationLine) && ( departureLineStations.indexOf(commonStation1)>=arrivalStationIndex || departureLineStations.indexOf(commonStation1)<=departureStationIndex)
                                    || ((arrivalStationLine==3 || arrivalStationLine==4) && arrivalLineStations.indexOf(commonStation2)>arrivalStationIndex && commonStation2.equalsIgnoreCase("KIT KAT")))  //(arrivalStationLine==2 && arrivalStationIndex==9)
                                continue;

//                            System.out.println(commonStation1);
//                            System.out.println(departureLineStations.indexOf(commonStation1));
//                            System.out.println(arrivalStationIndex);
//                            System.out.println(departureLineStations.indexOf(commonStation1)>=arrivalStationIndex );
//                            System.out.println(departureStationLine);
//                            System.out.println(arrivalStationLine);
//                            System.out.println("sadasdsa");
//                            System.out.println();
                            String lineStartDirection = getLineStartDirection(departureStationLine);
                            String lineEndDirection = getLineEndDirection(departureStationLine);
                            String lineName = lineName(departureStationLine);
                            route1=findRoute1(departureLineStations, departureStationIndex, departureLineStations.indexOf(commonStation1), lineStartDirection, lineEndDirection, lineName, false);

                            lineStartDirection = getLineStartDirection(midLineIndex);
                            lineEndDirection = getLineEndDirection(midLineIndex);
                            lineName = lineName(midLineIndex);
                            route2=findRoute1(midLine, midLine.indexOf(commonStation1), midLine.indexOf(commonStation2), lineStartDirection, lineEndDirection, lineName, false);

                            lineStartDirection = getLineStartDirection(arrivalStationLine);
                            lineEndDirection = getLineEndDirection(arrivalStationLine);
                            lineName = lineName(arrivalStationLine);
                            route3=findRoute1(arrivalLineStations, arrivalLineStations.indexOf(commonStation2), arrivalStationIndex, lineStartDirection, lineEndDirection, lineName, false);


                            int numStations1 = Integer.parseInt(route1.get(0).get(0));
                            int numStations2 = Integer.parseInt(route2.get(0).get(0));
                            int numStations3 = Integer.parseInt(route3.get(0).get(0));

                            if(numStations1<1 || numStations2<1 || numStations3<1 || route2.get(5).get(0).contains(arrivalStation)) {
                                allRoutes.add(null);
                            }
                            else {
                                tempRoute.add(("No. of stations: " + (numStations1 + numStations2 + numStations3)));
                                int estimatedMinutes = (numStations1 + numStations2 + numStations3) * 3;
                                tempRoute.add("Estimated travel time: " + convertMinutesToHoursAndMinutes(estimatedMinutes));
                                tempRoute.add("Estimated travel time for changing lines: 10 minutes");
                                tempRoute.add("Estimated total travel time: " + convertMinutesToHoursAndMinutes(estimatedMinutes + 10));
                                tempRoute.add("Ticket Price: " + ticketPrice(numStations1 + numStations2 + numStations3));

                                tempRoute.add("First take: " + route1.get(1).get(0));
                                tempRoute.add("Direction: " + route1.get(2).get(0));
                                tempRoute.add("Departure: " + route1.get(3).get(0));
                                tempRoute.add("Arrival: " + route1.get(4).get(0));
                                tempRoute.add("Intermediate Stations: " + route1.get(5).get(0));

                                tempRoute.add("Second take: " + route2.get(1).get(0));
                                tempRoute.add("Direction: " + route2.get(2).get(0));
                                tempRoute.add("Departure: " + route2.get(3).get(0));
                                tempRoute.add("Arrival: " + route2.get(4).get(0));
                                tempRoute.add("Intermediate Stations: " + route2.get(5).get(0));

                                tempRoute.add("Third take: " + route3.get(1).get(0));
                                tempRoute.add("Direction: " + route3.get(2).get(0));
                                tempRoute.add("Departure: " + route3.get(3).get(0));
                                tempRoute.add("Arrival: " + route3.get(4).get(0));
                                tempRoute.add("Intermediate Stations: " + route3.get(5).get(0));
                                allRoutes.add(tempRoute);
                            }
                        }
                    }
                }
            }
        }
        if (departureStationLine == arrivalStationLine){
            ArrayList<Byte> lines1 = new ArrayList<Byte>(Arrays.asList((byte) 1, (byte) 2, (byte) 3, (byte) 4));
            lines1.remove(departureStationLine-1);
            ArrayList<Byte> lines2 = new ArrayList<Byte>(Arrays.asList((byte) 1, (byte) 2, (byte) 3, (byte) 4));
            lines2.remove(departureStationLine-1);
            for (Byte line1 : lines1) {
                for (Byte line2 : lines2) {
                    if (!Objects.equals(line1, line2)) {
                        List<String> midLine1 = getLineStations(line1, metroLine1Stations, metroLine2Stations, metroLine3Branch1Stations, metroLine3Branch2Stations);
                        byte midLineIndex1 = line1;
                        List<String> midLine2 = getLineStations(line2, metroLine1Stations, metroLine2Stations, metroLine3Branch1Stations, metroLine3Branch2Stations);
                        byte midLineIndex2 = line2;
                        List<String> commonStationsDepMed = findCommonStation(departureStationLine, midLineIndex1);
                        List<String> commonStationsMedMed = findCommonStation(midLineIndex1, midLineIndex2);
                        List<String> commonStationsMedArr = findCommonStation(midLineIndex2, arrivalStationLine);
                        if (!commonStationsDepMed.isEmpty()) {
                            for (String commonStation1 : commonStationsDepMed) {
                                if((departureStationIndex < departureLineStations.indexOf(commonStation1) && arrivalStationIndex > departureLineStations.indexOf(commonStation1))
                                        || (departureStationIndex > departureLineStations.indexOf(commonStation1) && arrivalStationIndex < departureLineStations.indexOf(commonStation1))) {
                                    for (String commonStation2 : commonStationsMedMed) {
                                        for (String commonStation3 : commonStationsMedArr) {

                                            if (!commonStation1.equalsIgnoreCase(commonStation3)) {
                                                List<List<String>> route1;
                                                List<List<String>> route2;
                                                List<List<String>> route3;
                                                List<List<String>> route4;
                                                List<String> tempRoute = new ArrayList<>();

                                                String lineStartDirection = getLineStartDirection(departureStationLine);
                                                String lineEndDirection = getLineEndDirection(departureStationLine);
                                                String lineName = lineName(departureStationLine);
                                                route1 = findRoute1(departureLineStations, departureStationIndex, departureLineStations.indexOf(commonStation1), lineStartDirection, lineEndDirection, lineName, false);

                                                lineStartDirection = getLineStartDirection(midLineIndex1);
                                                lineEndDirection = getLineEndDirection(midLineIndex1);
                                                lineName = lineName(midLineIndex1);
                                                route2 = findRoute1(midLine1, midLine1.indexOf(commonStation1), midLine1.indexOf(commonStation2), lineStartDirection, lineEndDirection, lineName, false);

                                                lineStartDirection = getLineStartDirection(midLineIndex2);
                                                lineEndDirection = getLineEndDirection(midLineIndex2);
                                                lineName = lineName(midLineIndex2);
                                                route3 = findRoute1(midLine2, midLine2.indexOf(commonStation2), midLine2.indexOf(commonStation3), lineStartDirection, lineEndDirection, lineName, false);

                                                lineStartDirection = getLineStartDirection(arrivalStationLine);
                                                lineEndDirection = getLineEndDirection(arrivalStationLine);
                                                lineName = lineName(arrivalStationLine);
                                                route4 = findRoute1(arrivalLineStations, arrivalLineStations.indexOf(commonStation3), arrivalStationIndex, lineStartDirection, lineEndDirection, lineName, false);

                                                int numStations1 = Integer.parseInt(route1.get(0).get(0));
                                                int numStations2 = Integer.parseInt(route2.get(0).get(0));
                                                int numStations3 = Integer.parseInt(route3.get(0).get(0));
                                                int numStations4 = Integer.parseInt(route4.get(0).get(0));

                                                if (numStations2 < 1 || numStations3 < 1) {
                                                    allRoutes.add(null);
                                                } else {
                                                    tempRoute.add(("No. of stations: " + (numStations1 + numStations2 + numStations3 + numStations4)));
                                                    int estimatedMinutes = (numStations1 + numStations2 + numStations3 + numStations4) * 3;
                                                    tempRoute.add("Estimated travel time: " + convertMinutesToHoursAndMinutes(estimatedMinutes));
                                                    tempRoute.add("Estimated travel time for changing lines: 15 minutes");
                                                    tempRoute.add("Estimated total travel time: " + convertMinutesToHoursAndMinutes(estimatedMinutes + 15));
                                                    tempRoute.add("Ticket Price: " + ticketPrice(numStations1 + numStations2 + numStations3 + numStations4));

                                                    tempRoute.add("First take: " + route1.get(1).get(0));
                                                    tempRoute.add("Direction: " + route1.get(2).get(0));
                                                    tempRoute.add("Departure: " + route1.get(3).get(0));
                                                    tempRoute.add("Arrival: " + route1.get(4).get(0));
                                                    tempRoute.add("Intermediate Stations: " + route1.get(5).get(0));

                                                    tempRoute.add("Second take: " + route2.get(1).get(0));
                                                    tempRoute.add("Direction: " + route2.get(2).get(0));
                                                    tempRoute.add("Departure: " + route2.get(3).get(0));
                                                    tempRoute.add("Arrival: " + route2.get(4).get(0));
                                                    tempRoute.add("Intermediate Stations: " + route2.get(5).get(0));

                                                    tempRoute.add("Third take: " + route3.get(1).get(0));
                                                    tempRoute.add("Direction: " + route3.get(2).get(0));
                                                    tempRoute.add("Departure: " + route3.get(3).get(0));
                                                    tempRoute.add("Arrival: " + route3.get(4).get(0));
                                                    tempRoute.add("Intermediate Stations: " + route3.get(5).get(0));

                                                    if (numStations4 > 0) {
                                                        tempRoute.add("Forth take: " + route4.get(1).get(0));
                                                        tempRoute.add("Direction: " + route4.get(2).get(0));
                                                        tempRoute.add("Departure: " + route4.get(3).get(0));
                                                        tempRoute.add("Arrival: " + route4.get(4).get(0));
                                                        tempRoute.add("Intermediate Stations: " + route4.get(5).get(0));
                                                    }

                                                    allRoutes.add(tempRoute);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return allRoutes;
    }

    public int ticketPrice(int numberOfStations) {
        if (numberOfStations <= 9) {
            return 10;
        } else if (numberOfStations <= 16) {
            return 15;
        } else {
            return 20;
        }
    }

    public String lineName(byte lineNumber) {
        switch (lineNumber) {
            case 1:
                return "Metro Line 1";
            case 2:
                return "Metro Line 2";
            case 3:
                return "Metro Line 3 Branch 1";
            case 4:
                return "Metro Line 3 Branch 2";
            default:
                return "";
        }
    }

    public byte lineNumber(String lineName) {
        switch (lineName) {
            case "Metro Line 1":
                return 1;
            case "Metro Line 2":
                return 2;
            case "Metro Line 3 Branch 1":
                return 3;
            case "Metro Line 3 Branch 2":
                return 4;
            default:
                return 0;
        }
    }

    private String convertMinutesToHoursAndMinutes(int minutes) {
        int hours = minutes / 60;
        int remainingMinutes = minutes % 60;
        return hours + " hours " + remainingMinutes + " minutes";
    }

    public List<List<List<String>>> removeRepeatedRoutes(List<List<List<String>>> routeData){
        HashSet<List<List<String>>> noDuplicate = new HashSet<>(routeData);
        return new ArrayList<>(noDuplicate);

    }

    @SuppressLint("NewApi")
    public void printRoutes(List<List<List<String>>> routes) {
        String possibleRoutes = "";
        // Remove null routes



        if(sortValue == 0) {
            // Sort routes based on the number of stations
            routes.sort(Comparator.comparingInt(RoutesActivity::extractNumberOfStations));
        }
        else if (sortValue == 1) {
            // Sort routes based on the number of stations
            routes.sort(Comparator.comparingInt(RoutesActivity::extractNumberOfStations));
            routes.sort(Comparator.comparingInt(RoutesActivity::extractNumberOfExchanges));
        }

        //  Sort routes based on the number of stations in diff way
//        routes.sort(Comparator.comparingInt(route -> {
//            int totalSize = 0;
//            for (List<String> sublist : route) {
//                totalSize += sublist.size();
//            }
//            return totalSize;
//        }));

        possibleRoutes+=("No. of possible routes: " + routes.size()+"\n\n");
        addTextView(routesContainer, possibleRoutes);
        byte routeNo = 1;
        String ticketPrice = "";
        boolean ticketPriceFound = false;
        String lineName = "";
        List<List<List<int[]>>> routesCoordinates = new ArrayList<>();
        List<String> allroutesDetails = new ArrayList<>();
//        for (List<List<String>> route : routes) {
//            possibleRoutes+="Route (" + routeNo + ")\n";
//            for (List<String> segment : route) {
//                for (String detail : segment) {
//                    if(detail.contains("take") || detail.contains("Take")){
//                        lineName = detail.split(": ")[1];
//                    }
//                    if(detail.contains("→")){
//                        extractStationsPixels(routesCoordinates,detail,lineName,routeNo);
//                    }
//                    if(!ticketPriceFound && detail.startsWith("Ticket")) {
//                        possibleRoutes+=detail + "\n";
//                        ticketPrice = detail;
//                        ticketPriceFound = true;
//                    }
//                    else if (detail.startsWith("Ticket")) possibleRoutes+=ticketPrice+'\n';
//                    else  possibleRoutes+=detail + "\n";
//                }
//            }
//            routeNo++;
//            possibleRoutes+="\n";
//        }
//        routesText.setText(possibleRoutes);


        for (List<List<String>> route : routes) {
            StringBuilder routeDetails = new StringBuilder("Route (" + routeNo + ")\n");
            for (List<String> segment : route) {
                for (String detail : segment) {
                    if (detail.contains("take") || detail.contains("Take")) {
                        lineName = detail.split(": ")[1];
                    }
                    if (detail.contains("→")) {
                        extractStationsPixels(routesCoordinates, detail, lineName, routeNo);
                    }
                    if (!ticketPriceFound && detail.startsWith("Ticket")) {
                        routeDetails.append(detail).append("\n");
                        ticketPrice = detail;
                        ticketPriceFound = true;
                    } else if (detail.startsWith("Ticket")) {
                        routeDetails.append(ticketPrice).append('\n');
                    } else {
                        routeDetails.append(detail).append("\n");
                    }
                }
            }
            routeNo++;
            routeDetails.append("\n");

            allroutesDetails.add(String.valueOf(routeDetails));


        }



        // Convert routesCoordinates to ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>
        ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> serializedData = new ArrayList<>();
        for (List<List<int[]>> route : routesCoordinates) {
            ArrayList<ArrayList<ArrayList<Integer>>> routeList = getArrayLists(route);
            serializedData.add(routeList);
        }

        for (int i = 0; i < allroutesDetails.size(); i++) {
            // Add TextView for route details
            addTextView(routesContainer, allroutesDetails.get(i));

            // Add Button for each route
            addButton(routesContainer, "Show Route " + (i+1),serializedData.get(i));
        }

    }

    private static @NonNull ArrayList<ArrayList<ArrayList<Integer>>> getArrayLists(List<List<int[]>> route) {
        ArrayList<ArrayList<ArrayList<Integer>>> routeList = new ArrayList<>();
        for (List<int[]> segment : route) {
            ArrayList<ArrayList<Integer>> segmentList = new ArrayList<>();
            for (int[] coordinate : segment) {
                ArrayList<Integer> coordinateList = new ArrayList<>();
                for (int value : coordinate) {
                    coordinateList.add(value);
                }
                segmentList.add(coordinateList);
            }
            routeList.add(segmentList);
        }
        return routeList;
    }


    private static int extractNumberOfStations(List<List<String>> route) {
        for (List<String> segment : route) {
            for (String detail : segment) {
                if (detail.startsWith("No. of stations:")) {
                    String[] parts = detail.split(": ");
                    return Integer.parseInt(parts[1].trim());
                }
            }
        }
        return Integer.MAX_VALUE; // If no "No. of stations" is found, assign a large number to move it to the end
    }


    private static int extractNumberOfExchanges(List<List<String>> route) {
        int exchangeLines = 0;
        for (List<String> segment : route) {
            for (String detail : segment) {
                if (detail.contains("take") || detail.contains("Take")) {
                    exchangeLines ++;
                }
            }
        }
        return exchangeLines;
    }



    private void extractStationsPixels(List<List<List<int[]>>> routesCoordinates, String detail, String lineName, byte routeNo) {
        String temp = detail.split(":")[1];
        // Split the string into a list of station names
        List<String> stationList = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            stationList = Arrays.stream(temp.split("→"))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }

        byte lineNo = lineNumber(lineName);
        System.out.println(routeNo);
        System.out.println(stationList);
        List<String> metroStations = getLineStations(lineNo, metroLine1Stations, metroLine2Stations, metroLine3Branch1Stations, metroLine3Branch2Stations);
        List<int[]> metroCoordinates = getLineCoordinates(lineNo, metroLine1Coordinates, metroLine2Coordinates, metroLine3Branch1Coordinates, metroLine3Branch2Coordinates);

        List<int[]> lineCoordinates = new ArrayList<>();
        List<List<int[]>> routeLineCoordinates;

        if (routesCoordinates.size() == routeNo) {
            routeLineCoordinates = routesCoordinates.get(routeNo - 1);
            routesCoordinates.remove(routeNo - 1);
        } else {
            routeLineCoordinates = new ArrayList<>();
        }

        assert stationList != null;
        for (String station : stationList) {
            int index = metroStations.indexOf(station);
            // Create a copy of the coordinate array
            int[] coordinateCopy = Arrays.copyOf(metroCoordinates.get(index), metroCoordinates.get(index).length);
            lineCoordinates.add(coordinateCopy);
        }

        // Create a deep copy of lineCoordinates to add to routeLineCoordinates
        List<int[]> lineCoordinatesCopy = new ArrayList<>();
        for (int[] coords : lineCoordinates) {
            lineCoordinatesCopy.add(Arrays.copyOf(coords, coords.length));
        }

        routeLineCoordinates.add(lineCoordinatesCopy);

        // Create a deep copy of routeLineCoordinates to add to routesCoordinates
        List<List<int[]>> routeLineCoordinatesCopy = new ArrayList<>();
        for (List<int[]> coordsList : routeLineCoordinates) {
            List<int[]> coordsListCopy = new ArrayList<>();
            for (int[] coords : coordsList) {
                coordsListCopy.add(Arrays.copyOf(coords, coords.length));
            }
            routeLineCoordinatesCopy.add(coordsListCopy);
        }

        routesCoordinates.add(routeNo - 1, routeLineCoordinatesCopy);

        System.out.println(routeLineCoordinates);
        System.out.println(routesCoordinates);
        routeLineCoordinates.clear();
    }


    private void addTextView(LinearLayout container, String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(0, 0, 0, 16);  // Optional: add padding for better readability
        container.addView(textView);
    }

    private void addButton(LinearLayout container, String buttonText, ArrayList<ArrayList<ArrayList<Integer>>> serializedData) {
        Button button = new Button(this);
        button.setText(buttonText);
        button.setOnClickListener(view -> {
            // Handle button click
            Intent intent = new Intent(this,StationsViewActivity.class);
            intent.putExtra("RouteCoordinates", serializedData);
            startActivity(intent);
        });
        container.addView(button);
    }


}