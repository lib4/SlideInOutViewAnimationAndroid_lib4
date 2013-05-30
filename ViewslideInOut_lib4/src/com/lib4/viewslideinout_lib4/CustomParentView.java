package com.lib4.viewslideinout_lib4;
/**
 * @author AnasAbubacker
 */
import com.lib4.viiewslideinout_lib4.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

public class CustomParentView extends FrameLayout{

    Animation animationSlideInLeft, animationSlideOutRight;
    
    public CustomParentView(Context context, AttributeSet attrs) {
	super(context, attrs);
	// TODO Auto-generated constructor stub
	InitializeAnimation(context);
    }
    
    public CustomParentView(Context context) {
	super(context);
	// TODO Auto-generated constructor stub
    }
    @Override
    public void addView (View child){
	super.addView(child);
	child.startAnimation(animationSlideInLeft);
    }
    
    
    @Override
    public void removeView (final View child){
	
	animationSlideOutRight.setAnimationListener(new AnimationListener() {
	    
	    @Override
	    public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	    }
	    
	    @Override
	    public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	    }
	    
	    @Override
	    public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		Remove(child);
	    }
	});
	child.startAnimation(animationSlideOutRight);
    }
    
    public void Remove(View view){
	super.removeView(view);
    }

    private void InitializeAnimation(Context context){
	animationSlideInLeft = AnimationUtils.loadAnimation(context,
		R.anim.right_left);
	animationSlideOutRight = AnimationUtils.loadAnimation(context,
		R.anim.left_right);
	animationSlideInLeft.setDuration(1000);
	animationSlideOutRight.setDuration(1000);
	
    }
    
}
