package biz.shxn.framework.util;

import java.util.Random;

public class AutomaticString {
	private static char[] str=null;
	private static Random random=null;
	
	public AutomaticString(){
		
	}
	/**
	 * 用法如:getAutoString(32),32就是要生成的长度
	 *根据传入的长度参数自动生成id
	 *
	 * @return 返回字符串
	 */
	public static String getAutoString(int len){
		if(len<1){
			return null;
		}
		if(random==null){
			random=new Random();
			str=("0123456789abcdefghijklmnopqrstuvwyzABCDEFGHIJKLMNOPQRSTUVWYZ").toCharArray();
		}
		StringBuffer sb=new StringBuffer(len);
		for(int i=0;i<len;i++){
			char strbuf=str[random.nextInt(str.length)];
			sb.append(strbuf);
		}
		return sb.toString();
	}
	/*public  static void main(String[] args){
		AutomaticString as=new AutomaticString();
		String aa=as.getAutoString(32);
		System.out.println(aa);
	}*/
}
