package com.alexxz.hawkingquotes;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.ExpandableListView.*;
import android.support.v4.view.*;
import android.graphics.*;
import android.content.*;
import android.content.res.*;

public class MainActivity extends Activity
{
	static private String[] quotes = {};
		
	private GestureDetectorCompat mDetector; 
	private TextSwitcher mSwitcher;
	
	private int counter = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
		
		this.quotes = getResources().getStringArray(R.array.quoteslist);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	
	    java.util.Random r = new java.util.Random(); 
		this.counter=r.nextInt(MainActivity.quotes.length-1);
		
		
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
		mSwitcher.setFactory(new android.widget.ViewSwitcher.ViewFactory() {
				
				public View makeView() {
					// TODO Auto-generated method stub
					
					TextView myText = new TextView(MainActivity.this);
					myText.setGravity(Gravity.CENTER);
				    myText.setTextAppearance(activity, android.R.style.TextAppearance_Large);
					myText.setTextColor(android.graphics.Color.parseColor("#AD5C56"));
					myText.setPadding(10, 10, 10, 10);
					return myText;
				}
			});

	
		final int time = 200;
	 	android.view.animation.Animation in = new android.view.animation.AlphaAnimation(0,1);
		in.setDuration(time);
	    android.view.animation.Animation out = new android.view.animation.AlphaAnimation(1,0);
		out.setDuration(time);
		in.setStartOffset(time/2);
		
		// set the animation type of textSwitcher
		mSwitcher.setInAnimation(in);	
	    mSwitcher.setOutAnimation(out);
		
		showQuote();
		
    }
	
	private  void showNextQuote()
	{
		this.counter++;
		this.counter = this.counter % MainActivity.quotes.length;
		showQuote();
		
	}
	
	private void showQuote()
	{
		this.mSwitcher.setText(MainActivity.quotes[this.counter]);
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
}
