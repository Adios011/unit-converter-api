package com.muhsener98.unit.converter.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Script {
    private String script;
    private String lineToBeEvaluated;
    private Map<String, Class<?>> scriptArguments;

    public Script(String script, String lineToBeEvaluated) {
        this.script = script;
        this.lineToBeEvaluated = lineToBeEvaluated;
        this.scriptArguments = new LinkedHashMap<>();
    }

    public void addArgument(String name, Class<?> type) {
        assert scriptArguments != null;

        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("illegal name");
        }

        if (type == null) {
            throw new IllegalArgumentException("illegal type");
        }

        scriptArguments.put(name, type);
    }

    public static Script createScriptByReadingFile(File scriptFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(scriptFile))) {
            String commentLine = "";
            String lineToBeEvaluated = "";
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
                if (line.startsWith("#")) {
                    commentLine = line;
                    lineToBeEvaluated = reader.readLine(); // subsequent line after comment line
                    sb.append(lineToBeEvaluated);
                }
            }

            Script script = new Script(sb.toString(), lineToBeEvaluated);

            String[] commentLineParsed = commentLine.split(":");
            for (int i = 1; i < commentLineParsed.length; i++) {
                String[] argumentPart = commentLineParsed[i].trim().split("-");
                String argumentName = argumentPart[0];
                String argumentType = argumentPart[1];
                script.addArgument(argumentName, parseArgumentType(argumentType));
            }


            return script;

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    private static Class<?> parseArgumentType(String typeInString) {
        if (typeInString.equalsIgnoreCase("double"))
            return Double.class;
        else if (typeInString.equalsIgnoreCase("string"))
            return String.class;
        else if (typeInString.equalsIgnoreCase("integer"))
            return Integer.class;
        else if (typeInString.equalsIgnoreCase("boolean"))
            return Boolean.class;
        else
            throw new UnsupportedOperationException("Unsupported argument type for script");

    }


    public String getScript() {
        return script;
    }

    public String getLineToBeEvaluated() {
        return lineToBeEvaluated;
    }

    public Map<String, Class<?>> getScriptArguments() {
        return scriptArguments;
    }
}




