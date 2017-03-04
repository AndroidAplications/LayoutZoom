package com.example.albertsegura.layoutzoom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ZoomableRelativeLayout mZoomableRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mZoomableRelativeLayout = (ZoomableRelativeLayout) findViewById(R.id.mZoomableLayout);
        final ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(this, new OnPinchListener());
        mZoomableRelativeLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                scaleGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    private class OnPinchListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        float startingSpan;
        float endSpan;
        float startFocusX;
        float startFocusY;


        public boolean onScaleBegin(ScaleGestureDetector detector) {
            startingSpan = detector.getCurrentSpan();
            startFocusX = detector.getFocusX();
            startFocusY = detector.getFocusY();
            return true;
        }


        public boolean onScale(ScaleGestureDetector detector) {
            mZoomableRelativeLayout.scale(detector.getCurrentSpan()/startingSpan, startFocusX, startFocusY);
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector detector) {
            mZoomableRelativeLayout.restore();
        }
    }
}
