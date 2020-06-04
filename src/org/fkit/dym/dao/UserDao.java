package org.fkit.dym.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.fkit.dym.dao.provider.UserDynaSqlProvider;
import org.fkit.dym.domain.User;
import static org.fkit.dym.util.common.DymConstants.USERTABLE;

import java.util.List;
import java.util.Map;

/**
 * UserMapper�ӿ�
 * @author ������
 *
 */
public interface UserDao {
	//���ݵ�¼���������ѯ�û��Ƿ�ע��
	@Select("select * from "+USERTABLE+" where loginname=#{loginname} and password = #{password}")
	User selectByLoginnameAndPassword(
			@Param("loginname") String loginname,
			@Param("password") String password);
	
	//����Ա��ע�����Ϣ��Ա����Ϣ���뵽���ݿ��user_inf��
	@Insert("insert into "+USERTABLE+"(loginname,password,realname,realclass,phonenum) values(#{loginname},#{password},#{realname},#{realclass},#{phonenum})")
	void insertByall(@Param("loginname") String loginname, 
			@Param("password") String password, 
			@Param("realname") String realname, 
			@Param("realclass") String realclass, 
			@Param("phonenum") String phonenum);
	
	//�����û�����ѯ��ע���û��Ƿ����ͨ��
	@Select("select userstatus from "+USERTABLE+" where loginname=#{loginname}")
	Integer selectByLoginnametostatus(@Param("loginname") String loginname);
	
	//���ݲ�����ѯ�û�����,�������û���ʵ�����Ͱ༶����ģ����ѯ
	@SelectProvider(type=UserDynaSqlProvider.class,method="count")
	int count(Map<String, Object> params);

	//��̬��ѯ
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectWithParam")
	List<User> selectByPage(Map<String, Object> params);

	//����idɾ���û�
	@Delete("delete from "+USERTABLE+" where id=#{id}")
	void deleteById(Integer id);

	//����id��ѯ�û�
	@Select("select * from "+USERTABLE+" where id=#{id}")
	User selectById(Integer id);

	//��̬�޸��û���Ϣ
	@SelectProvider(type=UserDynaSqlProvider.class,method="updateUser")
	void update(User user);

	//��̬�����û�
	@SelectProvider(type=UserDynaSqlProvider.class,method="insertUser")
	void save(User user);
}
