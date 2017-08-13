package com.colaborotech.thehinduwedlock.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.adapter.MultipleAdapter;
import com.colaborotech.thehinduwedlock.adapter.OneAdapter;
import com.colaborotech.thehinduwedlock.models.MultipleModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Gokul on 9/14/2016.
 */
public class SliderFragment extends Fragment implements OneAdapter.ReturnView, MultipleAdapter.ReturnMultipleView, View.OnClickListener {
    ListView listView;
    private static SliderFragment sliderFragment;

    private RelativeLayout sliderfragment_relativelayout;
    private Button btn_done;
    private ListView multiple_lists;

    ReturnView returnView;
    ReturnMultipleView returnMultipleView;
    ReturnDone returnDone;

    TextView title_slider;

    List<?> list_multiple;
    int multiple_selection = 0;

    @Override
    public void getMultipleAdapterView(View view, List objects, int position, int from) {
        returnMultipleView.getMultipleAdapterView(view, objects, position, from);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_done:
                List<MultipleModel> multipleModelList = new ArrayList<>();
                for (int i = 0; i < list_multiple.size(); i++) {
                    MultipleModel multipleModel = (MultipleModel) list_multiple.get(i);
                    if (multipleModel.isSelected()) {
                        multipleModelList.add(multipleModel);
                    }
                }
                returnDone.selectedMultipleModelList(multipleModelList, multiple_selection);
                break;
        }
    }

    public interface ReturnMultipleView {
        void getMultipleAdapterView(View view, List Objects, int position, int from);
    }

    public interface ReturnDone {
        void selectedMultipleModelList(List<MultipleModel> multipleModelList, int selection);
    }


    public interface ReturnView {
        void getAdapterView(View view, List Objects, int position, int from);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_slider_fragment, container, false);
        sliderFragment = this;
        listView = (ListView) view.findViewById(R.id.list_slider);
        title_slider = (TextView) view.findViewById(R.id.title_slider);

        sliderfragment_relativelayout = (RelativeLayout) view.findViewById(R.id.sliderfragment_relativelayout);
        btn_done = (Button) view.findViewById(R.id.btn_done);
        multiple_lists = (ListView) view.findViewById(R.id.multiple_lists);
        sliderfragment_relativelayout.setVisibility(View.GONE);

        multiple_lists.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        btn_done.setOnClickListener(this);
        return view;
    }


    public static SliderFragment getInstance() {
        return sliderFragment;
    }

    public void setLists(List list, int selection, String title) {
        if (list != null)
            listView.setAdapter(new OneAdapter(getActivity(), R.layout.layout_slider_item, list, this, selection));
        title_slider.setText(title);
        listView.setVisibility(View.VISIBLE);
        sliderfragment_relativelayout.setVisibility(View.GONE);
    }


    public void setMultiple_lists(List list, int selection, String title) {
        this.list_multiple = list;
        this.multiple_selection = selection;
        title_slider.setText(title);
        if (list != null) {
            multiple_lists.setAdapter(new MultipleAdapter(getActivity(), R.layout.layout_spinner_item, list, this, selection));
        }
        listView.setVisibility(View.GONE);
        sliderfragment_relativelayout.setVisibility(View.VISIBLE);
    }

    public void setReturnView(ReturnView returnView) {
        this.returnView = returnView;
    }


    @Override
    public void getAdapterView(View view, List objects, int position, int from) {
        returnView.getAdapterView(view, objects, position, from);
    }

    public void setReturnMultipleView(ReturnMultipleView returnMultipleView) {
        this.returnMultipleView = returnMultipleView;
    }

    public void setReturnDone(ReturnDone returnDone) {
        this.returnDone = returnDone;
    }
}
