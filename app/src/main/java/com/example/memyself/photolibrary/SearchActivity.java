package com.example.memyself.photolibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.memyself.photolibrary.search.SearchResultActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    public static final String TAGS_EXTRA = "TAGS_EXTRA";
    @BindView(R.id.editTagsSearch)
    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.search_button)
    public void goToSearch() {
        Intent intent = new Intent(this, SearchResultActivity.class);
        intent.putExtra(TAGS_EXTRA, searchEditText.getText().toString());
        startActivity(intent);
    }
}
