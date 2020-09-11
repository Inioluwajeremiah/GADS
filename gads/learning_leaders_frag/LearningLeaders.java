package com.example.gads.learning_leaders_frag;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gads.IQdata;
import com.example.gads.IQrecAdapter;
import com.example.gads.MainActivity;
import com.example.gads.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LearningLeaders extends Fragment {

    private static final String URL_DATA = "https://gadsapi.herokuapp.com/api/hours";

    private RecyclerView ll_recView;
    RecyclerView.Adapter mAdapter;
    private List<LLData> llData;
    private  View view;

    public LearningLeaders() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);

        ll_recView = view.findViewById(R.id.ll_rec);
        ll_recView.setHasFixedSize(true);
        ll_recView.setLayoutManager(new LinearLayoutManager(getActivity()));

        llData = new ArrayList<>();


        loadData();
        /*for (int i=0; i<=10; i++) {
            LLData myData = new LLData("heading" , "2", "Lorem Ipsum tex",
                    "display url");
            llData.add(myData);
        }
        mAdapter = new LLAdapter(getActivity(), llData);
        ll_recView.setAdapter(mAdapter); */
        return view;
    }

    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {

                    JSONArray array = new JSONArray(response);
                    for (int i=0; i<array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        LLData llData1  = new LLData(jsonObject.getString("name"), jsonObject.getString("hours"),
                                jsonObject.getString("country"),jsonObject.getString("badgeUrl"));
                        llData.add(llData1);
                    }

                    mAdapter = new LLAdapter(getActivity(), llData);
                    ll_recView.setAdapter(mAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}