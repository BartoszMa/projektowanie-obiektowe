package main

import (
	"github.com/joho/godotenv"
	"github.com/labstack/echo"
	"lab4/handlers"
)

func main() {
	e := echo.New()
	godotenv.Load()

	e.GET("/weather", handlers.WeatherHandler)

	e.Logger.Fatal(e.Start(":5100"))
}
