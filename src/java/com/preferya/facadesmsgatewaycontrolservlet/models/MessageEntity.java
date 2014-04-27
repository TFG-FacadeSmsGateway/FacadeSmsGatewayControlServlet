/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preferya.facadesmsgatewaycontrolservlet.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sergio
 */
public class MessageEntity implements Serializable {
    
    //private static final int NUMBUTESVALIDATIONCODE = 5;
    //private static final int NUMBYTESISOLANG = 5;
    
    private String action;
    private String args;
    private String iso_lang;
    
    public MessageEntity() {
        
    }
    
    public MessageEntity(String action, String args, String iso_lang) {
        this.action = action;
        this.args = args;
        this.iso_lang = iso_lang;
    }
    
    public String toString() {
        String _ret = "";
        
        _ret += this.action + ",";
        _ret += this.args + ",";
        _ret += this.iso_lang;
        
        return _ret;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }
    
    /**
     * @return the args
     */
    public String getArgs() {
        return args;
    }
    
    /**
     * @return the args
     */
    public String getIsoLang() {
        return iso_lang;
    }
    
}
