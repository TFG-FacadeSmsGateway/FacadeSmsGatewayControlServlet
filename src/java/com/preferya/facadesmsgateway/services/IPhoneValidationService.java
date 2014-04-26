/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preferya.facadesmsgateway.services;

import com.preferya.facadesmsgateway.models.MessageEntity;

/**
 *
 * @author Sergio
 */
public interface IPhoneValidationService {
    
    public boolean checkIntegrity(MessageEntity message, String token);
    
    public void senQueue(MessageEntity message);

    public void sendExternalQueue(MessageEntity _message, String iso_lang);
    
}
