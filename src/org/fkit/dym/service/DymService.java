package org.fkit.dym.service;

import java.util.List;

import org.fkit.dym.domain.Appointment;
import org.fkit.dym.domain.Diagnose;
import org.fkit.dym.domain.Employee;
import org.fkit.dym.domain.Notice;
import org.fkit.dym.domain.User;
import org.fkit.dym.util.tag.PageModel;

/**
 * @Description:�����ӿ�
 * @author ������
 *
 */
public interface DymService {
	/**
	 * �û���½
	 * @param loginname
	 * @param password
	 * @return User����
	 */
	User login(String loginname,String password);

	/**
	 * �û�ע��
	 * @param loginname
	 * @param password
	 * @param realname
	 * @param realclass
	 * @param phonenum
	 * @return
	 */
	void register(String loginname, String password, String realname, String realclass, String phonenum);

	
	/**
	 * �ж��û��Ƿ����ͨ��
	 * @param loginname
	 * @return
	 */
	Integer testpass(String loginname);

	
	/**
	 * ��ѯ�����û�
	 * @param user
	 * @param pageModel
	 * @return User�����List����
	 */
	List<User> findUser(User user, PageModel pageModel);
	
	
	/**
	 * ����ѡ�е��û���id��ɾ���û���Ϣ
	 * @param parseInt
	 */
	void removeUserById(Integer id);

	
	/**
	 * ����id����ѯ�û�
	 * @param id
	 * @return
	 */
	User findUserById(Integer id);

	
	/**
	 * ִ���޸��û���Ϣ������flagΪ2��ִ���޸Ĳ���
	 * @param user
	 */
	void modifyUser(User user);

	
	/**
	 * ִ������û���Ϣ�Ĳ�����flagΪ2��ִ����Ӳ���
	 * @param user
	 */
	void addUser(User user);

	/**
	 * ִ�в�ѯԱ����Ϣ�Ĳ���
	 * @param employee
	 * @param pageModel
	 * @return
	 */
	List<Employee> findEmployee(Employee employee, PageModel pageModel);

	/**
	 * ִ�����Ա������
	 * @param employee
	 */
	void addEmployee(Employee employee);

	/**
	 * ִ��ɾ��Ա����Ϣ����
	 * @param parseInt
	 */
	void removeEmployeeById(Integer id);

	/**
	 * ����Ա��id��ѯԱ����Ϣ����
	 * @param id
	 * @return
	 */
	Employee findEmployeeById(Integer id);

	/**
	 * �޸�Ա����Ϣ����
	 * @param employee
	 */
	void modifyEmployee(Employee employee);

	/**
	 * ��ѯ�������
	 * @param notice
	 * @param pageModel
	 * @return
	 */
	List<Notice> findNotice(Notice notice, PageModel pageModel);

	/**
	 * ��ӹ������
	 * @param notice
	 */
	void addNotice(Notice notice);

	/**
	 * ���ݹ���idɾ���������
	 * @param parseInt
	 */
	void removeNoticeById(Integer id);

	/**
	 * ���ݹ���id��ѯ����
	 * @param id
	 * @return
	 */
	Notice findNoticeById(Integer id);

	/**
	 * ִ���޸Ĺ���Ĳ���
	 * @param notice
	 */
	void modifyNotice(Notice notice);

	/**
	 * ��ѯԤԼ��Ϣ
	 * @param appointment
	 * @param pageModel
	 * @return
	 */
	List<Appointment> findAppointment(Appointment appointment, PageModel pageModel);

	/**
	 * ����ԤԼid��ѯԤԼ��Ϣ
	 * @param id
	 * @return
	 */
	Appointment findAppointmentById(Integer id);

	//���ԤԼͨ������
	void updateAppointment(Appointment appointment);

	//��ѯ������Ϣ
	List<Diagnose> findDiagnose(Diagnose diagnose, PageModel pageModel);

	//�ϴ�������Ϣ
	void addDiagnose(Diagnose diagnose);

	//���ݹ�����Ϣ�ĵ���idɾ��������Ϣ
	void removeDiagnoseById(Integer id);

	//����id��ѯ������Ϣ
	Diagnose findDiagnoseById(Integer id);

	//�޸���Ӧ�Ĺ�����Ϣ
	void modifyDiagnose(Diagnose diagnose);

	//��ѯ����ԤԼ��Ϣ
	List<Appointment> findappointmentByUserid(Appointment appointment, PageModel pageModel);

	//��Ӹ���ԤԼ��Ϣ
	void addAppointment(Appointment appointment);

	//����idɾ������ԤԼ��Ϣ
	void removeAppointmentById(Integer id);

	//��ѯ�����ϴ������ĵ�
	List<Diagnose> finddiagnoseByUserid(Diagnose diagnose, PageModel pageModel);

	//�����صĹ����ĵ���Ϣ���뵽�ҵ����ر���
	void addDownload(Diagnose target);

	//��ѯ�������ع����ĵ���Ϣ
	List<Diagnose> findmydownloadByUserid(Diagnose diagnose, PageModel pageModel);

	//ɾ�������ĵ�
	void removeDownloadById(Integer id);
}
