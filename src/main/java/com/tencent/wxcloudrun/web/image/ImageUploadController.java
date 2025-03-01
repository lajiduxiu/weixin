package com.tencent.wxcloudrun.web.image;

import com.tencent.wxcloudrun.utils.ResultUtils;
import com.tencent.wxcloudrun.utils.ResultVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class ImageUploadController {
    @Value("${web.uploadpath}")
    private String webUploadPath;

    @RequestMapping("/uploadImage")
    public ResultVo uploadImage(@RequestParam("file") MultipartFile file) {
        String Url = "";
        String fileName = file.getOriginalFilename();
    //获取文件拓展名
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
//      新的文件名
        String newName = UUID.randomUUID() +fileExtensionName;
        String path = webUploadPath;
        File fileDIR = new File(path);
        if (!fileDIR.exists()) {
            fileDIR.mkdirs();
            //设置权限
            fileDIR.setWritable(true);
        }
        File targetFile = new File(path, newName);
        try {
            file.transferTo(targetFile);
            Url = "/"+targetFile.getName();
        } catch (Exception e) {
            return null;
        }
        return ResultUtils.success("上传成功","/images"+Url);
    }
}
