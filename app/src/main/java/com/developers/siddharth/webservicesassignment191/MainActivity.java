package com.developers.siddharth.webservicesassignment191;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import com.developers.siddharth.webservicesassignment191.adaptor.DataAdaptor;
import com.developers.siddharth.webservicesassignment191.models.DataHandler;
import com.developers.siddharth.webservicesassignment191.network.CallAddr;
import com.developers.siddharth.webservicesassignment191.network.NetworkStatus;
import com.developers.siddharth.webservicesassignment191.network.OnWebServiceResult;
import com.developers.siddharth.webservicesassignment191.utils.CommonUtilities;
import com.squareup.okhttp.FormEncodingBuilder;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnWebServiceResult {
    String url = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";
    List<DataHandler> model = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        hitRequest();
    }
    private void hitRequest(){
        FormEncodingBuilder parameters = new FormEncodingBuilder();
        parameters.add("page","1");

        if(NetworkStatus.getInstance(this).isConnectedToInternet()){
            CallAddr call = new CallAddr(this,url,parameters, CommonUtilities.SERVICE_TYPE.GET_DATA,this);
            call.execute();
        }else
        {
            Toast.makeText(this,"No Network ! You are offline.",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type) {
        Log.i("Response IS::","type ="+type+"XML is ::"+result);

        try{
            JSONObject obj= new JSONObject(result);
                JSONArray arr=obj.getJSONArray("main");
            for(int i=0;i<arr.length();i++){
                JSONObject jsonObject=arr.getJSONObject(i);
                DataHandler data= new DataHandler();

             //   data.setName(jsonObject.getString("name"));
            //    data.setCountry(jsonObject.getString("country"));
             //   data.setLat(jsonObject.getDouble("lat"));
             //   data.setSunrise(jsonObject.getDouble("sunrise"));
              //  data.setSunset(jsonObject.getDouble("sunset"));
               // data.setLon(jsonObject.getDouble("lon"));
              //  data.setVisibility(jsonObject.getDouble("visibility"));
                data.setTemp(jsonObject.getDouble("temp"));
                data.setPressure(jsonObject.getDouble("pressure"));
                data.setHumidity(jsonObject.getDouble("humidity"));
                data.setTemp_min(jsonObject.getDouble("temp_min"));
                data.setTemp_max(jsonObject.getDouble("temp_max"));

                model.add(data);
            }
           

            DataAdaptor adaptor = new DataAdaptor(this,model);
            recyclerView.setAdapter(adaptor);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
