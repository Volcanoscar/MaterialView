package com.unary.materialview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Abhishek on 22/03/15.
 */
public class MaterialView extends View {

    private final static String COLOR = "#cc4989f2";

    private int color;
    private Paint bgPaint;
    private int height = 0;
    private int width = 0;

    public MaterialView(Context context) {
        super(context);
        init(context);
    }

    public MaterialView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MaterialView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        color = Color.parseColor(COLOR);
        bgPaint = new Paint();
        bgPaint.setColor(color);
        bgPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, width, height, bgPaint);
        super.onDraw(canvas);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
        width = w;
    }
}
