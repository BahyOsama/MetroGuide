package com.example.metroguide;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.List;

public class MetroLineView extends View {
    private List<List<int[]>> routesCoordinates;
    private Paint paint;
    private Drawable backgroundDrawable;
    private int width, height;

    public MetroLineView(Context context) {
        super(context);
        init(context, null);
    }

    public MetroLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MetroLineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // Initialize the paint object
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(12f);
        paint.setStyle(Paint.Style.STROKE);

        // Load the background image
        backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.metrostations);
    }

    public void setCoordinates(List<List<int[]>> routesCoordinates, int width, int height) {
        this.width = width;
        this.height =height;
        this.routesCoordinates = routesCoordinates;
        invalidate(); // Redraw the view
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);



        // Draw the background image
        if (backgroundDrawable != null) {
            backgroundDrawable.setBounds(0, 0, getWidth(), getHeight());
            backgroundDrawable.draw(canvas);
        }

        if (routesCoordinates == null || routesCoordinates.isEmpty()) return;


        int[] start;
        // Example usage in onDraw or similar method
        drawRouteCoordinates(canvas, paint, routesCoordinates, width, height);

    }

    private void drawCircle(Canvas canvas, Paint paint, int[] start, int width, int height, int color, float strokeWidth) {
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        canvas.drawCircle((float) (start[0] * (width / 950.0)), (float) (start[1] * (height / 950.0)), (float) 3.0, paint);
    }

    private void drawRouteCoordinates(Canvas canvas, Paint paint, List<List<int[]>> routesCoordinates, int width, int height) {
        for (int i = 0; i < routesCoordinates.size(); i++) {
            List<int[]> routeCoordinate = routesCoordinates.get(i);
            for (int j = 0; j < routeCoordinate.size(); j++) {
                int[] start = routeCoordinate.get(j);

                if (i == 0 && j == 0) {
                    // Start point of the first route
                    drawCircle(canvas, paint, start, width, height, Color.rgb(19, 244, 239), 28f);
                } else if (i == routesCoordinates.size() - 1 && j == routeCoordinate.size() - 1) {
                    // End point of the last route
                    drawCircle(canvas, paint, start, width, height, Color.rgb(102,169,255), 28f);
                }else if ((i != routesCoordinates.size() - 2 && j == 0)  ||  j == routeCoordinate.size() - 1) {
                    // Transition stations
                    drawCircle(canvas, paint, start, width, height, Color.rgb(255,102,157), 28f);
                } else if(i==0) {
                    // Intermediate points
                    drawCircle(canvas, paint, start, width, height, Color.rgb(250, 255, 0), 22f);
                }else if(i==1) {
                    // Intermediate points
                    drawCircle(canvas, paint, start, width, height, Color.rgb(255, 191, 0), 22f);
                } else if(i==2) {
                    // Intermediate points
                    drawCircle(canvas, paint, start, width, height, Color.rgb(153,115,0), 22f);
                }
            }
        }
    }



}