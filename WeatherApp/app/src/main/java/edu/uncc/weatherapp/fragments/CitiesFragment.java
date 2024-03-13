package edu.uncc.weatherapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import edu.uncc.weatherapp.databinding.FragmentCitiesBinding;
import edu.uncc.weatherapp.models.City;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CitiesFragment extends Fragment {
    public CitiesFragment() {
        // Required empty public constructor
    }

    FragmentCitiesBinding binding;
//    CitiesAdapter adapter;
    ArrayAdapter<String> adapter;

    ArrayList<City> cities = new ArrayList<>();
    ArrayList<String> cityList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCitiesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, cityList);
        binding.listView.setAdapter(adapter);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.gotoWeatherForecast(cities.get(position));
            }
        });

        getCitites();

    }

    private final OkHttpClient client = new OkHttpClient();
    private void getCitites() {

        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/api/cities")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String body = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(body);
                        JSONArray jsonArray = jsonObject.getJSONArray("cities");
                        ArrayList<String> tempCityList = new ArrayList<>();
                        ArrayList<City> tempCities = new ArrayList<>();
                        for (int j = 0; j < jsonArray.length(); j++) {
                            JSONObject cityJsonObject = jsonArray.getJSONObject(j);
                            City city = new City(cityJsonObject);

                            String name = city.getName();
                            String state = city.getState();
                            String cityItem = name + ", " + state;
                            tempCityList.add(cityItem);
                            tempCities.add(city);

                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cityList.clear();
                                cities.clear();
                                cityList.addAll(tempCityList);
                                cities.addAll(tempCities);
                                adapter.notifyDataSetChanged();
                            }
                        });

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });

    }

    CityListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CityListener) context;
    }

    public interface CityListener{
        void gotoWeatherForecast(City city);
    }

}