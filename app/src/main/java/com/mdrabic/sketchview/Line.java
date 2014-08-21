package com.mdrabic.sketchview;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public class Line {

    private List<PointF> points = new ArrayList<PointF>();

    /**
     * Add a new point to this line
     * @param x position
     * @param y position
     */
    public void addPoint(float x, float y) {
        points.add(new PointF(x, y));
    }

    /**
     * This line as an array of x, y points.
     * @return an array of x,y points
     */
    public float[] asArray() {
        float[] line = new float[(points.size() * 4) - 1];
        int ptr = 0;
        for (int i = 0; i < points.size(); i++) {
            PointF point = points.get(i);
            line[ptr] = point.x;
            line[++ptr] = point.y;
            ptr++;
            if (i > 0) {
                line[ptr] = point.x;
                line[++ptr] = point.y;
                ptr++;
            }
        }
        return line;
    }
}
