/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preferya.facadesmsgateway.utils;

import com.preferya.facadesmsgateway.models.MessageEntity;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import java.io.IOException;

/**
 *
 * @author Sergio
 */
public class RabbitMQUtils {
    
    private static final String TASK_QUEUE_NAME = "internalQueue";
    
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    
    public RabbitMQUtils() throws IOException{
        this.factory = new ConnectionFactory();
        this.factory.setHost("localhost");
        this.connection = this.factory.newConnection();
        this.channel = this.connection.createChannel();
        
        this.channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
    }
    
    //This method closes the channel and the connection.
    public void closeConnection() throws IOException {
        this.channel.close();
        this.connection.close();
    }
    
    public void sendMessage(MessageEntity message) throws IOException {
        String _sndMess = message.toString();
        channel.basicPublish( "", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, _sndMess.getBytes());
    }
    
}
