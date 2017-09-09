package com.wx.entity;

public class ImgAndTxtMsg {
	private String toUserName;// ���շ��ʺţ��յ���OpenID��
	private String fromUserName;// ������΢�ź�
	private long createTime;// ��Ϣ����ʱ�� �����ͣ�
	private String msgType;// news
	private String articleCount;// ͼ����Ϣ����������Ϊ8������
	private String title;// ͼ����Ϣ����
	private String description;// ͼ����Ϣ����
	private String picUrl;// ͼƬ���ӣ�֧��JPG��PNG��ʽ���Ϻõ�Ч��Ϊ��ͼ360*200��Сͼ200*200
	private String url;// ���ͼ����Ϣ��ת����
	
	public String getXml(){
		return "<xml><ToUserName><![CDATA["+fromUserName+"]]></ToUserName><FromUserName><![CDATA["+toUserName+"]]></FromUserName><CreateTime>"+createTime+"</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1</ArticleCount><Articles><item><Title><![CDATA["+title+"]]></Title> <Description><![CDATA["+description+"]]></Description><PicUrl><![CDATA["+picUrl+"]]></PicUrl><Url><![CDATA["+url+"]]></Url></item></Articles></xml>";
	}
	
	
	
	public ImgAndTxtMsg(String toUserName, String fromUserName, String title,
			String description, String picUrl, String url) {
		super();
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = System.currentTimeMillis();
		this.title = title;
		this.description = description;
		this.picUrl = picUrl;
		this.url = url;
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
	public String getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
