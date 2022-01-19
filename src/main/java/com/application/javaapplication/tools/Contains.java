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

    public static final String EM_CONTAIN = "contain";//  in () id in (1,2,3,4,6)

    public static final String EM_BETWEEN = "between"; //  between '2021-09-18 00:00:00' and now()

    // select sum(a) from tablea where XXX and sum(num) between 10 and 100;

    public static final String EM_LIKE = "like";// like  '%xxx', 'xxx%', '%xxx%'

    public static final String EM_LEFT_LIKE = "lLike"; // left like

    public static final String EM_RIGHT_LIKE = "rlike"; // right like

    public static final Map<String, String> ExplainParameters = new HashMap<String, String>() {{
        put(EM_EQ, "=");
        put(EM_GREATER_THAN, ">");
        put(EM_GREATER_THAN_CONTAIN, ">=");
        put(EM_LESS_THAN, "<");
        put(EM_LESS_THAN_CONTAIN, "<=");
        put(EM_CONTAIN, "in");
        put(EM_BETWEEN, "between");
        put(EM_LIKE, "%~%");
        put(EM_LEFT_LIKE, "%~");
        put(EM_RIGHT_LIKE, "~%");
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
