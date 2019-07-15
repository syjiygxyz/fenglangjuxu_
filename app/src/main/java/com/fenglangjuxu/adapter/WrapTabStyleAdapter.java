package com.fenglangjuxu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.List;

/**
 * @author syj
 * @date 2019/7/6
 */
public class WrapTabStyleAdapter extends CommonNavigatorAdapter {
    private List<String> titles;

    private ViewPager ly_pages;

    private int Mode;

    public WrapTabStyleAdapter(List<String> titles, ViewPager ly_pages, int Mode) {
        this.titles = titles;
        this.ly_pages = ly_pages;
        this.Mode = Mode;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, int index) {
        SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
        simplePagerTitleView.setText(titles.get(index));
        simplePagerTitleView.setTextSize(12);
        simplePagerTitleView.getPaint().setFakeBoldText(true);
        simplePagerTitleView.setNormalColor(Color.parseColor("#B8B8B8"));
        simplePagerTitleView.setSelectedColor(Color.parseColor("#737373"));
        simplePagerTitleView.setTag(index);
        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.valueOf(v.getTag()+"");
                ly_pages.setCurrentItem(position);
            }
        });
        return simplePagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setMode(Mode);
        indicator.setLineHeight(UIUtil.dip2px(context, 2));
        indicator.setLineWidth(UIUtil.dip2px(context, 20));
        indicator.setRoundRadius(UIUtil.dip2px(context, 2));
        indicator.setStartInterpolator(new AccelerateInterpolator());
        indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
        indicator.setColors(Color.parseColor("#FA8D1E"));
        return indicator;
    }
}
