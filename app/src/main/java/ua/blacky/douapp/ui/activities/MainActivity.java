package ua.blacky.douapp.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ua.blacky.douapp.Constants;
import ua.blacky.douapp.DouParser;
import ua.blacky.douapp.R;
import ua.blacky.douapp.models.Event;
import ua.blacky.douapp.ui.EventListAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mEventList;

    private DouParser mParser = new DouParser(Constants.ROOT_URL);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEventList = (RecyclerView) findViewById(R.id.eventList);
        mEventList.setLayoutManager(new LinearLayoutManager(this));
        Event event = Event.newBuilder()
                .setName("Name")
                .setPictureUrl("http://s.developers.org.ua/CACHE/images/img/events/cyberbionicsyst_JZAlYbJ_l5Y4ITv/2e985562cad97db422f71126e799f235.png")
                .setAddress("Address")
                .setCity("Odessa")
                .setDate("6 липня, понеділок")
                .build();
        List<Event> eventList = mParser.getEventList();
        eventList.add(event);
        mEventList.setAdapter(new EventListAdapter(eventList));
    }
}
