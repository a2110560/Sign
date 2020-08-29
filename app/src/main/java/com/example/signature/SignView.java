package com.example.signature;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.LinkedList;

public class SignView extends View {
    private LinkedList<LinkedList<HashMap<String,Float>>> lines =new LinkedList<>();
    private LinkedList<LinkedList<HashMap<String,Float>>> recycler =new LinkedList<>();
    public SignView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);

        for (LinkedList<HashMap<String,Float>> line:lines){
            for (int i=1;i<line.size();i++){
                HashMap<String,Float> p0=line.get(i-1);//起始點
                HashMap<String,Float> p1=line.get(i);//結束點
                canvas.drawLine(p0.get("x"),p0.get("y"),p1.get("x"),p1.get("y"),paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        HashMap<String,Float> point=new HashMap<>();
        point.put("x",event.getX());
        point.put("y",event.getY());
        LinkedList<HashMap<String,Float>> line=null;
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            line=new LinkedList<>();
            lines.add(line);
        }else if(event.getAction()==MotionEvent.ACTION_MOVE){
            line=lines.getLast();
        }
        if(line!=null){
        line.add(point);
        recycler.clear();
        invalidate();//會去觸發ondraw，改寫畫面
        }
        return true;//改true會持續偵測，最原始觸發點
    }
    public void clear(){
        lines.clear();
        invalidate();
    }

    public void undo(){
        if(lines.size()>0){
            recycler.add(lines.removeLast());
            invalidate();
        }

    }
    public void redo(){
        if(recycler.size()>0){
            lines.addLast(recycler.removeLast());
            invalidate();
        }

    }
}
