package com.crud.tasks.patterns2.adapter.company;

import com.crud.tasks.patterns2.adapter.company.newhrsystem.Employee;
import com.crud.tasks.patterns2.adapter.company.oldhrsystem.SalaryCalculator;

import java.math.BigDecimal;
import java.util.*;

public class SalaryAdapter extends SalaryAdaptee implements SalaryCalculator {

    @Override
    public double totalSalary(String[][] workers, double[] salaries) {
        List<Employee> employeeList = new ArrayList<>();
        for (int n = 0; n < salaries.length; n++) {
            employeeList.add(new Employee(
                    workers[n][0],
                    workers[n][1],
                    workers[n][2],
                    new BigDecimal(salaries[n])));
        }
        return calculateSalaries(employeeList).doubleValue();
    }
}
