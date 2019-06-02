import com.tlkj.cod.service.system.FileService;
import com.tlkj.cod.service.system.impl.FileOssServiceImpl;
import com.tlkj.cod.service.system.impl.FileQiNiuServiceImpl;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName: FileTest
 * @Description: TODO
 * @Author yjk
 * @Date 2019/5/29 6:13 PM
 */

public class FileTest {

    @Test
    public void main() {
        File file = new File("/Users/yujinkai/Downloads/WechatIMG366.jpeg");
        InputStream io = null;
        try {
            io = new FileInputStream(file);
        } catch (IOException e) {
            System.out.print("获取文件失败, {}");
        }
        String url = "test";
        String fileName = "WechatIMG366.jpeg";
        FileService fileService;
        fileService = new FileOssServiceImpl();
        boolean b = fileService.uploadFile(url, fileName, io);

    }


    @Test
    public void qiniu() {
        File file = new File("/Users/yujinkai/Downloads/WechatIMG366.jpeg");
        InputStream io = null;
        try {
            io = new FileInputStream(file);
        } catch (IOException e) {
            System.out.print("获取文件失败, {}");
        }
        String url = "test";
        String fileName = "WechatIMG366.jpeg";
        FileService fileService;
        fileService = new FileQiNiuServiceImpl();
        boolean b = fileService.uploadFile(url, fileName, io);
    }


}
