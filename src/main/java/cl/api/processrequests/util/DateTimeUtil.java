package cl.api.processrequests.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

	public static final String FORMAT_SHORT_DATE_EN = "MM-dd-yyyy";
	public static final String FORMAT_SHORT_DATE_ES = "dd-MM-yyyy";
	public static final String FORMAT_DB_DATE = "yyyy-MM-dd";
	public static final String FORMAT_SHORT_DATE_EN_TIMESTAMP = "MM-dd-yyyy HH:mm:ss";
	public static final String FORMAT_SHORT_DATE_ES_TIMESTAMP = "dd-MM-yyyy HH:mm:ss";
	public static final String FORMAT_DB_DATE_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";

	public static String formatoFront(String date, String language) throws ParseException {
		String result;
		if (language.equals("es")) {
			Date fecha = new SimpleDateFormat(FORMAT_DB_DATE).parse(date);
			result = new SimpleDateFormat(FORMAT_SHORT_DATE_ES).format(fecha);
		} else {
			Date fecha = new SimpleDateFormat(FORMAT_DB_DATE).parse(date);
			result = new SimpleDateFormat(FORMAT_SHORT_DATE_EN).format(fecha);
		}
		return result;
	}
	
	public static String formatoFrontTimestamp(String date, String language) throws ParseException {
		String result;
		if (language.equals("es")) {
			Date fecha = new SimpleDateFormat(FORMAT_DB_DATE_TIMESTAMP).parse(date);
			result = new SimpleDateFormat(FORMAT_SHORT_DATE_ES_TIMESTAMP).format(fecha);
		} else {
			Date fecha = new SimpleDateFormat(FORMAT_DB_DATE_TIMESTAMP).parse(date);
			result = new SimpleDateFormat(FORMAT_SHORT_DATE_EN_TIMESTAMP).format(fecha);
		}
		return result;
	}

	public static String formatoDB(String f, String language) throws ParseException {
		String result;
		if (language.equals("es")) {
			Date fecha = new SimpleDateFormat(FORMAT_SHORT_DATE_ES).parse(f);
			result = new SimpleDateFormat(FORMAT_DB_DATE).format(fecha);
		} else {
			Date fecha = new SimpleDateFormat(FORMAT_SHORT_DATE_EN).parse(f);
			result = new SimpleDateFormat(FORMAT_DB_DATE).format(fecha);
		}
		return result;
	}
	
	public static String formatoDBTimestamp(String f, String language) throws ParseException {
		String result;
		if (language.equals("es")) {
			Date fecha = new SimpleDateFormat(FORMAT_SHORT_DATE_ES_TIMESTAMP).parse(f);
			result = new SimpleDateFormat(FORMAT_DB_DATE_TIMESTAMP).format(fecha);
		} else {
			Date fecha = new SimpleDateFormat(FORMAT_SHORT_DATE_EN_TIMESTAMP).parse(f);
			result = new SimpleDateFormat(FORMAT_DB_DATE_TIMESTAMP).format(fecha);
		}
		return result;
	}
	
	public static Boolean validateDateLanguage(String fecha, String language) {
		Boolean result = null;
		String[] parts = fecha.split("-");
		Date date;
		int anio;
		int mes;
		int dia;
		Calendar calendar = Calendar.getInstance();
		try {
			if (language.equals("es")) {
				anio = Integer.parseInt(parts[2]);
				mes = Integer.parseInt(parts[1]);
				dia = Integer.parseInt(parts[0]);
				calendar.setLenient(false);
				calendar.set(Calendar.YEAR, anio);
				calendar.set(Calendar.MONTH, mes - 1);
				calendar.set(Calendar.DAY_OF_MONTH, dia);
				date = calendar.getTime();
				SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_SHORT_DATE_ES);
				if (anio >= 1900) {
					sdf.format(date);
					result = true;
				}
				else {
					result = false;
				}
			} else if (language.equals("en")) {
				 anio = Integer.parseInt(parts[2]);
				 mes = Integer.parseInt(parts[0]);
				 dia = Integer.parseInt(parts[1]);
				
				calendar.setLenient(false);
				calendar.set(Calendar.YEAR, anio);
				calendar.set(Calendar.MONTH, mes - 1);
				calendar.set(Calendar.DAY_OF_MONTH, dia);
				date = calendar.getTime();
				SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_SHORT_DATE_EN);
				if (anio >= 1900) {
					sdf.format(date);
					result = true;
				}
				else {
					result = false;
				}
					
				
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	public static Boolean validateDateLanguageTimestamp(String fecha, String language) {
		boolean result;
		String partsDate = fecha.replace(" ", "-").replace(":", "-");
		String[] parts = partsDate.split("-");
		Date date;
		try {
			int anio = Integer.parseInt(parts[2]);
			int mes;
			int dia;
			if (language.equalsIgnoreCase("es")) {
				mes = Integer.parseInt(parts[1]);
				dia = Integer.parseInt(parts[0]);
			} else {
				mes = Integer.parseInt(parts[0]);
				dia = Integer.parseInt(parts[1]);
			}
			int hora = Integer.parseInt(parts[3]);
			int minutos = Integer.parseInt(parts[4]);
			int segundos = Integer.parseInt(parts[5]);
			Calendar calendar = Calendar.getInstance();
			calendar.setLenient(false);
			calendar.set(Calendar.YEAR, anio);
			calendar.set(Calendar.MONTH, mes - 1);
			calendar.set(Calendar.DAY_OF_MONTH, dia);
			calendar.set(Calendar.HOUR_OF_DAY, hora);
			calendar.set(Calendar.MINUTE, minutos);
			calendar.set(Calendar.SECOND, segundos);
			date = calendar.getTime();
			SimpleDateFormat sdf;
			if (language.equalsIgnoreCase("es")) {
				sdf = new SimpleDateFormat(FORMAT_SHORT_DATE_ES_TIMESTAMP);
			} else {
				sdf = new SimpleDateFormat(FORMAT_SHORT_DATE_EN_TIMESTAMP);
			}
			if (anio >= 1900) {
				sdf.format(date);
				result = true;
			}
			else {
				result = false;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public static int compareStartDateToEndDate(String sStartEffectiveDate, String sEndEffectiveDate, String language)
			throws ParseException {
		SimpleDateFormat formatter;
		Date startEffectiveDate;
		Date endEffectiveDate;
		if (language.equals("en")) {
			formatter = new SimpleDateFormat(FORMAT_SHORT_DATE_EN);
		} else {
			formatter = new SimpleDateFormat(FORMAT_SHORT_DATE_ES);
		}
		startEffectiveDate = formatter.parse(sStartEffectiveDate);
		endEffectiveDate = formatter.parse(sEndEffectiveDate);

		return startEffectiveDate.compareTo(endEffectiveDate);
	}
	
	public static int compareStartDateToEndDateTimestamp(String sStartEffectiveDate, String sEndEffectiveDate, String language)
			throws ParseException {
		SimpleDateFormat formatter;
		Date startEffectiveDate;
		Date endEffectiveDate;
		if (language.equals("en")) {
			formatter = new SimpleDateFormat(FORMAT_SHORT_DATE_EN_TIMESTAMP);
		} else {
			formatter = new SimpleDateFormat(FORMAT_SHORT_DATE_ES_TIMESTAMP);
		}
		startEffectiveDate = formatter.parse(sStartEffectiveDate);
		endEffectiveDate = formatter.parse(sEndEffectiveDate);

		return startEffectiveDate.compareTo(endEffectiveDate);
	}

	private DateTimeUtil() {
		// Constructor
	}
}
