package demo.com.br.plot_filmguide.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import demo.com.br.plot_filmguide.models.Movie;
import demo.com.br.plot_filmguide.models.Result;

public class MovieDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement element = json.getAsJsonObject();

        if(json.getAsJsonObject() != null) {
            element = json.getAsJsonObject();
        }

        return (new Gson().fromJson(element, Movie.class));
    }
}
