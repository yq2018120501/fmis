package com.zhenghao.fmis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhenghao.fmis.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMapper extends BaseMapper<Users> {

    Users getUsersByUserName(String name);

}
