package org.fkit.dym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author 范俊杰
 *页面跳转控制
 */
@Controller
public class FormController {
	@RequestMapping(value="/{formName}")
	
	/**pathvariabe注解可以方便获得请求url中的动态参数*/
	public String loginForm(@PathVariable String formName) {
		return formName;
	}
}
