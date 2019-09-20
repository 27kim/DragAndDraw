package com.d27.draganddraw;

import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

abstract public class SingleFragmentActivity extends AppCompatActivity {
    abstract Fragment createFragment();

    @LayoutRes
    public int getLayoutResId(){
        return R.layout.activity_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getLayoutResId 사용
        setContentView(getLayoutResId());

        FragmentManager fragmentManager = getSupportFragmentManager();
        //id 는 activity_fragment 의 frame layout id
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if(fragment ==null){
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, createFragment())
                    .commit();
        }
    }


}
