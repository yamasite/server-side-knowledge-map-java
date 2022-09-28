package com.javadevmap.controller;

import com.javadevmap.mybatis.dao.ProductModelMapper;
import com.javadevmap.mybatis.model.ProductModel;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/file")
public class FileUploadController {


    @Autowired
    ProductModelMapper productModelMapper;


    @RequestMapping(value = "/uploadFile", method = {RequestMethod.POST, RequestMethod.GET})
    public String uploadFile() throws Exception {
        return "uploadFile";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadProductPic(
            HttpServletRequest request,
            Model model,
            @RequestParam("productId") int productId,
            @RequestParam("description") String description,
            MultipartFile file) throws Exception {


        System.out.println(description);
        //原始名称
        String originalFilename = file.getOriginalFilename();
        String newFileName =null;
        //上传图片
        if (file != null && originalFilename != null && originalFilename.length() > 0) {

            //存储图片的物理路径
            String path = request.getServletContext().getRealPath(".");
            System.out.println(path);
            //新的图片名称
            newFileName = "javadevmap" + originalFilename.substring(originalFilename.lastIndexOf("."));
            System.out.println("newFileName = " + newFileName);
            //新图片
            File newFile = new File(path ,newFileName);

            //将内存中的数据写入磁盘
            file.transferTo(newFile);

            model.addAttribute("title", "文件上传");
            model.addAttribute("message", "文件上传成功");
            model.addAttribute("originalFilename", originalFilename);
            model.addAttribute("newFileName", newFileName);
        }else{
            model.addAttribute("title", "文件上传");
            model.addAttribute("message", "文件上传失败");
        }
        // 更新商品信息
        ProductModel productModel = productModelMapper.selectByPrimaryKey(productId);
        if(null != productModel){
            model.addAttribute("productName", productModel.getProductName());
            productModel.setProductPic("download?filename="+newFileName);
            productModel.setProductDesc(description);
            productModelMapper.updateByPrimaryKeySelective(productModel);
        }

//        return "statusTips";
        return "status";

    }


    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request,
                                           @RequestParam("filename") String filename,
                                           Model model) throws Exception {
        //下载文件路径
        String path = request.getServletContext().getRealPath(".");
        File file = new File(path + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFileName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }


}
