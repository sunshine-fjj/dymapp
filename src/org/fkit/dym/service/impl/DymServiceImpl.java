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
 * @Description:�����ӿ�ʵ����
 * @author ������
 *
 */

@Service("dymService")
public class DymServiceImpl implements DymService {
	//�Զ�ע��־ò�dao����
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
	 * DymServiceImpl�ӿ�login����ʵ��
	 * ͨ����¼�����������鿴�û��Ƿ���ע�ᣬ����ֵΪuser����
	 */
	@Override
	public User login(String loginname, String password) {
		// TODO Auto-generated method stub
		return userDao.selectByLoginnameAndPassword(loginname, password);
	}
	
	
	/**
	 * DymServiceImpl�ӿ�testpass����ʵ��
	 * ͨ����¼���鿴��ע����û��Ƿ����ͨ������Χ֮Ϊuserstatus��ֵ��Ϊ1�����ͨ����2���Ϊͨ��
	 */
	@Override
	public Integer testpass(String loginname) {
		// TODO Auto-generated method stub
		return userDao.selectByLoginnametostatus(loginname);
	}
	
	
	/**
	 * DymServiceImpl�ӿ�register����ʵ��
	 * ͨ���û�ע�����Ϣ����Ϣ���뵽���ݿ���
	 */
	@Override
	public void register(String loginname, String password, String realname, String realclass, String phonenum) {
		// TODO Auto-generated method stub
		userDao.insertByall(loginname,password,realname,realclass,phonenum);
	}

    /**
     * DymServiceImpl�ӿ�findUser����ʵ��
     */
	@Override
	public List<User> findUser(User user, PageModel pageModel) {
		// TODO Auto-generated method stub
		//��ǰ��Ҫ��ҳ������������
		Map<String,Object> params=new HashMap<>();
		params.put("user",user);
		int recordCount=userDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0)
		{
			//��ʼ��ҳ��ѯ����ѯ�ڼ�ҳ������
			params.put("pageModel", pageModel);
		}
		List<User> users=userDao.selectByPage(params);
		return users;
	}

	/**
	 * DymServiceImpl�ӿ�removeUserById������ʵ��
	 */
	@Override
	public void removeUserById(Integer id) {
		// TODO Auto-generated method stub
		userDao.deleteById(id);
	}

	/**
	 * DymServiceImpl�ӿ�findUserById����ʵ�֣�����user����
	 */
	@Override
	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.selectById(id);
	}

	/**
	 * DymServiceImpl�ӿ�modifyUser����ʵ�֣�ִ���޸��û�����
	 */
	@Override
	public void modifyUser(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	/**
	 * DymServiceImpl�ӿ�addUser����ʵ�֣�ִ������û�����
	 */
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
		
	}

	/**
	 * DymServiceImpl�ӿ�findEmployee����ʵ�֣�ִ�в�ѯԱ����Ϣ����
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
			//��ʼ��ҳ��ѯ����ѯ�ڼ�ҳ������
			params.put("pageModel", pageModel);
		}
		List<Employee> employees=employeeDao.selectByPage(params);
		return employees;
	}

	/**
	 * DymServiceImpl�ӿ�addEmployee����ʵ�֣�ִ�����Ա����Ϣ����
	 */
	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.save(employee);
	}
	
	/**
	 * DymServiceImpl�ӿ�removeEmployee����ʵ�֣�ִ��ɾ��Ա����Ϣ����
	 */
	@Override
	public void removeEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		employeeDao.deleteById(id);
	}

	/**
	 * DymServiceImpl�ӿ�findEmployeeById����ʵ��
	 */
	@Override
	public Employee findEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		return employeeDao.selectById(id);
	}

	/**
	 *  DymServiceImpl�ӿ�modifyEmployee����ʵ��,ִ���޸�Ա����Ϣ����
	 */
	@Override
	public void modifyEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.update(employee);
	}

	/**
	 * DymServiceImpl�ӿ�findNotice����ʵ��,��ѯ����
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
	 * DymServiceImpl�ӿ�addNotice����ʵ��,��ӹ���
	 */
	@Override
	public void addNotice(Notice notice) {
		// TODO Auto-generated method stub
		noticeDao.save(notice);
	}

	/**
	 * DymServiceImpl�ӿ�removeNotice����ʵ��,ɾ������
	 */
	@Override
	public void removeNoticeById(Integer id) {
		// TODO Auto-generated method stub
		noticeDao.deleteById(id);
	}

	/**
	 *  DymServiceImpl�ӿ�findNoticeById����ʵ��,���ݹ���id��ѯ����
	 */
	@Override
	public Notice findNoticeById(Integer id) {
		// TODO Auto-generated method stub
		return noticeDao.selectById(id);
	}

	/**
	 * DymServiceImpl�ӿ�modifyNotice����ʵ��,�޸Ĺ���
	 */
	@Override
	public void modifyNotice(Notice notice) {
		// TODO Auto-generated method stub
		noticeDao.update(notice);
	}

	/**
	 * DymServiceImpl�ӿ�findAppointment����ʵ��,��ѯԤԼ��Ϣ
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
	 * DymServiceImpl�ӿ�findAppointmentById����ʵ��,����ԤԼid����ѯԤԼ��Ϣ
	 */
	@Override
	public Appointment findAppointmentById(Integer id) {
		// TODO Auto-generated method stub
		return appointmentDao.selectById(id);
	}

	//DymServiceImpl�ӿ�checkAppointment����ʵ�֣����ԤԼͨ��
	@Override
	public void updateAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		appointmentDao.update(appointment);
	}

	//DymServiceImpl�ӿ�findDiagnose����ʵ�֣���ѯ������Ϣ
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
	
	//DymServiceImpl�ӿ�addDiagnose����ʵ�֣���ӹ�����Ϣ
	@Override
	public void addDiagnose(Diagnose diagnose) {
		// TODO Auto-generated method stub
		diagnoseDao.save(diagnose);
	}

	//DymServiceImpl�ӿ�removeDiagnoseById����ʵ�֣����ݹ�����Ϣidɾ��������Ϣ
	@Override
	public void removeDiagnoseById(Integer id) {
		// TODO Auto-generated method stub
		diagnoseDao.deleteById(id);
	}

	//DymServiceImpl�ӿ�findDiagnoseById����ʵ�֣����ݹ�����Ϣid��ѯ������Ϣ
	@Override
	public Diagnose findDiagnoseById(Integer id) {
		// TODO Auto-generated method stub
		return diagnoseDao.selectById(id);
	}

	//DymServiceImpl�ӿ�modifyDiagnose����ʵ�֣��޸Ĺ�����Ϣ
	@Override
	public void modifyDiagnose(Diagnose diagnose) {
		// TODO Auto-generated method stub
		diagnoseDao.update(diagnose);
	}

	//DymServiceImpl�ӿ�findappointmentByUserid����ʵ�֣����ݸ���id����ѯ��������ԤԼ��Ϣ
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

	//DymServiceImpl�ӿ�addAppointment����ʵ�֣���Ӹ���ԤԼ��Ϣ
	@Override
	public void addAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		appointmentDao.save(appointment);
	}

	//DymServiceImpl�ӿ�removeAppointmentById����ʵ�֣���ѯ����ԤԼ��Ϣ
	@Override
	public void removeAppointmentById(Integer id) {
		// TODO Auto-generated method stub
		appointmentDao.deleteById(id);
	}

	//DymServiceImpl�ӿ�finddiagnoseByUserid����ʵ�֣���ѯ�����ϴ������ĵ���Ϣ
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

	//DymServiceImpl�ӿ�addDownload����ʵ�֣����������ص��ĵ���Ϣ��������
	@Override
	public void addDownload(Diagnose target) {
		// TODO Auto-generated method stub
		diagnoseDao.save2(target);
	}

	//DymServiceImpl�ӿ�findmydownloadByUserid����ʵ�֣���ѯ�������صĹ����ĵ���Ϣ
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

	//DymServiceImpl�ӿ�removeDownloadById����ʵ�֣�ɾ�������ĵ�
	@Override
	public void removeDownloadById(Integer id) {
		// TODO Auto-generated method stub
		diagnoseDao.deleteByDownId(id);
	}
	
	
	
	
}
