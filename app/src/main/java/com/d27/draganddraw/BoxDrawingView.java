package com.d27.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BoxDrawingView extends View {
    public static final String TAG = BoxDrawingView.class.getSimpleName();

    private Box mCurrentBox;
    private List<Box> mBoxen = new ArrayList<>();

    //paint 생성
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    //코드에서 뷰를 생성할 때 사용
    public BoxDrawingView(Context context) {
        super(context);
    }

    // XML로부터 뷰를 인플레이트 할 때 사용
    public BoxDrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //반투명의 붉은색으로 박스를 그린다
        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

        //배경을 황백색으로 칠한다
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());

        String action = "";

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                mCurrentBox = new Box(current);
                mBoxen.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                if(mCurrentBox!=null){
                    mCurrentBox.setCurrent(current);
                    //BoxDrawingView가 자신을 다시 그리게
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "ACTION_CANCEL";
                mCurrentBox = null;
                break;
        }

        Log.i(TAG, action
                + " at x = " + current.x
                + " , y = " + current.y);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //배경을 채운다
        canvas.drawPaint(mBackgroundPaint);

        for(Box box : mBoxen){
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);

            canvas.drawRect(left, top, right, bottom, mBoxPaint);
        }
    }
}
