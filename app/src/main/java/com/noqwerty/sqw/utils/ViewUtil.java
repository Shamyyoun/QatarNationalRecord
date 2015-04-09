package com.noqwerty.sqw.utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;

public class ViewUtil {
	/*
	 * used to show or hide view in default duration 250
	 */
	public static void showView(final View view, boolean show) {
		fadeView(view, show, 250);
	}

	/*
	 * used to show or hide view in passed duration
	 */
	public static void showView(final View view, boolean show, int animDuration) {
		fadeView(view, show, animDuration);
	}

	/*
	 * shows or hides a view with a smooth animation in specific duration
	 */
	private static void fadeView(final View view, final boolean show,
			int animDuration) {

		Animation anim = new AlphaAnimation((show ? 0 : 1), (show ? 1 : 0));
		anim.setInterpolator(new LinearInterpolator());
		anim.setDuration(animDuration);

		anim.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				view.setVisibility(show ? View.VISIBLE : View.GONE);
			}
		});

		view.startAnimation(anim);
	}
}
