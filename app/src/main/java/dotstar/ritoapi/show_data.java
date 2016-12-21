package dotstar.ritoapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import dotstar.ritoapi.Riot_Api;
import dotstar.ritoapi.NetworkController;

public class show_data extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        //Add the button Create function
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Declare and init the API
                final Riot_Api api = new Riot_Api();

                api.Summoner_name_to_info(new Riot_Api.VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject result) throws JSONException {
                        api.Summoner_id_to_match_history(new Riot_Api.VolleyCallback(){
                            @Override
                            public void onSuccess(JSONObject result) throws JSONException {
                                System.out.println(result);
                                TextView tv = (TextView)findViewById(R.id.data);
                                tv.setText(result.toString());
                            }
                        }, result.getInt("id"), "na");
                    }
                }, ((TextView)findViewById(R.id.name)).getText().toString(), "na");
            }
        });
    }


}
