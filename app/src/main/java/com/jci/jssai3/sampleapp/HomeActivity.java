package com.jci.jssai3.sampleapp;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.support.v7.widget.SwitchCompat;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.edmodo.rangebar.RangeBar;
import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.angads25.toggle.model.ToggleableView;
import com.github.angads25.toggle.widget.LabeledSwitch;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = HomeActivity.class.getName();
    Button mood;
    TextView switchtext;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String urlget = "http://192.168.1.13/light?redled";
    private String urlpost = "http://192.168.1.13/light";
    LabeledSwitch labeledSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mood = findViewById(R.id.mood);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        TextView tv = new TextView(getApplicationContext());
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(18);
        tv.setText("P E N D A N T"); // ActionBar title text
        Typeface typeface = ResourcesCompat.getFont(this, R.font.biryani_light);
        tv.setTypeface(typeface);
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ab.setCustomView(tv);
        LabeledSwitch labeledSwitch = findViewById(R.id.switchLight);
        labeledSwitch.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean isOn) {
                if (isOn == true){
                    postData("on");
                }
                if (isOn == false){
                    postData("off");
                }
            }
            });
        RangeBar rangebar = (RangeBar) findViewById(R.id.rangebar);
        RangeBar rangebar1 = (RangeBar) findViewById(R.id.rangebar1);
        RangeBar rangebar2 = (RangeBar) findViewById(R.id.rangebar2);
        RangeBar rangebar3 = (RangeBar) findViewById(R.id.rangebar3);
        rangebar.setTickHeight(10);
        rangebar.setBarWeight(2);
        rangebar.setBarColor(Color.WHITE);
        rangebar.setConnectingLineWeight(2);
        rangebar.setConnectingLineColor(Color.WHITE);
        rangebar.setThumbColorNormal(Color.rgb(236, 240, 241));
        rangebar.setThumbColorPressed(Color.rgb(236, 240, 241));
        rangebar.setThumbRadius(5);

        rangebar1.setTickHeight(10);
        rangebar1.setBarWeight(2);
        rangebar1.setBarColor(Color.WHITE);
        rangebar1.setConnectingLineWeight(2);
        rangebar1.setConnectingLineColor(Color.WHITE);
        rangebar1.setThumbColorNormal(Color.rgb(236, 240, 241));
        rangebar1.setThumbColorPressed(Color.rgb(236, 240, 241));
        rangebar1.setThumbRadius(5);

        rangebar2.setTickHeight(10);
        rangebar2.setBarWeight(2);
        rangebar2.setBarColor(Color.WHITE);
        rangebar2.setConnectingLineWeight(2);
        rangebar2.setConnectingLineColor(Color.WHITE);
        rangebar2.setThumbColorNormal(Color.rgb(236, 240, 241));
        rangebar2.setThumbColorPressed(Color.rgb(236, 240, 241));
        rangebar2.setThumbRadius(5);

        rangebar3.setTickHeight(10);
        rangebar3.setBarWeight(2);
        rangebar3.setBarColor(Color.WHITE);
        rangebar3.setConnectingLineWeight(2);
        rangebar3.setConnectingLineColor(Color.WHITE);
        rangebar3.setThumbColorNormal(Color.rgb(236, 240, 241));
        rangebar3.setThumbColorPressed(Color.rgb(236, 240, 241));
        rangebar3.setThumbRadius(5);
        mood.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Mood set successfuly",Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void getData() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, urlget, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG,"Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }
    public void postData(final String state) {

        mRequestQueue = Volley.newRequestQueue(this);
        //this is the url where you want to send the request
        //TODO: replace with your own url to send request, as I am using my own localhost for this tutorial

        //  Log.d(TAG,"device id is:" + deviceID.getText());
        //final String deviceid = "5555";
        // Request a string response from the provided URL.
        mStringRequest = new StringRequest(Request.Method.POST, urlpost,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Toast.makeText(getApplicationContext(),"Switch state: " + state.toUpperCase(), Toast.LENGTH_LONG).show();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("errorpost", error.getMessage());

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("redled", state);

                return params;
            }
        };
        // Add the request to the RequestQueue.
        mRequestQueue.add(mStringRequest);


    }
}
