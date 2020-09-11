package com.example.gads;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gads.my_post_request.MyApiClient;
import com.example.gads.my_post_request.UserRequest;
import com.example.gads.my_post_request.UserResponse;
import com.example.gads.post_requestii.MConstants;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class SubmitForm extends AppCompatActivity {

    ImageView back_img, image_icon;
    EditText first_name, last_name, email, git_link;
    Button submit_form_btn;
    Toolbar form_toolbar;
    ImageView error_toast_imgview, success_toast_imgview;
    TextView error_toast_textview, success_toast_textview;
    Button mbutton;
    ImageView cancelImage;
    RequestQueue requestQueue;

    int sec = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        back_img = findViewById(R.id.back_btn);
        image_icon = findViewById(R.id.image_icon);
        first_name = findViewById(R.id.firstname);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        git_link = findViewById(R.id.github_link);
        submit_form_btn = findViewById(R.id.submit_form_btn);
        error_toast_imgview = findViewById(R.id.error_imgview);
        error_toast_textview = findViewById(R.id.error_textview);

        requestQueue = Volley.newRequestQueue(SubmitForm.this);

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        submit_form_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (first_name.getText().toString().trim().isEmpty() || last_name.getText().toString().trim().isEmpty() ||
                        email.getText().toString().trim().isEmpty()  || git_link.getText().toString().trim().isEmpty()) {

                    Toast.makeText(SubmitForm.this, "Kindly ensure no field is empty", Toast.LENGTH_LONG).show();
                } else {

                    final Dialog dialog = new Dialog(SubmitForm.this);
               dialog.setContentView(R.layout.custom_dialogue);
                mbutton = dialog.findViewById(R.id.yes_dialogue_btn);
                dialog.show();
                cancelImage = dialog.findViewById(R.id.dialogue_cancel_img);

                mbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View fv) {
                        //saveUser(createUserRequest());
                        saveUserData(first_name.getText().toString().trim(), last_name.getText().toString().trim(), email.getText().toString().trim(),
                                git_link.getText().toString().trim());
                        dialog.dismiss();
                    }
                });

                }

            }
        });

    }

    public void saveUserData(final String mfirstname, final String mlastname, final String memail, final String mgit_iink) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MConstants.URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "Response: " + response);
                        if (response.length() > 0) {
                            View succcess_view = getLayoutInflater().inflate(R.layout.success_toast, (ViewGroup) findViewById(R.id.success_toast));
                            success_toast_imgview = findViewById(R.id.success_imgview);
                            success_toast_textview = findViewById(R.id.success_textview);
                            Toast toast = new Toast(getApplicationContext());
                            toast.setView(succcess_view);
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.show();

                            first_name.setText("");
                            last_name.setText("");
                            email.setText("");
                            git_link.setText("");
                        } else {
                            View error_view = getLayoutInflater().inflate(R.layout.error_toast, (ViewGroup) findViewById(R.id.error_toast));
                            error_toast_imgview = findViewById(R.id.error_imgview);
                            error_toast_textview = findViewById(R.id.error_textview);
                            Toast toast = new Toast(getApplicationContext());
                            toast.setView(error_view);
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                View error_view = getLayoutInflater().inflate(R.layout.error_toast, (ViewGroup) findViewById(R.id.error_toast));
                error_toast_imgview = findViewById(R.id.error_imgview);
                error_toast_textview = findViewById(R.id.error_textview);
                Toast toast = new Toast(getApplicationContext());
                toast.setView(error_view);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
            }
        }) {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<>();
                params.put(MConstants.firstName, mfirstname);
                params.put(MConstants.lastName, mlastname);
                params.put(MConstants.email, memail);
                params.put(MConstants.gitLink, mgit_iink);

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

}


        /*submit_form_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser(createUserRequest());

            /*if (first_name.getText().toString().trim().isEmpty() || last_name.getText().toString().trim().isEmpty() ||
                    email.getText().toString().trim().isEmpty()  || git_link.getText().equals("")) {
                    submit_form_btn.setClickable(false);
                } else {
                submit_form_btn.setClickable(true);  }
        });}*/
               /* AlertDialog.Builder adialog = new AlertDialog.Builder(SubmitForm.this);
                ViewGroup aviewgroup = findViewById(R.id.custom_dialogue_box);
                View dialogue_view = LayoutInflater.from(v.getContext()).inflate(R.layout.custom_dialogue, aviewgroup, false);
                adialog.setView(dialogue_view);
                adialog.create();
                adialog.show();*/
               /*final Dialog dialog = new Dialog(SubmitForm.this);
               dialog.setContentView(R.layout.custom_dialogue);
                mbutton = dialog.findViewById(R.id.yes_dialogue_btn);
                cancelImage = dialog.findViewById(R.id.dialogue_cancel_img);

                mbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View fv) {
                        //saveUser(createUserRequest());

                    }
                });
                cancelImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show(); */
 /*
            }
        });
                // saveUser(createUserRequest());


            }



    public UserRequest createUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstname(first_name.getText().toString().trim());
        userRequest.setLastname(last_name.getText().toString().trim());
        userRequest.setEmail(email.getText().toString().trim());
        userRequest.setLink(git_link.getText().toString().trim());

        return userRequest;
    }

    public void saveUser(UserRequest userRequest) {
        Call<UserResponse> userResponseCall = MyApiClient.getUserService().saveUser(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>(){

            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SubmitForm.this, "success", Toast.LENGTH_LONG).show();
                    /*View succcess_view = getLayoutInflater().inflate(R.layout.success_toast, (ViewGroup) findViewById(R.id.success_toast));
                    success_toast_imgview = findViewById(R.id.success_imgview);
                    success_toast_textview = findViewById(R.id.success_textview);
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(succcess_view);
                   toast.show(); */
            /*     } else {
                    Toast.makeText(SubmitForm.this, "error", Toast.LENGTH_LONG).show();
                    /*View error_view = getLayoutInflater().inflate(R.layout.error_toast, (ViewGroup) findViewById(R.id.error_toast));
                    error_toast_imgview = findViewById(R.id.error_imgview);
                    error_toast_textview = findViewById(R.id.error_textview);
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(error_view);
                    toast.show(); */
         /*       }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(SubmitForm.this, "error"+ t.getLocalizedMessage(), Toast.LENGTH_LONG
                ).show();

            }
        });


    }
} */
