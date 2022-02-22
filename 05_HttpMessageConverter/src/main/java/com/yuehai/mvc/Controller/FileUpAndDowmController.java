package com.yuehai.mvc.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author 月海
 * @create 2022/1/26 14:57
 */

// @Controller注解，标识该类为控制器
@Controller
public class FileUpAndDowmController {

    // 下载
    // 配置请求地址
    @RequestMapping("/testDown")
    // 返回值为 ResponseEntity<byte[]>，参数 session 获取session参数
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        // 获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        // 获取服务器中文件的真实路径
        String realPath = servletContext.getRealPath("/static/img/1.jpg");
        // 创建输入流
        InputStream is = new FileInputStream(realPath);
        // 创建字节数组，is.available()：获取当前输入文件的字节数
        byte[] bytes = new byte[is.available()];
        // 将流读到字节数组中
        is.read(bytes);
        // 创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        /**
         * 设置要下载方式以及下载文件的名字
         * attachment：以附件的方式下载
         * filename：为当前下载的文件设置的默认的名字
         */
        headers.add("Content-Disposition", "attachment;filename=1.jpg");
        // 设置响应状态码，200
        HttpStatus statusCode = HttpStatus.OK;
        /**
         * 创建ResponseEntity对象
         * 参数1，bytes：存放了当前要下载的文件所有的字节流数据
         * 参数2，headers：响应头，map类型的键值对信息
         * 参数3，statusCode：
         */
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bytes, headers, statusCode);
        // 关闭输入流
        is.close();

        // 返回 responseEntity 响应报文
        return responseEntity;
    }

    // 配置请求地址
    @RequestMapping("/testUp")
    // MultipartFile：将上传的文件封装到了对象 photo 中
    // HttpSession：作用是获取服务器路径
    public String testUp(MultipartFile photo, HttpSession session) throws IOException {
        // 获取上传的文件的文件名
        String fileName = photo.getOriginalFilename();

        // 处理文件重名问题
        /**
         * 获取上传的文件的后缀名
         * lastIndexOf(String str)：从最右边开始查找，返回指定字符（包括）出现处的索引
         * substring(int beginIndex)：从 beginIndex 开始，截取至最后的字符串
         */
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        // 将 UUID 作为文件名，并在后面拼接上后缀名
        // replaceAll：将 UUID 中的 - 替换为空字符串
        fileName = UUID.randomUUID().toString().replaceAll("-","") + hzName;

        // 获取服务器中photo目录的路径（上传到服务器的位置）
        ServletContext servletContext = session.getServletContext();
        String photoPath = servletContext.getRealPath("photo");

        // 创建 photoPath 的文件映射
        File file = new File(photoPath);
        // 判断 photoPath 所对应的路径是否存在
        if(!file.exists()){
            // 不存在则创建目录
            file.mkdir();
        }
        // 确定文件上传的路径
        // File.separator：分隔符，可以解决操作系统不一样导致的分隔符不一样的问题
        String finalPath = photoPath + File.separator + fileName;

        //实现上传功能，抛出异常
        photo.transferTo(new File(finalPath));

        return "success";
    }

}

