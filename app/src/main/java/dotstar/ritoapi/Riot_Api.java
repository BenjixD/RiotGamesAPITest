package dotstar.ritoapi;

import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dotstar.ritoapi.NetworkController;

public class Riot_Api{
    //Static key
    private static final String key = "fc583505-9dda-425b-847b-ca94daec7373";

    //Interface for synchronous callbacks
    public interface VolleyCallback{
        void onSuccess(JSONObject result) throws JSONException;
    }

    //SUMMONER NAME -> SUMMONER INFO
    public void Summoner_name_to_info(final VolleyCallback callback, String name, String region){
        /*NEED TO SANITIZE THESE!!!!*/
        String url = "https://na.api.pvp.net/api/lol/" + region + "/v1.4/summoner/by-name/" + name + "?api_key=" + key;

        // Request a JSON response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            callback.onSuccess(response.getJSONObject(response.keys().next()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Not Working");
            }
        });

        //Queue the request
        NetworkController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    //SUMMONER ID -> SUMMONER MATCH HISTORY
    public void Summoner_id_to_match_history(final VolleyCallback callback, int id, String region){
        /*NEED TO SANITIZE THESE!!!!*/
        String url = "https://na.api.pvp.net/api/lol/" + region + "/v1.3/game/by-summoner/" + id + "/recent?api_key=" + key;

        // Request a JSON response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response){
                try{
                    callback.onSuccess(response);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                System.out.println("Not Working");
            }
        });

        //Queue the request
        NetworkController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
