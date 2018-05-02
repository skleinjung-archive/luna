package com.thrashplay.runetrace.desktop;

import com.thrashplay.luna.app.LunaAppConfiguration;
import com.thrashplay.luna.desktop.app.AbstractLunaApplication;
import com.thrashplay.runetrace.app.RunetraceAppConfig;

/**
 * @author Sean Kleinjung
 */
public class RuneTraceApplication extends AbstractLunaApplication {
    private RuneTraceApplication(LunaAppConfiguration appConfiguration) {
        super(appConfiguration);
    }

    public static void main(String[] args) {
        new RuneTraceApplication(new RunetraceAppConfig()).run();
    }
}