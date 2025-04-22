package models

import "gorm.io/gorm"

type Weather struct {
	gorm.Model
	City        string
	Country     string
	Temperature float64
	Date        string
}
