package com.example.scan;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    EditText ed_id;
    ImageView im_alert;
    TextView tv_sync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);


        ed_id = findViewById(R.id.ed_ID);
        im_alert = findViewById(R.id.im_alert);
        tv_sync = findViewById(R.id.tv_sync);
        tv_sync.setText(SharedPreferencesHelper.getData(this, "sync"));
        tv_sync.setTextColor(Color.parseColor("red"));
//        if (SharedPreferencesHelper.getBoolean(this, "first", true)) {
//
//
//            try {
//                JSONObject obj = new JSONObject(loadJSONFromAsset());
//                JSONArray idArray = obj.getJSONArray("Hrietan");
//                for (int i = 0; i < idArray.length(); i++) {
//                    JSONObject jsonObject = idArray.getJSONObject(i);
//                    System.out.println(jsonObject.getString("id"));
//                    dbHelper.insertID(jsonObject.getString("id"));
//                }
//                SharedPreferencesHelper.saveBoolean(this, "first", false);
//
//            } catch (JSONException | IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void Check(View view) {
        if (!dbHelper.getData(ed_id.getText().toString()).isEmpty()) {
            Toast.makeText(this, "the Family Exist", Toast.LENGTH_SHORT).show();
            im_alert.setImageResource(R.drawable.sucess);
            new Handler().postDelayed(this::removeImage, 5000);
        } else {
            Toast.makeText(this, "Family need to scan", Toast.LENGTH_SHORT).show();
            im_alert.setImageResource(R.drawable.error);
            new Handler().postDelayed(this::removeImage, 5000);

        }
    }


    public String loadJSONFromAsset() throws IOException {
        String json = null;
        InputStream is = getAssets().open("HrietanScan.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");
        return json;
    }

    public void removeImage() {
        im_alert.setImageResource(R.drawable.logo_ihsan);
    }

    public void Sync(View view) {


        ApiInterface apiInterface = ApiClient.getAppClient().create(ApiInterface.class);
        ArrayList<Id> ids=new ArrayList<>();
        Call<ArrayList<Id>> call = apiInterface.getId();
            call.enqueue(new Callback<ArrayList<Id>>() {
                @Override
                public void onResponse(Call<ArrayList<Id>> call, Response<ArrayList<Id>> response) {
                    ArrayList<Id> ids=response.body();
                    for (int i = 0; i < ids.size(); i++) {
                        try{dbHelper.insertID(ids.get(i).getId());}
                        catch (Exception e){
                            System.out.println(e);

                        }
                    }


                    SharedPreferencesHelper.saveData(MainActivity.this,"sync", Calendar.getInstance().getTime().toString().substring(0,20));
                    tv_sync.setText(Calendar.getInstance().getTime().toString().substring(0,20));
                    tv_sync.setTextColor( getResources().getColor(R.color.purple_200));


                }

                @Override
                public void onFailure(Call<ArrayList<Id>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Please check the wifi connection", Toast.LENGTH_SHORT).show();
                    System.out.println(t.toString());
                }
            });

    }


}