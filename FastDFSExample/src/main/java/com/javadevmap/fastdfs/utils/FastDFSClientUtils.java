package com.javadevmap.fastdfs.utils;

import com.javadevmap.fastdfs.model.FileInfoModel;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Map;

public class FastDFSClientUtils {
    private static Logger logger = LoggerFactory.getLogger(FastDFSClientUtils.class);
    /** 配置文件信息 */
    private static final String confFileName="fdfs_client.conf";
    private static TrackerClient trackerClient =null;
    static {
        try {
            String filePath = new ClassPathResource(confFileName).getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
            trackerClient = new TrackerClient();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("FastDFS Client 初始化失败", e);
        }
    }


    private static TrackerServer getTrackerServer() throws IOException {
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerServer;
    }

    private static StorageClient getTrackerClient() throws IOException {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    public static String getTrackerUrl() throws IOException {
        return "http://" + getTrackerServer().getInetSocketAddress().getHostString() + ":" + ClientGlobal.getG_tracker_http_port() + "/";
    }

    public static String[] uploadFile(FileInfoModel file, Map<String,String> extMap) {
        try {
            logger.info("文件名: " + file.getName() + "文件大小:" + file.getContent().length);
            NameValuePair[] metaArr  =null;
            // 添加额外数据
            if(null != extMap){
                metaArr = new NameValuePair[extMap.size()];
                int index=0;
                for(String key:extMap.keySet()){
                    metaArr[index] = new NameValuePair(key, extMap.get(key));
                }
            }
            long startTime = System.currentTimeMillis();
            String[] uploadFileResults = null;
            StorageClient storageClient = null;

            storageClient = getTrackerClient();
            uploadFileResults = storageClient.upload_file(file.getContent(), file.getExtName(), metaArr);

            logger.info("上传文件耗时:" + (System.currentTimeMillis() - startTime) + " ms");

            if (uploadFileResults == null && storageClient != null) {
                logger.error("上传文件失败，错误码:" + storageClient.getErrorCode());
            }
            String groupName = uploadFileResults[0];
            String remoteFileName = uploadFileResults[1];

            logger.info("上传文件成功：" + "group_name:" + groupName + ", remoteFileName:" + " " + remoteFileName);
            return uploadFileResults;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] downloadFile(String groupName, String remoteFileName) {
        try {
            StorageClient storageClient = getTrackerClient();
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            return fileByte;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FileInfo getFile(String groupName, String remoteFileName) {
        try {
            StorageClient storageClient = getTrackerClient();
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteFile(String groupName, String remoteFileName)
            throws Exception {
        System.out.println("deleteFile() called with: groupName = [" + groupName + "], remoteFileName = [" + remoteFileName + "]");
        StorageClient storageClient = getTrackerClient();
        int i = storageClient.delete_file(groupName, remoteFileName);
        logger.info("删除文件成功：" + i);
    }

    public static StorageServer[] getStoreStorages(String groupName)
            throws IOException {
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorages(trackerServer, groupName);
    }

    public static ServerInfo[] getFetchStorages(String groupName, String remoteFileName) throws IOException {
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
    }

}