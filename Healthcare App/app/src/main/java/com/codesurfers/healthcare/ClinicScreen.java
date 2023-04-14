package com.codesurfers.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.FutureTask;

public class ClinicScreen extends AppCompatActivity {

    ArrayList<ClinicModel> arrayList = new ArrayList<>();
    Adapter.RecyclerViewClick listener;
    RecyclerView rv_1;
    String URL = "http://192.168.8.119:9092/dut_healthcare_clinic/api/clinic";
    Button fetchClinicBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_screen);
        setTitle("Clinics");
        rv_1 = findViewById(R.id.rv_1);
        fetchClinicBtn = findViewById(R.id.fetchClinic);
        onClick();

        fetchClinicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchClinics();
            }
        });




        //arrayList.add(new ClinicModel("1","Steve biko", "ssss", "123", "1233"));
        //arrayList.add(new ClinicModel("2","Ritson", "ssss", "123", "1233"));
       // arrayList.add(new ClinicModel("3","ML Sultan", "ssss", "123", "1233"));
        System.out.println("My Array");
        System.out.println(arrayList.toString());





    }

    private void onClick() {
        listener = new Adapter.RecyclerViewClick() {
            @Override
            public void click(View v, int position) {
                Log.v(arrayList.get(position).clinicName, "cc");
                System.out.println(arrayList.get(position).clinicName);
                Toast.makeText(ClinicScreen.this, "clinic name"+arrayList.get(position).getClinicName(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void fetchClinics(){
        System.out.println("FETCH STARTING");
        Adapter adapter = new Adapter(this, arrayList, listener);
        rv_1.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false));
        rv_1.setAdapter(adapter);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject data = new JSONObject();
                System.out.println(response.toString());

                try {
                    System.out.println("START LOADING LIST");
                    for (int i = 0; i < response.length(); i++) {
                        data = response.getJSONObject(i);
                        arrayList.add(new ClinicModel(data.getString("clinicId"),data.getString("clinicName"), data.getString("clinicDescription"), "123", "1233"));
                    }
                    System.out.println("FINISH LOADING LIS");
                    System.out.println("INSIDE THE METHOD");
                    System.out.println(arrayList.size());

                    Toast.makeText(ClinicScreen.this, ""+data.getString("clinicName"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("MY ERROR");
                System.out.println(error.toString());
                Toast.makeText(ClinicScreen.this, "Error while fetching clinics "+ error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}