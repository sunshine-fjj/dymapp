package org.fkit.dym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author ������
 *ҳ����ת����
 */
@Controller
public class FormController {
	@RequestMapping(value="/{formName}")
	
	/**pathvariabeע����Է���������url�еĶ�̬����*/
	public String loginForm(@PathVariable String formName) {
		return formName;
	}
}
