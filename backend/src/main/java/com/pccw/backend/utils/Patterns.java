package com.pccw.backend.utils;

import java.util.regex.Pattern;

public class Patterns {

    public static final Pattern usernamePattern = Pattern.compile("\\s");
    public static final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    public static final Pattern namePattern = Pattern.compile("^[a-zA-Z\\s'-]+$");

}
