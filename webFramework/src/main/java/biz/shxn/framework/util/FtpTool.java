package biz.shxn.framework.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpTool {

	/**
	 * 测试服务器是否可连通
	 * 
	 * @Version 1.0
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @return 成功返回true，否则返回false
	 */
	public static boolean testFtpConnect(String url, int port, String user, String pwd) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		int reply;
		try {
			ftp.connect(url, port);// 连接FTP服务器
			ftp.login(user, pwd);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			colseFtpConnect(ftp);
		}
		return result;
	}

	/**
	 * 
	 * @param ftp
	 */
	public static void colseFtpConnect(FTPClient ftp) {
		if (ftp.isConnected()) {
			try {
				ftp.disconnect();
			} catch (IOException ioe) {
			}
		}
	}

	/**
	 * 向FTP服务器上传文件
	 * 
	 * @Version 1.0
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFile(String url, int port, String username, String password, String path,
			String filename, InputStream input) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
	    // 设置PassiveMode被动模式-向服务发送传输请求  
		ftp.enterLocalPassiveMode();
		
		try {
			int reply;
			ftp.connect(url, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			try {
				
				ftp.setFileType(FTP.BINARY_FILE_TYPE); //设置下载文件为二进制模式
		//		ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE); ///传输文件为流的形式
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (!ftp.changeWorkingDirectory(path)) {
				// 如果目录不存在创建目录
				String[] dirs = path.split("/");
				String tempPath = "";
				for (String dir : dirs) {
					if (!StringUtil.checkStr(dir))
						continue;
					tempPath += "/" + dir;
					if (!ftp.changeWorkingDirectory(tempPath)) {
						if (!ftp.makeDirectory(tempPath)) {
							return result;
						} else {
							ftp.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
				
			if(ftp.storeFile(new String(filename.getBytes("gbk"),"iso-8859-1"), input)){
				result = true;	
			}
			ftp.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(input!=null){
					input.close();
				}
				if(ftp.isConnected()){
					ftp.disconnect();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}

	/**
	 * ftp下载数据
	 * 
	 * @Version 1.0
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 * 
	 *         单文件下载
	 */
	public static boolean downFile(String url, int port, String username, String password, String remotePath,
			String localPath, String filename) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		  // 设置PassiveMode被动模式-向服务发送传输请求  
		  ftp.enterLocalPassiveMode(); 
		try {
			int reply;
			ftp.connect(url, port);
			ftp.login(username, password);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			try {
				ftp.setFileType(ftp.BINARY_FILE_TYPE); //设置下载文件为二进制模式
			//	ftp.setFileTransferMode(ftp.STREAM_TRANSFER_MODE); ///传输文件为流的形式
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// 4.指定要下载的目录
			// ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			if (!ftp.changeWorkingDirectory(remotePath)) {
				return result;
			}
			// 5.遍历下载的目录
			FTPFile[] fs = ftp.listFiles();
			if (fs == null || fs.length == 0) {
				return result;
			}
			for (FTPFile ff : fs) {
				// 解决中文乱码问题，两次解码
				byte[] bytes = ff.getName().getBytes("iso-8859-1");
				String fn = new String(bytes, "utf8");
				if (fn.equals(filename)) {
					File localFile = new File(localPath + "/" + ff.getName());
					File parentFile = localFile.getParentFile();
					if (!parentFile.exists()) {
						if (parentFile.mkdirs()) { // 穿件本地目录成功
							OutputStream output = new FileOutputStream(localFile);
							ftp.retrieveFile(ff.getName(), output);
							output.flush();
							output.close();
							result = true;
							break;
						} else {
							return result; // 创建本地目录失败
						}
					} else {
						OutputStream output = new FileOutputStream(localFile);
						ftp.retrieveFile(ff.getName(), output);
						output.flush();
						output.close();
						result = true;
						break;
					}
					/*
					 * if(localFile.exists()){ return result; }
					 */

				}
			}
			ftp.logout();
			// result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}
	// 文件夹下载

	public static boolean downDirectory(String url, int port, String username, String password, String remoteDirectory,
			String localDirectory) throws IOException {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		int reply;
		ftp.connect(url, port);
		ftp.login(username, password);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			return result;
		}
		// 4.指定要下载的目录
		// ftp.changeWorkingDirectory(remoteDirectory);// 转移到FTP服务器目录
		if (!ftp.changeWorkingDirectory(remoteDirectory)) { // 存在才继续
			return result;
		}
		// 5.遍历下载的目录
		FTPFile[] fs = ftp.listFiles();
		for (FTPFile ff : fs) {
			if (ff.isFile()) {
				downFile(url, port, username, password, remoteDirectory, localDirectory, ff.getName());
			} else if (ff.isDirectory()) {
				String templocalDirectory = localDirectory + "/" + ff.getName();
				if (!new File(localDirectory).exists()) {
					new File(localDirectory).mkdirs();
				}
				String strremoteDirectoryPath = remoteDirectory + "/" + ff.getName();
				downDirectory(url, port, username, password, strremoteDirectoryPath, templocalDirectory);
			} else {
				continue;
			}
		}

		ftp.logout();
		if (ftp.isConnected()) {
			ftp.disconnect();
		}
		result = true;
		return result;
	}

	// 获取ftp指定目录下指定后缀的所有文件名集合

	public static List<String> getByPathName(String url, int port, String username, String password, String path,
			String suffixName) throws IOException {
		FTPClient ftp = new FTPClient();
		List<String> list = new ArrayList<>();
		try {
			int reply;
			ftp.connect(url, port);
			ftp.login(username, password);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return null;
			}
			String directory = path;
			// 更换目录到当前目录
			ftp.changeWorkingDirectory(directory);
			FTPFile[] files = ftp.listFiles();
			if (files == null || files.length == 0) {
				return null;
			}
			for (FTPFile file : files) {
				if (file.isFile()) {
					if (file.getName().endsWith(suffixName)) {
						list.add(file.getName());
						System.out.println("ftp:" + file.getName() + "---");
					}
				} else if (file.isDirectory()) {
					getByPathName(url, port, username, password, directory + file.getName() + "/", suffixName);
				}
			}

			// result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return list;
	}
}
