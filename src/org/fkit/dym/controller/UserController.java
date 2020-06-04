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
 * 处理用户请求控制器
 * @author 范俊杰
 *
 */
@Controller
public class UserController {
	//自动注入UserService
	@Autowired
	@Qualifier("dymService")
	private DymService dymService;
	/**
	 * 处理登陆请求
	 * @param loginname 登录名
	 * @param password 密码
	 * @param session 保存user对象
	 * @param mv 跳转的视图
	 */
	@RequestMapping(value="/login")
	public ModelAndView login(
			@RequestParam("loginname") String loginname,
			@RequestParam("password") String password,
			@RequestParam("flag") Integer flag,
			HttpSession session,
			ModelAndView mv) {
		//调用业务逻辑组件判断用户是否可以登陆
		User user=dymService.login(loginname, password);
		Integer status=dymService.testpass(loginname);
		if(flag==1)
		{
			if(user!=null) {
				//将用户保存到httpSession中
				session.setAttribute(DymConstants.USER_SESSION, user);
				if(loginname.equals("admin"))
				{
					//管理员登陆跳转到main页面，客户端跳转
					mv.setViewName("redirect:/main");
				}
				else
				{
					if(status==1)
					{
						//普通已审核用户登陆跳转到usermian页面
						mv.setViewName("redirect:/usermain");
					}
					else
					{
						//普通未审核用户跳转到LoginForm
						mv.addObject("message","用户信息未审核，请等待审核通过再登陆");
						mv.setViewName("forward:/LoginForm");
					}
					
				}
			}else {
				//设置登陆失败提示信息
				mv.addObject("message","用户未注册或者登录名、密码错误，请重新输入");
				//服务器内部跳转到登陆页面
				mv.setViewName("forward:/LoginForm");
			}
		}else {
			mv.addObject("message","验证码错误！");
			mv.setViewName("forward:/LoginForm");
		}
		
		return mv;
	}
	/**
	 * 处理用户注册信息
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
	 * 处理查询用户请求，将pageIndex,recordCount(在DymServiceImpl中调用count动态查询总数),pageSize(在DymServiceImpl中调用userDao.selectByPage(params)分页动态查询)
	 * 三者的值放到pageModel中传到user.jsp页面中，进行分页。users的作用是在user.jsp页面中显示查询到的信息。
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
		 
		 System.out.print("我的id:");
		 System.out.print(id);
		 
		 model.addAttribute("user",target);
		 return "user/userinfo";
	 }
	 
	 
	 
	 
	 /**
	  * 处理删除用户操作，根据选中的用户id来删除用户信息
	  * @param ids
	  * @param mv
	  * @return
	  */
	 @RequestMapping(value="/user/removeUser")
	 public ModelAndView removeUser(String ids,ModelAndView mv) {
		 String[] idArray=ids.split(",");//分解id字符串
		 for(String id:idArray) {
			 dymService.removeUserById(Integer.parseInt(id));
		 }
		 mv.setViewName("redirect:/user/selectUser");
		 return mv;
	 }
	 
	 /**
	  * 处理修改用户信息的操作
	  * @param flag 标记，1表示跳转到修改页面，2表示执行修改操作
	  * @param user 需要修改的用户
	  * @param mv
	  * @return
	  */
	 @RequestMapping(value="/user/updateUser")
	 public ModelAndView updateUser(String flag,
			 @ModelAttribute User user,
			 ModelAndView mv) {
		 if(flag.equals("1")){
			 User target=dymService.findUserById(user.getId());
			 //设置model数据
			 mv.addObject("user",target);
			 //跳转到修改员工信息页面
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
	  * 处理添加用户请求
	  * @param flag 标记，1表示跳转到添加用户页面，2表示执行添加用户操作
	  * @param user 要添加的用户
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
