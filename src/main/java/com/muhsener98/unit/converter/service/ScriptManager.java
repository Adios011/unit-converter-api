package com.muhsener98.unit.converter.service;

import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Component
public class ScriptManager {

    private Map<String,Script> scriptMap;
    private ScriptEngine scriptEngine ;


    public ScriptManager(){
        this.scriptMap = new HashMap<>();
        scriptEngine = new ScriptEngineManager().getEngineByName("python");
    }

    public void addScript(String name, File scriptFile){
        Script script = Script.createScriptByReadingFile(scriptFile);
        scriptMap.put(name , script);
    }

    public Object executeScript(String key, Map<String,Object> arguments){
       Script script = scriptMap.get(key);

       if(script == null)
           throw new RuntimeException("No such script found with name: " + key);

        try {
            for(Map.Entry<String,Object> entry : arguments.entrySet()){
                String argumentName = entry.getKey();
                Object argumentValue = entry.getValue() ;
                scriptEngine.put(argumentName , argumentValue);;
            }

            scriptEngine.eval(script.getScript() );
            return scriptEngine.eval(script.getLineToBeEvaluated());


        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }


    }

    public Map<String, Script> getScriptMap() {
        return scriptMap;
    }
}
