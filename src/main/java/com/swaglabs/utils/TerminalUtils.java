package com.swaglabs.utils;

public class TerminalUtils {

    public static void executeCommand(String... command){
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            process.waitFor();
            LogsUtil.info("Command executed successfully: " + String.join(" ", command));
        }
        catch (Exception e) {
            LogsUtil.error("Failed to execute command: " + e.getMessage());
        }
    }
}
