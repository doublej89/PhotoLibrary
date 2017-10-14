package com.example.memyself.photolibrary;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.memyself.photolibrary.search.SearchResultActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends Fragment {

    public static final String TAGS_EXTRA = "TAGS_EXTRA";
    @BindView(R.id.editTagsSearch)
    EditText searchEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_search, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.search_btn)
    public void goToSearch() {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.putExtra(TAGS_EXTRA, searchEditText.getText().toString());
        startActivity(intent);
    }
}
