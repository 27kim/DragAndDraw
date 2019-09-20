package com.d27.draganddraw;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

class DragAndDrawFragment extends Fragment {
    public static DragAndDrawFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DragAndDrawFragment fragment = new DragAndDrawFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
