package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;



public class JsonUtils {


    public JsonUtils() throws JSONException {


    }


    public static Sandwich parseSandwichJson(String json) {

        String mainName=null;
        String origin=null;
        String desc=null;
        String alsoKnownAs;



        try{

            //accessing..
            JSONObject rootObject = new JSONObject(json);

            //Image
            String image = rootObject.optString("image");

            //Objects
            JSONObject subObj=new JSONObject(json);

            //Name
            if(rootObject.has("name")) {
                subObj = rootObject.getJSONObject("name");
            }


            //handling mainName
            if(subObj.has("mainName")){
               mainName = subObj.optString("mainName");
            }



            //handling Description
            if(rootObject.has("description")) {
                desc = rootObject.optString("description");
            }




            // handling alsoKnownAs from the JSON Array
            JSONArray setArray = subObj.optJSONArray("alsoKnownAs");
            List<String> alsoKnownList = new ArrayList<>();

            for (int i = 0; i < setArray.length(); i++) {

                if(subObj.has("alsoKnownAs")) {
                    alsoKnownAs = setArray.getString(i);

                    alsoKnownList.add(alsoKnownAs);
                }
            }

            //handling Origin
            if(rootObject.has("placeOfOrigin")) {
                origin = rootObject.optString("placeOfOrigin");
            }


            //handling ingredients
            JSONArray ingredientsArray = rootObject.optJSONArray("ingredients");

            List<String> ingredientsList = new ArrayList<>();

            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredientsList.add(ingredientsArray.getString(i));
            }

            //create Sandwich Object
            Sandwich sObject;
            sObject = new Sandwich(mainName, alsoKnownList, origin, desc, image, ingredientsList);
            return sObject;

        }catch (JSONException je) {
            je.printStackTrace();
        }

       return null;
    }
}


/*using this links to finish the project
* https://developer.android.com/reference/org/json/JSONObject.html
*
* https://stackoverflow.com/questions/9605913/how-to-parse-json-in-android
*
* http://square.github.io/picasso/
*
* https://stackoverflow.com/questions/6674341/how-to-use-scrollview-in-android
* */