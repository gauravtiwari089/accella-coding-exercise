# Accella Coding Exercise

### Description:
This project is allow user to create/edit/get/delete Person object in in-memory database (h2) via Rest interface.
This project also allow Address to be added into the Person object along with edit/delete/get Address object in in-memory database (h2) via Rest interface.

### Requirements
1.	JDK 1.8 or later
2.	Maven


### How to Install
1.	git clone git@github.com:gauravtiwari089/coding-exercise.git
2.	cd coding-exercise
3.	mvn clean install


### How to Run
1. mvn spring-boot:run

### How to Test
1. mvn clean test

**Note** Any Rest Client can be used to use the Rest API

### Usage
Rest API would be launch on **http://localhost:8082**

### Add Person

#### Endpoint

```Java
POST: /api/v1/persons
```
#### Request Body

```JSON
{
 "firstName" : "ABC",
 "lastName" : "XYZ"
}
```
`
### Edit Person

#### Endpoint

```Java
PUT: /api/v1/persons/{personId}
```

#### Request Body

```JSON
{
 "firstName" : "DEF",
 "lastName" : "PQR"
}
```

### Delete Person

#### Endpoint

```Java
DELETE: /api/v1/persons/{personId}
```

### Add Address To Person

#### Endpoint

```Java
POST: /api/v1/persons/{personId}/address
```
#### Request Body

```JSON
{
 "street" : "STREET",
 "city": "CITY",
 "state": "STATE",
 "postalCode": "POSTAL CODE"
}
```

### Edit Address

#### Endpoint

```Java
PUT: /api/v1/address/{addressId}
```

#### Request Body

```JSON
{
 "street" : "STREET1",
 "city": "CITY1",
 "state": "STATE1",
 "postalCode": "POSTAL CODE1",
}
```

### Delete Address

#### Endpoint

```Java
DELETE: /api/v1/address/{addressId}
```

### Count Persons

#### Endpoint

```Java
GET: /api/v1/persons/count
```

### List Persons

#### Endpoint

```Java
GET: /api/v1/persons
```
