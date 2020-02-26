package com.orange.util;

public class Constants {

	// GENERAL
	public final static String PANEL = "MyPanel";
	public final static String PANEL_NAME = "Reflex";

	// SCRAP
	public final static String URL = "http://10.132.72.164:8080/xc/canon/HTML/ESM/";
	public final static String RESULT_URL_HALF = "http://10.132.72.164:8080/xc/canon/";
	public final static String URL_INDEX = "ESM_AE4AE5_Index.htm";
	public final static String URL_TRY = "AE4AE5_Index.htm";
	public final static String MAIN_AREA_CLASS = "mainArea";
	public final static String CODE_CLASS = "codigo";

	// REGEX
	public final static String ROW_REGEX = "<a href=\"(\\w+.\\w+)\"";
	public final static String IC_REGEX = "<a href=\"../../(.*)\"";
	public final static String TREATMENT_REGEX = "\\<[^>]*>";
	public final static String BACKLINE_STRING = "\\n";
	public final static String EMPTY_STRING = "";
	public final static String SPACE_STRING = " ";

	// EXPLORER
	public final static String DEFAULT_EXPLORER = "Mozilla/5.0";
	public final static int DEFAULT_TIMEOUT = 10000;

	// ERROR
	public final static String NOT_AVAILABLE_ERROR = "El servicio web no está disponible";
	public final static String NOT_AVAILABLE_IC_ERROR = "No existe IC asignado para el mdw";
	
	// EASTEREGG
	public final static String EASTEREGG = "gacha";
	public final static String EASTEREGG_RESPONSE = "GACHA!?";
	
}
