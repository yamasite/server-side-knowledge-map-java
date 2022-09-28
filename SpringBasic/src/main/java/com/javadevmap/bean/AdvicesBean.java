package com.javadevmap.bean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
/**
 * 通知
 */
public class AdvicesBean {
    //前置通知
    public void before(JoinPoint jp)
    {
        System.out.println("---------->前置通知<----------");
        System.out.println("方法名："+jp.getSignature().getName()
        		+"，参数长度："+jp.getArgs().length+"，被代理对象："+jp.getTarget().getClass().getName());
    }
    //后置通知
    public void after(JoinPoint jp){
        System.out.println("---------->后置通知<----------");
    }
    //环绕通知
    public Object around(ProceedingJoinPoint pjd) throws Throwable{
        System.out.println("---------->环绕开始<----------");
        Object object=pjd.proceed();
        System.out.println("---------->环绕结束<----------");
        return object;
    }
    //异常后通知
    public void afterThrowing(JoinPoint jp,Exception exp)
    {
        System.out.println("---------->异常后通知，发生了异常："+exp.getMessage()+"<----------");
    }
    //返回结果后通知
    public void afterReturning(JoinPoint joinPoint, Object result)
    {
        System.out.println("---------->返回结果后通知<----------");
        System.out.println("结果是："+result);
    }
}