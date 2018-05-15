call mvn clean
rem call mvn test
call mvn test -Dtest=Test_MainAdmin_iTiny
call mvn test -Dtest=Test_Admin_iTiny
call mvn test -Dtest=Test_UL_iTiny
call mvn test -Dtest=Test_FL_iTiny
call mvn site
call mvn jetty:run
