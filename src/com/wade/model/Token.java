/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wade.model;

/**
 *
 * @author Gewa
 */
public class Token {
    
    static final int T_IDENTIFIER = 100;
    static final int T_UNKNOWN = 101;
    static final int T_PLUSOP = 103;
    static final int T_MINUSOP = 104;
    static final int T_DIVOP = 105;
    static final int T_MULOP = 106;
    static final int T_SEMICOLON = 107;
    static final int T_EQUALITYOP = 108;
    static final int T_INT = 109;
    static final int T_FLOAT = 110;
    static final int T_EOF = 112;
    static final int T_FLOAT_NUMBER = 113;
    static final int T_INT_NUMBER = 114;
    static final int T_IF_KEYWORD = 115;
    static final int T_OPENROUNDBRACKET = 116;
    static final int T_CLOSEROUNDBRACKET = 117;
    static final int T_OPENCURLYBRACKET = 118;
    static final int T_CLOSECURLYBRACKET = 119;
    static final int T_GREATERTHAN = 120;
    static final int T_LESSTHAN = 121;
    static final int T_WHILE_KEYWORD = 122;
    
    int type;
    String value;
    
    Token (int type, String value) {
        this.type = type;
        this.value = value;
    }
    
    public int getType() {
        return this.type;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
}
