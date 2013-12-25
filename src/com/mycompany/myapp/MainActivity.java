package com.mycompany.myapp;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.ExpandableListView.*;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		Button exitButton = (Button) findViewById(R.id.exitbutton);
		exitButton.setOnClickListener( new OnClickListener () {
			public void onClick(View p1) {
				finish();
			}
		});
		
		Button NextQuoteButton = (Button) findViewById(R.id.nextquotebutton);
		NextQuoteButton.setOnClickListener( new OnClickListener () {
				public void onClick(View p1) {
					TextView text = (TextView) findViewById(R.id.quotetext);
					text.setText("My quote text");
				}
			});
    }
}
