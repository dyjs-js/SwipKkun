package test.util;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class StringUtil {
	
	public static Object nullToObjectReplace(Object obj, Object defaultObj) {
		if(obj == null) return defaultObj;
		
		if(obj instanceof String) {
			if(obj.equals("")) return defaultObj;
			else return (String)obj.toString().replaceAll("(\r\n|\r|\n|\n\r|\\p{Z}|\\t)", "");
		} else if(obj instanceof Double) {
			return (Double)obj;
		} else if(obj instanceof Integer) {
			return (Integer)obj;
		} else if(obj instanceof Float) {
			return (Float)obj;
		} else {
			return obj;
		}
	}
	
	public static Object nullToObject(Object obj, Object defaultObj) {
		if(obj == null) return defaultObj;
		
		if(obj instanceof String) {
			if(obj.equals("")) return defaultObj;
			else return (String)obj;
		} else if(obj instanceof Double) {
			return (Double)obj;
		} else if(obj instanceof Integer) {
			return (Integer)obj;
		} else if(obj instanceof Float) {
			return (Float)obj;
		} else {
			return obj;
		}
	}
	
	public static String nullToNumber(String obj, String defaultObj) {
		String result;
		
		if(obj == null) {
			result = defaultObj;
		} else {
			result = obj.replaceAll("[^0-9]", "");
		}
		
		return result;
	}
	
	public static long doDiffofDate(String date1, String date2) {
		String start = date1;
		String end = date2;
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
			Date beginDate = formatter.parse(start);
			Date endDate = formatter.parse(end);
			
			long diffDays = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
			
			return diffDays;
		} catch (ParseException e) {
			return 0;
		}
	}
	
	@SuppressWarnings({ "deprecation" })
	public static int getMonthsDifference(String date1, String date2){		
        /* 해당년도에 12를 곱해서 총 개월수를 구하고 해당 월을 더 한다. */		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Date dateStr1;
		Date dateStr2;
		
		try {
			dateStr1 = format.parse(date1);
			dateStr2 = format.parse(date2);
			
			int month1 = dateStr1.getYear() * 12 + dateStr1.getMonth();
			int month2 = dateStr2.getYear() * 12 + dateStr2.getMonth();
			return month2 - month1;
		} catch (ParseException e) {
			return -1;
		}
	}

	/** SQL Injection방지 처리메소드 정의 */
	public static String SQL_Injection(String s) {
		String str = s;
		
		if(str == null) {
			return "";
		} else {
			str = str.replaceAll("'", "''" );
			str = str.replaceAll(";", "" );
			str = str.replaceAll("--", "" );
			str = str.replaceAll("|", "");
			str = str.replaceAll(":", "");
			
			str = str.replaceAll("–", "");
			str = str.replaceAll("‘", "");
			str = str.replaceAll("“", "");
			str = str.replaceAll("#", "");
			str = str.replaceAll("@", "");
			str = str.replaceAll("=", "");
			
			if(str.toLowerCase().indexOf("select") > -1) {
				str = str.toLowerCase().replaceAll("select", "");
			}
			if(str.toLowerCase().indexOf("insert") > -1) {
				str = str.toLowerCase().replaceAll("insert", "");
			}
			if(str.toLowerCase().indexOf("update") > -1) {
				str = str.toLowerCase().replaceAll("update", "");
			}
			if(str.toLowerCase().indexOf("delete") > -1) {
				str = str.toLowerCase().replaceAll("delete", "");
			}
			if(str.toLowerCase().indexOf("drop") > -1) {
				str = str.toLowerCase().replaceAll("drop", "");
			}
			if(str.toLowerCase().indexOf("union") > -1) {
				str = str.toLowerCase().replaceAll("union", "");
			}
			if(str.toLowerCase().indexOf("@variable") > -1) {
				str = str.toLowerCase().replaceAll("@variable", "");
			}
			if(str.toLowerCase().indexOf("@@variable") > -1) {
				str = str.toLowerCase().replaceAll("@@variable", "");
			}
			if(str.toLowerCase().indexOf("sp_") > -1) {
				str = str.toLowerCase().replaceAll("sp_", "");
			}
			if(str.toLowerCase().indexOf("xp_") > -1) {
				str = str.toLowerCase().replaceAll("xp_", "");
			}
			if(str.toLowerCase().indexOf("exec") > -1) {
				str = str.toLowerCase().replaceAll("exec", "");
			}
			if(str.toLowerCase().indexOf("sysobject") > -1) {
				str = str.toLowerCase().replaceAll("sysobject", "");
			}
			str = str.replaceAll("1=1", "");
			return str;
		}
	}
	
	/** 날짜 형식 체크하는 메소드 추가 */
	public static boolean validationDate(String checkDate){
		try {
			SimpleDateFormat dateFormat = new  SimpleDateFormat("yyyy.MM.dd");
			dateFormat.setLenient(false);
			dateFormat.parse(checkDate);
			return true;
		} catch (ParseException  e){
			return false;
		}
	}
	
	/** 날짜 형식 체크하는 메소드 추가(측정일자) */
	public static boolean validationPollutant_div_Date(String checkDate){
		try {
			SimpleDateFormat dateFormat = new  SimpleDateFormat("yyyy/MM/dd");
			dateFormat.setLenient(false);
			dateFormat.parse(checkDate);
			return true;
		} catch (ParseException  e){
			return false;
		}
	}
	
	/** 날짜 형식 체크하는 메소드 추가 */
	public static boolean validationYYYY(String checkDate){
		try {
			SimpleDateFormat dateFormat = new  SimpleDateFormat("yyyy");
			dateFormat.setLenient(false);
			dateFormat.parse(checkDate);
			return true;
		} catch (ParseException  e){
			return false;
		}
	}	

	/** 날짜 형식 체크하는 메소드 추가 */
	public static boolean validationMM(String checkDate){
		try {
			SimpleDateFormat dateFormat = new  SimpleDateFormat("MM");
			dateFormat.setLenient(false);
			dateFormat.parse(checkDate);
			return true;
		} catch (ParseException  e){
			return false;
		}
	}
	
	/** 첨부파일명 중복방지 공통 메소드 */
	public static String uploadFile(String originalName, byte[] fileData, MultipartHttpServletRequest request, String upload_path) throws Exception {
		UUID uuid = UUID.randomUUID();
		
		// 랜덤생성+파일이름 저장
		String savedName = (uuid.toString().replaceAll("-", "")) + getCurrentDate("yyyyMMddHHmmss") + ".pdf";
		File target = null;
		
		if(originalName != null) {
			target = new File(upload_path, savedName);
			FileCopyUtils.copy(fileData, target);
		}
		return savedName;
	}
	
	/** 첨부파일명 중복방지 공통 메소드 */
	public static String uploadFile(String originalName, byte[] fileData, String upload_path) throws Exception {
		UUID uuid = UUID.randomUUID();// 랜덤문자 UID
		
		// 랜덤생성+파일이름 저장
		String savedName = (uuid.toString().replaceAll("-", "")) + getCurrentDate("yyyyMMddHHmmss") + "";
		
		if(originalName != null) {
			File target = new File(upload_path, savedName);
			FileCopyUtils.copy(fileData, target);
		}
		return savedName;
	}
	
	public static String getCurrentDate(String format) {
	       String dtStr = "";
	       SimpleDateFormat sdf = new SimpleDateFormat(format);
	       Date dt1 = new Date();

	       dtStr = sdf.format(dt1);

	       return dtStr;
	}
	
	public static String encrypt(String text) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(text.getBytes());
		
		return bytesToHex(md.digest());
	}
	
	private static String bytesToHex(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		for (byte b : bytes) {
			builder.append(String.format("%02x", b));
		}
		return builder.toString();
	}
	
	public static List<String> makeForeach(String code, String gb) {
		if(code == null || "".equals(code)) {
			return null;
		}
		
		List<String> codeList = new ArrayList<String>();
		String[] aCode = code.split(gb);
		for(int i=0; i<aCode.length; i++) {
			codeList.add(aCode[i].toString());
		}
		
		return codeList;
	}
	
	public static boolean isNumberic(String s) { //숫자 판별 함수
		try {
	     	Double.parseDouble(s.replace(",", ""));
	    	return true;
	    } catch(NumberFormatException e) {  //문자열이 나타내는 숫자와 일치하지 않는 타입의 숫자로 변환 시 발생
	    	return false;
	    }
	}
	
	public static boolean isInteger(String s) { //정수 판별 함수
		try {
	     	Integer.parseInt(s.replace(",", ""));
	    	return true;
	    } catch(NumberFormatException e) {  //문자열이 나타내는 숫자와 일치하지 않는 타입의 숫자로 변환 시 발생
	    	return false;
	    }
	}
	
	public static double parseDouble(String str){
		double dbl = 0.0;
		
		if(str == null){
			dbl = 0.0;
		}else if(str.equals("")){
			dbl = 0.0;
		}else if(str.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")){
			dbl = 0.0;
		}else{
			dbl = Double.parseDouble(str);
		}
		
		return dbl;
	}
	
	public static double parseDouble(String str, Double dou){
		double dbl = 0.0;
		
		if(str == null){
			dbl = 0.0;
		}else if(str.equals("")){
			dbl = 0.0;
		}else if(str.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")){
			dbl = 0.0;
		}else{
			dbl = Double.parseDouble(str);
		}
		
		if(dbl == 0.0){
			dbl = dou;
		}
		return dbl;
	}
}
