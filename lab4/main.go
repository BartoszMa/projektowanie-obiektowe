package main

import (
	"github.com/joho/godotenv"
	"github.com/labstack/echo"
	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
	"lab4/handlers"
	"lab4/models"
)

func main() {
	e := echo.New()
	godotenv.Load()

	e.GET("/weather", handlers.WeatherHandler)

	db, err := gorm.Open(sqlite.Open("weather.db"), &gorm.Config{})
	if err != nil {
		panic("Failed to connect database")
	}
	db.AutoMigrate(&models.Weather{})

	handlers.Init(db)

	e.Logger.Fatal(e.Start(":5100"))
}
