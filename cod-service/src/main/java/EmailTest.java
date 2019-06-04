import com.tlkj.cod.service.system.SendEmailService;
import com.tlkj.cod.service.system.impl.SendEmailServiceImpl;
import org.junit.Test;

/**
 * @ClassName: FileTest
 * @Description: TODO
 * @Author yjk
 * @Date 2019/5/29 6:13 PM
 */

public class EmailTest {

    @Test
    public void email() {

        SendEmailService sendEmailService;
        sendEmailService = new SendEmailServiceImpl();
        boolean b = sendEmailService.sendEmail("18614234320@163.com", "测试", "12313123123");

    }





}
