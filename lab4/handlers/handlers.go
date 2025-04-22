package handlers

import (
	"encoding/json"
	"fmt"
	"github.com/labstack/echo"
	"gorm.io/gorm"
	"lab4/models"
	"net/http"
	"os"
)

var DB *gorm.DB

func Init(db *gorm.DB) {
	DB = db
}

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

	var raw map[string]interface{}
	if err := json.NewDecoder(resp.Body).Decode(&raw); err != nil {
		return c.String(http.StatusInternalServerError, "Failed to parse weather data")
	}

	location := raw["location"].(map[string]interface{})
	current := raw["current"].(map[string]interface{})

	weather := models.Weather{
		City:        location["name"].(string),
		Country:     location["country"].(string),
		Temperature: current["temp_c"].(float64),
		Date:        location["localtime"].(string),
	}

	if result := DB.Create(&weather); result.Error != nil {
		return c.String(http.StatusInternalServerError, "Failed to save weather data to database")
	}

	return c.JSON(http.StatusOK, weather)
}
