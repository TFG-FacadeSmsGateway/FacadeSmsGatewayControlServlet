/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preferya.facadesmsgatewaycontrolservlet.services;

import com.preferya.facadesmsgatewaycontrolservlet.models.MessageEntity;

/**
 *
 * @author Sergio
 */
public interface IPhoneValidationService {
    
    public boolean checkIntegrity(MessageEntity message, String token);
    
    public void sendQueue(MessageEntity message);

    public void sendExternalQueue(MessageEntity _message, String iso_lang);
    
}
