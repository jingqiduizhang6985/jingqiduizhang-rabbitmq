package com.jingqiduizhang.rabbitmqservice.servcice;

import com.jingqiduizhang.rabbitmqservice.entity.Student;

public interface StudentService {
    /**
     * 发送数据
     * @param student
     */
     void sendMessage(Student student);
    /**
     * 接收的数据
     * @param message
     */
    void receiveMessage(String message);
}
