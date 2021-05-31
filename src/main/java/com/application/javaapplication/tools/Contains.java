package com.application.javaapplication.tools;

import java.util.HashMap;
import java.util.Map;

public final class Contains
{
    private Contains() {}

    public static final String EM_EQ = "eq";

    public static final String EM_GREATER_THAN = "gto";

    public static final String EM_GREATER_THAN_CONTAIN = "gto in";

    public static final String EM_LESS_THAN = "lto";

    public static final String EM_LESS_THAN_CONTAIN = "lto in";

    public static final String EM_CONTAIN = "contain";

    public static final String EM_BETWEEN = "between";

    public static final Map<String, String> ExplainParameters = new HashMap<String, String>() {{
        put(EM_EQ, "=");
        put(EM_GREATER_THAN, ">");
        put(EM_GREATER_THAN_CONTAIN, ">=");
        put(EM_LESS_THAN, "<");
        put(EM_LESS_THAN_CONTAIN, "<=");
        put(EM_CONTAIN, "in");
        put(EM_BETWEEN, "between");
    }};

    public static String ExplainEnums(String key) throws Exception
    {
        String ExplainValue = ExplainParameters.get(key);
        if (ExplainValue == null) {
            throw new Exception("No match key");
        }
        return ExplainValue;
    }
}
