package com.example.japp.ui.fragment.active_jobs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.japp.Utils.LocaleHelper;
import com.example.japp.adapter.ActiveAdapter;
import com.example.japp.databinding.FragmentActiveJobsBinding;
import com.example.japp.model.Job;

import java.util.ArrayList;

public class ActiveJobsFragment extends Fragment {
    private FragmentActiveJobsBinding binding;
    public ActiveJobsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentActiveJobsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActiveViewModel viewModel = new ViewModelProvider(this).get(ActiveViewModel.class);

        LocaleHelper.setLocale(binding.getRoot().getContext(), LocaleHelper.getLanguage(binding.getRoot().getContext()));

        viewModel.getActiveJobs(getContext());

        viewModel.jobs.observe(getViewLifecycleOwner(), list -> {
            if (!list.isEmpty()) {
                binding.ivNotFound.setVisibility(View.GONE);
                binding.tvNotFound.setVisibility(View.GONE);
                binding.rvJobs.setVisibility(View.VISIBLE);
                binding.rvJobs.setAdapter(new ActiveAdapter(list));
            } else {
                binding.ivNotFound.setVisibility(View.VISIBLE);
                binding.tvNotFound.setVisibility(View.VISIBLE);
                binding.rvJobs.setVisibility(View.GONE);
            }
        });
    }
}