package com.javadevmap.fastdfs.model;

public class FileInfoModel {
	private String name;
	private byte[] content;
	/**  文件扩展名*/
	private String extName;

	public FileInfoModel() {
	}

	public FileInfoModel(String name, byte[] content,String extName) {
		this.name = name;
		this.content = content;
		this.extName=extName;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getExtName() {
		return extName;
	}

	public void setExtName(String extName) {
		this.extName = extName;
	}
}