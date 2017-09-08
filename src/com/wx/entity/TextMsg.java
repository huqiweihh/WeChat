package com.wx.entity;
/**
 * �ظ��ı���Ϣ
 * @author Administrator
 *
 */
public class TextMsg {
	private String toUserName;// ���շ��ʺţ��յ���OpenID��
	private String fromUserName;// ������΢�ź�
	private long createTime;// ��Ϣ����ʱ�� �����ͣ�
	private String msgType;// text
	private String content;// �ظ�����Ϣ���ݣ����У���content���ܹ����У�΢�ſͻ��˾�֧�ֻ�����ʾ��

	public String getXml() {
		return "<xml><ToUserName><![CDATA["+fromUserName+"]]></ToUserName><FromUserName><![CDATA["+toUserName+"]]></FromUserName><CreateTime>"+createTime+"</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA["+content+"]]></Content></xml>";
	}

	public TextMsg() {
		super();
		msgType = "text";
		createTime = System.currentTimeMillis();
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
