package com.wx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.wx.entity.ImgAndTxtMsg;
import com.wx.entity.TextMsg;
import com.wx.util.Api;
import com.wx.util.Token;
import com.wx.util.XmlUtil;

public class WeChatServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WeChatServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// ΢�ż���ǩ����signature����˿�������д��token�����������е�timestamp������nonce������
		String signature = request.getParameter("signature");
		// ʱ���
		String timestamp = request.getParameter("timestamp");
		// �����
		String nonce = request.getParameter("nonce");
		// ����ַ���
		String echostr = request.getParameter("echostr");
		System.out.println("signature=" + signature);
		System.out.println("timestamp=" + timestamp);
		System.out.println("nonce=" + nonce);
		System.out.println("echostr=" + echostr);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			// ͨ������signature���������У�飬��У��ɹ���ԭ������echostr���������ʧ��
			// if (SignUtil.checkSignature(signature,timestamp, nonce)) {
			out.print(echostr);
			// }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
			out = null;
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String result = "success";
		try {
			Map<String, String> map = XmlUtil.parseXml(request);
			// Ĭ�ϻظ�һ��"success"
			System.out.println("******************");
			for (String key : map.keySet()) {

				System.out.println("Key = " + key + "---value = "
						+ map.get(key));

			}
			System.out.println("******************");
			String msgType = map.get("MsgType");
			String fromUserName = map.get("FromUserName");// ���ͷ��ʺţ�һ��OpenID��
			String toUserName = map.get("ToUserName"); // ������΢�ź�

			if (msgType.equals("event")) {// �ж���Ϣ����(event=����˵���������Ϣ����)
				String event = map.get("Event");
				if (event.equals("CLICK")) {// �˵�����¼�
					String key = map.get("EventKey");
					if (key.equals("msg")) {// ��ȡ������Ϣ
						String userMsg = Api
								.get("https://api.weixin.qq.com/cgi-bin/user/info?access_token="
										+ Token.wx_token
										+ "&openid="
										+ fromUserName + "&lang=zh_CN");
						JSONObject object = JSONObject.fromObject(userMsg);
						String nickname = object.getString("nickname");
						String sex = object.getString("sex").equals("1")?"��":"Ů";
						String city = object.getString("city");
						String province = object.getString("province");
						String country = object.getString("country");
						String headimgurl = object.getString("headimgurl");

						ImgAndTxtMsg msg = new ImgAndTxtMsg(toUserName,
								fromUserName, nickname, "�Ա�"+sex+"\n���У�"+province+city, headimgurl, "http://www.baidu.com");
						result = (msg.getXml());
						
					}

				} else if (event.equals("location_select")) {// ����˵���ת�ϱ�����λ���¼�
				// String Location_X = map.get("Location_X");// γ��
				// String Location_Y = map.get("Location_Y"); // ����
				// String Label = map.get("Label"); // λ����Ϣ
				// String Poiname = map.get("Poiname"); // poi��
				// TextMsg tmMsg = new TextMsg();
				// tmMsg.setFromUserName(fromUserName);
				// tmMsg.setToUserName(toUserName);
				// tmMsg.setContent("γ�ȣ�"+
				// Location_X+"\n���ȣ�"+Location_Y+"\nλ����Ϣ��"+Label+"\n"+Poiname);
				// result = (tmMsg.getXml());

				} else if (event.equals("VIEW")) {// ����˵���ת����ʱ���¼�

				} else if (event.equals("scancode_waitmsg")) {// ���ɨ���¼�

					String ScanResult = map.get("ScanResult"); //
					TextMsg tmMsg = new TextMsg();
					tmMsg.setFromUserName(fromUserName);
					tmMsg.setToUserName(toUserName);
					tmMsg.setContent("ɨ������" + ScanResult);
					result = (tmMsg.getXml());
				}
			} else if (msgType.equals("text")) {
				String content = map.get("Content"); // �û����͵���Ϣ����
				System.out.println(fromUserName + "����̨��������Ϣ��" + content);
				String userMsg = Api
						.get("http://apicloud.mob.com/v1/weather/query?key=appkey&city="
								+ URLEncoder.encode(content, "utf-8")
								+ "&province=");
				TextMsg tmMsg = new TextMsg();
				tmMsg.setFromUserName(fromUserName);
				tmMsg.setToUserName(toUserName);
				tmMsg.setContent(userMsg);
				result = (tmMsg.getXml());
			} else if (msgType.equals("location")) {

				System.out.println(fromUserName + "����̨������λ����Ϣ");
				String Location_X = map.get("Location_X");// γ��
				String Location_Y = map.get("Location_Y"); // ����
				String Label = map.get("Label"); // λ����Ϣ
				TextMsg tmMsg = new TextMsg();
				tmMsg.setFromUserName(fromUserName);
				tmMsg.setToUserName(toUserName);
				tmMsg.setContent("γ�ȣ�" + Location_X + "\n���ȣ�" + Location_Y
						+ "\nλ����Ϣ��" + Label);
				result = (tmMsg.getXml());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(result);
		out.print(result);
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
