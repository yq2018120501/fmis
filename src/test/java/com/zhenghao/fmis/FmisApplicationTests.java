package com.zhenghao.fmis;

import com.zhenghao.fmis.dao.EmployeeMapper;
import com.zhenghao.fmis.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FmisApplicationTests {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void contextLoads() {

        System.out.println(("----- selectAll method test ------"));
//        List<Employee> userList =employeeMapper.selectList(null);
//        for (Employee employee : userList) {
//            System.out.println(employee);
//        }

        Employee e = employeeMapper.findAllEmployee();
        System.out.println(e);

        System.out.println("----------结束-----------------");
    }

}
