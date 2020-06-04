package org.fkit.dym.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.fkit.dym.dao.provider.EmployeeDynaSqlProvider;
import org.fkit.dym.domain.Employee;
import static org.fkit.dym.util.common.DymConstants.EMPLOYEETABLE;



public interface EmployeeDao {

	//根据参数查询员工总数,参数是员工真实姓名和班级进行模糊查询
		@SelectProvider(type=EmployeeDynaSqlProvider.class,method="count")
		int count(Map<String, Object> params);
		
	//动态查询
		@SelectProvider(type=EmployeeDynaSqlProvider.class,method="selectWithParam")
		List<Employee> selectByPage(Map<String, Object> params);

	//动态插入员工
		@SelectProvider(type=EmployeeDynaSqlProvider.class,method="insertEmployee")
		void save(Employee employee);

	//根据id删除员工
		@Delete("delete from "+EMPLOYEETABLE+" where id=#{id}")
		void deleteById(Integer id);

	//根据id查询员工
		@Select("select * from "+EMPLOYEETABLE+" where id=#{id}")
		Employee selectById(Integer id);

	//动态修改员工信息
		@SelectProvider(type=EmployeeDynaSqlProvider.class,method="updateEmployee")
		void update(Employee employee);
}
