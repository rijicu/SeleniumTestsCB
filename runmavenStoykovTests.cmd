cd /d D:\LAN\AUTOtest\selenium_iTiny\
call mvn clean
call mvn test -Dtest=MainPageTest
call mvn test -Dtest=MyAccountsPageTest
call mvn site
call mvn jetty:run
