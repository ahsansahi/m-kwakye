## Light weight service.
  None Spring based Rest API.
 
## List of frames works used

- ORM Lite
- H2 database
- Jersey JDK container, Jersey json and Jersey inject
- junit
## Build
- please issue "mvn clean install" over prompt to build this service and then " java -jar [artifact with all dependencies]"
- "mvn clean install exec:java" command can be issued in project directory where pom is located

## Endpoints
 Host for this service will be local and port 8080 making to http://localhost:8080/api/account where /api/account is context name for this service

## CURL Scripts. 
The following commands will have character encoding issue of windows command prompt. hence it is recommended to use git-bash application that features linux behaviour. To avoid invocation issues. The curl script is available on main project directory
    
    
```
curl -H "Content-Type: application/json" --request POST  -d '{"id":"0","accountName":"Test Account","title":"First Account","balance":"100.00"}'  http://localhost:8080/api/account
curl -H "Content-Type: application/json" --request POST  -d '{"id":"0","accountName":"Test Account","title":"First Account","balance":"100.00"}'  http://localhost:8080/api/account

curl http://localhost:8080/api/account/1
curl http://localhost:8080/api/account/2

curl -H "Content-Type: application/json" --request GET   http://localhost:8080/api/account/transfer/1/2/50.50

curl http://localhost:8080/api/account/1
curl http://localhost:8080/api/account/2

curl -H "Content-Type: application/json" --request GET   http://localhost:8080/api/account/transfer/1/2/50.50
curl -H "Content-Type: application/json" --request GET   http://localhost:8080/api/account/transfer/1/2/-50.50
curl -H "Content-Type: application/json" --request GET   http://localhost:8080/api/account/transfer/1/12/50.50
curl -H "Content-Type: application/json" --request GET   http://localhost:8080/api/account/transfer/12/2/50.50
curl http://localhost:8080/api/account/1
curl http://localhost:8080/api/account/2
```
