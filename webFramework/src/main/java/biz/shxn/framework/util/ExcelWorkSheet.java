package biz.shxn.framework.util;

import java.util.ArrayList;
import java.util.List;

//import org.apache.poi.hssf.record.formula.functions.T;

/**
 * @ClassName: ExcelWorkSheet
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangjf
 * @version 1.0, 2017/04/24
 * 
 */
public class ExcelWorkSheet<T>
{

	private String sheetName;//工作单位名称
	
	private List<T> data =new ArrayList<T>();//数据行
	
	private List<String> columns;//列名
	
	public ExcelWorkSheet()
	{
		super();
	}

	public String getSheetName()
	{
		return sheetName;
	}

	public void setSheetName(String sheetName)
	{
		this.sheetName = sheetName;
	}

	public List<T> getData()
	{
		return data;
	}

	public void setData(List<T> data)
	{
		this.data = data;
	}

	public List<String> getColumns()
	{
		return columns;
	}

	public void setColumns(List<String> columns)
	{
		this.columns = columns;
	}

	
	
}
