package com.tsena.mastermind.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.opencsv.CSVReader;
import com.tsena.mastermind.model.LogLine;

@Service("HistoryService")
public class HistoryServiceImpl {

	CSVReader reader = null;
	private static final Logger logger = Logger.getLogger(HistoryServiceImpl.class);

	public List<LogLine> readCsvFile(String fileName) {

		List<LogLine> list = new ArrayList<LogLine>();

		try {
			reader = new CSVReader(new FileReader(fileName), '|');

			String[] record = null;

			while ((record = reader.readNext()) != null) {

				if (record.length != 4)
					break;

				LogLine line = new LogLine();
				line.setDate(record[0].trim());
				line.setCodeGuess(record[1].trim());
				line.setFeedback(record[2].trim());
				list.add(line);
			}

		} catch (FileNotFoundException fne) {
			logger.error("***File no encontrado:" + fileName);
		} catch (IOException ioe) {
			logger.error("***Error I/O en lectura de file:" + fileName);
		}

		return list;
	}
}
