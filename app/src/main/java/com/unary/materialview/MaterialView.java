package com.unary.materialview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Abhishek on 22/03/15.
 */
public class MaterialView extends RelativeLayout {

    private final static String COLOR = "#D94686ef";

    private int color;
    private Paint bgPaint;
    private int height = 0;
    private int width = 0;
    private Context context;
    private Bitmap materialBitmap;
    private int bitmapLeft;
    private int bitmapTop;
    private Path animePath;
    private boolean isViewCreated;

    private PathMeasure pathMeasure;
    private float pathLength;

    float step;   //distance each step
    float distance;  //distance moved

    float[] pos;
    float[] tan;


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
        this.context = context;
        addBackground();
        addText("This is from constructor");
        materialBitmap = null;
        isViewCreated = false;
        step = 10;
        distance = 0;
    }

    public void addBitmap(Bitmap bitmap, int left, int top) {
        bitmapTop = top;
        bitmapLeft = left;
        materialBitmap = bitmap;
        if (isViewCreated) {

            animePath = new Path();
            animePath.moveTo(left, top);
            animePath.lineTo(50, height - 50);
            pathMeasure = new PathMeasure(animePath, false);
            pathLength = pathMeasure.getLength();
            pos = new float[2];
            tan = new float[2];
            invalidate();
        }

    }

    public void addText(String textParam) {

        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);


        TextView tv = new TextView(context);
        tv.setText(textParam);
        tv.setTextColor(Color.WHITE);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        tv.setLayoutParams(layoutParams);
        this.addView(tv);
    }

    public void addBackground() {
        this.setBackgroundColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (materialBitmap == null || !isViewCreated) {
            return;
        }

        if (distance < pathLength) {
            pathMeasure.getPosTan(distance, pos, tan);
            canvas.drawBitmap(materialBitmap, pos[0], pos[1], null);
            distance += step;
            invalidate();
        } else {
            canvas.drawBitmap(materialBitmap, pos[0], pos[1], null);
        }


        super.onDraw(canvas);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
        width = w;
        isViewCreated = true;
        if (materialBitmap != null) {

            animePath = new Path();
            animePath.moveTo(bitmapLeft, bitmapTop);
            animePath.lineTo(50, h - 50 - (materialBitmap.getHeight()));
            pathMeasure = new PathMeasure(animePath, false);
            pathLength = pathMeasure.getLength();
            pos = new float[2];
            tan = new float[2];
            invalidate();
        }
    }
}
