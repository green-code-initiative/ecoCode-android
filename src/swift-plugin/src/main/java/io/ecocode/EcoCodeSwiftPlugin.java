package io.ecocode;

import org.sonar.api.Plugin;

public class EcoCodeSwiftPlugin implements Plugin  {

    @Override
    public void define(Context context) {

        context.addExtensions(SwiftSensor.class, EcoCodeSwiftProfile.class, EcoCodeSwiftRulesDefinition.class);
    }
}
