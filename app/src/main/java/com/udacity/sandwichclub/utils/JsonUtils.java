package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    String mName;
    String origin;
    String desc;
    String knownAs;

    public JsonUtils() throws JSONException {


    }


    public static Sandwich parseSandwichJson(String json) {

        String mName=null;
        String origin=null;
        String desc=null;
        String knownAs=null;

        try{

            JSONObject rootObject = new JSONObject(json);
            JSONObject subObj=new JSONObject(json);

            //Name
            if(rootObject.has("name"))
                subObj = rootObject.getJSONObject("name");


            //handling mainName
            if(subObj.has("mainName")){
               mName = subObj.getString("mainName");


            }



            //handling Origin
            if(rootObject.has("placeOfOrigin")) {
                origin = rootObject.getString("placeOfOrigin");


            }

            //handling Description
            if(rootObject.has("description")) {
                desc = rootObject.getString("description");


            }

            //Image
            String image = rootObject.getString("image");


            // handling alsoknownAs from the JSON Array
            JSONArray setArray = subObj.getJSONArray("alsoKnownAs");
            List<String> alsoKnownList = new ArrayList<>();

            for (int i = 0; i < setArray.length(); i++) {

                if(subObj.has("alsoKnownAs")) {
                    knownAs = setArray.getString(i);

                    alsoKnownList.add(knownAs);
                }
            }




            //handling ingredients
            JSONArray ingreArray = rootObject.getJSONArray("ingredients");

            List<String> ingreList = new ArrayList<>();

            for (int i = 0; i < ingreArray.length(); i++) {
                ingreList.add(ingreArray.getString(i));
            }

            //create Sandwich Object
            Sandwich sObject;
            sObject = new Sandwich(mName, alsoKnownList, origin, desc, image, ingreList);
            return sObject;

        }catch (JSONException je) {
            je.printStackTrace();
        }

        return null;
    }
}
