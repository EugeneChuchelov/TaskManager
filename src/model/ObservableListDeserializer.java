package model;

import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ObservableListDeserializer implements JsonDeserializer<ObservableList> {
    @Override
    public ObservableList deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonArray list = jsonElement.getAsJsonArray();
        ObservableList<Task> observableList = FXCollections.observableArrayList();
        for (JsonElement element : list) {
            JsonObject object = element.getAsJsonObject();
            String title = object.get("title").getAsString();
            String description = object.get("description").getAsString();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = null;
            try {
                date = ft.parse(object.get("date").getAsString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String contacts = object.get("contacts").getAsString();
            observableList.add(new Task(title, description, date, contacts));
        }
        return observableList;
    }
}
