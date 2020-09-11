package com.example.gads;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class IQleaders extends Fragment {

    RecyclerView iq_recyclerView;
    LinearLayoutManager iq_linearLayoutManager;
    RecyclerView.Adapter  iQrecAdapter;
    List<IQdata> iQdata;
    View view;

    private static final String URL_DATA = "https://gadsapi.herokuapp.com/api/skilliq";


    public IQleaders() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_iqleaders, container, false);

        iq_recyclerView = view.findViewById(R.id.iq_rec);
       iq_recyclerView.setHasFixedSize(true);
        iq_linearLayoutManager = new LinearLayoutManager(getContext());
        iq_recyclerView.setLayoutManager(iq_linearLayoutManager);

        iQdata = new ArrayList<>();

        loadDataFromServer();

        return view;
    }

    private void loadDataFromServer() {
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
                        IQdata myDataList = new IQdata(jsonObject.getString("name"), jsonObject.getString("score"),
                                jsonObject.getString("country"),jsonObject.getString("badgeUrl"));
                       iQdata.add(myDataList);
                    }

                    iQrecAdapter = new IQrecAdapter(getContext(),iQdata);
                    iq_recyclerView.setAdapter(iQrecAdapter);

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
