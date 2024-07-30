package com.yupi.hewoj.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.yupi.hewoj.annotation.AuthCheck;
import com.yupi.hewoj.common.BaseResponse;
import com.yupi.hewoj.common.DeleteRequest;
import com.yupi.hewoj.model.entity.AnswerFavour;
import com.yupi.hewoj.model.entity.QuestionAnswer;
import com.yupi.hewoj.model.entity.QuestionSubmit;
import com.yupi.hewoj.model.enums.ResponseCodeEnum;
import com.yupi.hewoj.common.ResultUtils;
import com.yupi.hewoj.constant.UserConstant;
import com.yupi.hewoj.exception.BusinessException;
import com.yupi.hewoj.exception.ThrowUtils;
import com.yupi.hewoj.model.dto.user.*;
import com.yupi.hewoj.model.entity.User;
import com.yupi.hewoj.model.vo.LoginUserVO;
import com.yupi.hewoj.model.vo.SelfAnswerVO;
import com.yupi.hewoj.model.vo.UserVO;
import com.yupi.hewoj.service.AnswerFavourService;
import com.yupi.hewoj.service.QuestionAnswerService;
import com.yupi.hewoj.service.UserService;
import com.yupi.hewoj.service.impl.AnswerFavourServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.yupi.hewoj.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户接口
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private AnswerFavourService answerFavourService;


    @Resource
    private QuestionAnswerService questionAnswerService;

    /**
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String userRole = userRegisterRequest.getUserRole();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, userRole)) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        long result = userService.userRegister(userRegisterRequest);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(loginUserVO);
    }


    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @GetMapping("/get/login")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        return ResultUtils.success(userService.getLoginUserVO(user));
    }

    /**
     * 创建用户
     *
     * @param userAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addUser(@RequestBody UserAddRequest userAddRequest, HttpServletRequest request) {
        if (userAddRequest == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userAddRequest, user);
        boolean result = userService.save(user);
        ThrowUtils.throwIf(!result, ResponseCodeEnum.OPERATION_ERROR);
        return ResultUtils.success(user.getId());
    }

    /**
     * 删除用户
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        boolean b = userService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新用户
     *
     * @param userUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest,
                                            HttpServletRequest request) {
        if (userUpdateRequest == null || userUpdateRequest.getId() == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest, user);
        boolean result = userService.updateById(user);
        ThrowUtils.throwIf(!result, ResponseCodeEnum.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 更新用户头像
     *
     * @param userAvatar
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/updateuseravatar")
    public BaseResponse<Boolean> updateUserAvatar(@RequestParam String userAvatar,
                                                  HttpServletRequest httpServletRequest) {
        if (httpServletRequest == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        User user = (User) httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
        if (user == null) throw new BusinessException(ResponseCodeEnum.NOT_LOGIN_ERROR);
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id", user.getId());
        userUpdateWrapper.set("userAvatar", userAvatar);
        userService.update(userUpdateWrapper);
        return ResultUtils.success(true);
    }

    /**
     * 根据 id 获取用户（仅管理员）
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<User> getUserById(long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        User user = userService.getById(id);
        ThrowUtils.throwIf(user == null, ResponseCodeEnum.NOT_FOUND_ERROR);
        return ResultUtils.success(user);
    }


    /**
     * 根据用户id分页获取用户收藏的题解
     *
     * @param userQueryThumbAnswerRequest
     * @return
     */

    @PostMapping("/list/gethtumbanswerlistPage")
    public BaseResponse<PageInfo<AnswerFavour>> getThumbAnswerListByUserId(@RequestBody UserQueryThumbAnswerRequest userQueryThumbAnswerRequest) {
        if (userQueryThumbAnswerRequest == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        long size = userQueryThumbAnswerRequest.getPageSize();
        ThrowUtils.throwIf(size > 20, ResponseCodeEnum.PARAMS_ERROR);
        PageInfo<AnswerFavour> answerFavourList = answerFavourService.getthumbanswerlistPage(userQueryThumbAnswerRequest);
        return ResultUtils.success(answerFavourList);
    }


    /**
     * 用户获取自己的题解
     *
     * @param userQuerySelfAnswerRequest
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/list/getselfanswerlistPage")
    public BaseResponse<Page<SelfAnswerVO>> getSelfAnswerListPageByUserId(@RequestBody UserQuerySelfAnswerRequest userQuerySelfAnswerRequest, HttpServletRequest httpServletRequest) {
        if (userQuerySelfAnswerRequest == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        User user = (User) httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
        if (user == null) throw new BusinessException(ResponseCodeEnum.NOT_LOGIN_ERROR);
        long size = userQuerySelfAnswerRequest.getPageSize();
        int current = userQuerySelfAnswerRequest.getCurrent();
        ThrowUtils.throwIf(size > 20, ResponseCodeEnum.PARAMS_ERROR);
        // 从数据库中查询原始的题目提交分页信息
        Page<QuestionAnswer> questionAnswerPage = questionAnswerService.page(new Page<>(current, size),
                questionAnswerService.getQueryWrapperForSelfAnswer(userQuerySelfAnswerRequest, user.getId()));
        // 返回脱敏信息
        return ResultUtils.success(questionAnswerService.getSelfAnswersVOPage(questionAnswerPage));
    }


    /**
     * 根据 id 获取包装类
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<UserVO> getUserVOById(long id, HttpServletRequest request) {
        BaseResponse<User> response = getUserById(id, request);
        User user = response.getData();
        return ResultUtils.success(userService.getUserVO(user));
    }

    /**
     * 分页获取用户列表（仅管理员）
     *
     * @param userQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<User>> listUserByPage(@RequestBody UserQueryRequest userQueryRequest,
                                                   HttpServletRequest request) {
        long current = userQueryRequest.getCurrent();
        long size = userQueryRequest.getPageSize();
        Page<User> userPage = userService.page(new Page<>(current, size),
                userService.getQueryWrapper(userQueryRequest));
        return ResultUtils.success(userPage);
    }

    /**
     * 分页获取用户封装列表
     *
     * @param userQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<UserVO>> listUserVOByPage(@RequestBody UserQueryRequest userQueryRequest,
                                                       HttpServletRequest request) {
        if (userQueryRequest == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        //当前页码
        long current = userQueryRequest.getCurrent();
        //页面大小
        long size = userQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ResponseCodeEnum.PARAMS_ERROR);
        Page<User> userPage = userService.page(new Page<>(current, size),
                userService.getQueryWrapper(userQueryRequest));
        Page<UserVO> userVOPage = new Page<>(current, size, userPage.getTotal());
        List<UserVO> userVO = userService.getUserVO(userPage.getRecords());
        userVOPage.setRecords(userVO);
        return ResultUtils.success(userVOPage);
    }

    // endregion

    /**
     * 更新个人信息
     *
     * @param userUpdateMyRequest
     * @param request
     * @return
     */
    @PostMapping("/update/my")
    public BaseResponse<Boolean> updateMyUser(@RequestBody UserUpdateMyRequest userUpdateMyRequest,
                                              HttpServletRequest request) {
        if (userUpdateMyRequest == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        User user = new User();
        BeanUtils.copyProperties(userUpdateMyRequest, user);
        user.setId(loginUser.getId());
        boolean result = userService.updateById(user);
        ThrowUtils.throwIf(!result, ResponseCodeEnum.OPERATION_ERROR);
        return ResultUtils.success(true);
    }
}
