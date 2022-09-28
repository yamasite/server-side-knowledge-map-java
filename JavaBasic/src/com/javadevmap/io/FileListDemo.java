package com.javadevmap.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileListDemo {
	
	public static List<String> getFileListByDir(File dir){
		List<String> list = new ArrayList<>();
		for(File item : dir.listFiles()) {
			if(item.isDirectory()) {
				list.addAll(getFileListByDir(item));
			}else {
				list.add(item.getName());
			}
		}
		return list;
	}
	
	public static List<String> getFileList(String uri) {
		List<String> list = new ArrayList<>();
		File file = new File(uri);
		if(file.isDirectory()) {
			list.addAll(getFileListByDir(file));
		}else if(file.exists()&&file.isFile()){
			list.add(uri);
		}else if(!file.exists()) {
			System.out.println("file not found");
		}
		return list;
	}
	
	public static void main(String[] args) {
		System.out.println(getFileList("D:\\projects\\JavaDeveloperMap\\JavaBasic\\src"));
	}
}
