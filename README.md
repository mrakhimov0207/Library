# Library
Library project with many manipulation cases.


Project uses Postgresql database and it's mandatory to use Postgresql with "postgres" db name, "postgres" username and "2002" password or adapt these parameters 
for your convenience using Library\src\main\resources\application.properties file. If you don't have already "books" table on db, to create it automatically, change the "spring.jpa.hibernate.ddl-auto" value to "create". Then you can change it to "update" again. 


All of the books in findAuthorsWithMatchCount() method have been fetched from database for demonstrating the Java Stream API functions. 
That's not the best practise, as it could be fetched and filtered on database with native query.

