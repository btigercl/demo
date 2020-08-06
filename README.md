#Demo Shopping Cart API

###Summary
This is a backend focused, small shopping cart api demo that allows a user to create a cart, add products, update product qty, and delete items. Each call returns an updated cart that will be rendered by the frontend. Because carts need greater reliability and persistence, the backend receives, persists, and returns an updated cart upon each call. The frontend should have to do little work besides rendering the json upon change and validating any data sent to the API. In a full application, the cart would be associated with a session, and this app would be using the session to retrieve the cart id. 


###Technologies Used
	- Spring Boot 2
	- Java 8
	- Mysql 8
	- Docker 3.7


###To Run this app
Please clone this git locally. Inside the proeject's rootdirectory, demo-services, run the command: 
	docker-compose up --build
	- the products are preloaded. See api response below, so this app should ready to use out of the box 



###Api 
 - Base URL http://localhost:8080/


####Cart Endpoints

#####To Create Cart
* Method POST 
* URL cart/  
* Status 200 
* Body `{"id":1,"dateCreated":"2020-08-06","cartItems":null,"cartTotal":0.0}`

#####To Get Cart
* Method GET 
* URL cart/:id 
* Status 200 
* Body `{"id":1,"dateCreated":"2020-08-06","cartItems":[{"id":1,"productId":2,"qty":2,"cartId":1,"imageUrl":"https://placekitten.com/97/140","productName":"Kitten 2","price":160.0,"totalPrice":320.0}],"cartTotal":320.0}`

#####To Add Cart Item 
* Method POST 
* URL cart/:cartId/cartItem/:cartItemid?qty=:qty 
* Status 200 
* Body `{"id":1,"dateCreated":"2020-08-06","cartItems":[{"id":1,"productId":2,"qty":2,"cartId":1,"imageUrl":"https://placekitten.com/97/140","productName":"Kitten 2","price":160.0,"totalPrice":320.0}],"cartTotal":320.0}`

#####To Update Cart Item Qty
* Method PUT 
* URL cart/:cartId/cartItem/:cartItemid?qty=:qty 
* Status 200 
* Body `{"id":1,"dateCreated":"2020-08-06","cartItems":[{"id":1,"productId":2,"qty":3,"cartId":1,"imageUrl":"https://placekitten.com/97/140","productName":"Kitten 2","price":160.0,"totalPrice":480.0}],"cartTotal":480.0}`

#####To Delete Cart Item 
* Method DELETE
* URL cart/:cartId/cartItem/:cartItemid 
* Status 200 
* Body: `{"id":1,"dateCreated":"2020-08-06","cartItems":[{"id":3,"productId":1,"qty":3,"cartId":1,"imageUrl":"https://placekitten.com/96/140","productName":"Kitten 1","price":100.0,"totalPrice":300.0}],"cartTotal":300.0}`


#####Products Endpoint
* Method GET 
* URL http://localhost:8080/products 
* Status 200 Body : `[{"id":1,"name":"Kitten 1","price":100.0,"imageUrl":"https://placekitten.com/96/140"},{"id":2,"name":"Kitten 2","price":160.0,"imageUrl":"https://placekitten.com/97/140"},{"id":3,"name":"Kitten 3","price":90.0,"imageUrl":"https://placekitten.com/98/140"},{"id":4,"name":"Kitten 4","price":5.0,"imageUrl":"https://placekitten.com/99/140"}]`


TODO

- Vuejs frontend
- Complete more of the unit tests 


