cd /d D:\LAN\AUTOtest\selenium_iTiny\
call mvn clean
call mvn test -Dtest=Test1
call mvn site
call mvn jetty:run
