
package com.rolmex.android.oa.widget;

import com.rolmex.android.emalltone.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class HomeMenuWidget extends FrameLayout {

    public HomeMenuWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater lif = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        lif.inflate(R.layout.widget_home_menu, this);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
