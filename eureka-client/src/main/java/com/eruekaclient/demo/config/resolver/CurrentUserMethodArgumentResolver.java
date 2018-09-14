package com.eruekaclient.demo.config.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户
 * Created by 7winkle on 2017/4/20.
 */

@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

//    @Autowired
//    private UserServiceV3 userServiceV3;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
//        //如果参数类型是User并且有CurrentUser注解则支持
//        if (methodParameter.getParameterType().isAssignableFrom(UserEntity.class) &&
//                methodParameter.hasParameterAnnotation(CurrentUser.class)) {
//            return true;
//        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
//        //取出鉴权时存入的登录用户Id
//        String currentUserId = (String) nativeWebRequest.getAttribute(Constants.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
//        if (currentUserId != null) {
//            //从数据库中查询并返回
//            UserEntity user = userServiceV3.getById(currentUserId);
//            if (user != null) {
//                return user;
//            }
//        }
//        throw new GlobalErrorInfoException(GlobalErrorInfoEnum.UNAUTHORIZED);
        return null;
    }
}
