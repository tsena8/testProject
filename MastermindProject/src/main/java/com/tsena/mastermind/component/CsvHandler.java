package com.tsena.mastermind.component;

import static java.util.Arrays.asList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.tsena.mastermind.constant.AppDefault;
import com.tsena.mastermind.constant.PegColor;
import com.tsena.mastermind.model.GameModel;
import com.tsena.mastermind.model.HistoryLine;

@Component
public class CsvHandler {

	private static final Logger logger = Logger.getLogger(CsvHandler.class);
			
	@Value("${application.log.folder}")
	private String logFolderPath;
	
	public GameModel readGameSession() throws Exception {
		GameModel gameModel = new GameModel();
		
		File file = new File(logFolderPath.concat(File.separator).concat(AppDefault.SESSION_CSV));
		
		ReversedLinesFileReader object = null;
		try {
			object = new ReversedLinesFileReader(file, Charset.defaultCharset());
			String lastLine = object.readLine();
			
			String[] csvValues = lastLine.split("\\|");
			if (csvValues == null || csvValues.length!=3) {
				throw new Exception("Session CSV record is not correct: " + csvValues.length + " >> " + csvValues);
			}
			
			gameModel.setGameId(csvValues[1]);
			String[] pegColorNames = csvValues[2].split("\\,");
			
			if (pegColorNames == null || pegColorNames.length!=4) {
				throw new Exception("Peg colors were saved not correclty");
			}
			List<PegColor> pegColors = asList(PegColor.fromValue(pegColorNames[0]), 
					PegColor.fromValue(pegColorNames[1]), 
					PegColor.fromValue(pegColorNames[2]), 
					PegColor.fromValue(pegColorNames[3]));
			
			gameModel.setCodemakerColorRow(pegColors);
			
			return gameModel;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			try {
				object.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}
		}
	}
	
	public List<HistoryLine> readGameInteractions(String gameId) throws Exception {
		GameModel gameModel = new GameModel();
		List<HistoryLine> list = new ArrayList<HistoryLine>();
		CSVReader reader;
		String fileName = logFolderPath.concat(File.separator).concat(AppDefault.INTERACTION_CSV);
		try {
			reader = new CSVReader(new FileReader(fileName), '|');

			String[] record = null;

			while ((record = reader.readNext()) != null) {

				if (record.length != 4 || !record[1].equals(gameId))
					break;

				HistoryLine line = new HistoryLine();
				line.setDate(record[0].trim());
				line.setCodeGuess(record[1].trim());
				line.setFeedback(record[2].trim());
				list.add(line);
			}
			
			reader.close();
			return list;
		} catch (FileNotFoundException fne) {
			logger.error("CSV file not found: " + fileName);
		} catch (IOException ioe) {
			logger.error("***Error I/O reading file: " + fileName);
		}
	}
}
