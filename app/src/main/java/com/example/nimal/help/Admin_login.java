package com.example.nimal.help;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Admin_login extends AppCompatActivity {

    EditText ed1,ed2;

    public ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null)
        {
            firebaseAuth.signOut();
        }

        progressDialog = new ProgressDialog(this);
    }



    public void signup(View view)
    {
        ed1 = (EditText) findViewById(R.id.Email);
        ed2 = (EditText) findViewById(R.id.Password);
        final String email = ed1.getText().toString();
        final String password = ed2.getText().toString();

        progressDialog.setMessage("Authenticating User!!!");
        progressDialog.setCancelable(false);
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(email,password).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            progressDialog.dismiss();
                            nexting(email);
                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(Admin_login.this, "Credentials wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

    public void nexting(String email)
    {
        sharedpreferences = getSharedPreferences("User_Name", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("Email",email);
        editor.commit();

        Intent i = new Intent(this,Login1.class);
        startActivity(i);
    }

}
