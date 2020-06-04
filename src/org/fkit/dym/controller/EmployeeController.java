package org.fkit.dym.controller;

import java.util.List;

import org.fkit.dym.domain.Employee;
import org.fkit.dym.service.DymService;
import org.fkit.dym.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 处理员工请求控制器
 * @author 范俊杰
 *
 */
@Controller
public class EmployeeController {
	@Autowired
	@Qualifier("dymService")
	private DymService dymService;
	
	/**
	 * 处理查询员工请求操作，与查询用户的过程相同
	 * @param pageIndex
	 * @param employee
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/employee/selectEmployee")
	public String selectEmployee(Integer pageIndex,
			@ModelAttribute Employee employee,
			Model model) {
		PageModel pageModel=new PageModel();
		if(pageIndex!=null)
		{
			pageModel.setPageIndex(pageIndex);
		}
		List<Employee> employees=dymService.findEmployee(employee,pageModel);
		model.addAttribute("employees",employees);
 		model.addAttribute("pageModel",pageModel);
		return "employee/employee";
	}
	
	/**
	 * 处理添加员工操作
	 * @param flag
	 * @param employee
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/employee/addEmployee")
	public ModelAndView addEmployee(String flag,
			 @ModelAttribute Employee employee,
			 ModelAndView mv) {
		 if(flag.equals("1")) {
			 mv.setViewName("employee/showAddEmployee");
		 }else {
			 dymService.addEmployee(employee);
			 mv.setViewName("redirect:/employee/selectEmployee");
		 }
		 
		 return mv;
	 }
	
	/**
	 * 处理删除员工信息操作，根据选中的员工的id来删除员工信息
	 * @param ids
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/employee/removeEmployee")
	public ModelAndView removeEmployee(String ids,ModelAndView mv) {
		String[] idArray=ids.split(",");//分解id字符才能
		for(String id:idArray) {
			dymService.removeEmployeeById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/employee/selectEmployee");
		return mv;
	}
	
	/**
	 * 处理修改员工信息的操作
	 * @param flag
	 * @param employee
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/employee/updateEmployee")
	public ModelAndView updateEmployee(String flag,
			@ModelAttribute Employee employee,
			ModelAndView mv) {
		if(flag.equals("1")) {
			Employee target=dymService.findEmployeeById(employee.getId());
			mv.addObject("employee",target);
			mv.setViewName("employee/showUpdateEmployee");
		}else {
			dymService.modifyEmployee(employee);
			mv.setViewName("redirect:/employee/selectEmployee");
		}
		return mv;
	}

}
