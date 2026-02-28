package com.arthurneves.TaskManager.utils;

public class CreateUserUsername {
    public static String format(String name) {
        String[] nameParts = name.trim().toLowerCase().split("\\s");

        int length = nameParts.length;

        if (length == 1) {
            return nameParts[0];
        }

        return nameParts[0] + "." + nameParts[length - 1];
    }
}
