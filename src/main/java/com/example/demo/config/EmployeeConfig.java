package com.example.demo.config;

import com.example.demo.entity.EmployeeRest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "employee")
public class EmployeeConfig {

    private List<EmployeeRest> employees;

}
