package com.colaborotech.thehinduwedlock.utility;


import android.content.Context;
import android.content.res.TypedArray;
import android.text.Html;
import android.text.TextUtils;
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
        title.setTextSize(18f);
        title.setTextColor(getResources().getColor(R.color.red_dark));
        value = (TextView) view.findViewById(R.id.value);
        value.setText(s2);
        value.setTextSize(15f);
        mValue = getChildAt(1);
    }

    public CustomLayoutTitleValue(Context context) {
        this(context, null);
    }

    public void setValue(String va, int maxline) {
        value.setText(va);
        value.setMaxLines(maxline);

    }

    public void setValue(String va, int maxline, boolean htmlText) {
        if (htmlText) {
            value.setText(Html.fromHtml(va));
        } else {
            value.setText(va);
        }
        value.setEllipsize(TextUtils.TruncateAt.END);
        value.setMaxLines(maxline);
    }

    public void setValue(String va, boolean htmlText) {
        if (htmlText) {
            value.setText(Html.fromHtml(va));
        } else {
            value.setText(va);
        }
        value.setEllipsize(TextUtils.TruncateAt.END);

    }


    public String getValue() {
        return value.getText().toString();
    }

    public void setValue(String va) {
        value.setText(va);

    }
} 