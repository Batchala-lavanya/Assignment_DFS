package com.demo.task.Cucumber;

import org.springframework.boot.test.context.SpringBootTest;

import com.demo.task.MyAssignmentApplication;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes=MyAssignmentApplication.class)
public class CucumberConfiguration {

}
