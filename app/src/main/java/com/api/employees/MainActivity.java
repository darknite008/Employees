package com.api.employees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnall,btnSearch,btnRegister,btnupd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnall=findViewById(R.id.all);
        btnall.setOnClickListener(this);
        btnRegister=findViewById(R.id.Register);
        btnRegister.setOnClickListener(this);
        btnSearch=findViewById(R.id.Search);
        btnSearch.setOnClickListener(this);
        btnupd=findViewById(R.id.upd);
        btnupd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case (R.id.Register) :
                Intent intent = new Intent(this, RegisterActivity.class);
                this.startActivity(intent);
                break;

            case (R.id.Search) :
                Intent intent1 = new Intent(this, SearchActivity.class);
                this.startActivity(intent1);
                break;
            case (R.id.all) :
                Intent intent2 = new Intent(this, ShowUserData.class);
                this.startActivity(intent2);
                break;


        }
    }
}
