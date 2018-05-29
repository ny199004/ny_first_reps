/**   
 * @Title: Md5PwdEncoder.java
 * @Package com.lto.bem.core.util.Md5PwdEncoder
 * @date 2011-7-12 下午01:56:37
 * @version V2.3.1   
 * 
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * LongTopOnline Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with LongTopOnline.
 */
package biz.shxn.framework.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;


/**
* @ClassName: UtilMd5Encoder
* @Description: MD5加密
* @author wangjf
* @version 1.0, 2017/04/24
* 
*/
public final class UtilMd5Encoder
{

	
	private static String salt = "#bizcn%%online#";

	public static String encodePassword(String paramString)
	{
		String str = mergePasswordAndSalt(paramString, salt, false);
		MessageDigest localMessageDigest = getMessageDigest();
		byte[] arrayOfByte;
		try
		{
			arrayOfByte = localMessageDigest.digest(str.getBytes("UTF-8"));
		}
		catch (UnsupportedEncodingException localUnsupportedEncodingException)
		{
			throw new IllegalStateException("UTF-8 not supported!");
		}
		return new String(Hex.encodeHex(arrayOfByte));
	}

	/**
	 * @Title: isPasswordValid
	 * @Description: TODO(判断密码是否一致)
	 * @param paramString1 密文
	 * @param paramString2
	 *            明文
	 * @return boolean
	 * @throws
	 */
	public static boolean isPasswordValid(String paramString1,
			String paramString2)
	{
		String str1 = "" + paramString1;
		String str2 = encodePassword(paramString2);
		return str1.equals(str2);
	}

	protected static final MessageDigest getMessageDigest()
	{
		String str = "MD5";
		try
		{
			return MessageDigest.getInstance(str);
		}
		catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
		{
		}
		throw new IllegalArgumentException("No such algorithm [" + str + "]");
	}

	protected static String mergePasswordAndSalt(String paramString,
			Object paramObject, boolean paramBoolean)
	{
		if (paramString == null) paramString = "";
		if ((paramBoolean)
				&& (paramObject != null)
				&& ((paramObject.toString().lastIndexOf("{") != -1) || (paramObject
						.toString().lastIndexOf("}") != -1)))
			throw new IllegalArgumentException(
					"Cannot use { or } in salt.toString()");
		if ((paramObject == null) || ("".equals(paramObject)))
			return paramString;
		return paramString + "{" + paramObject.toString() + "}";
	}

	protected String getSalt()
	{
		return salt;
	}

	public void setSalt(String paramString)
	{
		salt = paramString;
	}

}
