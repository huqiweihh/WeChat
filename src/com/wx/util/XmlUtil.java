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

	public static Map<String, String> parseXml(HttpServletRequest request)
			throws Exception {
		// ����������洢��HashMap��
		Map<String, String> map = new HashMap<String, String>();

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
		for (Element e : elementList) {

			if (e.elements().size() > 0) {//�ж��Ƿ񻹴����ӱ�ǩ
				List<Element> chindList = e.elements();
				for (int i = 0; i < chindList.size(); i++) {
					Element e1 = chindList.get(i);
					map.put(e1.getName(), e1.getText());
				}
			} else {
				map.put(e.getName(), e.getText());
			}
		}

		// �ͷ���Դ
		inputStream.close();
		inputStream = null;

		return map;
	}

}
