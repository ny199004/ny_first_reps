/**
 * 
 */
package biz.shxn.framework.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.util.Enumeration;

/**
 * @author wangjf
 * @version 1.0, 2017/04/24
 */
public class UtilUUID {
	private static SecureRandom seederStatic = null;
	private static byte addr[] = null;
	private static String midValueStatic = null;
	private String midValue = null;
	private SecureRandom seeder = null;
	private static long prevMillis = 0L;
	private static byte[] addrBytes = null;

	static {
		try {
			java.net.Inet4Address.getLocalHost().getHostAddress();
			
			addr = getLocalHost().getLocalHost().getAddress();
			//addr = InetAddress.getLocalHost().getAddress();
			//addrBytes = InetAddress.getLocalHost().getAddress();
			StringBuffer buffer = new StringBuffer(8);
			buffer.append(toHex(toInt(addr), 8));
			midValueStatic = buffer.toString();
			seederStatic = new SecureRandom();
			seederStatic.nextInt();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 构造函数
	 */
	public UtilUUID()
	{
		StringBuffer buffer = new StringBuffer(16);
		buffer.append(midValueStatic);
		buffer.append(toHex(System.identityHashCode(this), 8));
		midValue = buffer.toString();
		seeder = new SecureRandom();
		seeder.nextInt();
	}

	/**
	 * 该方法用来产生一个32位的唯一的标记String
	 *
	 * @param obj 传入一个参考的对象
	 * @return 返回结果字符串
	 */
	public static String generate(Object obj)
	{
		StringBuffer uid = new StringBuffer(32);

		// get the system time
		long currentTimeMillis = System.currentTimeMillis();
		uid.append(toHex((int) (currentTimeMillis & -1L), 8));

		// get the internet address
		uid.append(midValueStatic);

		// get the object hash value
		uid.append(toHex(System.identityHashCode(obj), 8));

		// get the random number
		uid.append(toHex(getRandom(), 8));

		return uid.toString();
	}

	/**
	 * 该方法用来产生一个32位的String唯一标记
	 */
	public String generate()
	{
		StringBuffer uid = new StringBuffer(32);

		// get the system time
		long currentTimeMillis = System.currentTimeMillis();
		uid.append(toHex((int) (currentTimeMillis & -1L), 8));

		// get the internet address
		uid.append(midValue);

		// get the random number
		uid.append(toHex(seeder.nextInt(), 8));

		return uid.toString();
	}

	private static String toHex(int value, int length)
	{
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		StringBuffer buffer = new StringBuffer(length);
		int shift = length - 1 << 2;
		for (int i = -1; ++i < length;) {
			buffer.append(hexDigits[value >> shift & 0xf]);
			value <<= 4;
		}

		return buffer.toString();
	}

	private static int toInt(byte bytes[])
	{
		int value = 0;
		for (int i = -1; ++i < bytes.length;) {
			value <<= 8;
			value |= bytes[i];
		}

		return value;
	}

	private static synchronized int getRandom()
	{
		return seederStatic.nextInt();
	}

	private static synchronized long getSystemTimeMillis()
	{
		long millis = System.currentTimeMillis();
		if (millis > prevMillis) {
			prevMillis = millis;
		} else {
			prevMillis++;
		}
		return prevMillis;
	}

	/**
	 * 生成16位长整形数
	 *
	 * @return 返回16位长整形数结果
	 */
	public static Long getUniqueLong()
	{
		long l = getSystemTimeMillis();
		/*l = l * 1000000;
		long b1 = addrBytes[3] & 0x0FF;
		long b2 = (addrBytes[2] & 0x0FF) * 1000;
		l = l + b2 +b1;*/
		l = l * 1000;
		long b1 = addrBytes[3] & 0x0FF;
		l = l + b1;

		return Long.valueOf(l);
	}
	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

	 public static InetAddress getLocalHost() {
	        Enumeration<NetworkInterface> netInterfaces = null;
	        try {
	            netInterfaces = NetworkInterface.getNetworkInterfaces();
	            while (netInterfaces.hasMoreElements()) {
	                NetworkInterface ni = netInterfaces.nextElement();
	                Enumeration<InetAddress> ips = ni.getInetAddresses();
	                while (ips.hasMoreElements()) {
	                    InetAddress ip = ips.nextElement();
	                    if (ip.isSiteLocalAddress()) {
	                        return ip;
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
}
