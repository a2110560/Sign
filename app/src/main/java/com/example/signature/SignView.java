package com.example.signature;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.LinkedList;

public class SignView extends View {
    private LinkedList<HashMap<String,Float>> line =new LinkedList<>();
    public SignView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        for (int i=1;i<line.size();i++){
            HashMap<String,Float> p0=line.get(i-1);//起始點
            HashMap<String,Float> p1=line.get(i);//結束點
            canvas.drawLine(p0.get("x"),p0.get("y"),p1.get("x"),p1.get("y"),paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        HashMap<String,Float> point=new HashMap<>();
        point.put("x",event.getX());
        point.put("y",event.getY());
        line.add(point);
        invalidate();//會去觸發ondraw
        return true;//改true會持續偵測，最原始觸發點
    }
}
