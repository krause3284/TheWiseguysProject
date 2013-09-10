package com.thewiseguys.android.ui.animation;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 * Created by Chris Krause on 6/09/13.
 * TheWiseguys - com.thewiseguys.android.ui.animation
 */
import android.annotation.TargetApi;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LiveButton {

    private DecelerateInterpolator decelerator = new DecelerateInterpolator();
    private OvershootInterpolator overshooter = new OvershootInterpolator(10f);

    @Inject
    LiveButton() {
    }

    public void setupLiveAnimOnButton( Button button, Runnable onEndRunnable ) {
        if (android.os.Build.VERSION.SDK_INT>=12) {
            this.setupLiveAnimOnButtonL12(button, onEndRunnable);
        }
    }

    @TargetApi(12)
    public void setupLiveAnimOnButtonL12( final Button button, final Runnable onEndRunnable ) {
        button.animate().setDuration(400);
        button.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
                    button.animate().setInterpolator(decelerator).
                            scaleX(.7f).scaleY(.7f);
                } else if (arg1.getAction() == MotionEvent.ACTION_UP) {
                    button.animate().setInterpolator(overshooter).
                            scaleX(1.6f).scaleY(1.6f).withEndAction(onEndRunnable);
                }
                return false;
            }
        });
    }
}
