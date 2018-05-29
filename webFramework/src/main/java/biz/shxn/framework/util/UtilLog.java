package biz.shxn.framework.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilLog {
	private static String _fileName="";
	/**
	 * 获取当天的系统时间yyyyMMdd
	 * 输入参数无
	 * @author wangjf
     * @version 1.0, 2017/04/24
	 * @return dateStr
	 */
	public static String getSysDate(){
		String dateStr="";
		Calendar   cal   =   Calendar.getInstance(); 
		cal.setTime(new Date());
		SimpleDateFormat format =new SimpleDateFormat("yyyyMMdd");
		dateStr=format.format(cal.getTime());
		return dateStr;
	}
	/**
	 * 
	 * add jevon 20100304
	 * @return fileNames
	 */
	public static String creadFileName(String str){
		String fileNames="";
		String dateStr=getSysDate();
		fileNames=str+dateStr+".txt";
		return fileNames;
	}
	/**
	 * 创建txt文档
	 * str是传入要打印的内容
	 * path日志路径如"/data/uniarv/log/LogFSQL/"
	 * fileNameCon生成文件名字
	 * jevon 20100304
	 */
	public static void creadFileTxt(String str,String path,String fileNameCon)throws IOException{
		String fileNames=creadFileName(fileNameCon);
		
			path=path+fileNames;
		File fileName=new File(path);
		if(!fileName.exists()){
			fileName.createNewFile();
			_fileName=path;
			writeFileTxt(str);
		}else{
			_fileName=path;
			writeFileTxt(str);
		}
		
	}
	/**
	 * 写入数据到创建的文本文件中
	 * add jevon 20100304
	 * 
	 * @param s
	 */
	public static void writeFileTxt(String str) throws IOException{
		
		String writeData=str+"\r\n";
		FileWriter mm=null;
		mm=new FileWriter(_fileName,true);
		BufferedWriter bw = new BufferedWriter(mm);
		bw.write(writeData);
		bw.close();
		mm.close();
	}
	/*public static void main(String[] args) throws IOException {
		UtilLog aa=new UtilLog();
		String str="***********111111111111111";
		String path="F://project/createText/data/";
		String fileNameCon="_dd_";
		aa.creadFileTxt(str, path, fileNameCon);
	}*/
	
}
