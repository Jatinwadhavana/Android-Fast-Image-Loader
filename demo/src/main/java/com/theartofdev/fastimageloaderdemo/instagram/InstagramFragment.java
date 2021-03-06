// "Therefore those skilled at the unorthodox
// are infinite as heaven and earth,
// inexhaustible as the great rivers.
// When they come to an end,
// they begin again,
// like the days and months;
// they die and are reborn,
// like the four seasons."
//
// - Sun Tsu,
// "The Art of War"

package com.theartofdev.fastimageloaderdemo.instagram;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.theartofdev.fastimageloaderdemo.R;
import com.theartofdev.fastimageloaderdemo.instagram.service.Feed;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class InstagramFragment extends Fragment {

    private Adapter mAdapter;

    public InstagramFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_img_ix, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        mAdapter = new Adapter();
        recyclerView.setAdapter(mAdapter);

        loadData();

        return view;
    }

    private void loadData() {
        mAdapter.loadData(new Callback<Feed>() {
            @Override
            public void success(Feed feed, Response response) {
                Toast.makeText(InstagramFragment.this.getActivity(), "Loaded " + feed.data.length + " items", Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(InstagramFragment.this.getActivity(), "Failed to load data: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
