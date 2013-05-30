package com.lib4.viewslideinout_lib4;

/**
 * @Author AnasAbubacker
 */
import java.util.Random;

import com.lib4.viiewslideinout_lib4.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class LaunchActivity extends Activity {

    CustomParentView mFrameLayout;

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_launch);
	mFrameLayout = (CustomParentView) findViewById(R.id.parentholder);

	gestureDetector = new GestureDetector(this, new CustomGestureDetector());
	gestureListener = new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {

		return gestureDetector.onTouchEvent(event);
	    }
	};

	mFrameLayout.setOnTouchListener(gestureListener);
    }

    private void AddCustomView(FrameLayout mFrameLayout) {
	View mCustomView = new View(this);
	Random rnd = new Random();
	int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256),
		rnd.nextInt(256));
	mCustomView.setBackgroundColor(color);
	mFrameLayout.addView(mCustomView);
    }

    private void RemoveCustomView(FrameLayout mFrameLayout) {
	int childcount = mFrameLayout.getChildCount();
	Log.e("child Count", "Get child Count" + childcount);
	if (childcount > 0)
	    mFrameLayout.removeView(mFrameLayout.getChildAt(childcount-1));
	else {
	    Toast.makeText(LaunchActivity.this, "No more views to remove!!",
		    Toast.LENGTH_SHORT).show();
	}
    }

    /**
     * 
     * 
     * @author Anas Abubacker
     *         Right Left Gesture Detection
     * 
     */
    class CustomGestureDetector extends SimpleOnGestureListener {
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
		float velocityY) {
	    try {

		if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
		    return false;
		// right to left swipe
		if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
			&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
		    Toast.makeText(LaunchActivity.this, "Left Swipe",
			    Toast.LENGTH_SHORT).show();
		    AddCustomView(mFrameLayout);
		} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
			&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
		    Toast.makeText(LaunchActivity.this, "Right Swipe",
			    Toast.LENGTH_SHORT).show();
		    RemoveCustomView(mFrameLayout);
		}
	    } catch (Exception e) {
		// nothing
	    }
	    return false;
	}

	@Override
	public boolean onDown(MotionEvent event) {

	    return true;
	}

    }

}
