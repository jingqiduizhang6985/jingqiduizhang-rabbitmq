package com.jingqiduizhang.rabbitmqservice.controller;

import com.jingqiduizhang.rabbitmqservice.entity.Student;
import com.jingqiduizhang.rabbitmqservice.servcice.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;
    @RequestMapping("/list")
    public String getList(){
        log.info("list ...");
        return "list";
    }

    @RequestMapping("/save")
    public String addMessage(){
        Student student = new Student();
            student.setName("张三");
            student.setId("1");
            student.setAge(30);
        studentService.sendMessage(student);

        log.info("save ...");
        return "success";
    }

    @RequestMapping("/add")
    public String addMessage(@RequestBody Student student){
        studentService.sendMessage(student);
      log.info("add ...");
        return "success";
    }
}
