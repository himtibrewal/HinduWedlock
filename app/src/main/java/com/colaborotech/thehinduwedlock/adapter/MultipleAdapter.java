package com.colaborotech.thehinduwedlock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.models.MultipleModel;

import java.util.List;

/**
 * Created by Gokul on 9/2/2016.
 */
public class MultipleAdapter extends ArrayAdapter<MultipleModel> {
    int resource;
    List<MultipleModel> objects;
    Context context;
    ReturnMultipleView returnMultipleView;
    int from;

    public interface ReturnMultipleView {
        void getMultipleAdapterView(View view, List objects, int position, int from);
    }


    public MultipleAdapter(Context context, int resource, List<MultipleModel> objects, ReturnMultipleView returnMultipleView, int from) {
        super(context, resource, objects);
        this.resource = resource;
        this.context = context;
        this.objects = objects;
        this.returnMultipleView = returnMultipleView;
        this.from = from;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resource, parent, false);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
            viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView,
                                                     boolean isChecked) {
                            MultipleModel element = (MultipleModel) viewHolder.checkbox.getTag();
                            element.setSelected(buttonView.isChecked());

                        }
                    });
            view.setTag(viewHolder);
            viewHolder.checkbox.setTag(objects.get(position));
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).checkbox.setTag(objects.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.checkbox.setChecked(objects.get(position).isSelected());
        returnMultipleView.getMultipleAdapterView(view, objects, position, from);
        return view;
    }

    static class ViewHolder {
        protected CheckBox checkbox;
    }
}
