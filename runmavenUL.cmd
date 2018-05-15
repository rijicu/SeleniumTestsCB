rem call mvn clean
rem call mvn test
rem call mvn test -Dtest=Test_MainAdmin_iTiny
call mvn test -Dtest=Test_UL_iTiny
call mvn site
call mvn jetty:run
