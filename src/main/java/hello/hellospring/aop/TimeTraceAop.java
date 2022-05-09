package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")//패키지 명, 클래스명, 파라미터 타입 등등
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }

    //AOP를 사용하면 장점
    //핵심 관심사항과 공통 관심사항을 분리한다.
    //원하는 적용 대상을 선택할 수 있다.
    //변경이 필요하면 이 로직만 변경하면 되서 결합도를 낮출 수 있다.
}
