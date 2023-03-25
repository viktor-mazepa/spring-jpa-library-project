<h1>Spring Cource. Practical Project 2.</h1>
Repository for code which I wrote during Spring Course study (https://www.udemy.com/course/spring-alishev/).
Simple application which allow to add persons and books into postgresql database, edit selected person and books and update records with new parameter values.
Also it provide possibility no search books, show list of books using pagination and odering.
Application implements OneToMany relation between person and books in database.
It using docker-compose to build application container based on tomcat-jre image and postgres image for database container.
Application create with Spring MVC framework. For database collaboration, it is using Hibernate+Spring JPA. For UI it's using - Thymeleaf


