package org.fkit.dym.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fkit.dym.dao.AppointmentDao;
import org.fkit.dym.dao.DiagnoseDao;
import org.fkit.dym.dao.EmployeeDao;
import org.fkit.dym.dao.NoticeDao;
import org.fkit.dym.dao.UserDao;
import org.fkit.dym.domain.Appointment;
import org.fkit.dym.domain.Diagnose;
import org.fkit.dym.domain.Employee;
import org.fkit.dym.domain.Notice;
import org.fkit.dym.domain.User;
import org.fkit.dym.service.DymService;
import org.fkit.dym.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:服务层接口实现类
 * @author 范俊杰
 *
 */

@Service("dymService")
public class DymServiceImpl implements DymService {
	//自动注入持久层dao对象
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private AppointmentDao appointmentDao;
	
	@Autowired
	private DiagnoseDao diagnoseDao;
	
	/**
	 * DymServiceImpl接口login方法实现
	 * 通过登录名和密码来查看用户是否已注册，返回值为user对象
	 */
	@Override
	public User login(String loginname, String password) {
		// TODO Auto-generated method stub
		return userDao.selectByLoginnameAndPassword(loginname, password);
	}
	
	
	/**
	 * DymServiceImpl接口testpass方法实现
	 * 通过登录名查看已注册的用户是否审核通过，范围之为userstatus的值，为1则审核通过，2审核为通过
	 */
	@Override
	public Integer testpass(String loginname) {
		// TODO Auto-generated method stub
		return userDao.selectByLoginnametostatus(loginname);
	}
	
	
	/**
	 * DymServiceImpl接口register方法实现
	 * 通过用户注册的信息将信息插入到数据库中
	 */
	@Override
	public void register(String loginname, String password, String realname, String realclass, String phonenum) {
		// TODO Auto-generated method stub
		userDao.insertByall(loginname,password,realname,realclass,phonenum);
	}

    /**
     * DymServiceImpl接口findUser方法实现
     */
	@Override
	public List<User> findUser(User user, PageModel pageModel) {
		// TODO Auto-generated method stub
		//当前需要分页的总数据条数
		Map<String,Object> params=new HashMap<>();
		params.put("user",user);
		int recordCount=userDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0)
		{
			//开始分页查询，查询第几页的数据
			params.put("pageModel", pageModel);
		}
		List<User> users=userDao.selectByPage(params);
		return users;
	}

	/**
	 * DymServiceImpl接口removeUserById方法的实现
	 */
	@Override
	public void removeUserById(Integer id) {
		// TODO Auto-generated method stub
		userDao.deleteById(id);
	}

	/**
	 * DymServiceImpl接口findUserById方法实现，返回user对象
	 */
	@Override
	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.selectById(id);
	}

	/**
	 * DymServiceImpl接口modifyUser方法实现，执行修改用户操作
	 */
	@Override
	public void modifyUser(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	/**
	 * DymServiceImpl接口addUser方法实现，执行添加用户操作
	 */
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
		
	}

	/**
	 * DymServiceImpl接口findEmployee方法实现，执行查询员工信息操作
	 */
	@Override
	public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<>();
		params.put("employee",employee);
		int recordCount=employeeDao.count(params);
		
		System.out.println(recordCount);
		
		pageModel.setRecordCount(recordCount);
		if(recordCount>0)
		{
			//开始分页查询，查询第几页的数据
			params.put("pageModel", pageModel);
		}
		List<Employee> employees=employeeDao.selectByPage(params);
		return employees;
	}

	/**
	 * DymServiceImpl接口addEmployee方法实现，执行添加员工信息操作
	 */
	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.save(employee);
	}
	
	/**
	 * DymServiceImpl接口removeEmployee方法实现，执行删除员工信息操作
	 */
	@Override
	public void removeEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		employeeDao.deleteById(id);
	}

	/**
	 * DymServiceImpl接口findEmployeeById方法实现
	 */
	@Override
	public Employee findEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		return employeeDao.selectById(id);
	}

	/**
	 *  DymServiceImpl接口modifyEmployee方法实现,执行修改员工信息操作
	 */
	@Override
	public void modifyEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.update(employee);
	}

	/**
	 * DymServiceImpl接口findNotice方法实现,查询公告
	 */
	@Override
	public List<Notice> findNotice(Notice notice, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<>();
		params.put("notice",notice);
		int recordCount=noticeDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0)
		{
			params.put("pageModel", pageModel);
		}
		List<Notice> notices=noticeDao.selectByPage(params);
		return notices;
	}

	/**
	 * DymServiceImpl接口addNotice方法实现,添加公告
	 */
	@Override
	public void addNotice(Notice notice) {
		// TODO Auto-generated method stub
		noticeDao.save(notice);
	}

	/**
	 * DymServiceImpl接口removeNotice方法实现,删除公告
	 */
	@Override
	public void removeNoticeById(Integer id) {
		// TODO Auto-generated method stub
		noticeDao.deleteById(id);
	}

	/**
	 *  DymServiceImpl接口findNoticeById方法实现,根据公告id查询公告
	 */
	@Override
	public Notice findNoticeById(Integer id) {
		// TODO Auto-generated method stub
		return noticeDao.selectById(id);
	}

	/**
	 * DymServiceImpl接口modifyNotice方法实现,修改公告
	 */
	@Override
	public void modifyNotice(Notice notice) {
		// TODO Auto-generated method stub
		noticeDao.update(notice);
	}

	/**
	 * DymServiceImpl接口findAppointment方法实现,查询预约信息
	 */
	@Override
	public List<Appointment> findAppointment(Appointment appointment, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<>();
		params.put("appointment",appointment);
		int recordCount=appointmentDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0)
		{
			params.put("pageModel", pageModel);
		}
		List<Appointment> appointments=appointmentDao.selectByPage(params);
		return appointments;
	}

	/**
	 * DymServiceImpl接口findAppointmentById方法实现,根据预约id来查询预约信息
	 */
	@Override
	public Appointment findAppointmentById(Integer id) {
		// TODO Auto-generated method stub
		return appointmentDao.selectById(id);
	}

	//DymServiceImpl接口checkAppointment方法实现，审核预约通过
	@Override
	public void updateAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		appointmentDao.update(appointment);
	}

	//DymServiceImpl接口findDiagnose方法实现，查询故障信息
	@Override
	public List<Diagnose> findDiagnose(Diagnose diagnose, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<>();
		params.put("diagnose",diagnose);
		int recordCount=diagnoseDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0)
		{
			params.put("pageModel", pageModel);
		}
		List<Diagnose> diagnoses=diagnoseDao.selectByPage(params);
		return diagnoses;
	}
	
	//DymServiceImpl接口addDiagnose方法实现，添加故障信息
	@Override
	public void addDiagnose(Diagnose diagnose) {
		// TODO Auto-generated method stub
		diagnoseDao.save(diagnose);
	}

	//DymServiceImpl接口removeDiagnoseById方法实现，根据故障信息id删除故障信息
	@Override
	public void removeDiagnoseById(Integer id) {
		// TODO Auto-generated method stub
		diagnoseDao.deleteById(id);
	}

	//DymServiceImpl接口findDiagnoseById方法实现，根据故障信息id查询故障信息
	@Override
	public Diagnose findDiagnoseById(Integer id) {
		// TODO Auto-generated method stub
		return diagnoseDao.selectById(id);
	}

	//DymServiceImpl接口modifyDiagnose方法实现，修改故障信息
	@Override
	public void modifyDiagnose(Diagnose diagnose) {
		// TODO Auto-generated method stub
		diagnoseDao.update(diagnose);
	}

	//DymServiceImpl接口findappointmentByUserid方法实现，根据个人id来查询个人所有预约信息
	@Override
	public List<Appointment> findappointmentByUserid(Appointment appointment, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<>();
		params.put("appointment", appointment);
		int recordCount=appointmentDao.count2(params);
		
		pageModel.setRecordCount(recordCount);
		if(recordCount>0) {
			params.put("pageModel", pageModel);
		}
		List<Appointment> appointments=appointmentDao.selectByPage2(params);
		return appointments;
	}

	//DymServiceImpl接口addAppointment方法实现，添加个人预约信息
	@Override
	public void addAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		appointmentDao.save(appointment);
	}

	//DymServiceImpl接口removeAppointmentById方法实现，查询个人预约信息
	@Override
	public void removeAppointmentById(Integer id) {
		// TODO Auto-generated method stub
		appointmentDao.deleteById(id);
	}

	//DymServiceImpl接口finddiagnoseByUserid方法实现，查询个人上传故障文档信息
	@Override
	public List<Diagnose> finddiagnoseByUserid(Diagnose diagnose, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<>();
		params.put("diagnose", diagnose);
		int recordCount=diagnoseDao.count3(params);
		
		pageModel.setRecordCount(recordCount);
		if(recordCount>0) {
			params.put("pageModel", pageModel);
		}
		List<Diagnose> diagnoses=diagnoseDao.selectByPage3(params);
		return diagnoses;
	}

	//DymServiceImpl接口addDownload方法实现，将个人下载的文档信息保存起来
	@Override
	public void addDownload(Diagnose target) {
		// TODO Auto-generated method stub
		diagnoseDao.save2(target);
	}

	//DymServiceImpl接口findmydownloadByUserid方法实现，查询个人下载的故障文档信息
	@Override
	public List<Diagnose> findmydownloadByUserid(Diagnose diagnose, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<>();
		params.put("diagnose", diagnose);
		int recordCount=diagnoseDao.count4(params);
		
		pageModel.setRecordCount(recordCount);
		if(recordCount>0) {
			params.put("pageModel", pageModel);
		}
		List<Diagnose> diagnoses=diagnoseDao.selectByPage4(params);
		return diagnoses;
	}

	//DymServiceImpl接口removeDownloadById方法实现，删除下载文档
	@Override
	public void removeDownloadById(Integer id) {
		// TODO Auto-generated method stub
		diagnoseDao.deleteByDownId(id);
	}
	
	
	
	
}
