package com.seriousface.m.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;

import com.seriousface.m.myapplication.R;
import com.seriousface.m.myapplication.activity.CameraFaceActivity;
import com.seriousface.m.myapplication.adapter.GridViewAdapter;

/**
 * Created by Administrator on 2016/4/14.
 */
public class FaceFragment extends Fragment implements AdapterView.OnItemClickListener{

    View contentView;

    GridView gridLayout;
    GridViewAdapter adapter;
    public static FaceFragment newInstant(Bundle bundle){
        FaceFragment fragment = new FaceFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_face_content,container,false);
        initView();
        return contentView;
    }

    private void initView(){
        gridLayout = (GridView)contentView.findViewById(R.id.gv_face);
        adapter = new GridViewAdapter(getActivity());
        gridLayout.setAdapter(adapter);
        gridLayout.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(getActivity(), CameraFaceActivity.class);
        getActivity().startActivity(i);
    }
}
