package com.dialogflow;

public class TestEnvVariable {
    public static void main(String[] args) {
        System.out.println("GOOGLE_APPLICATION_CREDENTIALS: " + System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));
        System.out.println("GOOGLE_CLOUD_PROJECT: " + System.getenv("GOOGLE_CLOUD_PROJECT"));
    }
}
