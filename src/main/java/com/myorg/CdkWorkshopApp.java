package com.myorg;

import software.amazon.awscdk.App;

// This code loads and instantiates the CdkWorkshopStack class from the ~/CdkWorkshopStack.java file
public final class CdkWorkshopApp {
    public static void main(final String[] args) {
        App app = new App();

        new CdkWorkshopStack(app, "CdkWorkshopStack");

        app.synth();
    }
}
