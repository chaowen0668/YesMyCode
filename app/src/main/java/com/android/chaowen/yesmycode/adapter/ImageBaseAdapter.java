package com.android.chaowen.yesmycode.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

public abstract class ImageBaseAdapter<T> extends BaseAdapter {
    protected final String TAG = getClass().getSimpleName();
    // adapter中的内部点击事件
    public Map<Integer, onInternalClickListener> canClickItem;


    protected LayoutInflater mInflater;
    private List<T> mItems;

    public ImageBaseAdapter(Context context, List<T> collection, boolean hasImageLoader) {
        mItems = collection;
        mInflater = LayoutInflater.from(context);
    }

    public ImageBaseAdapter(Context context, List<T> collection) {
        this(context, collection, false);
    }

    public void addItem(T object) {
        mItems.add(object);
        notifyDataSetChanged();
    }

    public void addItems(List<T> items) {
        if (items == null || items.isEmpty()) {
            return;
        }

        if (mItems == null) {
            mItems = items;
        } else {
            mItems.addAll(items);
        }

        notifyDataSetChanged();
    }

    public void removeItem(T object) {
        if (mItems != null) {
            mItems.remove(object);
            notifyDataSetChanged();
        }
    }

    public T removeItem(int position) {
        if (mItems != null) {
            T removedItem = mItems.remove(position);
            notifyDataSetChanged();
            return removedItem;
        }

        return null;
    }

    /**
     * Removes all items from the list
     */
    public void clear() {
        if (mItems != null) {
            mItems.clear();
            notifyDataSetChanged();
        }
    }

    public List<T> getAllItems() {
        return mItems;
    }

    @Override
    public int getCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public T getItem(int position) {
        return mItems != null ? mItems.get(position) : null;
    }




    public ArrayList<T> retainItems() {
        return new ArrayList<T>(mItems);
    }

    public abstract View bindView(int position, View convertView,
                                  ViewGroup parent);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = bindView(position, convertView, parent);
        // 绑定内部点击监听
        addInternalClickListener(convertView, position, getAllItems().get(position));
        return convertView;
    }

    private void addInternalClickListener(final View itemV, final Integer position, final Object valuesMap) {
        if (canClickItem != null) {
            for (Integer key : canClickItem.keySet()) {
                View inView = itemV.findViewById(key);
                final onInternalClickListener inviewListener = canClickItem.get(key);
                if (inView != null && inviewListener != null) {
                    inView.setOnClickListener(new OnClickListener() {

                        public void onClick(View v) {
                            inviewListener.OnClickListener(itemV, v, position,
                                    valuesMap);
                        }
                    });
                }
            }
        }
    }

    public void setOnInViewClickListener(Integer key,
                                         onInternalClickListener onClickListener) {
        if (canClickItem == null)
            canClickItem = new HashMap<Integer, onInternalClickListener>();
        canClickItem.put(key, onClickListener);
    }

    public interface onInternalClickListener {
        public void OnClickListener(View parentV, View v, Integer position,
                                    Object values);
    }

    public abstract static class ImgurViewHolder {
        public ImgurViewHolder(View view) {
            ButterKnife.inject(this, view);
            view.setTag(this);
        }
    }

}
