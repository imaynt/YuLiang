package com.zykj.yuliang.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Description: �ı�У�鹤����
 */
 
public class TextUtil {
	
	/**
	 * �ֻ�����֤
	 * @param str
	 * @return ��֤ͨ������true
	 */
	static public boolean isMobile(String mobiles) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // ��֤�ֻ���
		m = p.matcher(mobiles);
		b = m.matches();
		// Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[^1^4,\\D]))\\d{8}").matcher(mobiles).matches();
		return b;
	}

	/**
	 * �绰������֤
	 * @param str
	 * @return ��֤ͨ������true
	 */
	static public boolean isPhone(String str) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // ��֤�����ŵ�
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // ��֤û�����ŵ�
		if (str.length() > 9) {
			m = p1.matcher(str);
			b = m.matches();
		} else {
			m = p2.matcher(str);
			b = m.matches();
		}
		return b;
	}
	
	/**
	 * ���볤�ȺϷ���У��6-20λ�����ַ�
	 * @param str
	 * @return ��֤ͨ������true
	 */
	static public boolean isPasswordLengthLegal(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^\\s*[^\\s\u4e00-\u9fa5]{6,20}\\s*$"); // ���볤��
		m = p.matcher(str);
		b = m.matches();
		return b;
	}
	
	/**
	 * ����ǿ��У��
	 * @param str
	 * @return ��֤ͨ������true
	 */
	static public boolean isPasswordStrength(String str) {
		Pattern p1 = Pattern.compile("\\d+");						// ֻ�������ֵ�������֤
		Pattern p2 = Pattern.compile("[a-zA-Z]+");					// ֻ������ĸ��������֤
		Matcher m1 = null;
		Matcher m2 = null;
		boolean b1 = false;
		boolean b2= false;
		m1 = p1.matcher(str);
		m2 = p2.matcher(str);
		b1 = m1.matches();
		b2 = m2.matches();
		return b1 || b2;
	}
	
	/**
     * У�����п�����
     * @param cardId
     * @return
     */
    static public boolean checkBankCard(String cardId) {
    	cardId = cardId.replaceAll(" ", "");
		char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
		if(bit == 'N'){
		 return false;
		}
		return cardId.charAt(cardId.length() - 1) == bit;
    }
   
    /**
     * �Ӳ���У��λ�����п����Ų��� Luhm У���㷨���У��λ
     * @param nonCheckCodeCardId
     * @return
     */
    static public char getBankCardCheckCode(String nonCheckCodeCardId){
    	nonCheckCodeCardId = nonCheckCodeCardId.replaceAll(" ", "");
        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
        	//������Ĳ������ݷ���N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;           
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }

    /*
     * ���������ַ�
     */
    static public String stringFilter(String str) throws PatternSyntaxException {
    	String regEx = "[`~!@#$%^&*|{}':;',//[//].�̡�����_<>������/?~��@#������&*����|{}�����������������������������������������������������������������֡ԡ١ܡݡڡۡˡ��¡ҡӡءޡġšơǡȡɡʡߡ�͡ϡСѡաף�]";
    	Pattern p = Pattern.compile(regEx);
    	Matcher m = p.matcher(str);
    	return m.replaceAll("").trim();
    }
    
    /*
     * ���Ʋ�����0��ͷ
     */
    static public String firstZeroFilter(String str) throws PatternSyntaxException {
    	String regEx = "^0";
    	Pattern p = Pattern.compile(regEx);
    	Matcher m = p.matcher(str);
    	return m.replaceAll("").trim();
    }

	static public boolean isCode(String mobiles, int longth) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[0-9]{"+longth+"}$"); // ��֤�ֻ���
		m = p.matcher(mobiles);
		b = m.matches();
		return b;
	}
}