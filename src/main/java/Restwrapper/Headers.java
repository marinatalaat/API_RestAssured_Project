package Restwrapper;

import java.util.HashMap;
import java.util.Map;

public class Headers {

    public static Map<String, String> generalHeaders() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("accept", "application/json");
        params.put("Content-Type", "application/json");
        return params;
    }
}
