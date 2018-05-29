package biz.shxn.framework.dao.impl;

import biz.shxn.framework.dao.Dialect;

public class OracleDialect extends Dialect{
	
	public boolean supportsLimit() {
		return true;
	}

	public boolean supportsLimitOffset() {
		return true;
	}
	@Override
	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
		sql = sql.trim();
		boolean isForUpdate = false;
		if ( sql.toLowerCase().endsWith(" for update") ) {
			sql = sql.substring( 0, sql.length()-11 );
			isForUpdate = true;
		}
		
		StringBuffer pagingSelect = new StringBuffer( sql.length()+100 );
		if (offset > 0) {
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		}
		else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (offset > 0) {
//			int end = offset+limit;
			String endString = offsetPlaceholder+"+"+limitPlaceholder;
			pagingSelect.append(" ) row_ ) where rownum_ <= " + (offset+limit) + " and rownum_ > " + offset);
		}
		else {
			pagingSelect.append(" ) where rownum <= " + limit);
		}

		if ( isForUpdate ) {
			pagingSelect.append( " for update" );
		}
		return pagingSelect.toString();
	}
	public String getCountString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
        //if (offset > 0) {   
        	return "select count(*) as totalCount from ("+sql+")  AAA"; 
        //} else {   
        //    return sql + " limit "+limit;
        //}  
	}

}
