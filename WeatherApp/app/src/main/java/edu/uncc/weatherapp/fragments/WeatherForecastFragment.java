package edu.uncc.weatherapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import edu.uncc.weatherapp.databinding.ForecastListItemBinding;
import edu.uncc.weatherapp.databinding.FragmentWeatherForecastBinding;
import edu.uncc.weatherapp.models.City;
import edu.uncc.weatherapp.models.Forecast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WeatherForecastFragment extends Fragment {
    private static final String ARG_PARAM_CITY = "ARG_PARAM_CITY";
    private City mCity;
    ArrayList<Forecast> forecasts = new ArrayList<>();

    public WeatherForecastFragment() {
        // Required empty public constructor
    }

    public static WeatherForecastFragment newInstance(City city) {
        WeatherForecastFragment fragment = new WeatherForecastFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_CITY, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCity = (City) getArguments().getSerializable(ARG_PARAM_CITY);
        }
    }

    FragmentWeatherForecastBinding binding;
    ForecastAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeatherForecastBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ForecastAdapter();
        binding.recyclerView.setAdapter(adapter);
        if(mCity!= null) {
            binding.textViewCityName.setText(mCity.getName() + ", " + mCity.getState());
        }

        getWeather();
    }

    private final OkHttpClient client = new OkHttpClient();

    void getWeather() {
        String url = "https://api.weather.gov/points/" + mCity.getLat() + "," + mCity.getLng();
        HttpUrl urlHttp = HttpUrl.parse(url).newBuilder()
                .build();

        Request request = new Request.Builder()
                .url(url)
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
                        JSONObject propertiesJsonObject = jsonObject.getJSONObject("properties");
                        String tempUrl = propertiesJsonObject.getString("forecast");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getForecast(tempUrl);
                            }
                        });

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });
    }

    void getForecast(String tempUrl) {
        Request request = new Request.Builder()
                .url(tempUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String body = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(body);
                        JSONObject propertiesJsonObject = jsonObject.getJSONObject("properties");
                        JSONArray periodJsonArray = propertiesJsonObject.getJSONArray("periods");
                        for (int i = 0; i < periodJsonArray.length(); i++) {
                            JSONObject forecastJsonObject = periodJsonArray.getJSONObject(i);
                            Forecast forecast = new Forecast(forecastJsonObject);
                            forecasts.add(forecast);
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
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

    class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>{
        @NonNull
        @Override
        public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ForecastListItemBinding mBinding = ForecastListItemBinding.inflate(getLayoutInflater(), parent, false);
            return new ForecastViewHolder(mBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
            Forecast forecast = forecasts.get(position);
            holder.setupUI(forecast);

        }

        @Override
        public int getItemCount() {
            return forecasts.size();
        }

        class ForecastViewHolder extends RecyclerView.ViewHolder{
            ForecastListItemBinding mBinding;
            Forecast mForecast;
            public ForecastViewHolder(ForecastListItemBinding binding) {
                super(binding.getRoot());
                mBinding = binding;
            }


            public void setupUI(Forecast forecast){
                String unit = "";
                if (forecast.getHumidityUnit().contains("percent")) {
                    unit = "%";
                }

                mBinding.textViewDateTime.setText(forecast.getStartTime());
                mBinding.textViewHumidity.setText(String.valueOf(forecast.getRelativeHumidity()) + unit);
                mBinding.textViewTemperature.setText(String.valueOf(forecast.getTemperature()) + " " +forecast.getTemperatureUnit());
                mBinding.textViewWindSpeed.setText(forecast.getWindSpeed());
                mBinding.textViewForecast.setText(forecast.getShortForecast());

                mForecast = forecast;

                Picasso.get().load(mForecast.getIconURL()).into(mBinding.imageView);

            }
        }
    }
}