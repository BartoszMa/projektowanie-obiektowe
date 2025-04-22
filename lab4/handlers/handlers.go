package handlers

import (
	"encoding/json"
	"github.com/labstack/echo"
	"net/http"
)

func WeatherHandler(c echo.Context) error {
	sampleResponse := `
{
    "location": {
        "name": "Gdynia",
        "region": "",
        "country": "Poland",
        "lat": 54.5,
        "lon": 18.55,
        "tz_id": "Europe/Warsaw",
        "localtime_epoch": 1745347628,
        "localtime": "2025-04-22 20:47"
    },
    "current": {
        "last_updated_epoch": 1745347500,
        "last_updated": "2025-04-22 20:45",
        "temp_c": 9.3,
        "temp_f": 48.7,
        "is_day": 0,
        "condition": {
            "text": "Mist",
            "icon": "//cdn.weatherapi.com/weather/64x64/night/143.png",
            "code": 1030
        },
        "wind_mph": 2.5,
        "wind_kph": 4.0,
        "wind_degree": 103,
        "wind_dir": "ESE",
        "pressure_mb": 1017.0,
        "pressure_in": 30.03,
        "precip_mm": 0.0,
        "precip_in": 0.0,
        "humidity": 93,
        "cloud": 0,
        "feelslike_c": 9.3,
        "feelslike_f": 48.8,
        "windchill_c": 8.0,
        "windchill_f": 46.3,
        "heatindex_c": 8.1,
        "heatindex_f": 46.6,
        "dewpoint_c": 7.6,
        "dewpoint_f": 45.6,
        "vis_km": 10.0,
        "vis_miles": 6.0,
        "uv": 0.0,
        "gust_mph": 5.2,
        "gust_kph": 8.3
    }
}`

	var response map[string]interface{}
	json.Unmarshal([]byte(sampleResponse), &response)

	return c.JSON(http.StatusOK, response)
}
