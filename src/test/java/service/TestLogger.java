package service;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Created by haraman on 08.06.2016.
 */

public class TestLogger {
    private static final Logger logger = Logger.getLogger(TestLogger.class.getName());

    public static void main(String[] args) {

        DOMConfigurator.configure("log4j.xml");
        Byte mode = 0;

        try {
            String msg = args[1] + " : " + args[2];
            try {
            mode = new Byte( args[0]);
            }
            catch (NumberFormatException e) {
               loggingInfo ("The first argument must be a number (1..2)");
            }

            switch (mode){
                case 1:
                    loggingErr(msg);
                    break;
                case 2:
                    loggingInfo(msg);
                    break;
            }
        }
        catch  (ArrayIndexOutOfBoundsException e ) {
            loggingInfo ("Not enough arguments for logger (must be 3)");
        }

    }

    private static void loggingErr(String msg) {
       logger.error(msg);
    }

    private static void loggingInfo(String msg) {
        logger.info(msg) ;
    }

}





