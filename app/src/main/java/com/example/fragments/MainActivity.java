package com.example.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fragments.AdapterView.ActivityBase;
import com.example.fragments.AdapterView.NavigationAdapter;
import com.example.fragments.AdapterView.NavigationDelegate;
import com.example.fragments.AdapterView.NavigationState;
import com.example.fragments.Fragmentos.BlankFragmentPrimero;
import com.example.fragments.Fragmentos.BlankFragmentSegundo;
import com.example.fragments.databinding.ActivityMainBinding;

public class MainActivity extends ActivityBase implements  NavigationDelegate<Bundle>{

    private ActivityMainBinding viewDataBinding;
    private TextView txt;
    private NavigationAdapter navigationAdapter;
    private NavigationState<Integer> navigationState;
    private String agentName;
    private String version;
    private int currentFragmentPosition = -1;
    private boolean popPending;
    private boolean showTimelinePending;
    private boolean isPopInitialCapture;


    private androidx.viewpager.widget.ViewPager.OnPageChangeListener onPageChangeListener = new androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener(){
        @Override
        public void onPageSelected(final int position) {
            if (!popPending) {
                NavigationAdapter.Fragment fragment;
                fragment = navigationAdapter.getItem(position);

                if (!fragment.isBackEnabled()) {
                    viewDataBinding.navigationViewPager.setCurrentItem(currentFragmentPosition, true);
                } else {
                    currentFragmentPosition = position;
                }
                fragment.resumeFragment();
                fragment.setFragmentResumed(true);

                navigationAdapter.getItem(position).getLifecycle().addObserver(new DefaultLifecycleObserver() {
                    @Override
                    public void onResume(@NonNull LifecycleOwner owner) {
                        NavigationAdapter.Fragment fragment;
                        fragment = navigationAdapter.getItem(position);

                        if (!fragment.isFragmentResumed()) {
                            fragment.resumeFragment();
                            fragment.setFragmentResumed(true);
                        }
                    }
                });
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (popPending && state == ViewPager.SCROLL_STATE_IDLE) {
                navigationAdapter.popUpTo(currentFragmentPosition);
                if (!isPopInitialCapture){
                    //viewDataBinding.timelineView.setVisibility(View.GONE);
                }else {
                    isPopInitialCapture = false;
                }
                popPending = false;

                NavigationAdapter.Fragment fragment;
                fragment = navigationAdapter.getItem(currentFragmentPosition);

                if (fragment.isResettable()) {
                    fragment.reset();
                }
            } else if (showTimelinePending && state == ViewPager.SCROLL_STATE_IDLE) {
                //viewDataBinding.timelineView.setVisibility(View.VISIBLE);
                showTimelinePending = false;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        navigationAdapter = new NavigationAdapter(getSupportFragmentManager());
        navigationState = new NavigationState<>();

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewDataBinding.navigationViewPager.addOnPageChangeListener(onPageChangeListener);
        viewDataBinding.navigationViewPager.setAdapter(navigationAdapter);
        viewDataBinding.navigationViewPager.setVisibility(View.VISIBLE);
        //viewDataBinding.parent.requestFocus();
        //viewDataBinding.timelineView.setVisibility(View.GONE);
        viewDataBinding.btnPrimer.setOnClickListener(view -> {
            popPrimerFragmento();


        });

    }

    private void abrirPrimerFragmento(){}


    @Override
    public void popPrimerFragmento() {
        BlankFragmentPrimero primerFragment = new BlankFragmentPrimero();
        primerFragment.setNavigationDelegate(this);

        int fragmentPosition;
        fragmentPosition = navigationAdapter.pushFragment(primerFragment);
        navigationState.putState(NavigationState.PAGE_PRIMER_FRAGMENTO, fragmentPosition);

        //viewDataBinding.timelineView.setVisibility(View.GONE);
        //pushGreeting();

        onPageChangeListener.onPageSelected(0);
    }

    @Override
    public void popSegundoFragmento() {
        BlankFragmentSegundo fragment = new BlankFragmentSegundo();
        fragment.setNavigationDelegate(this);

        int fragmentPosition;
        fragmentPosition = navigationAdapter.pushFragment(fragment);

        showTimelinePending = true;

        navigationState.putState(NavigationState.PAGE_SEGUNDO_FRAGMENTO, fragmentPosition);
        viewDataBinding.navigationViewPager.setCurrentItem(fragmentPosition, true);

        //viewDataBinding.timelineView.setVisibility(View.VISIBLE);
        //viewDataBinding.timelineView.setCurrentPosition(TimelineView.POSITION_INITIAL_CAPTURE);
    }

    @Override
    public void popRegresarAlInicio() {
        int fragmentPosition;
        fragmentPosition = navigationState.getState(NavigationState.PAGE_PRIMER_FRAGMENTO);

        currentFragmentPosition = fragmentPosition;
        popPending = true;
        viewDataBinding.navigationViewPager.setCurrentItem(fragmentPosition, true);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}