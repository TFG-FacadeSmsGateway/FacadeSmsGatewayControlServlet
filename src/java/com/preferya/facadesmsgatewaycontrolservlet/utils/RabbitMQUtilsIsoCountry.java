/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preferya.facadesmsgatewaycontrolservlet.utils;

import com.preferya.facadesmsgatewaycontrolservlet.models.MessageEntity;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import java.io.IOException;

/**
 *
 * @author Sergio
 */
public class RabbitMQUtilsIsoCountry {
    
    private String task_queue_name;
    
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    
    public RabbitMQUtilsIsoCountry(String task_queue_name) throws IOException{
        this.factory = new ConnectionFactory();
        this.factory.setHost("localhost");
        this.connection = this.factory.newConnection();
        this.channel = this.connection.createChannel();
        
        this.task_queue_name = task_queue_name;
        
        this.channel.queueDeclare(task_queue_name, true, false, false, null);
    }
    
    //This method closes the channel and the connection.
    public void closeConnection() throws IOException {
        this.channel.close();
        this.connection.close();
    }
    
    public void sendMessage(MessageEntity message) throws IOException {
        String _sndMess = message.toString();
        channel.basicPublish( "", this.task_queue_name, MessageProperties.PERSISTENT_TEXT_PLAIN, _sndMess.getBytes());
    }
    
}
