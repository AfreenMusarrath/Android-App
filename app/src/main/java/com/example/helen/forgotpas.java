package com.example.helen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpas extends AppCompatActivity {
  private EditText emailtxt ;
 private Button res;
 private FirebaseAuth fire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpas);
        emailtxt=findViewById(R.id.emailtext1);
        res=(Button)findViewById(R.id.Reset);
        fire=FirebaseAuth.getInstance();
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail=emailtxt.getText().toString().trim();
                if(emailtxt.equals("")){
                    Toast.makeText(forgotpas.this, "Please enter registered email", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    fire.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(forgotpas.this, "Password Reset Email Sent", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(forgotpas.this,login.class));
                            }
                            else {
                                Toast.makeText(forgotpas.this, "Error in sending reset link", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }
}