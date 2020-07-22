package com.earth.shiro;

import com.earth.shiro.entity.Dto.UserDetailDto;
import com.earth.shiro.service.UserService;
import com.earth.shiro.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class ShiroApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        String username = "zhangsan";
        UserDetailDto userDetailDto = userService.queryUserRoleAndPermissionByUserName(username);
        System.out.println(">>>>> : " + JsonUtils.toJson(userDetailDto));
    }

}
