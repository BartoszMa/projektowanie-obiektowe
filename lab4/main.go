package main

import (
	"github.com/labstack/echo"
	"lab4/handlers"
)

func main() {
	e := echo.New()

	e.GET("/", handlers.WeatherHandler)

	e.Logger.Fatal(e.Start(":5100"))
}
