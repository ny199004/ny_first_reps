/**
 * 
 */
package biz.shxn.framework.common.keygen.impl;

import java.util.Date;

import org.springframework.stereotype.Component;

import biz.shxn.framework.common.keygen.IKeyGen;
import biz.shxn.framework.exception.CheckedException;
import biz.shxn.framework.util.UtilUUID;

/**
 * @author wangjf
 * @version 1.0, 2017/04/24
 *
 */
@Component("keyGen")
public class KeyGenImpl implements IKeyGen {

	
	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

	public Long getLongKey() throws CheckedException {
		// TODO Auto-generated method stub
		return UtilUUID.getUniqueLong();
	}

	public String getUUIDKey() throws CheckedException {
		// TODO Auto-generated method stub
		return UtilUUID.generate(new Date());
	}

	public String getUUIDKey(Object o) {
		// TODO Auto-generated method stub
		return UtilUUID.generate(o);
	}

}
