package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    // 두 방식의 핵심 아이디어는 진짜 객체 조회를 꼭 필요한 시점까지 지연 처리한다는 것

    // 프록시 사용하지 않는 방식
//    private final ObjectProvider<MyLogger> myLoggerProvider;
//    public void logic(String id) {
//        MyLogger myLogger = myLoggerProvider.getObject();
//        myLogger.log("service id = " + id);
//    }

    // 프록시 사용하는 방식
    private final MyLogger myLogger;

    public void logic(String id) {
        myLogger.log("service id = " + id);
    }
}
