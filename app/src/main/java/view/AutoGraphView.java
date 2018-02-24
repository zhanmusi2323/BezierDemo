package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class AutoGraphView extends View {
    private Path gesturePath;
    private Paint gesturePaint;
    private int defaultPaintWidth = 15;
    /**
     * 绘制Bezier的数据点和控制点
     */
    private PointF start, control;

    public AutoGraphView(Context context) {
        this(context, null);
    }

    public AutoGraphView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoGraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("测试事件传递", "---------- onTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(event);
                break;
        }
        return true;
    }

    private void touchDown(MotionEvent event) {
        start.x = event.getX();
        start.y = event.getY();
        gesturePath.moveTo(start.x, start.y);
    }

    private void touchMove(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        control.x = (x + start.x) / 2;
        control.y = (y + start.y) / 2;
        gesturePath.quadTo(start.x,start.y,control.x,control.y);
        start.x = x;
        start.y = y;
        invalidate();
    }

    private void init() {
        gesturePaint = new Paint();
        gesturePaint.setAntiAlias(true);
        gesturePaint.setStyle(Paint.Style.STROKE);
        gesturePaint.setStrokeWidth(defaultPaintWidth);
        gesturePaint.setColor(Color.BLACK);
        gesturePath = new Path();
        start = new PointF(0, 0);
        control = new PointF(0, 0);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(gesturePath, gesturePaint);
    }
}
