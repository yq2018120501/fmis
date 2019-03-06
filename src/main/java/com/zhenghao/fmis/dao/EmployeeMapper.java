package com.zhenghao.fmis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhenghao.fmis.entity.Employee;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {

    Employee findAllEmployee();

}
