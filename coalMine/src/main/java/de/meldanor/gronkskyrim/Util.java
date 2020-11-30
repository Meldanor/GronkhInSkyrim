package de.meldanor.gronkskyrim;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    private Util() {

    }

    public static String readProcessOutput(Process process) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        List<String> list = in.lines().collect(Collectors.toList());
        process.waitFor();
        in.close();
        return String.join("", list);
    }

    public static final ObjectMapper JSON = new ObjectMapper();
}
