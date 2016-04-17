package com.kxw.model;

import com.kxw.validator.CheckDigit;

/**
 * Created by kxw on 2015/9/30.
 */
public class RequestParameter implements Cloneable{

    @CheckDigit(message = "number is digit")
    private String number;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
