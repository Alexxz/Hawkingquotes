package com.alexxz.quoter;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.ExpandableListView.*;

public class MainActivity extends Activity
{
	final static private String[] quotes = {
		"We need something new. We can't predict what that will be or when we will find it because if we knew that, we would have found it already!",
		"quote2",
		"quote3"
		};
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		final Button exitButton = (Button) findViewById(R.id.exitbutton);
		exitButton.setOnClickListener( new OnClickListener () {
			public void onClick(View p1) {
				finish();
			}
		});
		
		final Button NextQuoteButton = (Button) findViewById(R.id.nextquotebutton);
		NextQuoteButton.setOnClickListener( new OnClickListener () {
			    private int counter = 0;
				
				public void onClick(View p1) {
					final TextView text = (TextView) findViewById(R.id.quotetext);
					//NextQuoteButton.setText("");
					text.setText(MainActivity.quotes[this.counter]);
					this.counter++;
					this.counter = this.counter % MainActivity.quotes.length;
				}
			});
    }
}
