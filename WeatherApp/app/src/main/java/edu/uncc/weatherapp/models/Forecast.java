package edu.uncc.weatherapp.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Forecast {

    String startTime;
    String shortForecast;
    String windSpeed;
    String temperatureUnit;
    String humidityUnit;

    String iconURL;
    int relativeHumidity, temperature;




    public Forecast(JSONObject jsonObject) throws JSONException {
        /*
        * "number": 1,
                "name": "Tonight",
                "startTime": "2024-03-11T21:00:00-04:00",
                "endTime": "2024-03-12T06:00:00-04:00",
                "isDaytime": false,
                "temperature": 36,
                "temperatureUnit": "F",
                "temperatureTrend": null,
                "probabilityOfPrecipitation": {
                    "unitCode": "wmoUnit:percent",
                    "value": null
                },
                "dewpoint": {
                    "unitCode": "wmoUnit:degC",
                    "value": 0
                },
                "relativeHumidity": {
                    "unitCode": "wmoUnit:percent",
                    "value": 73
                },
                "windSpeed": "5 mph",
                "windDirection": "SW",
                "icon": "https://api.weather.gov/icons/land/night/few?size=medium",
                "shortForecast": "Mostly Clear",
                "detailedForecast": "Mostly clear, with a low around 36. Southwest wind around 5 mph."
        *
        * */
        this.startTime = jsonObject.getString("startTime");

        JSONObject relativeHumidityObj = jsonObject.getJSONObject("relativeHumidity");
        this.relativeHumidity = relativeHumidityObj.getInt("value");
        this.humidityUnit = relativeHumidityObj.getString("unitCode");

        this.shortForecast = jsonObject.getString("shortForecast");
        this.windSpeed = jsonObject.getString("windSpeed");
        this.temperature = jsonObject.getInt("temperature") ;
        // include unit
        this.iconURL = jsonObject.getString("icon");
        this.temperatureUnit = jsonObject.getString("temperatureUnit");

    }

    public Forecast(String startTime, String shortForecast, String windSpeed, String iconURL, int relativeHumidity, int temperature) {
        this.startTime = startTime;
        this.shortForecast = shortForecast;
        this.windSpeed = windSpeed;
        this.iconURL = iconURL;
        this.relativeHumidity = relativeHumidity;
        this.temperature = temperature;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(int relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public String getShortForecast() {
        return shortForecast;
    }

    public void setShortForecast(String shortForecast) {
        this.shortForecast = shortForecast;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }



    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public String getHumidityUnit() {
        return humidityUnit;
    }

    public void setHumidityUnit(String humidityUnit) {
        this.humidityUnit = humidityUnit;
    }
}
