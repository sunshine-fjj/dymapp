package org.fkit.dym.controller;

import java.util.List;

import org.fkit.dym.domain.Notice;
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
 * �����������
 * @author ������
 *
 */
@Controller
public class NoticeController {
	@Autowired
	@Qualifier("dymService")
	private DymService dymService;
	
	/**
	 * �����ѯ�������
	 * @param pageIndex
	 * @param notice
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/notice/selectNotice")
	public String selectNotice(Integer pageIndex,
			@ModelAttribute Notice notice,
			Model model,
			String flag) {
		if(flag.equals("1")) {
			PageModel pageModel=new PageModel();
			if(pageIndex!=null) {
				pageModel.setPageIndex(pageIndex);
			}
			List<Notice> notices=dymService.findNotice(notice,pageModel);
			model.addAttribute("notices",notices);
			model.addAttribute("pageModel",pageModel);
			return "notice/notice";
		}else {
			PageModel pageModel=new PageModel();
			if(pageIndex!=null) {
				pageModel.setPageIndex(pageIndex);
			}
			List<Notice> notices=dymService.findNotice(notice,pageModel);
			model.addAttribute("notices",notices);
			model.addAttribute("pageModel",pageModel);
			return "notice/noticeinfo";
		}
		
	}
	
	/**
	 * ������ӹ��������
	 * @param flag ��ǣ�flagΪ��ʾ��ת����ӹ���ҳ�棬flagΪ2��ʾִ����ӹ������
	 * @param notice
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/notice/addNotice")
	public ModelAndView addNotice(String flag,
			@ModelAttribute Notice notice,
			ModelAndView mv) {
		if(flag.equals("1")) {
			mv.setViewName("notice/showAddNotice");
		}else {
			dymService.addNotice(notice);
			mv.setViewName("redirect:/notice/selectNotice");
		}
		return mv;
	}
	
	/**
	 * ����ɾ���������
	 * @param ids
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/notice/removeNotice")
	public ModelAndView removeNotice(String ids,ModelAndView mv) {
		String[] idArray = ids.split(",");
		for(String id:idArray) {
			dymService.removeNoticeById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/notice/selectNotice");
		return mv;
	}
	/**
	 * �����޸Ĺ������
	 * @param flag ��ǣ�ͬ����
	 * @param mv
	 * @param notice
	 * @return
	 */
	@RequestMapping(value="/notice/updateNotice")
	public ModelAndView updateNotice(String flag,
			ModelAndView mv,
			@ModelAttribute Notice notice) {
		if(flag.equals("1")) {
			Notice target=dymService.findNoticeById(notice.getId());
			mv.addObject("notice",target);
			mv.setViewName("notice/showUpdateNotice");
		}else {
			dymService.modifyNotice(notice);
			mv.setViewName("redirect:/notice/selectNotice?flag=1");
		}
		return mv;
	}
	
	/**
	 * ����Ԥ���������
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/notice/previewNotice")
	public String previewNotice(Integer id,Model model) {
		
		Notice notice=dymService.findNoticeById(id);
		model.addAttribute("notice",notice);
		return "notice/previewNotice";
	}
}
