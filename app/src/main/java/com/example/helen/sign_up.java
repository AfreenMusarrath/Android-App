package com.example.helen;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
public class sign_up extends AppCompatActivity {
    EditText name, txtemail, phone, txtpassword,txtpassword1;
    Button register;
    TextView login;
    boolean isNameValid, isEmailValid, isPhoneValid, isPasswordValid,isPasswordValid1;
    TextInputLayout nameError, emailError, phoneError, passError;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText) findViewById(R.id.name);
        txtemail = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        txtpassword = (EditText) findViewById(R.id.password);
        txtpassword1 = (EditText) findViewById(R.id.password1);
        login = (TextView) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        nameError = (TextInputLayout) findViewById(R.id.nameError);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        phoneError = (TextInputLayout) findViewById(R.id.phoneError);
        passError = (TextInputLayout) findViewById(R.id.passError);

        firebaseAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email=txtemail.getText().toString().trim();
                String password=txtpassword.getText().toString().trim();
                String conpassword=txtpassword1.getText().toString().trim();
                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(sign_up.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(sign_up.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(conpassword)) {
                    Toast.makeText(sign_up.this, "Enter confirm password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(sign_up.this, "enter minimum 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.equals(conpassword))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(sign_up.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(sign_up.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                    } else {

                                        Toast.makeText(sign_up.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }
                                    }

                            });
            }
        }
    });
}
}


