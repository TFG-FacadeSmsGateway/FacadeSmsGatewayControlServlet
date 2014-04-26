/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preferya.facadesmsgateway.utils;

import com.preferya.facadesmsgateway.models.MessageEntity;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 *
 * @author Sergio
 */
public class TokenUtil {
    
    private static final String PASSWORD = "preferya";
    
    public static String sha1(MessageEntity message) {
        
        String _textPlain = message.getAction() + message.getArgs() + PASSWORD;
        
        String _sha1 = "";
        try
        {
            MessageDigest _crypt = MessageDigest.getInstance("SHA-1");
            _crypt.reset();
            _crypt.update(_textPlain.getBytes("UTF-8"));
            _sha1 = byteToHex(_crypt.digest());
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return _sha1;
    }

    private static String byteToHex(final byte[] hash) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : hash) {
            builder.append(String.format("%02x", 0xFF & b));
        }
        return builder.toString();
    }
    
}
