/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preferya.facadesmsgatewaycontrolservlet.services;

import com.preferya.facadesmsgatewaycontrolservlet.models.MessageEntity;
import com.preferya.facadesmsgatewaycontrolservlet.utils.RabbitMQUtils;
import com.preferya.facadesmsgatewaycontrolservlet.utils.RabbitMQUtilsIsoCountry;
import com.preferya.facadesmsgatewaycontrolservlet.utils.TokenUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Sergio
 */
public class PhoneValidationService implements IPhoneValidationService {
    
    private RabbitMQUtils internalQueue;
    private RabbitMQUtilsIsoCountry externalQueue;
    
    public PhoneValidationService(){
        
    }
    
    public boolean checkIntegrity(MessageEntity message, String token) {
        //TODO: this method checks the integrity of message recived. Complete and comment.
        
        boolean _ret = false;
        
        //First, we go to check token with pattern.
        Pattern _patToken = Pattern.compile("[a-f0-9]{40}");
        Matcher _matToken = _patToken.matcher(token);
        
        if(_matToken.matches()){
            String _sha1 = TokenUtil.sha1(message);
            if(token.equals(_sha1))
                _ret = true;
        }
        
        return _ret;
    }
    
    //This method call to auxiliary class to send message with RabbitMQ.
    public void sendQueue(MessageEntity message) {
        try {
            this.internalQueue = new RabbitMQUtils();
            this.internalQueue.sendMessage(message);
            this.internalQueue.closeConnection();
        } catch (IOException ex) {
            Logger.getLogger(PhoneValidationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendExternalQueue(MessageEntity message, String iso_lang) {
        try {
            this.externalQueue = new RabbitMQUtilsIsoCountry(iso_lang);
            this.externalQueue.sendMessage(message);
            this.externalQueue.closeConnection();
        } catch (IOException ex) {
            Logger.getLogger(PhoneValidationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
