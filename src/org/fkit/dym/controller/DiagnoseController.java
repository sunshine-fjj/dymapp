package org.fkit.dym.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.fkit.dym.domain.Appointment;
import org.fkit.dym.domain.Diagnose;
import org.fkit.dym.domain.User;
import org.fkit.dym.service.DymService;
import org.fkit.dym.util.common.DymConstants;
import org.fkit.dym.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * ���������Ϣ����������
 * @author ������
 *
 */
@Controller
public class DiagnoseController {

	@Autowired
	@Qualifier("dymService")
	private DymService dymService;
	
	/**
	 *�����ѯ������Ϣ
	 * @param model
	 * @param pageIndex
	 * @param diagnose
	 * @return
	 */
	@RequestMapping(value="/diagnose/selectDiagnose")
	public String selectDiagnose(Model model,
			Integer pageIndex,
			@ModelAttribute Diagnose diagnose) {
		
		PageModel pageModel=new PageModel();
		if(pageIndex!=null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<Diagnose> diagnoses=dymService.findDiagnose(diagnose,pageModel);
		model.addAttribute("diagnoses",diagnoses);
		model.addAttribute("pageModel",pageModel);
		return "diagnose/diagnose";
	}
	
	
	@RequestMapping(value="/diagnose/selectAllDiagnose")
	public String selectAllDiagnose(Model model,
			Integer pageIndex,
			@ModelAttribute Diagnose diagnose) {
		
		PageModel pageModel=new PageModel();
		if(pageIndex!=null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<Diagnose> diagnoses=dymService.findDiagnose(diagnose,pageModel);
		model.addAttribute("diagnoses",diagnoses);
		model.addAttribute("pageModel",pageModel);
		return "diagnose/diagnoseinfo";
	}
	
	
	
	@RequestMapping(value="/diagnose/selectMyDownload")
	public String selectMyDownload(Model model,
			Integer pageIndex,
			@ModelAttribute Diagnose diagnose,
			HttpSession session) {
		User user = (User) session.getAttribute(DymConstants.USER_SESSION);
		diagnose.setUser(user);
		PageModel pageModel = new PageModel();
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Diagnose> diagnoses=dymService.findmydownloadByUserid(diagnose,pageModel);  
        System.out.println("������1");
        
        model.addAttribute("diagnoses", diagnoses);
        model.addAttribute("pageModel", pageModel);
        System.out.println("������2");
        return "diagnose/mydownload";
	}
	
	
	
	@RequestMapping(value="/diagnose/selectMyUpload")
	public String selectMyUpload(Model model,
			Integer pageIndex,
			@ModelAttribute Diagnose diagnose,
			HttpSession session) {
		User user = (User) session.getAttribute(DymConstants.USER_SESSION);
		diagnose.setUser(user);
		PageModel pageModel = new PageModel();
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Diagnose> diagnoses=dymService.finddiagnoseByUserid(diagnose,pageModel);
        model.addAttribute("diagnoses", diagnoses);
        model.addAttribute("pageModel", pageModel);
        return "diagnose/myupload";
	}
	
	
	
	
	
	/**
	 * ������ӹ�����Ϣ����
	 * @param flag ��ǣ�Ϊ1��ת����ӹ�����Ϣҳ�棬Ϊ2ִ����Ӳ���
	 * @param diagnose
	 * @param mv
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/diagnose/addDiagnose")
	public ModelAndView addDiagnose(String flag,
			@ModelAttribute Diagnose diagnose,
			ModelAndView mv,
			HttpSession session)throws Exception {
		
		if(flag.equals("1")) {
			mv.setViewName("diagnose/showAddDiagnose");
		}else {
			//�ϴ��ļ�·��
			String path=session.getServletContext().getRealPath("/upload/");
			System.out.println(path);
			
			//�ϴ��ļ���
			String filename=diagnose.getFile().getOriginalFilename();
			System.out.println(filename);
			
			//���ϴ��ļ����浽һ��Ŀ���ļ�����
			diagnose.getFile().transferTo(new File(path+File.separator+filename));
			
			String filepath=path+filename;
			System.out.print("filepath:");
			System.out.println(filepath);
			
			//����filenameΪ���ļ����������ݿ���׼��
			diagnose.setFilename(filename);
			diagnose.setFilepath(filepath);
			
			User user=(User) session.getAttribute(DymConstants.USER_SESSION);
			diagnose.setUser(user);
			
			//���뵽���ݿ���

			dymService.addDiagnose(diagnose);
			
			
		    mv.setViewName("redirect:/diagnose/selectDiagnose");
			
		}
		return mv;
	}
	
	
	
	@RequestMapping(value="/diagnose/addMyDiagnose")
	public ModelAndView addMyDiagnose(String flag,
			@ModelAttribute Diagnose diagnose,
			ModelAndView mv,
			HttpSession session)throws Exception {
		
		if(flag.equals("1")) {
			mv.setViewName("diagnose/showAddDiagnose");
		}else {
			//�ϴ��ļ�·��
			String path=session.getServletContext().getRealPath("/upload/");
			System.out.println(path);
			
			//�ϴ��ļ���
			String filename=diagnose.getFile().getOriginalFilename();
			System.out.println(filename);
			
			//���ϴ��ļ����浽һ��Ŀ���ļ�����
			diagnose.getFile().transferTo(new File(path+File.separator+filename));
			
			String filepath=path+filename;
			
			//����filenameΪ���ļ����������ݿ���׼��
			diagnose.setFilename(filename);
			diagnose.setFilepath(filepath);
			
			User user=(User) session.getAttribute(DymConstants.USER_SESSION);
			diagnose.setUser(user);
			
			//���뵽���ݿ���

			dymService.addDiagnose(diagnose);
			
			
		    mv.setViewName("redirect:/diagnose/selectMyDiagnose");
			
		}
		return mv;
	}
	
	
	
	
	
	
	
	/**
	 * ����ɾ��������Ϣ����
	 * @param ids
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/diagnose/removeDiagnose")
	public ModelAndView removeDiagnose(String ids,ModelAndView mv) {
		String[] idArray=ids.split(",");
		for(String id:idArray) {
			dymService.removeDiagnoseById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/diagnose/selectDiagnose");
		return mv;
	}
	
	
	@RequestMapping(value="/diagnose/removeMyDownload")
	public ModelAndView removeMyDownload(String ids,ModelAndView mv) {
		String[] idArray=ids.split(",");
		for(String id:idArray) {
			dymService.removeDownloadById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/diagnose/selectMyDownload");
		return mv;
	}
	
	
	
	/**
	 * �����޸Ĺ�����Ϣ����
	 * @param flag ��ǣ�Ϊ1��ת���޸�ҳ�棬Ϊ2ִ���޸Ĳ���
	 * @param diagnose
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/diagnose/updateDiagnose")
	public ModelAndView updateDiagnose(String flag,
			@ModelAttribute Diagnose diagnose,
			ModelAndView mv) {
		if(flag.equals("1")) {
			Diagnose target=dymService.findDiagnoseById(diagnose.getId());
			mv.addObject("diagnose", target);
			mv.setViewName("diagnose/showUpdateDiagnose");
		}else {
			dymService.modifyDiagnose(diagnose);
			
			mv.setViewName("redirect:/diagnose/selectDiagnose");
			
			
		}
		return mv;
	}
	
	/**
	 * �������ع�����Ϣ�ĵ�����
	 * @param id
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/diagnose/downLoad")
	public ResponseEntity<byte[]> downLoad(Integer id,HttpSession session)throws Exception{
		Diagnose target=dymService.findDiagnoseById(id);
		
		System.out.println("######");
		User user=(User) session.getAttribute(DymConstants.USER_SESSION);
		target.setUser(user);
		dymService.addDownload(target);
		
		
		
		System.out.println("&&&&&&");
		
		String filename=target.getFilename();
		//�ϴ��ļ�·��
		String path=session.getServletContext().getRealPath("/upload/");
		//���Ҫ�����ļ���File����
		File file=new File(path+File.separator+filename);
		
		
		
		
		//����springframework��httpheders����
		HttpHeaders headers=new HttpHeaders();
		//������ʾ���ļ��������������������
		String downloadFileName=new String(filename.getBytes("UTF-8"),"iso-8859-1");
		//֪ͨ�������attachment�����ط�ʽ����ͼƬ
		headers.setContentDispositionFormData("attachment", downloadFileName);
		//�����������ݣ�������ļ����أ�
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
	}
	
}
