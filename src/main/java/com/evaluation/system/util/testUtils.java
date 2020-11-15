package com.evaluation.system.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.evaluation.system.Service.Impl.ExamineServiceImpl;
import com.evaluation.system.util.ExcelUtils.ExcelUtils;

import java.io.File;
import java.io.IOException;

public class testUtils {

    public static void main(String[] args) {
        String router = "https://jyuxuan.oss-cn-beijing.aliyuncs.com/jyuxuan/2018212602-3b5a51f1-4178-4e5b-bd4f-534f0345e150.jpg";
        String[] ss = router.split("/");
        int length = ss.length;
        String url = ss[length - 1];
        System.out.println(url);
        String id = "LTAI4GFGWpKEHn6ba8cWeQWp";
        String key = "LW9kOwqLEuiEJEfzU4y7IbY0OeokMn";
        String bucket = "jyuxuan";
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        String dir = "jyuxuan/";
        OSS ossClient = new OSSClientBuilder().build(endpoint, id, key);
        ossClient.deleteObject(bucket, dir + url);
        System.out.println("删除 " + url + " 成功");
        ossClient.shutdown();
    }

}
