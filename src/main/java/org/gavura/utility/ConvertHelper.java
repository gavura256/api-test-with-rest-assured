package org.gavura.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.gavura.entity.Store;
import org.gavura.log.Log;

@UtilityClass
public class ConvertHelper {
    public static Store convertStringToPojoStoreClass(String actualOrder) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(actualOrder, Store.class);
        } catch (JsonProcessingException e) {
            Log.print(e.getMessage());

            return new Store();
        }
    }
}
