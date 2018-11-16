package com.epam.races.controller;

import java.util.ResourceBundle;

public enum ConfigurationManager {
    INSTANCE;

    private static String BUNDLE_NAME = "config";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);


    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";
    public static final String START_PAGE_PATH = "START_PAGE_PATH";

    public String getProperty(String key){
        String str = (String) resourceBundle.getObject(key);
        return str;
    }

}
