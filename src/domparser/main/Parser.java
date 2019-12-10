	/**
 * 
 */
package domparser.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import domparser.utilities.XmlFormation;
import domparser.utilities.XmlReader;
import domparser.dbcalls.DatabaseRetrival;
import domparser.pojos.PoHeader;
import domparser.pojos.PoLine;
import domparser.pojos.PoSchedule;

/**
 * @author sakkenapelly
 *
 */
public class Parser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final Logger logger = Logger.getLogger(Parser.class);
		// read from properties file

		File propertiesFile = new File("input.properties");
		Properties properties = null;
		try (FileInputStream fileInputStream = new FileInputStream(propertiesFile)) {
			properties = new Properties();
			properties.load(fileInputStream);
		} catch (IOException e) {
			logger.error("Io exception in creating input stream for properties file");
		}

		logger.info("finished reading from properties file");
		
		// In bound

		File inputFile = new File("input.xml");
		XmlReader xmlReader = new XmlReader();
		xmlReader.parseXml(inputFile);

		// Out bound

		DatabaseRetrival dbRetrieval = new DatabaseRetrival();
		long poLineId = 0;
		long poScheduleId = 0;
		List<PoSchedule> poScheduleList = null;
		XmlFormation xmlFormation = new XmlFormation();
		
		//getting the header id inserted
		
		Long headerId = xmlReader.getPoHeader().getId();
		PoHeader poHeader = dbRetrieval.getPoheaderPojo(headerId);
		poHeader.setStatus(dbRetrieval.getStatus(headerId, null, null));
		poHeader.setLocations(dbRetrieval.getLocation(headerId, null, null));
		poHeader.setCodes(dbRetrieval.getCode(headerId));

		List<PoLine> poLines = dbRetrieval.getPolines(headerId);

		for (int i = 0; i < poLines.size(); i++) {
			poLineId = poLines.get(i).getId();
			poLines.get(i).setStatus(dbRetrieval.getStatus(null, poLineId, null));
			poLines.get(i).setLocations(dbRetrieval.getLocation(null, poLineId, null));
			poScheduleList = dbRetrieval.getSchedules(poLineId);
			for (int j = 0; j < poScheduleList.size(); j++) {
				poScheduleId = poScheduleList.get(j).getPoScheduleNumber();
				poScheduleList.get(j).setStatus(dbRetrieval.getStatus(null, poLineId, poScheduleId));
				poScheduleList.get(j).setLocation(dbRetrieval.getLocation(null, poLineId, poScheduleId).get(0));
				poLines.get(i).setSchedules(poScheduleList);
			}
		}
		//setting all line to header object  finally 
		poHeader.setLines(poLines);
		xmlFormation.formXml(poHeader, properties);
	}

}
