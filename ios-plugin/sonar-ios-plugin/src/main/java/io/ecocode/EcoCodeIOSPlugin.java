package io.ecocode;

import io.ecocode.swift.EcoCodeSwiftProfile;
import io.ecocode.swift.EcoCodeSwiftRulesDefinition;
import io.ecocode.swift.SwiftSensor;
import org.sonar.api.Plugin;

public class EcoCodeIOSPlugin implements Plugin  {

    @Override
    public void define(Context context) {

        context.addExtensions(SwiftSensor.class, EcoCodeSwiftProfile.class, EcoCodeSwiftRulesDefinition.class);
    }
}
