package biz.shxn.framework.util;


public class MyBatisSQLCount {
	private static String sqlCount="";
	private static MyBatisSQLCount mybatisSQLCount;
	
	private MyBatisSQLCount(){}
	
	public static MyBatisSQLCount getInstance(){
		if(mybatisSQLCount==null){
			mybatisSQLCount=new MyBatisSQLCount();
		}
		return mybatisSQLCount;
		
	}
	public void setSqlCount(String _sqlCount){
		sqlCount=_sqlCount;
	}
	public String getSqlCount(){
		return sqlCount;
	}
}
