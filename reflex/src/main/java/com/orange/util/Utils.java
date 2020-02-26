package com.orange.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Utils {

	public static String mdwScraping(String input, String item, boolean success) throws IOException {
		Document doc = documentTreatment(Constants.URL_INDEX);
		Elements elem = doc.getElementsByClass(Constants.MAIN_AREA_CLASS);
		String[] row = elementsTreatment(elem, Constants.ROW_REGEX);
		return mdwSearch(input, item, success, row);
	}

	public static void tryConnection() throws Exception {
		try {
			Jsoup.connect(Constants.URL + Constants.URL_TRY).userAgent(Constants.DEFAULT_EXPLORER)
					.timeout(Constants.DEFAULT_TIMEOUT).ignoreHttpErrors(true).execute();
		} catch (Exception e) {
			throw new Exception(Constants.NOT_AVAILABLE_ERROR);
		}
	}

	private static String mdwSearch(String input, String item, boolean success, String[] row) {
		for (int i = 0; i < row.length; i++) {
			try {
				Document mdwDoc = documentTreatment(row[i]);
				Elements codigos = mdwDoc.getElementsByTag(Constants.CODE_CLASS);
				for (int j = 0; j < codigos.size(); j++) {
					if ((input).equals(codeTreatment(codigos, j)) && !success) {
						for (String mdwActual : elementsTreatment(mdwDoc.getElementsByTag(item), Constants.IC_REGEX)) {
							if (mdwActual.contains(input)) {
								success = true;
								return Constants.RESULT_URL_HALF + mdwActual;
							}
						}
						break;
					}
				}

			} catch (Exception e) {
				// Nothing
			}
		}
		return Constants.NOT_AVAILABLE_IC_ERROR + input;
	}

	private static String codeTreatment(Elements codigos, int j) {
		return codigos.get(j).toString().replaceAll(Constants.TREATMENT_REGEX, Constants.EMPTY_STRING)
				.replaceAll(Constants.SPACE_STRING, Constants.EMPTY_STRING)
				.replaceAll(Constants.BACKLINE_STRING, Constants.EMPTY_STRING);
	}

	private static Document documentTreatment(String input) throws IOException {
		return Jsoup.connect(Constants.URL + input).userAgent(Constants.DEFAULT_EXPLORER)
				.timeout(Constants.DEFAULT_TIMEOUT).get();
	}

	private static String[] elementsTreatment(Elements elementos, String regex) {
		String[] elemInput = elementos.toString().split("\n");
		String[] elemOutput = new String[elemInput.length];
		for (int i = 0; i < elemInput.length; i++) {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(elemInput[i]);
			if (matcher.find()) {
				elemOutput[i] = matcher.group(1);
			}
		}

		return Arrays.stream(elemOutput).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new);
	}
}
