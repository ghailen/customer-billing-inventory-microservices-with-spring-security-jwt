# customer-billing-inventory-microservices-with-spring-security-jwt

ces applications (services) communiquement entre eux avec OpenFeign ,les services sont enregistrés dans eureuka service , et le routage (le proxy ) est un service qui 
s'appelle gateway service, un serivce aussi pour la securité avec jwt ,service customer, billing et inventory 
exemple des apis qu'on peut l'appeler via le service peoxy sur le port 8888 : 

http://localhost:8888/BILLING-SERVICE/addBilling
http://localhost:8888/CUSTOMER-SERVICE/customers/1
http://localhost:8888/PRODUCT-SERVICE/products/1

service security:
localhost:8080/refreshToken
localhost:8080/login
localhost:8080/addRoleToUser

service customer : 
http://localhost:8081/customers/1
