package fi.tuni.simple_calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class addEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_view);
    }

    public void addEventBackend(View view) {
        Intent intent = getIntent();

        EditText eventNameEdit = (EditText)findViewById(R.id.eventName);
        EditText eventTextEdit = (EditText)findViewById(R.id.eventText);
        DatePicker dateText = findViewById(R.id.DatePicker);
        String name = eventNameEdit.getText().toString();
        String text = eventTextEdit.getText().toString();
        String month;
        String day;

        int j = dateText.getMonth() + 1;
        if(j < 10) {
            month = "0" + j;
        } else {
            month = "" + j;
        }

        if(dateText.getDayOfMonth() < 10) {
            day = "0" + dateText.getDayOfMonth();
        } else {
            day = "" + dateText.getDayOfMonth();
        }

        String date = dateText.getYear() + "-" + month + "-" + day;

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        JSONObject json = new JSONObject();
        try {
            json.put("date", date);
            json.put("makerName",name);
            json.put("eventText", text);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(JSON, json.toString());
        Request request = new Request.Builder()
                .url("https://desolate-mesa-25154.herokuapp.com/db/addEvent")
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String mMessage = response.body().string();
                Log.e("onResponse", mMessage);
            }
        });

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
