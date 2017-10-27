package caster.demo.code._function;

import com.jfinal.kit.HttpKit;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class CheckDomain {

	// 域名查询结果有三个状态：
	// 210：可以注册
	// 211：已经注册
	// 212：参数错误
	// 214：未知错误
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 100; i++) {
			String domain = i+"sadfasasf.com";
			
			String data = HttpKit.get("http://panda.www.net.cn/cgi-bin/check.cgi?area_domain="+domain);
			Document document = DocumentHelper.parseText(data);
			Element root = document.getRootElement();
			System.out.print(root.elementText("returncode")+" ");;
			System.out.print(root.elementText("key")+" ");
			System.out.println(root.elementText("original"));
			System.out.println();
		}
	}

}
