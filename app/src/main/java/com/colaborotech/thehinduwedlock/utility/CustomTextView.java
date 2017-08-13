package com.colaborotech.thehinduwedlock.utility;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;

/**
 * Created by him on 10-Jun-17.
 */

public class CustomTextView extends LinearLayout {

    TextView value;
    View mValue;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Options, 0, 0);
       // String s1 = a.getString(R.styleable.Options_heading);
        String s2 = a.getString(R.styleable.Options_value);

        a.recycle();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_custom_textinput, this, true);
      //  TextView title = (TextView) view.findViewById(R.id.titvaluele);
        //title.setText(s1);
        value = (TextView) view.findViewById(R.id.value);
        value.setText(s2);
        mValue = getChildAt(1);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setValue(String va) {
        value.setText(va);
    }

    public String getValue() {
        return value.getText().toString();
    }


}
