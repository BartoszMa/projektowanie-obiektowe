package handlers

import (
	"fmt"
	"github.com/labstack/echo"
	"net/http"
	"os"
)

func WeatherHandler(c echo.Context) error {
	city := c.QueryParam("city")
	apiKey := os.Getenv("WEATHER_API_KEY")

	url := fmt.Sprintf("http://api.weatherapi.com/v1/current.json?key=%s&q=%s&aqi=no", apiKey, city)
	fmt.Println(url)

	resp, err := http.Get(url)
	if err != nil {
		return c.String(http.StatusInternalServerError, "Failed to fetch weather data")
	}
	defer resp.Body.Close()

	if resp.StatusCode != http.StatusOK {
		return c.String(resp.StatusCode, "Error from weather API")
	}

	return c.Stream(resp.StatusCode, "application/json", resp.Body)
}
