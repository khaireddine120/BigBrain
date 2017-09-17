package com.bigbrain.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.hibernate.loader.GeneratedCollectionAliases;

public class PropertyReader {
	static final ClassLoader loader = PropertyReader.class.getClassLoader();

    public static Properties readProperties(String fileInClasspath) throws FileNotFoundException {
        File file = new File(loader.getResource(fileInClasspath).getFile());
        InputStream is = new FileInputStream(file);

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