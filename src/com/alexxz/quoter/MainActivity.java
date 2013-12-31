package com.alexxz.quoter;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.ExpandableListView.*;
import android.support.v4.view.*;
import android.graphics.*;
import android.content.*;

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
		"The world has changed far more in the past 100 years than in any other century in history. The reason is not political or economic but technological — technologies that flowed directly from advances in basic science.",
		"One might think this means that imaginary numbers are just a mathematical game having nothing to do with the real world. From the viewpoint of positivist philosophy, however, one cannot determine what is real. All one can do is find which mathematical models describe the universe we live in. It turns out that a mathematical model involving imaginary time predicts not only effects we have already observed but also effects we have not been able to measure yet nevertheless believe in for other reasons. So what is real and what is imaginary? Is the distinction just in our minds?",
		"I don't think the human race will survive the next thousand years, unless we spread into space. There are too many accidents that can befall life on a single planet. But I'm an optimist. We will reach out to the stars.",
		"We shouldn't be surprised that conditions in the universe are suitable for life, but this is not evidence that the universe was designed to allow for life. We could call order by the name of God, but it would be an impersonal God. There's not much personal about the laws of physics.",
	    "[on the possibility of contact with an alien civilization] I think it would be a disaster. The extraterrestrials would probably be far in advance of us. The history of advanced races meeting more primitive people on this planet is not very happy, and they were the same species. I think we should keep our heads low.",
		"The life we have on Earth must have spontaneously generated itself. It must therefore be possible for life to generate spontaneously elsewhere in the universe.",
		"I'm sorry to disappoint science fiction fans, but if information is preserved, there is no possibility of using black holes to travel to other universes."
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
