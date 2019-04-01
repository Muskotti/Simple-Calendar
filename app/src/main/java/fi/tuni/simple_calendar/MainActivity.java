package fi.tuni.simple_calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements dateSelected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemSelected(int year, int month, int dayOfMonth) {
        simpleEventViewFragment ef = (simpleEventViewFragment) getSupportFragmentManager().findFragmentById(R.id.eventFragment);

        //TODO: get selected days items
        ArrayList<eventItem> eventItemsList = new ArrayList<>();
        eventItemsList.add(new eventItem(0,"qweqweqwe","qweqweqwe"));
        eventItemsList.add(new eventItem(1, "qweqweqweqweqw", "qweewqqweqwewqeqe"));

        ef.setList(eventItemsList);
    }
}
