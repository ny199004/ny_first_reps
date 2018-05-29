/**
 * 
 */
package biz.shxn.framework.common.keygen;

import biz.shxn.framework.exception.CheckedException;

/**
 * @author wangjf
 * @version 1.0, 2017/04/24
 */
public interface IKeyGen {
	public final static String BEAN_ID = "keyGen";

	/**
	 * 获取key值 注意：该方法是基础平台的默认实现，其返回值为Long型<br>
	 * 注意： <br>
	 * Long型算法的组成，总长度为二进制下的64位<br>
	 * 1:系统毫秒级唯一性，根据获取系统时间，作为系统毫秒级唯一性的组成部分，对于jdk1.5，可考虑纳秒级<br>
	 * 目前的系统时间已经达到了10进制下的13位长度如1228285174187，早期的时间为12位，换成二进制就是41位长度<br>
	 * 系统毫秒的41+jvm的16=57长度，离总长度64还有7个长度的空余，而经过测试，到2213年的11月24号，系统时间也只是10进制下的7696581394431，即二进制下的43位，离总长度还有4个空余，因此，长度预留足够<br>
	 * 2:jvm级别的唯一性，由网路ip地址（非静态）的后两部分来决定，因为理论上，前两部分是用来决定可以互访的网段的，后两部分才能确定是什么机器<br>
	 * 一个部分是一个字节，二进制下的8位，取后两部分就是2个字节16位<br>
	 * 还要注意取出的值需要通过非负数处理，否则位移后就无法保证结果的合法性	<br>
	 * 3:对象级别的唯一性，在本方法中加入同步控制synchronized,以代替唯一性算法中的对象级唯一部分。<br>
	 * 4:对象内部毫秒级唯一性，这是做为补充的，本实现中不提供，如需要，可考虑通过Random随机数完成<br>
	 * <p/>
	 * 在整个实现中，需要对1和2获取的数据进行位移运算，但至于是1左位移还是2左位则无所谓。<br>
	 *
	 * @return Long 获取的可用的key值
	 */
	public Long getLongKey() throws CheckedException;

	/**
	 * 获取key值 注意：该方法是基础平台的默认实现，其返回值为uuid方式生成的String值
	 *
	 * @return String 获取的可用的key值
	 */
	public String getUUIDKey() throws CheckedException;

	/**
	 * 获取key值 注意：该方法是基础平台的默认实现，其返回值为uuid方式生成的String值
	 *
	 * @return String 获取的可用的key值
	 */
	public String getUUIDKey(Object o);
}
