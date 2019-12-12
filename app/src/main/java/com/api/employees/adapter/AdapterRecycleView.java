package com.api.employees.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.api.employees.R;
import com.api.employees.model.Employee;

import java.util.List;

import retrofit2.Callback;

public class AdapterRecycleView extends RecyclerView.Adapter<AdapterRecycleView.ContactsViewHolder> {
Context context;
List<Employee> userData;

    public AdapterRecycleView(Context context, List<Employee> userData) {
        this.context = context;
        this.userData = userData;
    }

    @NonNull
    @Override
    public AdapterRecycleView.ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_view, parent, false);
        return new ContactsViewHolder(view);
        //activity_adapter_view
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycleView.ContactsViewHolder holder, final int position) {
    final Employee ud= userData.get(position);
    holder.id.append(String.valueOf(ud.getId()));
    holder.name.append(ud.getEmployee_name());
    holder.salary.append(String.valueOf(ud.getEmployee_salary()));
    holder.age.append(String.valueOf(ud.getEmployee_age()));
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userData.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {
        TextView id,name,salary,age;
        ImageButton imageButton;
        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.userid);
            name=itemView.findViewById(R.id.username);
            salary=itemView.findViewById(R.id.usersalary);
            age=itemView.findViewById(R.id.userage);
            imageButton=itemView.findViewById(R.id.imageButton);


        }
    }
}
