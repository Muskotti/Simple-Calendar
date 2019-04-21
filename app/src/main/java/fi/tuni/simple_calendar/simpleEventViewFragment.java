package fi.tuni.simple_calendar;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class simpleEventViewFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.simple_event_view_fragment,container,false);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        final String newDate = dateFormat.format(cal.getTime());

        Button button = (Button) view.findViewById(R.id.addEvent);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try {
                    Intent i = new Intent(getActivity(), addEvent.class);
                    startActivity(i);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        AsyncTask async = new AsyncTask() {

            ArrayList<eventItem> eventItemsList = new ArrayList<>();

            @Override
            protected Object doInBackground(Object[] objects) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://desolate-mesa-25154.herokuapp.com/db/" + newDate)
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
                if(eventItemsList != null && !eventItemsList.isEmpty()) {
                    setList(eventItemsList);
                } else {
                    setViewEmpty();
                }
                super.onPostExecute(o);
            }
        }.execute();

        return view;
    }

    public void setList(ArrayList<eventItem> eventItemsList) {
        ArrayAdapter<eventItem> itemsAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, eventItemsList);
        ListView listView = view.findViewById(R.id.publicEventList);
        listView.setAdapter(itemsAdapter);
    }

    public void setViewEmpty() {
        ArrayAdapter<eventItem> itemsAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, new ArrayList<eventItem>());
        ListView listView = view.findViewById(R.id.publicEventList);
        listView.setAdapter(itemsAdapter);
    }
}
