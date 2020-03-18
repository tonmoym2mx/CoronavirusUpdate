package com.covid19v2mx.coronavirus.ui.global;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.covid19v2mx.coronavirus.R;
import com.covid19v2mx.coronavirus.databinding.FragmentGlobalBinding;

public class GlobalFragment extends Fragment {

    private GlobalViewModel globalViewModel;
    private FragmentGlobalBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        globalViewModel =
                new ViewModelProvider(getActivity()).get(GlobalViewModel.class);
        binding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_global,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
