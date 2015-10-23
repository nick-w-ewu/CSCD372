package com.witmer.nicholas.npwitmerlab4;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by nicho on 10/22/2015.
 */
public class SevenSegment extends View
{
    private int curNum;
    private int[] curSegments;

    public SevenSegment(Context context)
    {
        super(context);
        initializeValues();
    }

    public SevenSegment(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initializeValues();
    }

    public SevenSegment(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initializeValues();
    }

    private void initializeValues()
    {
        this.curNum = 0;
        this.curSegments = new int[7];
    }
}
