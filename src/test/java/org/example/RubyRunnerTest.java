package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class RubyRunnerTest {

    @Test
    public void run_script_in_string() {
        final JRubyWrapper jRubyWrapper = JRubyFactory.create();
        jRubyWrapper.run("puts 'Hello World!'");
    }

    @Test
    public void run_script_in_string_and_return_boolean() {
        final JRubyWrapper jRubyWrapper = JRubyFactory.create();

        var value = jRubyWrapper.run("0 < 1", Boolean.class);

        assertThat(value)
                .isInstanceOf(Boolean.class)
                .isTrue();
    }

    @Test
    public void run_script_in_string_and_return_integer() {
        final JRubyWrapper jRubyWrapper = JRubyFactory.create();

        var value = jRubyWrapper.run("2+3", Integer.class);

        assertThat(value)
                .isInstanceOf(Integer.class)
                .isEqualTo(5);
    }

    @Test
    public void run_script_in_string_and_return_string() {
        final JRubyWrapper jRubyWrapper = JRubyFactory.create();

        var value = jRubyWrapper.run("'hello '+'world'", String.class);

        assertThat(value)
                .isInstanceOf(String.class)
                .isEqualTo("hello world");
    }

    @Test
    public void run_run_command() {
        final JRubyWrapper jRubyWrapper = JRubyFactory.create();
        final File file = new File("src/main/resources/script.rb");

        var value = jRubyWrapper.runFile(file.getAbsolutePath(), String.class);

        assertThat(value)
                .isNull();
    }
}
