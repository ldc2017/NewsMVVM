package com.ldc.newsmvvm.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

public class MainAdapter extends FragmentStatePagerAdapter {
    private String[] tabs;
    private ArrayList<SupportFragment> fragments;

    public MainAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public void setNewData(String[] tabs, ArrayList<SupportFragment> fragments) {
        this.tabs = tabs;
        this.fragments = new ArrayList<>(fragments);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return null == fragments ? 0 : fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
