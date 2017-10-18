package com.example.sara.lab2;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.GestureDetectorCompat;
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
import android.app.AlertDialog;
import android.content.DialogInterface;
import 	android.view.GestureDetector;
import android.provider.Settings;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener ,
        GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener{
    float dx;
    float dy;
    GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView myView = (WebView) findViewById(R.id.myWebView);
        myView.loadUrl("http://met.guc.edu.eg");
        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);  //this will set the manual mode (set the automatic mode off)

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final EditText editText = (EditText) findViewById(R.id.editText5);
                String text = editText.getText().toString();

                TextView myTextView = (TextView) findViewById(R.id.textView4);
                myTextView.append('\n' + text);


            }


        });
        ImageView button2 = (ImageView) findViewById(R.id.imageView2);
        button2.setOnTouchListener(this);
        mDetector=new GestureDetectorCompat(this,this);

        mDetector.setOnDoubleTapListener(this);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }
       /*sara betfatty

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
*/
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
            case MotionEvent.ACTION_UP:
if(x>490&&x<885&&y>1000&&y<1565){
               /* AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("x is :"+y);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();*/




    findViewById(R.id.inc1).setVisibility(View.GONE);
    findViewById(R.id.inc2).setVisibility(View.VISIBLE);



}
                break;
            default: return false;


        }

        return true;



    }
    @Override
    public boolean onDoubleTap(MotionEvent event) {
        findViewById(R.id.inc2).setVisibility(View.GONE);
        findViewById(R.id.inc1).setVisibility(View.VISIBLE);

        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {

        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
       // Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
        return true;
    }
    @Override
    public void onShowPress(MotionEvent event) {
    //    Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }
    @Override
    public void onLongPress(MotionEvent event) {
     //   Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
    }
    @Override
    public boolean onSingleTapUp(MotionEvent event) {
       // Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                            float Y) {
        if(findViewById(R.id.inc1).getVisibility()==View.GONE){
        int brightness=120;
        try {
            brightness  = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);  //returns integer value 0-255
        } catch (Exception e) {}
        if(Y>0){
            brightness-=5;
            if(brightness<0)
                brightness=5;

        }else{
            brightness+=5;
            if(brightness>255){
                brightness=255;
            }
        }


        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, brightness);  //brightness is an integer variable (0-255), but dont use 0

        try {
            int br = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);  //this will get the information you have just set...

            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.screenBrightness = (float) br / 255; //...and put it here
            getWindow().setAttributes(lp);
        } catch (Exception e) {}}
        return true;
    }


    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
       // Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
        return true;
    }
    @Override
    public boolean onDown(MotionEvent event) {
              return true;
    }
}