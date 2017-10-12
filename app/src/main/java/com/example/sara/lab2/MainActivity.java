package com.example.sara.lab2;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    float dx;
    float dy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView myView = (WebView) findViewById(R.id.myWebView);
        myView.loadUrl("http://met.guc.edu.eg");

        Button button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){

                final EditText editText = (EditText) findViewById(R.id.editText5);
                String text = editText.getText().toString();

                TextView myTextView = (TextView) findViewById(R.id.textView4);
                myTextView.append('\n'+text);


            }


        });

       /* ImageButton button2=(ImageButton) findViewById(R.id.imageButton3);
        button2.setOnTouchListener(this);*/

       /*sara betfatty*/

        myDragEventListener mDragListen = new myDragEventListener();

        ImageView launcherIcon=(ImageView) findViewById(R.id.imageView2);
        // Sets the tag
        launcherIcon.setTag("icon bitmap");
        launcherIcon.setOnTouchListener(this);
        // Sets a long click listener for the ImageView using an anonymous listener object that
        // implements the OnLongClickListener interface
        launcherIcon.setOnLongClickListener(new View.OnLongClickListener() {

            // Defines the one method for the interface, which is called when the View is long-clicked
            public boolean onLongClick(View v) {

                // Create a new ClipData.
                // This is done in two steps to provide clarity. The convenience method
                // ClipData.newPlainText() can create a plain text ClipData in one step.

                // Create a new ClipData.Item from the ImageView object's tag
                ClipData.Item item = new ClipData.Item((String) v.getTag());

                // Create a new ClipData using the tag as a label, the plain text MIME type, and
                // the already-created item. This will create a new ClipDescription object within the
                // ClipData, and set its MIME type entry to "text/plain"
                String[] mimetypes = {"image/*"};
                ClipData dragData = new ClipData((String) v.getTag(), mimetypes, item);

                // Starts the drag

                v.startDrag(dragData,  // the data to be dragged
                        null,  // the drag shadow builder
                        null,      // no need to use local data
                        0          // flags (not currently used, set to 0)
                );
                return true;
            }
        });
        launcherIcon.setOnDragListener(mDragListen);
    };

    public boolean onTouch(View view, MotionEvent event){

        final int x = (int) event.getRawX();
        final int y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                dx = view.getX()- x;
                dy =  view.getY()- y;
                break;
            case MotionEvent.ACTION_MOVE:
                view.animate().x(x+dx).y(y+dy).setDuration(0).start();
                break;
            default: return false;


        }

        return true;



    }


}