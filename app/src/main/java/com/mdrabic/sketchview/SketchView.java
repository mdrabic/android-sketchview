package com.mdrabic.sketchview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayDeque;
import java.util.Deque;


public class SketchView extends View {
    private Deque<Line> lines = new ArrayDeque<Line>();
    private Paint paint;

    public SketchView(Context context) {
        super(context);
        init();
    }

    public SketchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SketchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean handled = false;
        Line line;

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                line = new Line();
                line.addPoint(event.getX(), event.getY());
                lines.push(line);
                handled = true;
                break;

            case MotionEvent.ACTION_MOVE:
                line = lines.pop();
                line.addPoint(event.getX(), event.getY());
                lines.push(line);
                invalidate();
                handled = true;
                break;

            case MotionEvent.ACTION_UP:
                line = lines.pop();
                line.addPoint(event.getX(), event.getY());
                lines.push(line);
                invalidate();
                handled = true;
                break;
        }

        return handled;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Line line : lines) {
            canvas.drawLines(line.asArray(), paint);
        }
    }

    public void undo() {
        if (!lines.isEmpty()) {
            lines.pop();
            invalidate();
        }
    }
}
