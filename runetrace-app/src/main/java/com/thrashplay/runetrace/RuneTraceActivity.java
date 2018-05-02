package com.thrashplay.runetrace;

import com.thrashplay.luna.android.app.LunaGame;
import com.thrashplay.luna.app.LunaAppConfiguration;
import com.thrashplay.runetrace.app.RunetraceAppConfig;

public class RuneTraceActivity extends LunaGame {
    @Override
    protected LunaAppConfiguration getAppConfiguration() {
        return new RunetraceAppConfig();
    }
}
