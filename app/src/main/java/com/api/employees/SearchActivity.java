package com.api.employees;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.api.employees.api.EmployeeAPI;
import com.api.employees.model.Employee;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
private EditText editText;
private TextView textView;
private Button button;
private final static String BASE_URL="http://dummy.restapiexample.com/api/v1/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editText=findViewById(R.id.etEmpid);
        textView=findViewById(R.id.tv);
        button=findViewById(R.id.btnS);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

    }

    private void loadData() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmployeeAPI employeeAPI=retrofit.create(EmployeeAPI.class);
        Call<Employee> listcall=employeeAPI.getEmployeeByID(Integer.parseInt(editText.getText().toString()));
        
        listcall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(SearchActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
           String content="";
           content+="ID :"+response.body().getId()+"\n";
           content+="Name :"+response.body().getEmployee_name()+"\n";
           content+="Age :"+response.body().getEmployee_age()+"\n";
           content+="Salary :"+response.body().getEmployee_salary()+"\n";
           textView.setText(content);
           
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
