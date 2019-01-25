package com.example.week2tutorial;


import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    TextView textView;
    EditText editText;
    Button btn1;
    ConstraintLayout backgrundLayout;
    float[] hsv;
    GestureDetector gestureScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView1);
        editText = (EditText) findViewById(R.id.editTxt1);
        btn1 = findViewById(R.id.btn1);
        btn1.setOnTouchListener(this);
        backgrundLayout = (ConstraintLayout) findViewById(R.id.backgroundLayoutId);
        backgrundLayout.setOnTouchListener(this);

        gestureScanner = new GestureDetector(getApplicationContext(),this);

        hsv = new float[3];
        hsv[0] = 0.0f;
        hsv[1] = 0.0f;
        hsv[2] = 1.0f;
    }

    public void btn1_Click(View view) {
        textView.setText(editText.getText());
    }

    public void editText_Click(View view) {
        editText.setText("");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureScanner.onTouchEvent(event);

        switch (v.getId()) {
            case R.id.backgroundLayoutId:
                setBackGroundColour_Touch(v, event);
                break;
            case R.id.btn1:
                setButtonColour_Touch(v, event);
                break;
        }
        return true;

    }

    private void setButtonColour_Touch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                btn1.setBackgroundColor(getResources().getColor(R.color.red));
                break;

            case (MotionEvent.ACTION_UP):
                btn1.setBackgroundColor(getResources().getColor(R.color.green));
                break;
        }
    }

    private void setBackGroundColour_Touch(View v, MotionEvent event){
            float eventX = event.getX();
            float eventY = event.getY();
            float height = backgrundLayout.getHeight();
            float width = backgrundLayout.getWidth();

            hsv[0] = eventY / height * 360;
            hsv[1] = eventX / width + 0.1f;
            backgrundLayout.setBackgroundColor(Color.HSVToColor(hsv));
    }

    @Override
    public boolean onTouchEvent (MotionEvent event){

        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                Log.d("Touch", "Action was DOWN");
                break;
            case (MotionEvent.ACTION_MOVE):
                Log.d("Touch", "Action was MOVE");
                break;
            case (MotionEvent.ACTION_UP):
                Log.d("Touch", "Action was DOWN");
                break;

        }
        return  super.onTouchEvent(event);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("on key", "onKeyDown function called");
        switch(keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (hsv[2] != 1f){
                        hsv[2] += 0.1f;
                }
                Toast.makeText(getApplicationContext(),
                        "Volume and Brightness increased!",
                        Toast.LENGTH_LONG).show();

                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (hsv[2] != 0.1){
                        hsv[2] += -0.1f;
                }
                Toast.makeText(getApplicationContext(),
                        "Volume and Brightness decreased!",
                        Toast.LENGTH_LONG).show();
                break;

        }
        backgrundLayout.setBackgroundColor(Color.HSVToColor(hsv));
        //true ignored default action
        //false also changes volume
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("Touch", "OnDown hit");

        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d("Touch","onShowPress hit");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d("Touch","onSingleTap hit");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d("Touch","onScroll hit");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d("Touch", "onLongPress hit");

        hsv[0] = 0f;
        hsv[1] = 0f;
        hsv[2] = 0f;
        backgrundLayout.setBackgroundColor(Color.HSVToColor(hsv));
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("Touch", "onFling hit");

        hsv[0] = (float) (Math.random() * 360); // random hue
        hsv[1] = (float) Math.random(); // random saturation
        hsv[2] = (float) Math.random(); // random value/lightness
        backgrundLayout.setBackgroundColor(getResources().getColor(R.color.white));
        return true;
    }
}

