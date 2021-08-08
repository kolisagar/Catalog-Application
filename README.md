# Catalog-Application

## Catagery Api

Post request url : http://localhost:8080/catalog-service/category

Request Body : 

{
      "categoryName":"Shirt"
}

Response:

{
    "categoryId": 1,
    "categoryName": "Shirt"
}

--------------------------------------------------------------------------------

## Catagery Attribute Api


Post request url : http://localhost:8080/catalog-service/categoryAttributes/

Request Body : 

{
    "categoryId":1,
    "attributes": ["size","brand","color"]
}

Get request url :http://localhost:8080/catalog-service/categoryAttributes/1

Response:

{
    "categoryId": 1,
    "attributes": [
        {
            "attributeId": 2,
            "attributeName": "size"
        },
        {
            "attributeId": 3,
            "attributeName": "brand"
        },
        {
            "attributeId": 4,
            "attributeName": "color"
        }
    ]
}

--------------------------------------------------------------------------------

## Product Api

Post request url : http://localhost:8080/catalog-service/product/

Request Body : 
[{
    "productName":"red stripes pontic shirt",
    "productId":"1hy26msd8h9",
    "categoryId":1,
    "categoryName":"shirt",
    "productAttributes": [ {
            "attributeId": 2,
            "attributeName": "size",
            "attributeValue": "Large"
        },
        {
            "attributeId": 3,
            "attributeName": "brand",
            "attributeValue": "Denim"
        },
        {
            "attributeId": 4,
            "attributeName": "color",
            "attributeValue": "Blue"
        }
    ]
},
{
    "productName":"red stripes pontic shirt",
    "productId":"1hy26msd8h9",
    "categoryId":2,
    "categoryName":"shirt",
    "productAttributes": [ {
            "attributeId": 2,
            "attributeName": "size",
            "attributeValue": "Large"
        },
        {
            "attributeId": 3,
            "attributeName": "brand",
            "attributeValue": "Denim"
        },
        {
            "attributeId": 4,
            "attributeName": "color",
            "attributeValue": "Blue"
        }
    ]
}]



Get request url : http://localhost:8080/catalog-service/product/1hy26msd8h9

Response:

{
    "productName": "red stripes pontic shirt",
    "productId": "1hy26msd8h9",
    "categoryId": 1,
    "categoryName": "shirt",
    "productAttributes": [
        {
            "attributeId": 2,
            "attributeName": "size",
            "attributeValue": "Large"
        },
        {
            "attributeId": 3,
            "attributeName": "brand",
            "attributeValue": "Denim"
        },
        {
            "attributeId": 4,
            "attributeName": "color",
            "attributeValue": "Blue"
        }
    ]
}
