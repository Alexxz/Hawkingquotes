package com.alexxz.hawkingquotes;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import android.widget.ExpandableListView.*;
import android.support.v4.view.*;
import android.graphics.*;
import android.content.*;
import android.content.res.*;
import android.preference.*;
import java.util.*;

public class MainActivity extends Activity
{
	private GestureDetectorCompat mDetector; 
	private TextSwitcher mSwitcher;
	private TextView mStatus;
	private SharedPreferences.OnSharedPreferenceChangeListener preflistener;
	
	private int counter = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setLocale();
		
		this.preflistener = new SharedPreferences.OnSharedPreferenceChangeListener() { 
			public void onSharedPreferenceChanged(SharedPreferences prefs, String key) { 
				setLocale();
				showQuote();
			} 
		};
		
		PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this.preflistener);
		
        setContentView(R.layout.main);
	
	    java.util.Random r = new java.util.Random(); 
		this.counter=r.nextInt(this.getQuotes().length - 1);
		
		this.mStatus = (TextView) findViewById(R.id.statustext);
		
		this.mDetector = new GestureDetectorCompat(this,  new MyGestureListener());
		
		final Button exitButton = (Button) findViewById(R.id.exitbutton);
		exitButton.setOnClickListener( new OnClickListener () {
			public void onClick(View p1) {
				finish();
			}
		});
		
		final Button NextQuoteButton = (Button) findViewById(R.id.nextquotebutton);
		NextQuoteButton.setOnClickListener( new OnClickListener () {
				
				public void onClick(View p1) {
					 showNextQuote();
                }
			});
			
		final MainActivity activity = this;
		mSwitcher = (TextSwitcher) findViewById(R.id.quotetext);
		mSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
				
				public View makeView() {
					
					TextView myText = new TextView(MainActivity.this);
					myText.setGravity(Gravity.CENTER);
				    myText.setTextAppearance(activity, android.R.style.TextAppearance_Large);
					myText.setTextColor(Color.parseColor("#AD5C56"));
					myText.setPadding(10, 10, 10, 10);
					return myText;
				}
			});

		final int time = 200;
	 	Animation in = new AlphaAnimation(0,1);
		in.setDuration(time);
	    Animation out = new AlphaAnimation(1,0);
		out.setDuration(time);
		in.setStartOffset(time/2);
		
		// set the animation type of textSwitcher
		mSwitcher.setInAnimation(in);	
	    mSwitcher.setOutAnimation(out);
		
		this.showQuote();
		
    }
	
	
	private void showNextQuote()
	{
		this.counter++;
		this.counter = this.counter % this.getQuotes().length;
		showQuote();
	}
	
	private void showQuote()
	{
		// no matter what, counter shall be within array index
		this.counter = this.counter % this.getQuotes().length;
		
		this.mSwitcher.setText(this.getQuotes()[this.counter]);
		this.mStatus.setText("" + this.getLanguage() + " " +  (this.counter + 1) + "/" + getQuotes().length);
	}
	
	private String getLanguage() {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		return pref.getString("quoteslanguage", "auto");
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){ 
	    this.mDetector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}
	
	private String[] getQuotes() {
		return getResources().getStringArray(R.array.quoteslist);
	}
	
	private void setLocale() {
		Locale locale = null;
		String lang = this.getLanguage();
		if (lang.equals("auto")) {
			locale = Locale.getDefault();
		} else {
			locale = new Locale(lang);
		}

		Configuration config = new Configuration(); 
		config.locale = locale; 
		getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
	}
	
	class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onFling(MotionEvent event1, MotionEvent event2, 
							  float velocityX, float velocityY) {
		    showNextQuote();
		    return true;
		}
    }
	
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem mi = menu.add(0, 1, 0, getString(R.string.preferencesoptn));
		mi.setIntent(new Intent(this, PrefActivity.class));
		return super.onCreateOptionsMenu(menu);
	}
}
