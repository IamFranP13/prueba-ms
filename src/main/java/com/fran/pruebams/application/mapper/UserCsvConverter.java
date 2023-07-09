package com.fran.pruebams.application.mapper;

import com.fran.pruebams.domain.model.User;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.PrintWriter;
import java.util.List;

public class UserCsvConverter {
    public void convertUsersToCsv(List<User> users, PrintWriter writer) {
        try {
            StatefulBeanToCsv<User> beanToCsv = new StatefulBeanToCsvBuilder<User>(writer)
                    .withSeparator(',')
                    .build();
            beanToCsv.write(users);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
    }
}
