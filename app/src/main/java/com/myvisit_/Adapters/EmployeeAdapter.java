package com.myvisit_.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myvisit_.CustomItemClickListener;
import com.myvisit_.Models.Employee;
import com.myvisit_.R;

import java.util.List;

public class EmployeeAdapter  extends RecyclerView.Adapter <EmployeeAdapter.ViewHolder>
{

    Context context;
    private List <Employee> employeeList;
    CustomItemClickListener listener;

    public EmployeeAdapter(Context context, List<Employee> employeeList , CustomItemClickListener listener )
    {
        this.context = context;
        this.employeeList = employeeList;
        this.listener = listener;
    }

    @Override
    public ViewHolder  onCreateViewHolder(ViewGroup viewGroup, int i)
    {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.employee_row, viewGroup, false);
    return new EmployeeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeAdapter.ViewHolder viewHolder, final int i)
    {
        viewHolder.textView.setText(employeeList.get(i).getFname());
        viewHolder.bind(employeeList.get(i) , listener);
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder
    {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.empname);
        }

        public void bind(final Employee Employee, final CustomItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.OnItemClick(Employee);
                }
            });
        }



    }



}
