package com.api.auto.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesFileUtils {
    private static String CONFIG_PROPERTIES_PATH= "src/main/java/configuration/configs.properties";
    private static String CONFIG_TOKEN_PATH= "src/main/java/configuration/token.properties";

    public static String getProperty(String key){
        Properties properties = new Properties();
        String value = null;
        FileInputStream file = null;

        try{
            file = new FileInputStream(CONFIG_PROPERTIES_PATH);
            properties.load(file);
            value = properties.getProperty(key);
            file.close();
            return value;
        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
        return value;
    }
    public static String getToken(){
        Properties properties = new Properties();
        String value = null;
        FileInputStream file = null;

        try{
            file = new FileInputStream(CONFIG_TOKEN_PATH);
            properties.load(file);
            value = properties.getProperty("token");
            file.close();
            return value;
        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
        return value;
    }

    public static void saveToken(String token){
        Properties properties = new Properties();
        FileOutputStream file = null;

        try{
            file = new FileOutputStream(CONFIG_TOKEN_PATH);
            properties.setProperty("token", token);
            properties.store(file,"Set success");
            file.close();
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
