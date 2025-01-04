package org.example;

import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class JRubyWrapper {

    private final Ruby rubyRuntime;

    public JRubyWrapper(Ruby rubyRuntime) {
        this.rubyRuntime = rubyRuntime;
    }

    public void run(String code) {
        IRubyObject iRubyObject = rubyRuntime.evalScriptlet(code);
        System.out.println(iRubyObject);
    }

    public <T> T run(String code, Class<T> returnType) {
        IRubyObject rubyObject = rubyRuntime.evalScriptlet(code);
        return rubyObject.toJava(returnType);
    }

    public <T> T runFile(String path, Class<T> returnType) {
        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + path);
        }
        try {
            String content = Files.readString(file.toPath());
            IRubyObject rubyObject = rubyRuntime.evalScriptlet(content);
            return rubyObject.toJava(returnType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
