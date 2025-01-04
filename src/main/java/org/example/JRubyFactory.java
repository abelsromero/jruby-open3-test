package org.example;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.javasupport.JavaEmbedUtils;

import java.util.*;

public class JRubyFactory {

    private static final String GEM_PATH = "GEM_PATH";

    public static JRubyWrapper create() {
        JRubyWrapper instance = createInstance(Collections.singletonMap(GEM_PATH, null), new ArrayList<>(), null);
        return instance;
    }

    private static JRubyWrapper createInstance(Map<String, String> environmentVars, List<String> loadPaths, ClassLoader classloader) {
        Ruby rubyRuntime = createRubyRuntime(environmentVars, loadPaths, classloader);
        return new JRubyWrapper(rubyRuntime);
    }

    private static Ruby createRubyRuntime(Map<String, String> environmentVars, List<String> loadPaths, ClassLoader classloader) {
        Map<String, String> env = environmentVars != null ? new HashMap<>(environmentVars) : new HashMap<>();

        RubyInstanceConfig config = createOptimizedConfiguration();
        if (classloader != null) {
            config.setLoader(classloader);
        }
        injectEnvironmentVariables(config, env);

        return JavaEmbedUtils.initialize(loadPaths, config);
    }

    private static RubyInstanceConfig createOptimizedConfiguration() {
        return new RubyInstanceConfig();
    }

    private static void injectEnvironmentVariables(RubyInstanceConfig config, Map<String, String> environmentVars) {
        EnvironmentInjector environmentInjector = new EnvironmentInjector(config);
        environmentInjector.inject(environmentVars);
    }
}
