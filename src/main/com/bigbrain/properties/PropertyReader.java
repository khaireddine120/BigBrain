package com.bigbrain.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    
    public static Properties readProperties(String fileInClasspath) throws FileNotFoundException {
        InputStream is = new FileInputStream(fileInClasspath);
        
        try {
            Properties properties = new Properties();
            properties.load(is);
            return properties;
        } catch (Exception e) {
            System.err.println("Could not read properties from file " + fileInClasspath + " in classpath. " + e);
        }
       
        return null;
    }
}