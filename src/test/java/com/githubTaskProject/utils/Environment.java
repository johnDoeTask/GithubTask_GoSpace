package com.githubTaskProject.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Environment {


        public static final String API_BASE_URL;
        public static final String USERNAME;
        public static final String PASSWORD;
        public static final String ORG_NAME;
        public static final String TOKEN;
        public static final String GITHUB_LOGIN_URL;


        static {

            Properties properties = null;
            String environment = System.getProperty("environment") != null ? environment = System.getProperty("environment") : ConfigurationReader.getProperty("environment");
            //String environment = ConfigurationReader.get("environment");

            try {

                String path = System.getProperty("user.dir") + "/src/test/resources/Environments/" + environment + ".properties";

                FileInputStream input = new FileInputStream(path);
                properties = new Properties();
                properties.load(input);
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            API_BASE_URL = properties.getProperty("apiBaseUrl");
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
            ORG_NAME = properties.getProperty("org-name");
            TOKEN = properties.getProperty("token");
            GITHUB_LOGIN_URL = properties.getProperty("git-hub-login-url");

        }

}
