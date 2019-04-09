package priv.sjc.base.aspect.syslog;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect//申明是个切面
@Component//申明是个spring管理的bean
@Order(1) //标记切面类的处理优先级,i值越小,优先级别越高.PS:可以注解类,也能注解到方法上注解类,i值是,值越小,优先级越高
//注解方法,分两种情况 
//注解的是 @Before 是i值越小,优先级越高
//注解的是 @After或者@AfterReturning 中,i值越大,优先级越高
//总结两者的概括就是:
//在切入点前的操作，按order的值由小到大执行
//在切入点后的操作，按order的值由大到小执行
/**
 * @Pointcut 定义一个方法为切点里面的内容为一个表达式,下面详细介绍
@Before 在切点前执行方法,内容为指定的切点
@After 在切点后,return前执行,
@AfterReturning 在切入点,return后执行,如果想对某些方法的返回参数进行处理,可以在这操作
@Around 环绕切点,在进入切点前,跟切点后执行
@AfterThrowing 在切点后抛出异常进行处理
@order(i) 标记切点的优先级,i越小,优先级越高
 * @author 20162
 *
 */
public class SysLogAspect {

//	@Autowired
//	private SysLogService sysLogService;
	
	@Pointcut("@annotation(priv.sjc.base.aspect.SysLog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		//保存日志
		saveSysLog(point, time);

		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		//从切面织入点处通过反射机制获取织入点处的方法
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		 //获取切入点所在的方法
		Method method = signature.getMethod();
//
//		SysLogEntity sysLog = new SysLogEntity();
//		SysLog syslog = method.getAnnotation(SysLog.class);
//		if(syslog != null){
//			//注解上的描述
//			sysLog.setOperation(syslog.value());
//		}
//
//		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
//		sysLog.setMethod(className + "." + methodName + "()");
//
		//请求的参数
		Object[] args = joinPoint.getArgs();
//		try{
//			String params = JSON.toJSONString(args);
//			String params = new Gson().toJson(args);
//			sysLog.setParams(params);
//		}catch (Exception e){
//
//		}
//
//		//获取request
//		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
//		//设置IP地址
//		sysLog.setIp(IPUtils.getIpAddr(request));
//
//		//用户名
//		String username = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
//		sysLog.setUsername(username);
//
//		sysLog.setTime(time);
//		sysLog.setCreateDate(new Date());
//		//保存系统日志
//		sysLogService.insert(sysLog);
	}
}
