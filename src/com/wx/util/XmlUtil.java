package com.wx.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlUtil {
	public static Map<String, String> map = new HashMap<String, String>();
	
	public static Map<String, String> parseXml(HttpServletRequest request)
			throws Exception {
		// ����������洢��HashMap��
		map = new HashMap<String, String>();

		// ��request��ȡ��������
		InputStream inputStream = request.getInputStream();
		// ��ȡ������
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// �õ�xml��Ԫ��
		 Element root = document.getRootElement();
		// �õ���Ԫ�ص������ӽڵ�
		List<Element> elementList = root.elements();

		// ���������ӽڵ�
		formatXml(elementList);

		// �ͷ���Դ
		inputStream.close();
		inputStream = null;

		return map;
	}
	
	/**
	 * �ݹ���������ӽڵ�
	 * @param elementList
	 */
	private static void formatXml(List<Element> elementList){
		for (Element e : elementList) {

			if (e.elements().size() > 0) {//�ж��Ƿ񻹴����ӱ�ǩ
				List<Element> chindList = e.elements();
				formatXml(chindList);
			} else {
				map.put(e.getName(), e.getText());
			}
		}
	}

}
