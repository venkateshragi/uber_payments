# uber_payments

Application can be run in multiple ways:
========================================

1 -->
	cd service
	mvn spring-boot:run
			This would start the application on localhost:8080

	To debug application
			mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
2 -->
	mvn clean package
	java -jar target/spayments.war
			This would start the application on localhost:8080
3 -->
	mvn clean package

	Start local tomcat (7.0.57+)
	Deploy the war

			This would start the application on localhost:8080
4 -->
	From your IDE, go to src/main/.../Application.java and run it as java application.