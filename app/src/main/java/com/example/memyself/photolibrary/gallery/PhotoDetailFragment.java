package com.example.memyself.photolibrary.gallery;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.memyself.photolibrary.R;
import com.example.memyself.photolibrary.gallery.dummy.DummyContent;

/**
 * A fragment representing a single Photo detail screen.
 * This fragment is either contained in a {@link PhotoListActivity}
 * in two-pane mode (on tablets) or a {@link PhotoDetailActivity}
 * on handsets.
 */
public class PhotoDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private String imageUrl;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PhotoDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            imageUrl = getArguments().getString(ARG_ITEM_ID);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.photo_detail, container, false);
        ImageView imageView = rootView.findViewById(R.id.displayImage);

        // Show the dummy content as text in a TextView.
        if (imageUrl != null) {
            Glide.with(getActivity())
                    .load(imageUrl)
                    .into(imageView);
        }

        return rootView;
    }
}
