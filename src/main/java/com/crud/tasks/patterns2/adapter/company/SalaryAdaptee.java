package com.crud.tasks.patterns2.adapter.company;

import com.crud.tasks.patterns2.adapter.company.newhrsystem.CompanySalaryProcessor;
import com.crud.tasks.patterns2.adapter.company.newhrsystem.Employee;
import com.crud.tasks.patterns2.adapter.company.newhrsystem.SalaryProcessor;

import java.math.BigDecimal;
import java.util.*;

public class SalaryAdaptee implements SalaryProcessor {
    @Override
    public BigDecimal calculateSalaries(List<Employee> employees)
    {
        CompanySalaryProcessor theProcessor = new
                CompanySalaryProcessor();
        return theProcessor.calculateSalaries(employees);
    }
}
