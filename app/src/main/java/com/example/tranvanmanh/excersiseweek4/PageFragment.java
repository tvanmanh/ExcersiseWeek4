package com.example.tranvanmanh.excersiseweek4;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tranvanmanh on 3/27/2018.
 */

public class PageFragment extends android.support.v4.app.Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    private static ArrayList<Movie> list;
    String movieData;
    private RecyclerView recyclerView;

    private SwipeRefreshLayout swipeContainer;


    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_listmovie);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        String url = "";
        if(mPage==1)
        {
            url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        }
        else
            url = "https://api.themoviedb.org/3/movie/top_rated?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        new GetJsonFromWeb().execute(url);
        return view;
    }
    class GetJsonFromWeb extends AsyncTask<String, String, String> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... strings) {
            Request request = new Request.Builder().url(strings[0]).build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!s.equals("")) {
                movieData = s;
                //  Log.e("abc", movieData);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);

                recyclerView.setHasFixedSize(true);
                list = new ArrayList<Movie>();
                Gson gson = new Gson();
                Type listType = new TypeToken<Profile>() {
                }.getType();
                Profile profiles = gson.fromJson(movieData, listType);
                List<Result> results = profiles.getResults();
                for (int i = 0; i < results.size(); i++) {

                    list.add(new Movie(results.get(i).getReleaseDate(), results.get(i).getVoteAverage(), results.get(i).getTitle(), results.get(i).getOverview(), "https://image.tmdb.org/t/p/w500" + results.get(i).getPosterPath()));

                }
                final MovieAdapter adapter = new MovieAdapter(getActivity(), list);
                final MovieAdapter adapter1 = adapter;
                recyclerView.setAdapter(adapter);

                // pull to refresh
                // Lookup the swipe container view
                // Setup refresh listener which triggers new data loading
                swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // Your code to refresh the list here.
                        // Make sure you call swipeContainer.setRefreshing(false)
                        // once the network request has completed successfully.
                        Toast.makeText(getActivity(), "Refresh RecyclerView", Toast.LENGTH_SHORT).show();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        swipeContainer.setRefreshing(false);
                    }
                });
                // Configure the refreshing colors
                swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light);


        } else {
                Toast.makeText(getActivity(), "error loading url", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
