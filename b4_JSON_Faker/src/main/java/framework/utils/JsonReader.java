package framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import framework.models.UserData;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class JsonReader {

    public static List<UserData> readUsers() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = JsonReader.class
                    .getClassLoader()
                    .getResourceAsStream("users.json");

            UserData[] users = mapper.readValue(is, UserData[].class);
            return Arrays.asList(users);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
