package com.android.chaowen.yesmycode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.chaowen.yesmycode.Activity.CustomerDialogActivity;
import com.android.chaowen.yesmycode.Activity.SpinnerTest;
import com.android.chaowen.yesmycode.widget.DividerItemDecoration;
import com.android.chaowen.yesmycode.widget.RecyclerItemClickListener;


/**
 * Created by Chenyc on 15/6/27.
 */
public class ExampleFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private String[] myDataset;
    private MyAdapter mAdapter;
    private RecyclerItemClickListener.OnItemClickListener onItemClickListener = new RecyclerItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent = null;
            switch (position) {
                case 0:
                    ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new GoogleDemoFragment()).commit();
                    break;
                case 1:
                    intent = new Intent(getActivity(), SpinnerTest.class);
                    startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(getActivity(), CustomerDialogActivity.class);
                    startActivity(intent);
                    break;

            }


        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_example, null);

        //RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), onItemClickListener));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // specify an adapter (see also next example)
        myDataset = new String[]{"Google控件示例", "自定义Spinner控件","自定义对话框"
        };
        mAdapter = new MyAdapter(getActivity(), myDataset);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private final int mBackground;
        private final TypedValue mTypedValue = new TypedValue();
        private String[] mDataset;

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(Context context, String[] myDataset) {
            mDataset = myDataset;
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_view, parent, false);
            v.setBackgroundResource(mBackground);
            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(mDataset[position]);

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextView;

            public int position;

            public ViewHolder(View v) {
                super(v);
                mTextView = (TextView) v.findViewById(R.id.textView);
            }
        }
    }


}
