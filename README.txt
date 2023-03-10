

	
	

	
	  Secured API Endpoints with Jwt Token 
	 	
	       Protect your API endpoints
Add authorization using this app to protect your APIs. 
When you finish, you have a secure REST API application that validates incoming requests.

    Installation
	
	

	Java 17 
	Maven
	SpringBoot3 
	SpringSecurity6 
	MySQL
	PostMan
	

	dependencies from Spring.io
	
        Spring Security
	Spring Web
        Spring Data JPA
        Lombok 
	MySQL Driver	

	* in POM.xml 
	you will find the 3 more dependecies 
	for Json WebToken
		<io.jsonwebtoken>
	- jjwt-impl  // - jjwt-api // jjwt-jackson
	            -<for qr code>
	-zxing-spring-boot-starter
		
		Don't forget to connect with DB in resources 

#	server.port=8080
#spring.datasource.url=jdbc:mysql://localhost/ <--your schema name->
#spring.datasource.username= <-- ex root -->
#spring.datasource.password=<-- your password ->
#spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
#spring.jpa.hibernate.ddl-auto=update
		
	
    
		Usage
	Test the endpoints  in Postman 

# Admin and User will authenticate and will receive the token
# Admin is authorize to add users or a new Admin.
# Admin is authorize to generate qrCode with Users short info.


# Password will be encoded in DB 
# Users can access the endpoints where have authorize only after they use token 
# Token is for 25 mins, after that is ncessary a new authentication.

#Customer and Products tables were made for simulate a real action 
		
# Still working on project.
	
    Contributing
    License
