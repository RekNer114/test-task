package me.vmachohan.data_api.transformer;

public class TextTransformer {

    public static String reverseAndUpper(String text){
        return new StringBuilder(text)
                .reverse()
                .toString()
                .toUpperCase();
    }
}
