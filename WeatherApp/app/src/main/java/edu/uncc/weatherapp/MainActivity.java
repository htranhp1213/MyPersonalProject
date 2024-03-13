package edu.uncc.weatherapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.uncc.weatherapp.fragments.CitiesFragment;
import edu.uncc.weatherapp.fragments.WeatherForecastFragment;
import edu.uncc.weatherapp.models.City;

public class MainActivity extends AppCompatActivity implements CitiesFragment.CityListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new CitiesFragment())
                .commit();

    }


    @Override
    public void gotoWeatherForecast(City city) {
        WeatherForecastFragment weatherForecastFragment = WeatherForecastFragment.newInstance(city);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, weatherForecastFragment)
                .addToBackStack(null)
                .commit();

    }
}