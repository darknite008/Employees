package com.api.employees.api;

import com.api.employees.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmployeeAPI {
    //retrieving all employee
    //retrofit2
    @GET("employees")
    Call<List<Employee>> getAllEmployees();

    //get employee on basis of empid
    @GET("employee/{empID}")
    Call<Employee> getEmployeeByID(@Path("empID")int empID);
}
