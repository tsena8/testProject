package com.tsena.mastermind.component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tsena.mastermind.constant.AppDefault;
import com.tsena.mastermind.constant.PegColor;
import com.tsena.mastermind.model.GameModel;

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
			List<PegColor> pegColors = new ArrayList();
			/*
			PegColor pegs[] = new PegColor[] {
					PegColor.fromValue(pegColorNames[0]),
					PegColor.fromValue(pegColorNames[1]),
					PegColor.fromValue(pegColorNames[2]),
					PegColor.fromValue(pegColorNames[3])
			};
			gameModel.setCodemakerColorRow(pegs);
			*/
			
			pegColors.add(PegColor.fromValue(pegColorNames[0]));
			pegColors.add(PegColor.fromValue(pegColorNames[1]));
			pegColors.add(PegColor.fromValue(pegColorNames[2]));
			pegColors.add(PegColor.fromValue(pegColorNames[3]));
			
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
}
