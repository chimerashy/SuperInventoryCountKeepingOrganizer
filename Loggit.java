package main;

import java.util.logging.*;
import java.io.FileInputStream;
import java.io.IOException;

public class Loggit {
	static Logger logger = Logger.getLogger(Loggit.class.getName());
	public static void log (Exception exept) {
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream("mylogging.properties"));
		}catch(SecurityException | IOException ex) {
			ex.printStackTrace();
		}
		logger.setLevel(Level.FINE);
		logger.addHandler(new ConsoleHandler());
		try {
			Handler fileHandler = new FileHandler("D:/myApps/ErrorLogs/logger.log", 2000, 5);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
			
			for(int i=0; i<1000; i++) {
				logger.log(Level.WARNING, "Msg"+i);
			}
		}catch(SecurityException | IOException e) {
			e.printStackTrace();
			} 
	}

}
