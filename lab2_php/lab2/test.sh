#!/bin/bash

BASE_URL="http://127.0.0.1:8000/product"

rm -f "var/data.db"
php bin/console doctrine:database:create --if-not-exists
php bin/console doctrine:migrations:migrate --no-interaction

RESPONSE=$(curl -s -X POST "$BASE_URL/create" -H "Content-Type: application/json" -d '{"name": "Laptop", "price": 2999.99}')
EXPECTED='{"message":"Product created","id":1}'

if [[ "$RESPONSE" == "$EXPECTED" ]]; then
    echo "Adding product - ok"
else
    echo "Adding product - error"
    echo "Received: $RESPONSE"
    exit 1
fi

RESPONSE=$(curl -s -X GET "$BASE_URL/")
EXPECTED='[{"id":1,"name":"Laptop","price":2999.99}]'

if [[ "$RESPONSE" == "$EXPECTED" ]]; then
    echo "Getting product - ok"
else
    echo "Getting product - error"
    echo "Received: $RESPONSE"
    exit 1
fi

RESPONSE=$(curl -s -X PUT "$BASE_URL/edit/1" -H "Content-Type: application/json" -d '{"name": "Gaming Laptop", "price": 3499.99}')
EXPECTED='{"message":"Product updated"}'

if [[ "$RESPONSE" == "$EXPECTED" ]]; then
    echo "Edit product - ok"
else
    echo "Edit product - error"
    echo "Received: $RESPONSE"
    exit 1
fi

RESPONSE=$(curl -s -X DELETE "$BASE_URL/delete/1")
EXPECTED='{"message":"Product deleted"}'

if [[ "$RESPONSE" == "$EXPECTED" ]]; then
    echo "Delete product - ok"
else
    echo "Delete product - error"
    echo "Received: $RESPONSE"
    exit 1
fi
