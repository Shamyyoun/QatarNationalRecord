package com.noqwerty.sqw;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.noqwerty.sqw.datamodels.Constants;
import com.noqwerty.sqw.utils.FontUtil;
import com.noqwerty.sqw.utils.FragmentUtil;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	// actionbar objects
	protected TextView textTitle;
	protected ImageButton buttonIcon;
	protected ImageButton buttonPlay;
	private boolean musicPlaying = false;

	// activity objects
	private FragmentUtil fragmentUtil;

	// menu drawer objects
	protected MenuDrawer mDrawer;
	private TextView textHome;
	private TextView textAboutQatar;
	private TextView textMediaCenter;
	private TextView textNationalDay;
	private TextView textSponsors;
	private TextView textLanguage;
	private RadioGroup radioGroupLanguage;
	private RadioButton radioAr;
	private RadioButton radioEn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// change default font
		FontUtil.setDefaultFont(getApplicationContext(), "MONOSPACE",
				"font.ttf");
		super.onCreate(savedInstanceState);
		
		// load language
		String appLanguage = AppController.getLanguage(getApplicationContext());
		if (appLanguage == null) {
			// save arabic as default
			appLanguage = Constants.LANG_AR;
			AppController.updateLanguage(getApplicationContext(), appLanguage);
		}
		AppController.updateLocaleSetting(getApplicationContext(), appLanguage);

		// customize actionbar
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(R.layout.actionbar);
		View actionbarRootView = actionBar.getCustomView();
		textTitle = (TextView) actionbarRootView.findViewById(R.id.text_title);
		buttonIcon = (ImageButton) actionbarRootView
				.findViewById(R.id.button_icon);
		buttonPlay = (ImageButton) actionbarRootView
				.findViewById(R.id.button_play);

		// init menu drawer and activity view
		Position menuDrawerPosition;
		if (appLanguage == Constants.LANG_EN) {
			menuDrawerPosition = Position.LEFT;
		} else {
			menuDrawerPosition = Position.RIGHT;
		}
		mDrawer = MenuDrawer.attach(this, MenuDrawer.Type.OVERLAY,
				menuDrawerPosition, MenuDrawer.MENU_DRAG_CONTENT);
		mDrawer.setMenuView(R.layout.menu_drawer);
		mDrawer.setContentView(R.layout.activity_main);

		// init menu drawer objects
		textHome = (TextView) findViewById(R.id.text_home);
		textAboutQatar = (TextView) findViewById(R.id.text_aboutQatar);
		textMediaCenter = (TextView) findViewById(R.id.text_mediaCenter);
		textNationalDay = (TextView) findViewById(R.id.text_nationalDay);
		textSponsors = (TextView) findViewById(R.id.text_sponsors);
		textLanguage = (TextView) findViewById(R.id.text_language);
		radioGroupLanguage = (RadioGroup) findViewById(R.id.radioGroup_language);
		radioAr = (RadioButton) findViewById(R.id.radio_ar);
		radioEn = (RadioButton) findViewById(R.id.radio_en);

		// disable window unit splash ends
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
				WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

		// init activity components
		fragmentUtil = new FragmentUtil(this, true);

		// set initials
		fragmentUtil.gotoFragment(R.id.container_main, new SplashFragment(),
				SplashFragment.TAG);
		if (appLanguage == Constants.LANG_AR) {
			radioAr.setChecked(true);
		} else {
			radioEn.setChecked(true);
		}

		// add listeners
		buttonIcon.setOnClickListener(this);
		buttonPlay.setOnClickListener(this);

		textHome.setOnClickListener(this);
		textAboutQatar.setOnClickListener(this);
		textMediaCenter.setOnClickListener(this);
		textNationalDay.setOnClickListener(this);
		textSponsors.setOnClickListener(this);
		textLanguage.setOnClickListener(this);
		
		radioGroupLanguage.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, final int checkedId) {
				final String selectedLanguage;
				if (checkedId == R.id.radio_en) {
					selectedLanguage = Constants.LANG_EN;
				} else {
					selectedLanguage = Constants.LANG_AR;
				}
				
				// show confirmation dialog
				AlertDialog.Builder builder = new Builder(MainActivity.this);

				String title = getString(R.string.language_settings);
				String message = getString(R.string.you_must_restart_app);

				builder.setTitle(title);
				builder.setMessage(message);
				builder.setPositiveButton(getString(R.string.ok),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								AppController.updateLanguage(getApplicationContext(), selectedLanguage);
								AppController.updateLocaleSetting(getApplicationContext(), selectedLanguage);

								// restart this activity
								Intent intent = new Intent(MainActivity.this,
										MainActivity.class);
								startActivity(intent);
								MainActivity.this.finish();
							}
						});
				builder.setNegativeButton(getString(R.string.cancel),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						});
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_icon:
			onIcon();
			break;

		case R.id.button_play:
			onPlay();
			break;

		case R.id.text_home:
			onHome();
			break;

		case R.id.text_aboutQatar:
			onAboutQatar();
			break;

		case R.id.text_mediaCenter:
			onMediaCenter();
			break;

		case R.id.text_nationalDay:
			onNationalDay();
			break;

		case R.id.text_sponsors:
			onSponsors();
			break;
			
		case R.id.text_language:
			onLanguage();
			break;

		default:
			break;
		}
	}

	private void onIcon() {
		if (mDrawer.getDrawerState() == MenuDrawer.STATE_CLOSED
				|| mDrawer.getDrawerState() == MenuDrawer.STATE_CLOSING) {
			mDrawer.openMenu();
		} else {
			mDrawer.closeMenu();
		}
	}

	protected void onPlay() {
		if (musicPlaying) {
			// TODO stop music
			buttonPlay.setImageResource(R.drawable.actionbar_play);
			musicPlaying = false;
		} else {
			// TODO play music
			buttonPlay.setImageResource(R.drawable.actionbar_pause);
			musicPlaying = true;
		}
	}

	private void onHome() {
		mDrawer.closeMenu();
		fragmentUtil.gotoFragment(R.id.container_main,
				new HomeFragment(), HomeFragment.TAG);
	}

	private void onAboutQatar() {
		mDrawer.closeMenu();
		fragmentUtil.gotoFragment(R.id.container_main,
				new AboutQatarFragment(), AboutQatarFragment.TAG);
	}

	private void onMediaCenter() {
		mDrawer.closeMenu();
		fragmentUtil.gotoFragment(R.id.container_main,
				new MediaCenterFragment(), MediaCenterFragment.TAG);
	}

	private void onNationalDay() {
		mDrawer.closeMenu();
		fragmentUtil.gotoFragment(R.id.container_main,
				new NationalDayFragment(), NationalDayFragment.TAG);
	}

	private void onSponsors() {
		mDrawer.closeMenu();
		fragmentUtil.gotoFragment(R.id.container_main,
				new SponsorsFragment(), SponsorsFragment.TAG);
	}
	
	private void onLanguage() {
		// show or hide radio group
		if (radioGroupLanguage.getVisibility() == View.VISIBLE) {
			// hide
			radioGroupLanguage.setVisibility(View.GONE);
		} else {
			// show
			radioGroupLanguage.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onBackPressed() {
		// check if menu drawer is opened so close it
		final int drawerState = mDrawer.getDrawerState();
		if (drawerState == MenuDrawer.STATE_OPEN
				|| drawerState == MenuDrawer.STATE_OPENING) {
			mDrawer.closeMenu();
			return;
		}

		// check back stack to return previous fragment or exit
		FragmentManager fragmentManager = getSupportFragmentManager();
		if (fragmentManager.getBackStackEntryCount() > 0) {
			fragmentManager.popBackStack();
		} else {
			super.onBackPressed();
		}
	}

}
