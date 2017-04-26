package com.example.nimal.help;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AddUser extends AppCompatActivity implements View.OnClickListener {

    private EditText e_mail, pass;
    private Button b;

    private ProgressDialog pd;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        firebaseAuth = FirebaseAuth.getInstance();

        pd = new ProgressDialog(this);

        b = (Button) findViewById(R.id.button);

        e_mail = (EditText)findViewById(R.id.editText2);
        pass = (EditText)findViewById(R.id.editText3) ;

        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == b) {
            final String em = e_mail.getText().toString();
            final String pa = pass.getText().toString();

            if (TextUtils.isEmpty(em)) {
                Toast.makeText(this, "Please Enter Your Email Id", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(pa)) {
                Toast.makeText(this, "Please Enter A Password", Toast.LENGTH_SHORT).show();
                return;
            }

            pd.setMessage("Registering User ...");
            pd.setCancelable(false);
            pd.show();

            firebaseAuth.createUserWithEmailAndPassword(em, pa).
                    addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                pd.dismiss();

                                Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                pd.dismiss();

                                Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
    public void onBackPressed()
    {
        Intent i = new Intent(this,Login1.class);
        startActivity(i);
    }
}
