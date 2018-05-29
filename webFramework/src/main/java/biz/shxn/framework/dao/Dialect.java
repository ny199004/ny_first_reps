package biz.shxn.framework.dao;

public abstract class Dialect {  
  
    public static enum Type{  
        MYSQL,
        ORACLE  
    }  
      
    public abstract String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder);  
    
    public abstract String getCountString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder);
}  
