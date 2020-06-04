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
 * UserMapper接口
 * @author 范俊杰
 *
 */
public interface UserDao {
	//根据登录名和密码查询用户是否注册
	@Select("select * from "+USERTABLE+" where loginname=#{loginname} and password = #{password}")
	User selectByLoginnameAndPassword(
			@Param("loginname") String loginname,
			@Param("password") String password);
	
	//根据员工注册的信息将员工信息插入到数据库表user_inf中
	@Insert("insert into "+USERTABLE+"(loginname,password,realname,realclass,phonenum) values(#{loginname},#{password},#{realname},#{realclass},#{phonenum})")
	void insertByall(@Param("loginname") String loginname, 
			@Param("password") String password, 
			@Param("realname") String realname, 
			@Param("realclass") String realclass, 
			@Param("phonenum") String phonenum);
	
	//根据用户名查询已注册用户是否审核通过
	@Select("select userstatus from "+USERTABLE+" where loginname=#{loginname}")
	Integer selectByLoginnametostatus(@Param("loginname") String loginname);
	
	//根据参数查询用户总数,参数是用户真实姓名和班级进行模糊查询
	@SelectProvider(type=UserDynaSqlProvider.class,method="count")
	int count(Map<String, Object> params);

	//动态查询
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectWithParam")
	List<User> selectByPage(Map<String, Object> params);

	//根据id删除用户
	@Delete("delete from "+USERTABLE+" where id=#{id}")
	void deleteById(Integer id);

	//根据id查询用户
	@Select("select * from "+USERTABLE+" where id=#{id}")
	User selectById(Integer id);

	//动态修改用户信息
	@SelectProvider(type=UserDynaSqlProvider.class,method="updateUser")
	void update(User user);

	//动态插入用户
	@SelectProvider(type=UserDynaSqlProvider.class,method="insertUser")
	void save(User user);
}
