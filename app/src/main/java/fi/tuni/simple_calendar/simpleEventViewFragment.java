package fi.tuni.simple_calendar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class simpleEventViewFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.simple_event_view_fragment,container,false);

        //TODO: get current date list
        ArrayList<eventItem> eventItemsList = new ArrayList<>();
        eventItemsList.add(new eventItem(0,"asd","asd"));
        eventItemsList.add(new eventItem(1, "qwe", "qwe"));

        setList(eventItemsList);

        return view;
    }

    public void setList(ArrayList<eventItem> eventItemsList) {
        ArrayAdapter<eventItem> itemsAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, eventItemsList);
        ListView listView = view.findViewById(R.id.publicEventList);
        listView.setAdapter(itemsAdapter);
    }
}
