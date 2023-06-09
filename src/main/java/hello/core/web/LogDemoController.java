package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    // 두 방식의 핵심 아이디어는 진짜 객체 조회를 꼭 필요한 시점까지 지연 처리한다는 것

    // 프록시 사용하지 않는 방식
//    private final ObjectProvider<MyLogger> myLoggerProvider;

//    @RequestMapping("log-demo")
//    @ResponseBody
//    public String logDemo(HttpServletRequest request) throws InterruptedException {
//        String requestURL = request.getRequestURL().toString();
//        MyLogger myLogger = myLoggerProvider.getObject();
//        myLogger.setRequestURL(requestURL);
//
//        myLogger.log("controller test");
//        Thread.sleep(1000);
//        logDemoService.logic("testId");
//        return "OK";
//    }

    // 프록시 사용하는 방식
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
