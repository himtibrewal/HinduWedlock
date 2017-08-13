package com.colaborotech.thehinduwedlock.utility;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;


public class CustomLayoutTitleValue extends LinearLayout {
    private View mValue;
    private TextView value;

    public CustomLayoutTitleValue(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Options, 0, 0);
        String s1 = a.getString(R.styleable.Options_heading);
        String s2 = a.getString(R.styleable.Options_value);

        a.recycle();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_custom_title_value, this, true);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(s1);
        value = (TextView) view.findViewById(R.id.value);
        value.setText(s2);

        mValue = getChildAt(1);
    }

    public CustomLayoutTitleValue(Context context) {
        this(context, null);
    }

    public void setValue(String va) {
        value.setText(va);
    }

    public String getValue() {
        return value.getText().toString();
    }
} 