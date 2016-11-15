package com.pixis.traktTV.views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.pixis.traktTV.R;

/**
 * Created by Dan on 11/9/2016.
 */

public class AdvancedRecyclerView extends LinearLayout {

    private RecyclerView recyclerView;
    private View emptyView;
    private boolean isDataView = true;

    public AdvancedRecyclerView(Context context) {
        super(context);
        init();
    }

    public AdvancedRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AdvancedRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AdvancedRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    void init() {
        if (isInEditMode()) {
            return;
        }

        View v = LayoutInflater.from(getContext()).inflate(R.layout.advanced_recyclerview, this, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        emptyView = v.findViewById(R.id.emptyView);

        //Init recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        addView(v);
    }

    public void setAdapter(final RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if(adapter.getItemCount() == 0) {
                    showEmptyView();
                }
                else {
                    showDataView();
                }
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
                super.onItemRangeChanged(positionStart, itemCount, payload);
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            }
        });
    }

    private void showEmptyView() {
        if(isDataView) {
            emptyView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            isDataView = false;
        }
    }

    private void showDataView() {
        if(!isDataView) {
            emptyView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            isDataView = true;
        }
    }

}
