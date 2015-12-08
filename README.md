# Jetty / websockets / MYSQL / hibernate bootstrap template 

This is a bootstrap project, to get you up and running with websockets application, storing information in MYSQL database using hibernate. This is a minimalistic project with small memory footprint and doesn't use spring or any other container.    
The bootstrap application accepts messages from client via websockets and store those in database.   

Following technologies are used - 
  - Jetty application server (No need to install, embedded)
  - Maven required to build
  - MySQL required to install
  - Uses hibernate to store information in database
  - Uses jetty for client/server communication

### Installation & running
1. Install mysql and create a database test. 
2. Run the script.sql in db folder
3. Configure the database credentials in hibernate.cfg.xml
4. Build the project
```sh
$ cd jetty-websocket-template
$ mvn clean install
```
5. Run 
```sh
$ mvn -Djetty.port=9999 jetty:run 
```
6. Check the magic on http://localhost:9999/


