package org.fkit.dym.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.fkit.dym.domain.User;
import org.fkit.dym.service.DymService;
import org.fkit.dym.util.common.DymConstants;
import org.fkit.dym.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * �����û����������
 * @author ������
 *
 */
@Controller
public class UserController {
	//�Զ�ע��UserService
	@Autowired
	@Qualifier("dymService")
	private DymService dymService;
	/**
	 * �����½����
	 * @param loginname ��¼��
	 * @param password ����
	 * @param session ����user����
	 * @param mv ��ת����ͼ
	 */
	@RequestMapping(value="/login")
	public ModelAndView login(
			@RequestParam("loginname") String loginname,
			@RequestParam("password") String password,
			@RequestParam("flag") Integer flag,
			HttpSession session,
			ModelAndView mv) {
		//����ҵ���߼�����ж��û��Ƿ���Ե�½
		User user=dymService.login(loginname, password);
		Integer status=dymService.testpass(loginname);
		if(flag==1)
		{
			if(user!=null) {
				//���û����浽httpSession��
				session.setAttribute(DymConstants.USER_SESSION, user);
				if(loginname.equals("admin"))
				{
					//����Ա��½��ת��mainҳ�棬�ͻ�����ת
					mv.setViewName("redirect:/main");
				}
				else
				{
					if(status==1)
					{
						//��ͨ������û���½��ת��usermianҳ��
						mv.setViewName("redirect:/usermain");
					}
					else
					{
						//��ͨδ����û���ת��LoginForm
						mv.addObject("message","�û���Ϣδ��ˣ���ȴ����ͨ���ٵ�½");
						mv.setViewName("forward:/LoginForm");
					}
					
				}
			}else {
				//���õ�½ʧ����ʾ��Ϣ
				mv.addObject("message","�û�δע����ߵ�¼���������������������");
				//�������ڲ���ת����½ҳ��
				mv.setViewName("forward:/LoginForm");
			}
		}else {
			mv.addObject("message","��֤�����");
			mv.setViewName("forward:/LoginForm");
		}
		
		return mv;
	}
	/**
	 * �����û�ע����Ϣ
	 * @param loginname
	 * @param password
	 * @param realname
	 * @param realclass
	 * @param phonenum
	 * @param mv
	 * @return
	 */
	  @RequestMapping(value="/register") 
	  public ModelAndView register(
			  @RequestParam("loginname") String loginname,
			  @RequestParam("password") String password,
			  @RequestParam("realname") String realname,
			  @RequestParam("realclass") String realclass,
			  @RequestParam("phonenum") String phonenum,
			  ModelAndView mv) { 
		  dymService.register(loginname,password,realname,realclass,phonenum);
			 mv.setViewName("redirect:/LoginForm");
		  return mv;
	}
	  
	/**
	 * �����ѯ�û����󣬽�pageIndex,recordCount(��DymServiceImpl�е���count��̬��ѯ����),pageSize(��DymServiceImpl�е���userDao.selectByPage(params)��ҳ��̬��ѯ)
	 * ���ߵ�ֵ�ŵ�pageModel�д���user.jspҳ���У����з�ҳ��users����������user.jspҳ������ʾ��ѯ������Ϣ��
	 * @param pageIndex
	 * @param user
	 * @param model
	 * @return
	 */
	 @RequestMapping(value="/user/selectUser")
	 public String selectUser(Integer pageIndex,
			 @ModelAttribute User user,
			 Model model) {
 		PageModel pageModel=new PageModel();
 		if(pageIndex!=null)
 		{
 			pageModel.setPageIndex(pageIndex);
 		}
 		List<User> users=dymService.findUser(user,pageModel);
 		model.addAttribute("users",users);
 		model.addAttribute("pageModel",pageModel);
		return "user/user";
	 }
	 
	 
	 
	 
	 @RequestMapping(value="/user/selectUser2")
	 public String selectUser2(Model model,Integer id) {
		 User target=dymService.findUserById(id);
		 
		 System.out.print("�ҵ�id:");
		 System.out.print(id);
		 
		 model.addAttribute("user",target);
		 return "user/userinfo";
	 }
	 
	 
	 
	 
	 /**
	  * ����ɾ���û�����������ѡ�е��û�id��ɾ���û���Ϣ
	  * @param ids
	  * @param mv
	  * @return
	  */
	 @RequestMapping(value="/user/removeUser")
	 public ModelAndView removeUser(String ids,ModelAndView mv) {
		 String[] idArray=ids.split(",");//�ֽ�id�ַ���
		 for(String id:idArray) {
			 dymService.removeUserById(Integer.parseInt(id));
		 }
		 mv.setViewName("redirect:/user/selectUser");
		 return mv;
	 }
	 
	 /**
	  * �����޸��û���Ϣ�Ĳ���
	  * @param flag ��ǣ�1��ʾ��ת���޸�ҳ�棬2��ʾִ���޸Ĳ���
	  * @param user ��Ҫ�޸ĵ��û�
	  * @param mv
	  * @return
	  */
	 @RequestMapping(value="/user/updateUser")
	 public ModelAndView updateUser(String flag,
			 @ModelAttribute User user,
			 ModelAndView mv) {
		 if(flag.equals("1")){
			 User target=dymService.findUserById(user.getId());
			 //����model����
			 mv.addObject("user",target);
			 //��ת���޸�Ա����Ϣҳ��
			 mv.setViewName("user/showUpdateUser");
		 }else{
			 dymService.modifyUser(user);
			 mv.setViewName("redirect:/user/selectUser");
		 }
		 return mv;
	 }
	 
	 
	 
	 @RequestMapping(value="/user/updateUser2")
	 public ModelAndView updateUser2(String flag,
			 @ModelAttribute User user,
			 ModelAndView mv,Integer id) {
		 if(flag.equals("1")) {
			 User target=dymService.findUserById(id);
			 mv.addObject("user",target);
			 mv.setViewName("user/showUpdateUserinfo");
		 }else {
			 dymService.modifyUser(user);
			 mv.addObject("id",id);
			 mv.setViewName("redirect:/user/selectUser2");
		 }
		 return mv;
	 }
	 
	 
	 
	 
	 
	 /**
	  * ��������û�����
	  * @param flag ��ǣ�1��ʾ��ת������û�ҳ�棬2��ʾִ������û�����
	  * @param user Ҫ��ӵ��û�
	  * @param mv
	  * @return
	  */
	 @RequestMapping(value="/user/addUser")
	 public ModelAndView addUser(String flag,
			 @ModelAttribute User user,
			 ModelAndView mv) {
		 if(flag.equals("1")) {
			 mv.setViewName("user/showAddUser");
		 }else {
			 dymService.addUser(user);
			 mv.setViewName("redirect:/user/selectUser");
		 }
		 
		 return mv;
	 }
}
