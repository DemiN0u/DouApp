package ua.blacky.douapp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ua.blacky.douapp.R;
import ua.blacky.douapp.models.Event;

/**
 * Created by amatsegor on 02.07.15.
 */
public final class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.Holder>{

    private List<Event> mEventList;

    public EventListAdapter(List<Event> eventList) {
        mEventList = eventList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, null);
        return new Holder(holderView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    protected class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }

    }
}
