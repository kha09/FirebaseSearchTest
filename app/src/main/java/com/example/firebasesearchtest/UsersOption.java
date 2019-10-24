package com.example.firebasesearchtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import static com.example.firebasesearchtest.AdapterClass.Tag2;
import static com.example.firebasesearchtest.AdapterClass.Tag3;

public class UsersOption extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    String one;
    String one2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_option);
        textView = findViewById(R.id.tvuserOption);
        textView2 = findViewById(R.id.tvuserOption2);

        if(Tag2 != null){
            one = Tag2;
            textView.setText(one);
        }
        if(Tag3 != null){
            one2 = Tag3;
            textView2.setText(one2);
        }
    }
}
