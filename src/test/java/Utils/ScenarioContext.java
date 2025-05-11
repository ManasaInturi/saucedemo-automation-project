package Utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static final ThreadLocal<Map<String, String>> data = ThreadLocal.withInitial(HashMap::new);

    public static void set(String key, String value) {
        data.get().put(key, value);
    }

    public static Map<String, String> getAll() {
        return new HashMap<>(data.get());
    }

    public static String get(String key) {
        return data.get().get(key);
    }

    public static void clear() {
        data.remove();
    }
}
