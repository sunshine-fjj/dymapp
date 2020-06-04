package org.fkit.dym.service;

import java.util.List;

import org.fkit.dym.domain.Appointment;
import org.fkit.dym.domain.Diagnose;
import org.fkit.dym.domain.Employee;
import org.fkit.dym.domain.Notice;
import org.fkit.dym.domain.User;
import org.fkit.dym.util.tag.PageModel;

/**
 * @Description:服务层接口
 * @author 范俊杰
 *
 */
public interface DymService {
	/**
	 * 用户登陆
	 * @param loginname
	 * @param password
	 * @return User对象
	 */
	User login(String loginname,String password);

	/**
	 * 用户注册
	 * @param loginname
	 * @param password
	 * @param realname
	 * @param realclass
	 * @param phonenum
	 * @return
	 */
	void register(String loginname, String password, String realname, String realclass, String phonenum);

	
	/**
	 * 判断用户是否审核通过
	 * @param loginname
	 * @return
	 */
	Integer testpass(String loginname);

	
	/**
	 * 查询所有用户
	 * @param user
	 * @param pageModel
	 * @return User对象的List集合
	 */
	List<User> findUser(User user, PageModel pageModel);
	
	
	/**
	 * 根据选中的用户的id来删除用户信息
	 * @param parseInt
	 */
	void removeUserById(Integer id);

	
	/**
	 * 根据id来查询用户
	 * @param id
	 * @return
	 */
	User findUserById(Integer id);

	
	/**
	 * 执行修改用户信息操作，flag为2，执行修改操作
	 * @param user
	 */
	void modifyUser(User user);

	
	/**
	 * 执行添加用户信息的操作，flag为2，执行添加操作
	 * @param user
	 */
	void addUser(User user);

	/**
	 * 执行查询员工信息的操作
	 * @param employee
	 * @param pageModel
	 * @return
	 */
	List<Employee> findEmployee(Employee employee, PageModel pageModel);

	/**
	 * 执行添加员工操作
	 * @param employee
	 */
	void addEmployee(Employee employee);

	/**
	 * 执行删除员工信息操作
	 * @param parseInt
	 */
	void removeEmployeeById(Integer id);

	/**
	 * 根据员工id查询员工信息操作
	 * @param id
	 * @return
	 */
	Employee findEmployeeById(Integer id);

	/**
	 * 修改员工信息操作
	 * @param employee
	 */
	void modifyEmployee(Employee employee);

	/**
	 * 查询公告操作
	 * @param notice
	 * @param pageModel
	 * @return
	 */
	List<Notice> findNotice(Notice notice, PageModel pageModel);

	/**
	 * 添加公告操作
	 * @param notice
	 */
	void addNotice(Notice notice);

	/**
	 * 根据公告id删除公告操作
	 * @param parseInt
	 */
	void removeNoticeById(Integer id);

	/**
	 * 根据公告id查询公告
	 * @param id
	 * @return
	 */
	Notice findNoticeById(Integer id);

	/**
	 * 执行修改公告的操作
	 * @param notice
	 */
	void modifyNotice(Notice notice);

	/**
	 * 查询预约信息
	 * @param appointment
	 * @param pageModel
	 * @return
	 */
	List<Appointment> findAppointment(Appointment appointment, PageModel pageModel);

	/**
	 * 根据预约id查询预约信息
	 * @param id
	 * @return
	 */
	Appointment findAppointmentById(Integer id);

	//审核预约通过操作
	void updateAppointment(Appointment appointment);

	//查询故障信息
	List<Diagnose> findDiagnose(Diagnose diagnose, PageModel pageModel);

	//上传故障信息
	void addDiagnose(Diagnose diagnose);

	//根据故障信息文档的id删除故障信息
	void removeDiagnoseById(Integer id);

	//根据id查询故障信息
	Diagnose findDiagnoseById(Integer id);

	//修改相应的故障信息
	void modifyDiagnose(Diagnose diagnose);

	//查询个人预约信息
	List<Appointment> findappointmentByUserid(Appointment appointment, PageModel pageModel);

	//添加个人预约信息
	void addAppointment(Appointment appointment);

	//根据id删除个人预约信息
	void removeAppointmentById(Integer id);

	//查询个人上传故障文档
	List<Diagnose> finddiagnoseByUserid(Diagnose diagnose, PageModel pageModel);

	//将下载的故障文档信息存入到我的下载表中
	void addDownload(Diagnose target);

	//查询个人下载故障文档信息
	List<Diagnose> findmydownloadByUserid(Diagnose diagnose, PageModel pageModel);

	//删除下载文档
	void removeDownloadById(Integer id);
}
