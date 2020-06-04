package org.fkit.dym.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.fkit.dym.domain.Appointment;
import org.fkit.dym.service.DymService;
import org.fkit.dym.util.common.DymConstants;
import org.fkit.dym.util.tag.PageModel;
import org.fkit.dym.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 处理预约信息控制器
 * @author 范俊杰
 *
 */
@Controller
public class AppointmentController {
	@Autowired
	@Qualifier("dymService")
	private DymService dymService;
	
	/**
	 * 处理预约信息查询操作
	 * @param pageIndex
	 * @param appointment
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/appointment/selectAppointment")
	public String selectAppointment(Integer pageIndex,
			@ModelAttribute Appointment appointment,
			Model model) {
		PageModel pageModel=new PageModel();
		if(pageIndex!=null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<Appointment> appointments=dymService.findAppointment(appointment,pageModel);
		model.addAttribute("appointments",appointments);
		model.addAttribute("pageModel", pageModel);
		return "appointment/appointment";
	}
	
	
	
	
	@RequestMapping(value="/appointment/selectMyAppointment")
	public String selectMyAppointment(Integer pageIndex,
			@ModelAttribute Appointment appointment,
			Model model,
			HttpSession session) {
		User user = (User) session.getAttribute(DymConstants.USER_SESSION);
		appointment.setUser(user);
		PageModel pageModel = new PageModel();
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Appointment> appointments=dymService.findappointmentByUserid(appointment,pageModel);  
   
        
        model.addAttribute("appointments", appointments);
        model.addAttribute("pageModel", pageModel);
       
        return "appointment/myappointment";
	}
	
	
	
	
	
	/**
	 * 处理预览预约信息操作
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/appointment/previewAppointment")
	public String previewAppointment(Integer id,Model model) {
		Appointment appointment=dymService.findAppointmentById(id);
		model.addAttribute("appointment", appointment);
		return "appointment/previewAppointment";
	}
	
	/**
	 * 处理修改预约信息操作
	 * @param flag 标记，为1跳转到修改信息页面，为2执行修改信息操作
	 * @param mv
	 * @param appointment
	 * @return
	 */
	@RequestMapping(value="/appointment/updateAppointment")
	public ModelAndView updateAppointment(String flag,
			ModelAndView mv,
			@ModelAttribute Appointment appointment) {
		if(flag.equals("1"))
		{
			Appointment target=dymService.findAppointmentById(appointment.getId());
			mv.addObject("appointment",target);
			mv.setViewName("appointment/showUpdateAppointment");
		}else {
			dymService.updateAppointment(appointment);
			mv.setViewName("redirect:/appointment/selectAppointment");
		}
		return mv;
	}
	
	
	
	
	@RequestMapping(value="/appointment/updateMyAppointment")
	public ModelAndView updateMyAppointment(String flag,
			ModelAndView mv,
			@ModelAttribute Appointment appointment) {
		
		return mv;
	}
	
	
	
	@RequestMapping(value="/appointment/removeMyAppointment")
	public ModelAndView deleteMyAppointment(String ids,ModelAndView mv) {
		String[] idArray = ids.split(",");
		for(String id:idArray) {
			dymService.removeAppointmentById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/appointment/selectMyAppointment");
		return mv;
	}
	
	
	
	@RequestMapping(value="/appointment/addMyAppointment")
	public ModelAndView addMyAppointment(String flag,
			ModelAndView mv,
			@ModelAttribute Appointment appointment,
			HttpSession session) {
		if(flag.equals("1")) {
			mv.setViewName("appointment/showAddMyAppointment");
		}else {
			User user = (User) session.getAttribute(DymConstants.USER_SESSION);
			appointment.setUser(user);
			dymService.addAppointment(appointment);
			mv.setViewName("redirect:/appointment/selectMyAppointment");
		}
		return mv;
	}
}
