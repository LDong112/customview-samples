package com.sky.hyh.customviewsamples;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.sky.hyh.customviewsamples.Factory.BaseFragmentFactory;
import com.sky.hyh.customviewsamples.adapter.DataListAdapter;
import com.sky.hyh.customviewsamples.customview.CustomEmojiPanel;
import com.sky.hyh.customviewsamples.fragment.BaseFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView mRvList = findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvList.setLayoutManager(layoutManager);
        DataListAdapter dataListAdapter = new DataListAdapter(this, new DataListAdapter.OnClickCallBack() {
            @Override
            public void onClick(BaseFragmentFactory baseFragmentFactory) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                BaseFragment fragment = baseFragmentFactory.createFragment();
                fragment.setOnDestroyCallBack(new BaseFragment.OnDestroyCallBack() {
                    @Override
                    public void onDestroy() {
                        mRvList.setVisibility(View.VISIBLE);
                    }
                });
                transaction.replace(R.id.fl_fragment_container, fragment);
                //添加到返回栈中，使得点击返回键会回到当前activity
                transaction.addToBackStack(null);
                transaction.commit();
                Log.d("hyh", "MainActivity: onClick ");
                mRvList.setVisibility(View.GONE);
            }
        });
        mRvList.setAdapter(dataListAdapter);

    }
}
