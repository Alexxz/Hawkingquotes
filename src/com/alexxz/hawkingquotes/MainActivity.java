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

public class MainActivity extends Activity
{
	static private String[] quotes = {};
		
	private GestureDetectorCompat mDetector; 
	private TextSwitcher mSwitcher;
	private TextView mStatus;
	
	private int counter = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
		
		this.quotes = getResources().getStringArray(R.array.quoteslist);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	
	    java.util.Random r = new java.util.Random(); 
		this.counter=r.nextInt(MainActivity.quotes.length - 1);
		
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
		
		showQuote();
		
    }
	
	private void showNextQuote()
	{
		this.counter++;
		this.counter = this.counter % MainActivity.quotes.length;
		showQuote();
		
	}
	
	private void showQuote()
	{
		this.mSwitcher.setText(MainActivity.quotes[this.counter]);
		this.mStatus.setText("" + this.getLanguage() + " " +  (this.counter + 1) + "/" + MainActivity.quotes.length);
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
	
	class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onFling(MotionEvent event1, MotionEvent event2, 
							  float velocityX, float velocityY) {
		    showNextQuote();
		    return true;
		}
    }
	
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem mi = menu.add(0, 1, 0, "Preferences");
		mi.setIntent(new Intent(this, PrefActivity.class));
		return super.onCreateOptionsMenu(menu);
	}
}
