package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class PathMeasureTest extends View{
    public PathMeasureTest(Context context) {
        super(context);
    }

    public PathMeasureTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathMeasureTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        Paint mDefaultPaint = new Paint();
        mDefaultPaint.setAntiAlias(true);
        mDefaultPaint.setStyle(Paint.Style.STROKE);
        mDefaultPaint.setStrokeWidth(15);
        mDefaultPaint.setColor(Color.BLACK);
//        path.moveTo(200,300);
//        path.lineTo(500,300);
//        path.lineTo(500,600);
//        path.lineTo(200,600);
//        PathMeasure pathMeasure1 = new PathMeasure(path,false);
//        PathMeasure pathMeasure2 = new PathMeasure(path,true);
//        Log.e("path的长度","pathMeasure1----"+pathMeasure1.getLength());
//        Log.e("path的长度","pathMeasure2----"+pathMeasure2.getLength());
        path.addRect(200,200,600,600, Path.Direction.CW);
        PathMeasure pathMeasure = new PathMeasure(path,false);
        Path dst = new Path();
        pathMeasure.getSegment(200,600,dst,false);
        canvas.drawPath(dst,mDefaultPaint);
    }
}
