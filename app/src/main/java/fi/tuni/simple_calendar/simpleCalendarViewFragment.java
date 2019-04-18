package fi.tuni.simple_calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;


public class simpleCalendarViewFragment extends Fragment {

    private dateSelected callback;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_view,container,false);
        CalendarView calendarView = view.findViewById(R.id.simpleCalendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String newYear = "" + year;
                String newMonth = "00";
                String date = "00";

                if(dayOfMonth < 10) {
                    date = "0" + dayOfMonth;
                } else {
                    date = "" + dayOfMonth;
                }

                int i = month;
                i++;

                if(month < 10) {
                    newMonth = "0" + i;
                } else {
                    newMonth = "" + i;
                }

                callback.itemSelected(newYear, newMonth, date);
            }
        });
        callback = (dateSelected) getActivity();
        return view;
    }
}
