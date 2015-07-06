package ua.blacky.douapp.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ua.blacky.douapp.AdapterBinding;
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
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final AdapterBinding binding = DataBindingUtil.inflate(inflater, R.layout.event_list_item, parent, false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.mBinding.setEvent(mEventList.get(position));
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    protected class Holder extends RecyclerView.ViewHolder {

        private AdapterBinding mBinding;

        private Holder(AdapterBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

    }
}
