package com.codesurfers.healthcare;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codesurfers.healthcare.constants.IClinicAPI;
import com.codesurfers.healthcare.model.Clinic;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClinicScreen extends AppCompatActivity {

    private IClinicAPI ClinicAPI;

    ArrayList<Clinic> arrayList = new ArrayList<>();
    RecyclerView rv_1;
    String URL = "http://192.168.8.119:9092/dut_healthcare_clinic/api/clinic";
    Button fetchClinicBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_screen);
        setTitle("Clinics");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.8.119:9092/dut_healthcare_clinic/api/")  // Set the baseUrl to your API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ClinicAPI = retrofit.create(IClinicAPI.class);
        rv_1 = findViewById(R.id.rv_1);
        fetchClinicBtn = findViewById(R.id.fetchClinic);
        fetchClinicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getClinics();

                Toast.makeText(ClinicScreen.this, "", Toast.LENGTH_SHORT).show();
            }

        });




    }
    private void getClinics() {
        Call<List<Clinic>> call = ClinicAPI.getClinics();

        call.enqueue(new Callback<List<Clinic>>() {
            @Override
            public void onResponse(Call<List<Clinic>> call, Response<List<Clinic>> response) {



                if (response.code() == 200) {
                    //arrayList.clear();
                    List<Clinic> tempList = response.body();
                    for (int i = 0; i < tempList.size(); i++) {
                        arrayList.add(new Clinic(tempList.get(1).getClinicName(), tempList.get(i).getClinicDescription(), tempList.get(i).getClinicCampus(), tempList.get(i).getLatitude(), tempList.get(i).getLongitude()));
                    }

                    System.out.println("SUCCESSFULLY");
                    System.out.println(arrayList.size());

                }else {
                    System.out.println("Error Occurred");
                }

            }
            @Override
            public void onFailure(Call<List<Clinic>> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }

    /**public void fetchClinics(){
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
    }*/
}