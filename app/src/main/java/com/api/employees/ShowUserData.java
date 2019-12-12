package com.api.employees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.api.employees.adapter.AdapterRecycleView;
import com.api.employees.api.EmployeeAPI;
import com.api.employees.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowUserData extends AppCompatActivity {

    RecyclerView recyclerView;

    private static final String base_url="http://dummy.restapiexample.com/api/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_data);

 
        recyclerView=findViewById(R.id.recycle);


        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //interface instance
        EmployeeAPI employeeAPI=retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> listCall=employeeAPI.getAllEmployees();
        //asynchronous
        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ShowUserData.this, "Error"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Employee> lstEmployee=response.body();
                AdapterRecycleView adapterRecycleView=new AdapterRecycleView(ShowUserData.this,lstEmployee);
               recyclerView.setAdapter(adapterRecycleView);
               recyclerView.setLayoutManager(new LinearLayoutManager(ShowUserData.this));

//                for (Employee employee:lstEmployee){
//                    String emp="";
//                    emp+="Emp Name :"+employee.getEmployee_name()+"\n";
//                    emp+="Emp Salary :"+employee.getEmployee_salary()+"\n";
//
//                    textView.append(emp);


//                    lstEmployee.add(new Employee(employee.getId(),employee.getEmployee_name(),
//                            employee.getEmployee_salary(),employee.getEmployee_age()));
//
//                    AdapterRecycleView adapterRecycleView=new AdapterRecycleView(this,lstEmployee);
//                    recyclerView.setAdapter(adapterRecycleView);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                }


            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(ShowUserData.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
