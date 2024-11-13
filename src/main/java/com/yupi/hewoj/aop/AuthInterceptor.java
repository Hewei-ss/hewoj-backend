//package com.yupi.hewoj.aop;
//
//import com.yupi.hewoj.annotation.AuthCheck;
//import com.yupi.hewoj.model.enums.ResponseCodeEnum;
//import com.yupi.hewoj.exception.BusinessException;
//import com.yupi.hewoj.model.entity.User;
//import com.yupi.hewoj.model.enums.UserRoleEnum;
//import com.yupi.hewoj.service.UserService;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
///**
// * 权限校验 AOP
// *
// */
//@Aspect
//@Component
//public class AuthInterceptor {
//
//    @Resource
//    private UserService userService;
//
//    /**
//     * 执行拦截
//     * 将切点用注解标记，再对这些用该注解标记的方法进行增强
//     *
//     * @param joinPoint
//     * @param authCheck
//     * @return
//     */
//    //用于指定对带有 authCheck 注解的方法进行拦截。切点表达式 @annotation(authCheck) 表示拦截带有 authCheck 注解的方法。
//
//    //todo 这里为什么是authCheck而不是AuthCheck
//    @Around("@annotation(authCheck)")
//    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
//        String mustRole = authCheck.mustRole();//获取注解的成员变量
//        //RequestContextHolder 是 Spring 提供的一个工具类，用于访问当前请求的上下文信息。
//        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
//        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//        // 当前登录用户
//        User loginUser = userService.getLoginUser(request);
//        // 必须有该权限才通过
//        if (StringUtils.isNotBlank(mustRole)) {
//            UserRoleEnum mustUserRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
//            if (mustUserRoleEnum == null) {
//                throw new BusinessException(ResponseCodeEnum.NO_AUTH_ERROR);
//            }
//            String userRole = loginUser.getUserRole();
//            // 如果被封号，直接拒绝
//            if (UserRoleEnum.BAN.equals(mustUserRoleEnum)) {
//                throw new BusinessException(ResponseCodeEnum.NO_AUTH_ERROR);
//            }
//            // 必须有管理员权限
//            if (UserRoleEnum.ADMIN.equals(mustUserRoleEnum)) {
//                if (!mustRole.equals(userRole)) {
//                    throw new BusinessException(ResponseCodeEnum.NO_AUTH_ERROR);
//                }
//            }
//        }
//        // 通过权限校验，放行
//        return joinPoint.proceed();
//    }
//}
//
