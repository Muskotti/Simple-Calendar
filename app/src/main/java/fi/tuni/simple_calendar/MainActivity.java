package fi.tuni.simple_calendar;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements dateSelected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemSelected(final String year, final String month, final String dayOfMonth) {

        //TODO: get data from selected date

        AsyncTask async = new AsyncTask() {

            ArrayList<eventItem> eventItemsList = new ArrayList<>();
            simpleEventViewFragment ef = (simpleEventViewFragment) getSupportFragmentManager().findFragmentById(R.id.eventFragment);

            @Override
            protected Object doInBackground(Object[] objects) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://desolate-mesa-25154.herokuapp.com/db/" + year + "-" + month + "-" + dayOfMonth)
                        .build();

                Response response = null;

                try {
                    response = client.newCall(request).execute();

                    String jsonData = response.body().string();
                    JSONArray Jarray = new JSONArray(jsonData);

                    for (int i=0; i<Jarray.length(); i++) {
                        JSONObject actor = Jarray.getJSONObject(i);
                        String date = actor.getString("date");
                        String text = actor.getString("eventText");
                        String name = actor.getString("makerName");
                        String id = actor.getString("eventId");
                        eventItemsList.add(new eventItem(Integer.parseInt(id),name,text,date));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                ef.setList(eventItemsList);
                super.onPostExecute(o);
            }
        }.execute();
    }
}
