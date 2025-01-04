package org.example;

public class ScriptRunner {

    public static void main(String[] args) {
        JRubyWrapper jRubyWrapper = JRubyFactory.create();

        if (args.length < 1) {
            System.out.println("Usage: <command> <script_path>");
            System.exit(1);
        }

        jRubyWrapper.runFile(args[0], String.class);

    }
}
