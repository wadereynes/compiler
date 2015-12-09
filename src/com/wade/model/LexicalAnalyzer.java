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
import java.io.File;
import java.io.FileInputStream;
public class LexicalAnalyzer {
    
    static final int LETTER = 0;
    static final int DIGIT = 1;
    static final int PLUS_OPERATOR = 2;
    static final int MINUS_OPERATOR = 3;
    static final int DIV_OPERATOR = 4;
    static final int MUL_OPERATOR = 5;
    static final int SEMICOLON = 6;
    static final int PERIOD = 7;
    static final int EQL_OPERATOR = 8;
    static final int OPEN_ROUND_BRACKET = 9;
    static final int CLOSE_ROUND_BRACKET = 10;
    static final int OPEN_CURLY_BRACKET = 11;
    static final int CLOSE_CURLY_BRACKET = 12;
    static final int GREATER_THAN = 13;
    static final int LESS_THAN = 14;
    static final int UNKNOWN = 555;
    
    static final int MAX_LEXEME_LENGTH = 100;
    
    int charClass;
    String lexeme;
    char nextChar;
    int lexLen;
    int readCharacter;
    
    Token t = null;
    
    boolean first = true;
    boolean eof = false;
    
    File theFile = null;
    FileInputStream sourceCode = null;
    
    LexicalAnalyzer(String fileName) {
        try {
            theFile = new File(fileName);
            sourceCode = new FileInputStream(theFile);
        } catch (Exception e) {
            System.out.println("Lexer: File \"" + fileName + "\" not found!");
            System.exit(1);
        }
        
        charClass = UNKNOWN;
    }
    void addChar() {
        if (lexLen < MAX_LEXEME_LENGTH) {
            lexeme += nextChar;
            lexLen++;
        } else {
            SyntaxAnalyzerDemo.showMessage("Lexer: Too long lexeme!");
        }
    }
    
    void getChar() {
        try {
            readCharacter = sourceCode.read();
            
            if (readCharacter == -1){
                eof = true;
                charClass = UNKNOWN;
                return;
            }
            
            nextChar = (char) readCharacter;
        } catch (Exception e) {
            System.out.println("Lexer: There was an error reading from the file!");
        }
        if ( (nextChar >= 'a' && nextChar <='z') || (nextChar >='A' && nextChar <= 'Z')) {
            charClass = LETTER;
        } 
        else if ( (nextChar >='0' && nextChar <= '9')) {
            charClass = DIGIT;
        }
        else {
            switch(nextChar){
                case ';': charClass = SEMICOLON; break;
                case '.': charClass = PERIOD; break;
                case '*': charClass = MUL_OPERATOR; break;
                case '/': charClass = DIV_OPERATOR; break;
                case '-': charClass = MINUS_OPERATOR; break;
                case '+': charClass = PLUS_OPERATOR; break;
                case '=': charClass = EQL_OPERATOR; break;
                case '(': charClass = OPEN_ROUND_BRACKET; break;
                case ')': charClass = CLOSE_ROUND_BRACKET; break;
                case '{': charClass = OPEN_CURLY_BRACKET; break;
                case '}': charClass = CLOSE_CURLY_BRACKET; break;
                case '>': charClass = GREATER_THAN; break;
                case '<': charClass = LESS_THAN; break;
                default: charClass = UNKNOWN;
            }
        }
    }
    
    void getNonBlank() {
        while(nextChar == ' '){
            getChar();
        }
    }
    
    int lookup(String l) {
        
        if (l.compareTo("int") == 0) {
            return Token.T_INT;
        }
        
        else if (l.compareTo("float") == 0) {
            return Token.T_FLOAT;
        }
        else if (l.compareTo("double") == 0){
            return Token.T_FLOAT;
        }
        
        else if (l.compareTo("if") == 0) {
            return Token.T_IF_KEYWORD;
        }
        
        else if (l.compareTo("while") == 0) {
            return Token.T_WHILE_KEYWORD ;
        }
        return Token.T_IDENTIFIER;
    }
    
    public Token lex() {
        
        lexLen = 0;
        lexeme = "";
        
        t = new Token(Token.T_UNKNOWN,lexeme);
        
        if (first) {
            getChar();
            first = false;
        }
        
        if (eof) {
            t.setType(Token.T_EOF);
            return t;
        }
        
        getNonBlank();
        switch(charClass) {
            
            case LETTER:
                addChar();
                getChar();
                while( charClass == LETTER || charClass == DIGIT) {
                    addChar();
                    getChar();
                }
                
            t.setType(lookup(lexeme));
            break;
                
            case DIGIT:
                addChar();
                getChar();
                while (charClass == DIGIT) {
                    addChar();
                    getChar();
                }
                if (charClass == PERIOD) {
                    addChar();
                    getChar();
                    while(charClass ==DIGIT) {
                        addChar();
                        getChar();
                    }
                    t.setType(Token.T_FLOAT_NUMBER);
                }
                else {
                    t.setType(Token.T_INT_NUMBER);
                }
                break;
                
            case SEMICOLON:
                    addChar();
                    getChar();
                    t.setType(Token.T_SEMICOLON);
                    break;
            
            case PLUS_OPERATOR:
                addChar();
                getChar();
                t.setType(Token.T_PLUSOP);
                break;
                
            case MINUS_OPERATOR:
                addChar();
                getChar();
                t.setType(Token.T_MINUSOP);
                break;
                
            case DIV_OPERATOR:
                addChar();
                getChar();
                t.setType(Token.T_DIVOP);
                break;
           
            case MUL_OPERATOR:
                addChar();
                getChar();
                t.setType(Token.T_MULOP);
                break;
                
            case EQL_OPERATOR:
                addChar();
                getChar();
                t.setType(Token.T_EQUALITYOP);
                break;
                
            case OPEN_ROUND_BRACKET:
                addChar();
                getChar();
                t.setType(Token.T_OPENROUNDBRACKET);
                break;
                
            case CLOSE_ROUND_BRACKET:
                addChar();
                getChar();
                t.setType(Token.T_CLOSEROUNDBRACKET);
                break;
            
            case OPEN_CURLY_BRACKET:
                addChar();
                getChar();
                t.setType(Token.T_OPENCURLYBRACKET);
                break;
            
            case CLOSE_CURLY_BRACKET:
                addChar();
                getChar();
                t.setType(Token.T_CLOSECURLYBRACKET);
                break;
                
            case GREATER_THAN:
                addChar();
                getChar();
                t.setType(Token.T_GREATERTHAN);
                break;
                
            case LESS_THAN:
                addChar();
                getChar();
                t.setType(Token.T_LESSTHAN);
                break;
                
            default:
                t.setType(Token.T_UNKNOWN);
                SyntaxAnalyzerDemo.showMessage("Lexer: Invalid character \"" + nextChar + "\" found");
                break;
                    
        }
        
        t.setValue(lexeme);
        
        System.out.println("Last Token returned = " + lexeme);
        return t;
        
        
    }
    
    
}
