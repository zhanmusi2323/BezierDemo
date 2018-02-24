package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author by :wangyanwei
 * @package name ：view
 * @describe :
 * @Date :2018/1/29 16:27
 */

public class BezierMySelf extends View {
    private Paint mPaint;
    private int centerX, centerY;
    private PointF start, end, control;
    private PointF mLastPoint;

    public BezierMySelf(Context context) {
        this(context, null);
    }

    public BezierMySelf(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierMySelf(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);
        start = new PointF(0, 0);
        end = new PointF(0, 0);
        control = new PointF(0, 0);
        mLastPoint = new PointF(0, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //根据触摸更改控制点
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("ACTION_DOWN", "---action_down");
                mLastPoint.x = event.getRawX();
                mLastPoint.y = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                control.x = event.getRawX();
                control.y = event.getRawY();
                Log.e("--------", "x的距离 " + Math.abs(control.x - mLastPoint.x) + "  y的距离" + Math.abs(control.y - mLastPoint.y));
                if (Math.abs(control.x - mLastPoint.x) > 10 && Math.abs(control.y - mLastPoint.y) > 10) {
                    invalidate();
                }
                Log.e("ACTION_MOVE", "---action_move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("ACTION_UP", "---ACTION_UP");
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;

        // 初始化数据点和控制点的位置
        start.x = centerX - 200;
        start.y = centerY;
        end.x = centerX + 200;
        end.y = centerY;
        control.x = centerX;
        control.y = centerY - 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(control.x, control.y, mPaint);

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x, start.y, control.x, control.y, mPaint);
        canvas.drawLine(control.x, control.y, end.x, end.y, mPaint);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.quadTo(control.x, control.y, end.x, end.y);
        canvas.drawPath(path, mPaint);
    }
}
