package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import util.Utils;

/**
 * @author by :wangyanwei
 * @package name ：view
 * @describe :
 * @Date :2018/1/30 16:07
 */

public class CustomView extends View {
    private Paint mPaint = new Paint();
    private Context mContext;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, Utils.getScreenWidth(mContext), 300, mPaint);
        RectF rect = new RectF(0, 200, Utils.getScreenWidth(mContext), 400);
        canvas.drawArc(rect, 0, 180, false, mPaint);
    }
}
