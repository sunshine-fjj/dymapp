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
 * ����Ա�����������
 * @author ������
 *
 */
@Controller
public class EmployeeController {
	@Autowired
	@Qualifier("dymService")
	private DymService dymService;
	
	/**
	 * �����ѯԱ��������������ѯ�û��Ĺ�����ͬ
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
	 * �������Ա������
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
	 * ����ɾ��Ա����Ϣ����������ѡ�е�Ա����id��ɾ��Ա����Ϣ
	 * @param ids
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/employee/removeEmployee")
	public ModelAndView removeEmployee(String ids,ModelAndView mv) {
		String[] idArray=ids.split(",");//�ֽ�id�ַ�����
		for(String id:idArray) {
			dymService.removeEmployeeById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/employee/selectEmployee");
		return mv;
	}
	
	/**
	 * �����޸�Ա����Ϣ�Ĳ���
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
