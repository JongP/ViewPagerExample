package com.example.viewpagerexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragWensday extends Fragment {
    private View view;

    //상태 저장하기
    public static FragWensday newInstance() {
        FragWensday fragWensday = new FragWensday();
        return fragWensday;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_wensday, container, false);

        return view;
    }
}
