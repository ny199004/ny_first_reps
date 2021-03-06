package biz.shxn.framework.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 构建树型数据
 * @author wangjf
 * @version 1.0, 2017/04/24
 * 
 */
public class BuildTree {
	
	/**
	 * 构造树型数据,此方法根据isCopy 来判断是否复制一份原数据，在新数据上进行树型改造
	 * true,代表复制，这样不会改变原数据结构，fasle反之
	 * @param lis 从数据库查出的原数据
	 * @param parentCol  父ID列名
	 * @param selfCol  主键列名
	 * @param isCopy   是否复制
	 * @return List 返回类型
	 */	
	@SuppressWarnings("rawtypes")
	public static List<Map<String, Object>> createTree(List<Map> lis,
			String parentCol, String selfCol, boolean isCopy) {
		  List<Map> list = isCopy?cloneList(lis):lis;//将数据复制一份，以免对原数据结构改变，此步骤
		  return createTree(list, parentCol, selfCol);
	}
	
	/**
	 * 构造树型数据，此方法会将原数据结构改变
	 * @param list  从数据库查出的原数据
	 * @param parentCol  父ID列名
	 * @param selfCol   主键列名
	 * @return List 返回类型
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map<String, Object>> createTree(List<Map> list,
			String parentCol, String selfCol) {
		List<Map<String, Object>>  tree = new ArrayList<Map<String, Object>>();
		//完全循环一次,是子节点的最终被替换为null
		for (Iterator<Map> it = list.iterator(); it.hasNext();) {
			Map mp = it.next();
			if (mp == null){
				continue;//为空,说明已经被设为一个子节点了
			}
			setChilds(list, parentCol, selfCol, mp);
		}

		for (Iterator<Map> it = list.iterator(); it.hasNext();) {
			Map mp = it.next();
			if (mp != null) {//不为空则说明是一个根节点
				tree.add(mp);
			}
		}
		return tree;
	}

	/**
	 * 根据当前节点找子节点
	 * @param list   数据库查出的原数据
	 * @param parentCol  父ID列名
	 * @param selfCol  主键列名
	 * @param mp  当前节点
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void setChilds(List<Map> list, String parentCol, String selfCol,
			Map mp) {
		String sid = mp.get(selfCol) + "";//当前节点主键
		String pid = "";
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Map temp = list.get(i);
			if (temp == null){
				continue;//为空,说明已经被设为一个子节点了
			}
			 pid = temp.get(parentCol) + "";//循环中的节点父ID
			//当循环节点的父ID=当前结点的主键时,说明循环节点就是一个子节点
			if (pid == sid || (sid != null && sid.equals(pid))) {
				//递归,寻找叶节点
				setChilds(list, parentCol, selfCol, temp);
				//获取子节点集合
				List<Map> arr = (List<Map>) mp.get("children");
				if (arr == null){    //如果还没有子节点集合,则新建一个
					arr = new ArrayList<Map>();
				}
				arr.add(temp);//节点集合添加此子节点
				mp.put("children", arr);//得新赋给当前结点
				list.set(i, null);//被设置为子节点后,进行清空标记,不能删除
			}
		}
	}

	/**
	 * 克隆，以免对原来list数据结构改变
	 * @param lis 请求集合
	 * @return List 返回类型
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List<Map> cloneList(List<Map> lis){
		List<Map> list = new ArrayList<Map>();
		int size = lis.size();
		for(int i = 0; i < size; i++){
			list.add(new HashMap(lis.get(i)));
		}
		return list;
	}
	
	/**
	 *  测试用数据
	 * @return List 返回集合
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	private static List getData() {
		Map<String, String> m1 = new HashMap<String, String>();
		m1.put("a", "1");
		m1.put("b", "dfdf");
		//m1.put("cPowerSysName", "AAA");
		Map<String, String> m2 = new HashMap<String, String>();
		m2.put("a", "2");
		m2.put("b", "1");
		//m2.put("cPowerSysName", "BBB");
		Map<String, String> m3 = new HashMap<String, String>();
		m3.put("a", "3");
		m3.put("b", "2");
		//m3.put("cPowerSysName", "CCC");
		Map<String, String> m4 = new HashMap<String, String>();
		m4.put("a", "4");
		m4.put("b", "dfdf");
		//m4.put("cPowerSysName", "DDD");
		Map<String, String> m5 = new HashMap<String, String>();
		m5.put("a", "5");
		m5.put("b", "4");
		//m5.put("cPowerSysName", "EEE");
	
		Map<String, String> m6 = new HashMap<String, String>();
		m6.put("a", "6");
		m6.put("b", "1");
		//m6.put("cPowerSysName", "FFFF");
		List list = new ArrayList<Map>();
		list.add(m5);
		list.add(m2);
		list.add(m3);
		list.add(m4);
		list.add(m1);
		list.add(m6);
		return list;
	}

//	/**
//	 * 主方法
//	 * @param args 请求参数
//	 * 
//	 */
	/*@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	public static void main(String[] args) {
		BuildTree bt = new BuildTree();
		List list = bt.getData();
		List<Map<String, Object>> ls = bt.createTree(list, "b", "a");
		String json = JackJson.getBaseJsonData(ls);
		System.out.println(json.replace("a", "id").replace("b", "text").replace("\"", "'"));
	}*/
}
