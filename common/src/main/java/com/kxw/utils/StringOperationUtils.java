package com.kxw.utils;

/**
 * Created by Kingson.wu on 2015/10/15.
 */
public class StringOperationUtils {


    public String join(String[] array, CharSequence delimiter) {

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String str : array) {
            if (first == true) {
                sb.append(str);
                first = false;
            } else {
                sb.append(delimiter).append(str);
            }
        }
        return sb.toString();
    }


    //用StringUtils.join(C,",")
    //String.join(CharSequence delimiter,Iterable<? extends CharSequence> elements)  这个比较好，jdk 8的新api
    //guava joiner也可以

}
