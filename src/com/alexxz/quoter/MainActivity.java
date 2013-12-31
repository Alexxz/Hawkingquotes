package com.alexxz.quoter;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.ExpandableListView.*;
import android.support.v4.view.*;
import android.graphics.*;

public class MainActivity extends Activity
{
	final static private String[] quotes = {
		"We need something new. We can't predict what that will be or when we will find it because if we knew that, we would have found it already!",
		"For millions of years, mankind lived just like the animals. Then something happened which unleashed the power of our imagination. We learned to talk and we learned to listen...",
		"Consideration of black holes suggests, not only that God does play dice, but that he sometimes confuses us by throwing them where they can't be seen.",
		"Many people would claim that the boundary conditions are not part of physics but belong to metaphysics or religion. They would claim that nature had complete freedom to start the universe off any way it wanted. That may be so, but it could also have made it evolve in a completely arbitrary and random manner. Yet all the evidence is that it evolves in a regular way according to certain laws.",
		"If you are disabled, it is probably not your fault, but it is no good blaming the world or expecting it to take pity on you. One has to have a positive attitude and must make the best of the situation that one finds oneself in; if one is physically disabled, one cannot afford to be psychologically disabled as well.",
		"My goal is simple. It is a complete understanding of the universe, why it is as it is and why it exists at all.",
		"There ought to be something very special about the boundary conditions of the universe and what can be more special than that there is no boundary?",
		"What I have done is to show that it is possible for the way the universe began to be determined by the laws of science. In that case, it would not be necessary to appeal to God to decide how the universe began. This doesn't prove that there is no God, only that God is not necessary.",
		"We are just an advanced breed of monkeys on a minor planet of a very average star. But we can understand the Universe. That makes us something very special.",
		"So Einstein was wrong when he said, \"God does not play dice.\" Consideration of black holes suggests, not only that God does play dice, but that he sometimes confuses us by throwing them where they can't be seen.",
		"The human race is just a chemical scum on a moderate-sized planet, orbiting around a very average star in the outer suburb of one among a hundred billion galaxies. We are so insignificant that I can't believe the whole universe exists for our benefit. That would be like saying that you would disappear if I closed my eyes.",
		"I think computer viruses should count as life ... I think it says something about human nature that the only form of life we have created so far is purely destructive. We've created life in our own image.",
		"It is not clear that intelligence has any long-term survival value.",
		"Although September 11 was horrible, it didn't threaten the survival of the human race, like nuclear weapons do.",
		};
		
	private GestureDetectorCompat mDetector; 
	private TextSwitcher mSwitcher;
	
	private int counter = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
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
				    myText.setTextAppearance(activity, android.R.attr.textAppearanceLarge);
					myText.setTextColor(android.graphics.Color.parseColor("#AD5C56"));
					return myText;
				}
			});

		
	 	android.view.animation.Animation in = android.view.animation.AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
	    android.view.animation.Animation out = android.view.animation.AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);
		
		// set the animation type of textSwitcher
		mSwitcher.setInAnimation(in);	
	    mSwitcher.setOutAnimation(out);
		
		showNextQuote();
		
    }
	
	private  void showNextQuote()
	{


		this.mSwitcher.setText(MainActivity.quotes[this.counter]);
		this.counter++;
		this.counter = this.counter % MainActivity.quotes.length;
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){ 
	    this.mDetector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}
	
	class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
		 
		@Override
	    public boolean onDown(MotionEvent event) { 
		//Log.d(DEBUG_TAG,"onDown: " + event.toString()); 
			return true;
		}

		@Override
		public boolean onFling(MotionEvent event1, MotionEvent event2, 
							  float velocityX, float velocityY) {
		    showNextQuote();
		    return true;
		}
    }
}
