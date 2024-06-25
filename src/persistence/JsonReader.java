package persistence;

import model.myCloset;
import model.myOutfit;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public myCloset read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCloset(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private myCloset parseCloset(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        myCloset newCloset = new myCloset(name);
        addOutfits(newCloset, jsonObject);
        addClothes(newCloset, jsonObject);


        return newCloset;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addClothes(myCloset closet, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("clothes");
        for (Object json : jsonArray) {
            JSONObject nextClothing = (JSONObject) json;
            addClothing(closet, nextClothing);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addClothing(myCloset closet, JSONObject jsonObject) {
        String clothingName = jsonObject.getString("clothingName");
        String clothingType = jsonObject.getString("clothingType");
        String photo = jsonObject.getString("clothingPhoto");


        closet.addClothing(clothingName, clothingType, photo);
    }

    private void addOutfits(myCloset closet, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("outfits");
        for (Object json : jsonArray) {
            JSONObject nextClothing = (JSONObject) json;
            addOutfit(closet, nextClothing);
        }
    }

    private void addOutfit(myCloset closet, JSONObject jsonObject) {
        myOutfit temp = new myOutfit(jsonObject.getString("outfitName"));

        for (int x = 1; x <= 10; x++) {
            try {
                if (jsonObject.getString("accessory " + Integer.toString(x)) != null) {
                    temp.addAccessory(jsonObject.getString("accessory " + Integer.toString(x)));
                }
            } catch (Exception e) {
            }
            try {
                if (jsonObject.getString("hat " + Integer.toString(x)) != null) {
                    temp.addHat(jsonObject.getString("hat " + Integer.toString(x)));
                }
            } catch (Exception e) {
            }
            try {
                if (jsonObject.getString("top " + Integer.toString(x)) != null) {
                    temp.addTop(jsonObject.getString("top " + Integer.toString(x)));
                }
            } catch (Exception e) {
            }
            try {
                if (jsonObject.getString("bottom " + Integer.toString(x)) != null) {
                    temp.addBottom(jsonObject.getString("bottom " + Integer.toString(x)));
                }
            } catch (Exception e) {
            }
            try {
                if (jsonObject.getString("shoe " + Integer.toString(x)) != null) {
                    temp.addShoe(jsonObject.getString("shoe " + Integer.toString(x)));
                }
            } catch (Exception e) {
            }
        }

        closet.addOutfit(temp);
    }

}
