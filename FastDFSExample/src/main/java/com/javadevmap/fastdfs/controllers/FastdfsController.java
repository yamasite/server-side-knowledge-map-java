package com.javadevmap.fastdfs.controllers;

import com.javadevmap.fastdfs.model.FileInfoModel;
import com.javadevmap.fastdfs.utils.FastDFSClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * fastDFS
 */
@Controller
public class FastdfsController {
    private static  final Logger logger = LoggerFactory.getLogger(FastdfsController.class);

    /**
     * 上传页面
     * @return
     */
    @GetMapping("/uploadpage")
    public String index() {
        return "uploadfile";
    }

    /**
     * 上传文件-- 上传后重定向
     */
    @PostMapping("/uploadAction")
    public String fileUploadAction(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        try {
            if (file.isEmpty()) {
                redirectAttributes.addFlashAttribute("message", "请选择要上传的文件");
                return "redirect:uploadStatus";
            }
            String[] arrs = saveFile(file);
            String groupName = arrs[0];
            String remoteFileName = arrs[1];
            //拼接路径
            String path = FastDFSClientUtils.getTrackerUrl() + groupName + "/" + remoteFileName;

            redirectAttributes.addFlashAttribute("message",
                    " 成功上传文件：'" + file.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("path", path);
            redirectAttributes.addFlashAttribute("groupName", groupName);
            redirectAttributes.addFlashAttribute("remoteFileName", remoteFileName);

            String fileName = file.getOriginalFilename();
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            if(ext.equalsIgnoreCase("jpg")){
                redirectAttributes.addFlashAttribute("ext", ext);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传文件失败！！");
        }
        return "redirect:/uploadStatus";
    }

    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request,
                                           @RequestParam("groupName") String groupName,
                                           @RequestParam("remoteFileName") String remoteFileName,
                                           Model model) throws Exception {
        //下载文件路径
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String filename = remoteFileName.substring(remoteFileName.lastIndexOf("/")+1);
        String downloadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFileName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        byte[] file_buff  = FastDFSClientUtils.downloadFile(groupName, remoteFileName);
        return new ResponseEntity<byte[]>(file_buff, headers, HttpStatus.CREATED);
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    /**
     * 保存文件
     */
    public String[] saveFile(MultipartFile multipartFile) throws IOException {
        try {
            String[] fileAbsolutePath;
            String fileName = multipartFile.getOriginalFilename();
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            byte[] file_buff = multipartFile.getBytes();// 获取文件流
            FileInfoModel file = new FileInfoModel(fileName, file_buff, ext);
            Map<String,String> data=new HashMap<>();
            data.put("test","javadevmap");
            // 上传文件
            fileAbsolutePath = FastDFSClientUtils.uploadFile(file,data);
            if (fileAbsolutePath == null) {
                logger.error("上传文件失败，请重新上传！");
            }
            return fileAbsolutePath;
        } catch (Exception e) {
            logger.error("upload file Exception!", e);
        }
        return null;
    }

}
