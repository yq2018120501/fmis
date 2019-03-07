package com.zhenghao.fmis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhenghao.fmis.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<Role> {

    List<String> getAllRoles();

}
