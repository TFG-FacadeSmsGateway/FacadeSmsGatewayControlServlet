/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preferya.facadesmsgateway.services;

/**
 *
 * @author Sergio
 */
public class PhoneValidationFactory {
    
    public static IPhoneValidationService getServiceByIsoLang(String isoLang) {
        
        IPhoneValidationService _ret = null;
        
        if(isoLang.equalsIgnoreCase("es_es")){ //Spanish case
            _ret = new PhoneValidationService();
        }
        
        return _ret; //Null! be careful.
    }
    
}
