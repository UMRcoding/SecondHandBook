package com.example.demo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.example.demo.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController extends BaseController {
    @Value("${server.port}")
    private String port;

    @Value("${file.ip}")
    private String ip;


    /**
    * 功能描述: 上传接口
    * @Param: [org.springframework.web.multipart.MultipartFile]
    * @Author: Liu Heng
    * @return: com.example.demo.common.Result<?>
    */
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        // 获取源文件的名称
        String originalFilename = file.getOriginalFilename();

        // 定义文件的唯一标识（前缀）
        String flag = IdUtil.fastSimpleUUID();

        // 获取项目根路径，上传的路径
        String rootFilePath = System.getProperty("user.dir") + "/files/" + flag + "_" + originalFilename;

        // 把文件写入到上传的路径
        FileUtil.writeBytes(file.getBytes(), rootFilePath);

        // 返回结果 url
        return Result.success("http://" + ip + ":" + port + "/files/" + flag);
    }

    /**
    * 功能描述: 富文本文件上传接口
    * @Param: [org.springframework.web.multipart.MultipartFile]
    * @Author: Liu Heng
    * @return: cn.hutool.json.JSON
    */
    @PostMapping("/editor/upload")
    public JSON editorUpload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();  // 获取源文件的名称
        // 定义文件的唯一标识（前缀）
        String flag = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir") + "/files/" + flag + "_" + originalFilename;  // 获取上传的路径
        FileUtil.writeBytes(file.getBytes(), rootFilePath);  // 把文件写入到上传的路径
        String url = "http://" + ip + ":" + port + "/files/" + flag;
        JSONObject json = new JSONObject();
        json.set("errno", 0);
        JSONArray arr = new JSONArray();
        JSONObject data = new JSONObject();
        arr.add(data);
        data.set("url", url);
        json.set("data", arr);
        return json;  // 返回结果 url
    }

    /**
    * 功能描述: 下载接口
    * @Param: [java.lang.String, javax.servlet.http.HttpServletResponse]
    * @Author: Liu Heng
    * @return: void
    */
    @GetMapping("/{flag}")
    public void getFiles(@PathVariable String flag, HttpServletResponse response) {
        // 新建一个输出流对象
        OutputStream os;

        // 定于文件上传的根路径
        String basePath = System.getProperty("user.dir") + "/files/";

        // 获取所有的文件名称
        List<String> fileNames = FileUtil.listFileNames(basePath);

        // 找到跟参数一致的文件，通过流的方式输出到浏览器
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try {
            if (StrUtil.isNotEmpty(fileName)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("application/octet-stream");

                // 通过文件的路径读取文件字节流
                byte[] bytes = FileUtil.readBytes(basePath + fileName);

                // 通过输出流返回文件
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }

}
