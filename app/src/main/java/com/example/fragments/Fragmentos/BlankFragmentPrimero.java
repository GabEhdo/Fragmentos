package com.example.fragments.Fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragments.AdapterView.NavigationAdapter;
import com.example.fragments.R;
import com.example.fragments.databinding.FragmentBlankPrimeroBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragmentPrimero#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentPrimero extends NavigationAdapter.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentBlankPrimeroBinding viewDataBinding;

    View.OnClickListener segundoFragment = (view) -> navigationDelegate.popSegundoFragmento();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragmentPrimero() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragmentPrimero.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragmentPrimero newInstance(String param1, String param2) {
        BlankFragmentPrimero fragment = new BlankFragmentPrimero();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank_primero, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewDataBinding = DataBindingUtil.bind(view);
        viewDataBinding.btnSegundoFragmento.setOnClickListener(segundoFragment);
    }
}