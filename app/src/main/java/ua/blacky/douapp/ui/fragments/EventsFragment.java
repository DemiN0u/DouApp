package ua.blacky.douapp.ui.fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import ua.blacky.douapp.Constants;
import ua.blacky.douapp.DouParser;
import ua.blacky.douapp.R;
import ua.blacky.douapp.models.Event;
import ua.blacky.douapp.ui.EventListAdapter;

public class EventsFragment extends Fragment {

    private RecyclerView mEventList;

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LinearLayout preloaderLL = (LinearLayout) view.findViewById(R.id.preloaderLL);
        mEventList = (RecyclerView) view.findViewById(R.id.eventList);
        mEventList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        getEventList.execute();
        preloaderLL.setVisibility(View.GONE);
    }

    private AsyncTask<Void, Void, List<Event>> getEventList = new AsyncTask<Void, Void, List<Event>>() {
        @Override
        protected List<Event> doInBackground(Void... params) {
            return DouParser.parse(Constants.ROOT_URL);
        }

        @Override
        protected void onPostExecute(List<Event> events) {
            mEventList.setAdapter(new EventListAdapter(events));
        }
    };
}
