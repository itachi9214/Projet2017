package ca.ulaval.glo4002.billing;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {

  static Map<Class<?>, Object> services = new HashMap<>();

  public static void register(Object service) {
    services.put(service.getClass(), service);
  }

  @SuppressWarnings("unchecked")
  public static <T> T getService(Class<T> type) {
    T instance = null;
    for (Map.Entry<Class<?>, Object> entry : services.entrySet()) {
      if (type.isAssignableFrom(entry.getKey())) {
        instance = (T) entry.getValue();
        break;
      }
    }
    return instance;
  }

}
