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

	//���ݲ�����ѯԱ������,������Ա����ʵ�����Ͱ༶����ģ����ѯ
		@SelectProvider(type=EmployeeDynaSqlProvider.class,method="count")
		int count(Map<String, Object> params);
		
	//��̬��ѯ
		@SelectProvider(type=EmployeeDynaSqlProvider.class,method="selectWithParam")
		List<Employee> selectByPage(Map<String, Object> params);

	//��̬����Ա��
		@SelectProvider(type=EmployeeDynaSqlProvider.class,method="insertEmployee")
		void save(Employee employee);

	//����idɾ��Ա��
		@Delete("delete from "+EMPLOYEETABLE+" where id=#{id}")
		void deleteById(Integer id);

	//����id��ѯԱ��
		@Select("select * from "+EMPLOYEETABLE+" where id=#{id}")
		Employee selectById(Integer id);

	//��̬�޸�Ա����Ϣ
		@SelectProvider(type=EmployeeDynaSqlProvider.class,method="updateEmployee")
		void update(Employee employee);
}
