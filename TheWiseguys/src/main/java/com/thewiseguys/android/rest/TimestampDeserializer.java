package com.thewiseguys.android.rest;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 * Created by Chris Krause on 6/09/13.
 * TheWiseguys - com.thewiseguys.android.rest
 */
import java.lang.reflect.Type;
import java.sql.Timestamp;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class TimestampDeserializer implements JsonDeserializer<Timestamp> {

    public Timestamp deserialize(JsonElement json, Type typeOfT,
                                 JsonDeserializationContext context) throws JsonParseException {
        long time = Long.parseLong(json.getAsString());
        return new Timestamp(time * 1000);
    }
}