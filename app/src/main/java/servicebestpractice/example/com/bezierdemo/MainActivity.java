package servicebestpractice.example.com.bezierdemo;

import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import view.AutoGraphView;

public class MainActivity extends Activity implements View.OnTouchListener{
    private AutoGraphView graphView;
    private TextView vTextView;
    private RelativeLayout vReLayout;
    private PointF down_point,up_point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("tag", "1111111");
        vReLayout = (RelativeLayout) findViewById(R.id.activity_main);
        down_point = new PointF();
        up_point = new PointF();
        vReLayout.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                down_point.x = event.getRawX();
                break;
            case MotionEvent.ACTION_UP:
                up_point.x = event.getRawX();
                if ((up_point.x-down_point.x)>0){
                    Toast.makeText(this,"右滑了",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this,"左滑了",Toast.LENGTH_LONG).show();
                }
                break;
        }
        return true;
    }
}
