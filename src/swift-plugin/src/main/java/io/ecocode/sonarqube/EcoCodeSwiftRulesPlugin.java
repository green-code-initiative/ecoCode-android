package io.ecocode.sonarqube;

import org.sonar.api.Plugin;

public class EcoCodeSwiftRulesPlugin implements Plugin  {

    @Override
    public void define(Context context) {

        context.addExtension(SwiftSensor.class);
    }
}
