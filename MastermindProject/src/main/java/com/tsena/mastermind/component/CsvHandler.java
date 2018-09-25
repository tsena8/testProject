package com.tsena.mastermind.component;

import static java.util.Arrays.asList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.tsena.mastermind.constant.AppDefault;
import com.tsena.mastermind.constant.PegColor;
import com.tsena.mastermind.model.GameModel;
import com.tsena.mastermind.model.GuessModel;
import com.tsena.mastermind.model.HistoryModel;

/**
 * Class for CSV file methods. We use CSV file structure and log4j library to log the game session and interactions 
 * @author tsena
 *
 */
@Component
public class CsvHandler {

	private static final Logger logger = Logger.getLogger(CsvHandler.class);
			
	@Value("${application.log.folder}")
	private String logFolderPath;
	
	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
	/**
	 * Read game session form game_session.csv file. 
	 * The current one is the last line in the file.
	 * For this purpose we use ReversedLinesFileReader.
	 * @return GameModel object
	 * @throws Exception
	 */
	public GameModel readGameSession() throws Exception {
		GameModel gameModel = new GameModel();
		
		File file = new File(logFolderPath.concat(File.separator).concat(AppDefault.SESSION_CSV));
		
		ReversedLinesFileReader object = null;
		try {
			object = new ReversedLinesFileReader(file, Charset.defaultCharset());
			String lastLine = object.readLine();
			
			String[] csvValues = lastLine.split(AppDefault.CSV_SEPARATION);
			if (csvValues == null || csvValues.length!=3) {
				throw new Exception("Session CSV record is not correct: " + csvValues.length + " >> " + csvValues);
			}
			gameModel.setDate(dateFormat.parse(csvValues[0]));
			gameModel.setGameId(csvValues[1]);
			String[] pegColorNames = csvValues[2].split(AppDefault.COLOR_SEPARATION);
			
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
			e.printStackTrace();
			throw e;
		} finally {
			try {
				object.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
	
	/**
	 * Read game interactions form game_interaction.csv file. 
	 * @param gameModel - game model object
	 * @return HistoryModel - history model object
	 * @throws Exception
	 */
	public HistoryModel readGameInteractions(GameModel gameModel) throws Exception {
		HistoryModel history = new HistoryModel();
		List<GuessModel> list = new ArrayList<GuessModel>();
		CSVReader reader;
		String fileName = logFolderPath.concat(File.separator).concat(AppDefault.INTERACTION_CSV);

		String gameId = gameModel.getGameId();
		history.setGameId(gameId);
		history.setDate(gameModel.getDate());
		String codeMasterCombinationStr = gameModel.getCodemakerColorRow().stream().map(Enum::name).collect(Collectors.joining(AppDefault.COLOR_SEPARATION));
		history.setCodeMasterCombination(codeMasterCombinationStr);
		
		try {
			reader = new CSVReader(new FileReader(fileName), AppDefault.CSV_SEPARATION_CHAR);
			String[] record = null;

			while ((record = reader.readNext()) != null) {
				if (record.length != 4 || !record[1].equals(gameId))
					continue;

				GuessModel guess = new GuessModel();
				
				guess.setGuessCombination(record[2].trim());
				guess.setFeedback(record[3].trim());
				list.add(guess);
			}
			
			reader.close();
			
			history.setGuessCombination(list);
			return history;
		} catch (FileNotFoundException fne) {
			logger.error("!!! CSV file not found: " + fileName);
			throw new Exception("CSV file not found: " + fileName);
		} catch (IOException ioe) {
			logger.error("!!! Error I/O reading file: " + fileName);
			throw new Exception("Error I/O reading file: " + fileName);
		}
	}
}
