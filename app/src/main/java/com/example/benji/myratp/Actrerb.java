package com.example.benji.myratp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.*;
import org.json.*;

public class Actrerb extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actrerb);

        Button next = (Button) findViewById(R.id.buttonrerbback);
        next.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        final TextView rerbtxt = (TextView) findViewById(R.id.rerbtxt);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/b/Robinson/A";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() { @Override
                public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                    try {


                        /*JSONObject jsonObj = new JSONObject(response);
                        JSONObject res = jsonObj.getJSONObject("result");
                        JSONArray sch = res.getJSONArray("schedules");
                        rerbtxt.setText("Prochain passage : "+ sch.getString(1));*/

                        JSONObject jsonObj = new JSONObject(response);
                        JSONObject res = jsonObj.getJSONObject("result");
                        JSONArray sch = res.getJSONArray("schedules");
                        String info = sch.getString(1);

                        String[] parts = info.split(",");
                        int i = 0;
                        while (parts[i] != null) {
                            if(parts[i].toLowerCase().contains("message".toLowerCase())) {
                                rerbtxt.setText(parts[i].substring(11, (parts[i].length()-1)));
                                break;
                            }
                            i++;
                        }

                        /*int i = 0;
                        JSONObject jsonObj = new JSONObject(response);
                        JSONArray heures = jsonObj.getJSONArray("result");
                        JSONArray hor = jsonObj.getJSONArray("schedules");
                        JSONObject c = heures.getJSONObject(i);
                        String heure = c.getString("schedules");
                        rerbtxt.setText("Prochain passage : "+ heure);*/

                        /*JSONObject sys  = reader.getJSONObject("sys");
                        erbtxt = sys.getString("result");*/

                        /*JSONArray arr = new JSONArray(response);
                        JSONObject jObj = arr.getJSONObject(0);
                        String heure = jObj.getString("result");
                        rerbtxt.setText("Prochain passage : "+ heure);*/
                    }
                    catch(JSONException e) {
                        System.out.print("No work");
                    }
                    }
                }, new Response.ErrorListener() { @Override
        public void onErrorResponse(VolleyError error) {
                rerbtxt.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }
}