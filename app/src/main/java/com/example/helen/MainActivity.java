package com.example.helen;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.speech.RecognizerIntent;
import android.widget.TextView;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Locale;
public class MainActivity extends AppCompatActivity {
    Button btn_attend, btn_time, btn_syll, btn_pro, btn_perform, btn_semes;
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    String email;
    String uid;
    public static final String EMAIL1="com.example.helen";
    public static final String UID2="com.example.helen";
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        email=intent.getStringExtra(login.EMAIL);
        firebaseAuth=FirebaseAuth.getInstance();
        currentUser=FirebaseAuth.getInstance().getCurrentUser();
        uid=currentUser.getUid();
        tv = (TextView) findViewById(R.id.Textview1);
        tv.setSelected(true);
        btn_pro=(Button) findViewById(R.id.buttonprofile);
        btn_attend = (Button) findViewById(R.id.buttonattendance);
        btn_time = (Button) findViewById(R.id.buttontimetable);
        btn_syll = (Button) findViewById(R.id.buttonsyllabus);
        btn_perform = (Button) findViewById(R.id.buttonperformance);
        btn_semes = (Button) findViewById(R.id.buttonresult);
        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        btn_attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
        btn_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4();
            }
        });
        btn_attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity5();
            }
        });
        btn_semes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity6();
            }
        });
        btn_perform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity7();
            }
        });
        btn_syll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity8();
            }
        });



    }
    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mVoiceInputTv.setText(result.get(0));
                }
                if (mVoiceInputTv.getText().toString().equals("show attendance")) {
                    Intent intent = new Intent(getApplicationContext(), Attendance.class);
                    intent.putExtra(UID2,uid);
                    startActivity(intent);
                }
                if (mVoiceInputTv.getText().toString().equals("show result")) {
                    Intent intent = new Intent(getApplicationContext(), Result.class);
                    intent.putExtra(UID2,uid);
                    startActivity(intent);
                }
                if (mVoiceInputTv.getText().toString().equals("show time table")) {
                    Intent intent = new Intent(getApplicationContext(), Timetable.class);
                    startActivity(intent);
                }
                if (mVoiceInputTv.getText().toString().equals("show syllabus")) {
                    Intent intent = new Intent(getApplicationContext(), syllabus.class);
                    intent.putExtra(UID2,uid);
                    startActivity(intent);
                }
                if (mVoiceInputTv.getText().toString().equals("show performance")) {
                    Intent intent = new Intent(getApplicationContext(), Perfor.class);
                    intent.putExtra(UID2,uid);
                    startActivity(intent);
                }
                if (mVoiceInputTv.getText().toString().equals("show profile")) {
                    Intent intent = new Intent(getApplicationContext(), profiled.class);
                    intent.putExtra(EMAIL1,email);
                    startActivity(intent);
                }
                break;
            }
        }
    }
    private void openActivity2() {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
    private void openActivity3() {
        Intent intent = new Intent(this, Timetable.class);
        startActivity(intent);
    }
    private void openActivity4() {
        Intent intent = new Intent(this, profiled.class);
        intent.putExtra(EMAIL1,email);
        startActivity(intent);
    }
    private void openActivity5() {
        Intent intent = new Intent(this, Attendance.class);
        intent.putExtra(UID2,uid);
        startActivity(intent);
    }
    private void openActivity6() {
        Intent intent = new Intent(this, Result.class);
        intent.putExtra(UID2,uid);
        startActivity(intent);
    }
    private void openActivity7() {
        Intent intent = new Intent(this, Perfor.class);
        intent.putExtra(UID2,uid);
        startActivity(intent);
    }
        private void openActivity8() {
            Intent intent = new Intent(this, syllabus.class);
            intent.putExtra(UID2,uid);
            startActivity(intent);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:{
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(MainActivity.this,login.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }
}