package com.xtsoft.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class FtpTest {

	private static Logger logger = Logger.getLogger(FtpTest.class);
	private static String userName; // FTP ��¼�û���
	private static String password; // FTP ��¼����
	private static String ip; // FTP ��������ַIP��ַ
	private static int port; // FTP �˿�
	private static Properties property = null; // ���Լ�
	private static FTPClient ftpClient = null; // FTP �ͻ��˴���
	// ʱ���ʽ��
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm");
	// FTP״̬��
	public static long i = 1;

	public static void main(String[] args) {
		connectServer();
		setFileType(FTP.BINARY_FILE_TYPE);// ���ô���������ļ�
		uploadManyFile(new File("E:\\workspace\\its2\\webapp\\images\\icons"),
				"2014", "/incoming/lh-xietf");
		closeConnect();// �ر�����
	}

	/**
	 * �ϴ������ļ�����������
	 * 
	 * @param localFile
	 *            --�����ļ�·��
	 * @param distFolder
	 *            --�µ��ļ���,��������Ϊ��""
	 * @return true �ϴ��ɹ���false �ϴ�ʧ��
	 */
	public static boolean uploadFile(File localFile,
			final String localRootFile, final String distFolder) {
		System.out.println("                    -------------------------");
		boolean flag = true;
		try {
			connectServer();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
			InputStream input = new FileInputStream(localFile);
			ftpClient.changeWorkingDirectory(distFolder + "/" + localRootFile);
			System.out.println("b>>>>>>> : " + localFile.getPath() + " "
					+ ftpClient.printWorkingDirectory());
			flag = ftpClient.storeFile(localFile.getName(), input);
			if (flag) {
				System.out.println("�ϴ��ļ��ɹ���");
			} else {
				System.out.println("�ϴ��ļ�ʧ�ܣ�");
			}
			input.close();
			if (flag) {
				deleteLocalFile(localFile);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.debug("�����ļ��ϴ�ʧ�ܣ�", e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * �ϴ�����ļ�
	 * 
	 * @param localFile
	 *            --�����ļ���·��
	 * @return true �ϴ��ɹ���false �ϴ�ʧ��
	 */
	public static String uploadManyFile(String localFile) {
		boolean flag = true;
		StringBuffer strBuf = new StringBuffer();
		try {
			connectServer();
			File file = new File(localFile); // �ڴ�Ŀ¼�����ļ�
			File fileList[] = file.listFiles();
			for (File f : fileList) {
				if (f.isDirectory()) { // �ļ����л����ļ���
					uploadManyFile(f.getAbsolutePath());
				} else {
				}
				if (!flag) {
					strBuf.append(f.getName() + "\r\n");
				}
			}
			System.out.println(strBuf.toString());
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("�����ļ��ϴ�ʧ�ܣ�", e);
		}
		return strBuf.toString();
	}

	/**
	 * �ϴ�����ļ�
	 * 
	 * @param localFile
	 *            ,--�����ļ���·��
	 * @param distFolder
	 *            --Ŀ��·��
	 * @return true �ϴ��ɹ���false �ϴ�ʧ��
	 */
	public static String uploadManyFile(File localFile, final String fileName,
			final String distFolder) {
		System.out.println("-------------------------");
		boolean flag = true;
		StringBuffer strBuf = new StringBuffer();
		int n = 0;
		try {
			connectServer();
			ftpClient.makeDirectory(distFolder + File.separator + fileName);
			File fileList[] = localFile.listFiles();
			for (File upfile : fileList) {
				if (upfile.isDirectory()) {// �ļ����л����ļ���
					uploadManyFile(upfile, fileName, distFolder);
				} else {
					flag = uploadFile(upfile, fileName, distFolder);
				}
				if (!flag) {
					//strBuf.append(upfile.getName() + "\r\n");
				}
			}
			//System.out.println(strBuf.toString());
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.debug("�����ļ��ϴ�ʧ�ܣ��Ҳ����ϴ��ļ���", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("�����ļ��ϴ�ʧ�ܣ�", e);
		}
		return strBuf.toString();
	}

	/**
	 * �����ļ�
	 * 
	 * @param remoteFileName
	 *            --�������ϵ��ļ���
	 * @param localFileName
	 *            --�����ļ���
	 * @return true ���سɹ���false ����ʧ��
	 */
	public static boolean loadFile(String remoteFileName, String localFileName) {
		boolean flag = true;
		connectServer();
		// �����ļ�
		BufferedOutputStream buffOut = null;
		try {
			buffOut = new BufferedOutputStream(new FileOutputStream(
					localFileName));
			flag = ftpClient.retrieveFile(remoteFileName, buffOut);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("�����ļ�����ʧ�ܣ�", e);
		} finally {
			try {
				if (buffOut != null)
					buffOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 * ɾ��һ���ļ�
	 */
	public static boolean deleteFile(String filename) {
		boolean flag = true;
		try {
			connectServer();
			flag = ftpClient.deleteFile(filename);
			if (flag) {
				System.out.println("ɾ���ļ��ɹ���");
			} else {
				System.out.println("ɾ���ļ�ʧ�ܣ�");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return flag;
	}

	public static boolean deleteLocalFile(File filename) {
		boolean flag = true;
		try {
			if (!filename.exists()) {
				return true;
			}
			flag = filename.delete();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		if (flag) {
			System.out.println("ɾ���ļ��ɹ���");
		} else {
			System.out.println("ɾ���ļ�ʧ�ܣ�");
		}
		return flag;
	}

	/**
	 * ɾ��Ŀ¼
	 */
	public static void deleteDirectory(String pathname) {
		try {
			connectServer();
			File file = new File(pathname);
			if (file.isDirectory()) {
				File file2[] = file.listFiles();
			} else {
				deleteFile(pathname);
			}
			ftpClient.removeDirectory(pathname);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * ɾ����Ŀ¼
	 */
	public static void deleteEmptyDirectory(String pathname) {
		try {
			connectServer();
			ftpClient.removeDirectory(pathname);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * �г����������ļ���Ŀ¼
	 * 
	 * @param regStr
	 *            --ƥ���������ʽ
	 */
	public static void listRemoteFiles(String regStr) {
		connectServer();
		try {
			String files[] = ftpClient.listNames(regStr);
			if (files == null || files.length == 0)
				System.out.println("û���κ��ļ�!");
			else {
				for (int i = 0; i < files.length; i++) {
					System.out.println(files[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �г�Ftp�������ϵ������ļ���Ŀ¼
	 */
	public static void listRemoteAllFiles() {
		connectServer();
		try {
			String[] names = ftpClient.listNames();
			for (int i = 0; i < names.length; i++) {
				System.out.println(names[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ر�����
	 */
	public static void closeConnect() {
		try {
			if (ftpClient != null) {
				ftpClient.logout();
				ftpClient.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���ô����ļ�������[�ı��ļ����߶������ļ�]
	 * 
	 * @param fileType
	 *            --BINARY_FILE_TYPE��ASCII_FILE_TYPE
	 * 
	 */
	public static void setFileType(int fileType) {
		try {
			connectServer();
			ftpClient.setFileType(fileType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��չʹ��
	 * 
	 * @return ftpClient
	 */
	protected static FTPClient getFtpClient() {
		connectServer();
		return ftpClient;
	}

	/**
	 * ���ò���
	 * 
	 * @param configFile
	 *            --�����������ļ�
	 */
	private static void setArg() {
		BufferedInputStream inBuff = null;
		try {
			 userName =AppPropsWeb.getInstance().getProp("username");
			 password = AppPropsWeb.getInstance().getProp("password");
			 ip =AppPropsWeb.getInstance().getProp("ip");
			 port =Integer.parseInt(AppPropsWeb.getInstance().getProp("port"));
		
		} catch (Exception e) {

		}
	}

	/**
	 * ���ӵ�������
	 * 
	 * @return true ���ӷ������ɹ���false ���ӷ�����ʧ��
	 */
	public static boolean connectServer() {
		boolean flag = true;
		if (ftpClient == null) {
			int reply;
			try {
				setArg();
				ftpClient = new FTPClient();
				ftpClient.setControlEncoding("GBK");
				ftpClient.setDefaultPort(port);
				ftpClient.configure(getFtpConfig());
				ftpClient.connect(ip);
				ftpClient.login(userName, password);
				ftpClient.setDefaultPort(port);
				// System.out.print(ftpClient.getReplyString());
				reply = ftpClient.getReplyCode();
				ftpClient.setDataTimeout(120000);

				if (!FTPReply.isPositiveCompletion(reply) && reply != 530) {
					ftpClient.disconnect();
					System.err.println("FTP server refused connection.");
					// logger.debug("FTP ����ܾ����ӣ�");
					flag = false;
				}
				// System.out.println(i);
				i++;
			} catch (SocketException e) {
				flag = false;
				e.printStackTrace();
				System.err.println("��¼ftp������ " + ip + " ʧ��,���ӳ�ʱ��");
			} catch (IOException e) {
				flag = false;
				e.printStackTrace();
				System.err.println("��¼ftp������ " + ip + " ʧ�ܣ�FTP�������޷��򿪣�");
			}
		}
		return flag;
	}

	/**
	 * ���뵽��������ĳ��Ŀ¼��
	 * 
	 * @param directory
	 */
	public static void changeWorkingDirectory(String directory) {
		try {
			connectServer();
			ftpClient.changeWorkingDirectory(directory);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * ���ص���һ��Ŀ¼
	 */
	public static void changeToParentDirectory() {
		try {
			connectServer();
			ftpClient.changeToParentDirectory();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * �������ļ�
	 * 
	 * @param oldFileName
	 *            --ԭ�ļ���
	 * @param newFileName
	 *            --���ļ���
	 */
	public static void renameFile(String oldFileName, String newFileName) {
		try {
			connectServer();
			ftpClient.rename(oldFileName, newFileName);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * ����FTP�ͷ��˵�����--һ����Բ�����
	 * 
	 * @return ftpConfig
	 */
	private static FTPClientConfig getFtpConfig() {
		FTPClientConfig ftpConfig = new FTPClientConfig(
				FTPClientConfig.SYST_UNIX);
		ftpConfig.setServerLanguageCode(FTP.DEFAULT_CONTROL_ENCODING);
		return ftpConfig;
	}

	/**
	 * ת��[ISO-8859-1 -> GBK] ��ͬ��ƽ̨��Ҫ��ͬ��ת��
	 * 
	 * @param obj
	 * @return ""
	 */
	private static String iso8859togbk(Object obj) {
		try {
			if (obj == null)
				return "";
			else
				return new String(obj.toString().getBytes("iso-8859-1"), "GBK");
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * �ڷ������ϴ���һ���ļ���
	 * 
	 * @param dir
	 *            �ļ������ƣ����ܺ��������ַ����� \ ��/ ��: ��* ��?�� "�� <��>...
	 */
	public static boolean makeDirectory(String dir) {
		connectServer();
		boolean flag = true;
		try {
			// System.out.println("dir=======" dir);
			flag = ftpClient.makeDirectory(dir);
			if (flag) {
				System.out.println("make Directory " + dir + " succeed");

			} else {

				System.out.println("make Directory " + dir + " false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
