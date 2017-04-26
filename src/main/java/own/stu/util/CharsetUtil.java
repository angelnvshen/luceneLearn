 /**  
 *@Description:     
 */ 
package own.stu.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;
  
  
public class CharsetUtil {
	
	//编码探测器
	private static final CodepageDetectorProxy detector;
	
	//初始化编码探测器
	static {
		detector = CodepageDetectorProxy.getInstance();
		detector.add(new ParsingDetector(false));
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		detector.add(JChardetFacade.getInstance());
	}
	
	/**
	 * @param url
	 * @param defaultCharset
	 * @return
	 * @Author:lulei  
	 * @Description: 检测URL下的编码方式，建议用于检测文件
	 */
	public static String getStreamCharset(URL url, String defaultCharset){
		if (url == null) {
			return defaultCharset;
		}
		
		try {
			Charset charset = detector.detectCodepage(url);
			if (charset != null) {
				return charset.name();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return defaultCharset;
	}
	
	/**
	 * @param inputStream
	 * @param defaultCharset
	 * @return
	 * @Author:lulei  
	 * @Description: 检测InputStream的编码方式
	 */
	public static String getStreamCharset(InputStream inputStream, String defaultCharset){
		if (inputStream == null) {
			return defaultCharset;
		}
		int count = 200;
		try {
			count = inputStream.available();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Charset charset = detector.detectCodepage(inputStream, count);
			if (charset != null) {
				return charset.name();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return defaultCharset;
	}

	/**  
	 * @param args
	 * @Author:lulei  
	 * @Description:  
	 */
	public static void main(String[] args) throws FileNotFoundException {

		String filePath = "C:\\Users\\dell\\Desktop\\xxxxxx.txt";
		InputStream inputStream = new FileInputStream(filePath);
		BufferedInputStream bufferInputStream = new BufferedInputStream(inputStream);
		String charset = getStreamCharset(bufferInputStream, "utf-8");
		System.out.println(charset);
	}

}
