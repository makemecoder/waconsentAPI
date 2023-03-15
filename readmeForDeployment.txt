Docker Commands for Springboot+Mysql application

1. docker pull mysql
mysql  4073e6a6f542

2. docker network create springboot-mysql-net
springboot-mysql-net	b3e5557e6828

3. docker run --name mysqlcontainer --network springboot-mysql-net -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=consentdb -d mysql
mysqlcontainer  cf9a2613e60d

4. Update application.properties for db url as spring.datasource.url=jdbc:mysql://mysqlcontainer:3306/consentdb

5. Run projct as "clean package" to create a jar file.

6. docker build -t consentapi .
consentapi   4b191b1dae42

7. docker run --name springboot-mysql-container --network springboot-mysql-net -p 8080:8080 consentapi
springboot-mysql-container 170a2192d113




Basic commands used::
docker image ls
docker container ls
docker network ls
docker image rm -f <imagename>
