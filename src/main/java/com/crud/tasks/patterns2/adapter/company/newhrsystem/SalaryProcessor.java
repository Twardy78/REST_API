package com.crud.tasks.patterns2.adapter.company.newhrsystem;

import java.util.*;
import java.math.BigDecimal;

public interface SalaryProcessor {
    BigDecimal calculateSalaries(List<Employee> employees);
}
