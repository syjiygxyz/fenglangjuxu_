package com.fenglangjuxu.model.main.social;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.fenglangjuxu.BR;
import com.fenglangjuxu.R;
import com.fenglangjuxu.base.model.BaseListViewModel;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

/**
 * @author syj
 * @date 2019/6/17
 */
public class SocialImageItemMp extends BaseListViewModel {
    public static BindingRecyclerViewAdapter commentAdapter = new BindingRecyclerViewAdapter();
    public static ObservableList<CommentItemMp> commentList = new ObservableArrayList<>();
    public static ItemBinding commentItemBind = ItemBinding.of(BR.item, R.layout.item_social_comment);

    public SocialImageItemMp(Application context) {
        super(context);
    }

    @Override
    protected void loadData(int pageNo) {
        commentList.clear();
        commentList.add(new CommentItemMp(this, "孟思思", "今天天气很不错,很适合出去玩"));
        commentList.add(new CommentItemMp(this, "孟思思", "就很赞"));
        commentList.add(new CommentItemMp(this, "孟思思", "很赞"));
        commentList.add(new CommentItemMp(this, "孟思思", "赞"));
    }

    @Override
    protected void initViewMapping(OnItemBindClass onItemBindClass) {
        multipleItems.map(ImageItemMp.class, BR.item, R.layout.item_iamge);
    }

    @Override
    protected void initHeaderFooter(MergeObservableList headerFooterItems) {
        ObservableList<ImageItemMp> images = new ObservableArrayList<>();
        for (int i = 0; i < 9; i++){
            images.add(new ImageItemMp(this));
        }
        headerFooterItems.insertList(images);
    }

    @Override
    public void accept(Object o) throws Exception {

    }
}
