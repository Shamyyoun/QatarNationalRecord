package com.noqwerty.sqw;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
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
import android.widget.Spinner;
import android.widget.TextView;

import com.noqwerty.sqw.datamodels.Constants;
import com.noqwerty.sqw.utils.FontUtil;
import com.noqwerty.sqw.utils.FragmentUtil;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	// actionbar objects
	protected TextView textTitle;
	protected ImageButton buttonIcon;
	protected ImageButton buttonPlay;
	protected Spinner spinner;
	private boolean musicPlaying = false;

	// activity objects
	private FragmentUtil fragmentUtil;
	private boolean paused;

	// menu drawer objects
	protected MenuDrawer mDrawer;
	private TextView textHome;
	private TextView textAboutQatar;
	private TextView textMediaCenter;
	private TextView textNationalDay;
	private TextView textSponsors;
	private TextView textQatar2020;
	private TextView textLanguage;
	private RadioGroup radioGroupLanguage;
	private RadioButton radioAr;
	private RadioButton radioEn;

	// music player objects
	private MediaPlayer playerMusic;

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
		spinner = (Spinner) actionbarRootView
				.findViewById(R.id.spinner);

		// init menu drawer and activity view
		Position menuDrawerPosition;
		if (appLanguage.equals(Constants.LANG_EN)) {
			menuDrawerPosition = Position.LEFT;
		} else {
			menuDrawerPosition = Position.RIGHT;
		}
		mDrawer = MenuDrawer.attach(this, MenuDrawer.Type.OVERLAY,
				menuDrawerPosition, MenuDrawer.MENU_DRAG_CONTENT);
		mDrawer.setMenuView(R.layout.menu_drawer);
		mDrawer.setContentView(R.layout.activity_main);
        mDrawer.setDropShadowSize(0);

		// init menu drawer objects
		textHome = (TextView) findViewById(R.id.text_home);
		textAboutQatar = (TextView) findViewById(R.id.text_aboutQatar);
		textMediaCenter = (TextView) findViewById(R.id.text_mediaCenter);
		textNationalDay = (TextView) findViewById(R.id.text_nationalDay);
		textSponsors = (TextView) findViewById(R.id.text_sponsors);
		textQatar2020 = (TextView) findViewById(R.id.text_qatar2020);
		textLanguage = (TextView) findViewById(R.id.text_language);
		radioGroupLanguage = (RadioGroup) findViewById(R.id.radioGroup_language);
		radioAr = (RadioButton) findViewById(R.id.radio_ar);
		radioEn = (RadioButton) findViewById(R.id.radio_en);

		// disable window unit splash ends
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
				WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

		// init activity components
		fragmentUtil = new FragmentUtil(this, false);

		// set initials
		fragmentUtil.gotoFragment(R.id.container_main, new SplashFragment(),
				SplashFragment.TAG);
		
		if (appLanguage.equals(Constants.LANG_AR)) {
			radioAr.setChecked(true);
		} else {
			radioEn.setChecked(true);
		}
		textHome.setSelected(true);

		// add listeners
		buttonIcon.setOnClickListener(this);
		buttonPlay.setOnClickListener(this);

		textHome.setOnClickListener(this);
		textAboutQatar.setOnClickListener(this);
		textMediaCenter.setOnClickListener(this);
		textNationalDay.setOnClickListener(this);
		textSponsors.setOnClickListener(this);
		textQatar2020.setOnClickListener(this);
		textLanguage.setOnClickListener(this);

		radioGroupLanguage
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group,
							final int checkedId) {
						final String selectedLanguage;
						if (checkedId == R.id.radio_en) {
							selectedLanguage = Constants.LANG_EN;
						} else {
							selectedLanguage = Constants.LANG_AR;
						}

						// show confirmation dialog
						AlertDialog.Builder builder = new Builder(
								MainActivity.this);

						String title = getString(R.string.language_settings);
						String message = getString(R.string.you_must_restart_app);

						builder.setTitle(title);
						builder.setMessage(message);
						builder.setPositiveButton(getString(R.string.ok),
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										AppController.updateLanguage(
												getApplicationContext(),
												selectedLanguage);
										AppController.updateLocaleSetting(
												getApplicationContext(),
												selectedLanguage);

										// restart this activity
										Intent intent = new Intent(
												MainActivity.this,
												MainActivity.class);
										startActivity(intent);
										MainActivity.this.finish();
									}
								});
						builder.setNegativeButton(getString(R.string.cancel),
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
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

		case R.id.text_qatar2020:
			onQatar2020();
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

	private void onPlay() {
		if (musicPlaying) {
			stopMusic();
		} else {
			playMusic();
		}
	}

	protected void playMusic() {
		try {
			// create player
			playerMusic = MediaPlayer.create(getApplicationContext(),
					R.raw.nashid_music);
			
			// add completion listener
			playerMusic.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					stopMusic();
				}
			});
			
			// statr player
			playerMusic.start();

			// change buttons
			buttonPlay.setImageResource(R.drawable.actionbar_pause);
			musicPlaying = true;
		} catch (Exception e) {
		}
	}

	protected void stopMusic() {
		try {
			// stop music
			playerMusic.stop();
			playerMusic.release();

			// change buttons
			buttonPlay.setImageResource(R.drawable.actionbar_play);
			musicPlaying = false;
		} catch (Exception e) {
		}
	}

	private void onHome() {
		fragmentUtil.gotoFragment(R.id.container_main, new HomeFragment(),
				HomeFragment.TAG);

		selectMenuItem(textHome);
		mDrawer.closeMenu();
	}

	private void onAboutQatar() {
		fragmentUtil.gotoFragment(R.id.container_main,
				new AboutQatarFragment(), AboutQatarFragment.TAG);

		selectMenuItem(textAboutQatar);
		mDrawer.closeMenu();
	}

	private void onMediaCenter() {
		fragmentUtil.gotoFragment(R.id.container_main,
				new MediaCenterFragment(), MediaCenterFragment.TAG);

		selectMenuItem(textMediaCenter);
		mDrawer.closeMenu();
	}

	private void onNationalDay() {
		fragmentUtil.gotoFragment(R.id.container_main,
				new NationalDayFragment(), NationalDayFragment.TAG);

		selectMenuItem(textNationalDay);
		mDrawer.closeMenu();
	}

	private void onSponsors() {
		fragmentUtil.gotoFragment(R.id.container_main, new SponsorsFragment(),
				SponsorsFragment.TAG);

		selectMenuItem(textSponsors);
		mDrawer.closeMenu();
	}

	private void onQatar2020() {
		Bundle bundle = new Bundle();
		bundle.putInt(Constants.KEY_TAB, Constants.ABOUT_QATAR_CATEGORY_FUTURE);
		fragmentUtil.gotoFragment(R.id.container_main,
				new AboutQatarFragment(), AboutQatarFragment.TAG, bundle);

		selectMenuItem(textQatar2020);
		mDrawer.closeMenu();
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

	private void selectMenuItem(TextView textView) {
		// unselect all first
		textHome.setSelected(false);
		textAboutQatar.setSelected(false);
		textMediaCenter.setSelected(false);
		textNationalDay.setSelected(false);
		textSponsors.setSelected(false);
		textQatar2020.setSelected(false);

		// select desired item
		textView.setSelected(true);
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

	@Override
	protected void onPause() {
		stopMusic();
		super.onPause();
		paused = true;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		paused = false;
	}

	public boolean isPaused() {
		return paused;
	}
}
