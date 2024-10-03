package com.muhsener98.unit.converter;

import org.python.antlr.ast.Dict;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.boot.sql.init.AbstractScriptDatabaseInitializer;

import javax.script.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Test {

    public static void main(String[] args) throws Exception {

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("python");
        BufferedReader reader = new BufferedReader
                (new FileReader("/Users/muhammetsener/Desktop/roadmap-projects/unit-converter/src/main/resources/scripts/kelvinTo.py"));

        StringBuilder sb = new StringBuilder();
        String line ;
        while( ( line= reader.readLine()) != null)
            sb.append(line).append("\n");

        engine.put("value" , 0);

        engine.eval(sb.toString());

        Object result = engine.eval("convert(value)");

        System.out.println(result.getClass());
        System.out.println(result);


    }
}
