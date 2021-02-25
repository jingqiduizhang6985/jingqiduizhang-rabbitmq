package com.jingqiduizhang.rabbitmqservice.servcice.impl;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.jingqiduizhang.rabbitmqservice.config.RabbitMQSenderService;
import com.jingqiduizhang.rabbitmqservice.entity.Student;
import com.jingqiduizhang.rabbitmqservice.servcice.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Autowired
    private RabbitMQSenderService rabbitMQSenderService;
    @Override
    public void sendMessage(Student student) {
         JSONObject jsonObject = new JSONObject(student);
        String message = jsonObject.toString();
        rabbitMQSenderService.sendMessage(message);
    }
    @Override
    public void receiveMessage(String message) {
        //message 转换
        Map<String,Object> map = com.alibaba.fastjson.JSONObject.parseObject(message);
        Student student = JSON.parseObject(JSON.toJSONString(map), Student.class);
        log.info("接收对象 student{}",student);
    }
}
