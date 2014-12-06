package com.noqwerty.sqw.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

public class FragmentUtil {
	private ActionBarActivity activity;
	private boolean skipIfVisible;
	private int animFragmentIn = -1;
	private int animFragmentOut = -1;

	public FragmentUtil(ActionBarActivity activity, boolean skipIfVisible) {
		this.skipIfVisible = skipIfVisible;
		this.activity = activity;
	}

	public void gotoFragment(int containerLayout, Fragment fragment,
			String tag, Bundle bundle) {
		gotoTheFragment(containerLayout, fragment, tag, bundle);
	}

	public void gotoFragment(int containerLayout, Fragment fragment, String tag) {
		gotoTheFragment(containerLayout, fragment, tag, null);
	}

	private void gotoTheFragment(int containerLayout, Fragment fragment,
			String tag, Bundle bundle) {
		if (bundle != null) {
			fragment.setArguments(bundle);
		}

		if (skipIfVisible) {
			// check if visible
			if (!isFragmentInLayout(fragment, tag)) {
				FragmentTransaction ft = activity.getSupportFragmentManager()
						.beginTransaction();

				// set custom animations if possible
				if (animFragmentIn != -1 && animFragmentOut != -1) {
					ft.setCustomAnimations(animFragmentIn, animFragmentOut);
				}

				// try & catch to avoid exception if container not in the current
				// view
				try {
					ft.replace(containerLayout, fragment, tag);
					ft.commit();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			FragmentTransaction ft = activity.getSupportFragmentManager()
					.beginTransaction();

			// set custom animations if possible
			if (animFragmentIn != -1 && animFragmentOut != -1) {
				ft.setCustomAnimations(animFragmentIn, animFragmentOut);
			}

			// try & catch to avoid exception if container not in the current
			// view
			try {
				ft.replace(containerLayout, fragment, tag);
				ft.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isFragmentInLayout(Fragment fragment, String tag) {
		Fragment myFragment = activity.getSupportFragmentManager()
				.findFragmentByTag(tag);
		if (myFragment == null || !myFragment.isVisible()) {
			return false;
		} else {
			return true;
		}
	}

	public void setFragmentInAnimation(int animFragmentIn) {
		this.animFragmentIn = animFragmentIn;
	}

	public void setFragmentOutAnimation(int animFragmentOut) {
		this.animFragmentOut = animFragmentOut;
	}
}
