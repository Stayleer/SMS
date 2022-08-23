package common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.util.Date;

public class GsonCustom {

    private static Gson instance;

    public GsonCustom() {
    }

    public static Gson getInstance(){
        if(instance == null){
            synchronized (GsonCustom.class){
                if (instance == null){
                        instance = new GsonBuilder()
                                .registerTypeAdapter(Date.class, (JsonDeserializer<Date>)
                                        (jsonElement, type, jsonDeserializationContext) -> null).create();
                }
            }
        }
        return instance;
    }
}
