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
 * 处理故障信息操作控制器
 * @author 范俊杰
 *
 */
@Controller
public class DiagnoseController {

	@Autowired
	@Qualifier("dymService")
	private DymService dymService;
	
	/**
	 *处理查询故障信息
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
        System.out.println("到这了1");
        
        model.addAttribute("diagnoses", diagnoses);
        model.addAttribute("pageModel", pageModel);
        System.out.println("到这了2");
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
	 * 处理添加故障信息操作
	 * @param flag 标记，为1跳转到添加故障信息页面，为2执行添加操作
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
			//上传文件路径
			String path=session.getServletContext().getRealPath("/upload/");
			System.out.println(path);
			
			//上传文件名
			String filename=diagnose.getFile().getOriginalFilename();
			System.out.println(filename);
			
			//将上传文件保存到一个目标文件当中
			diagnose.getFile().transferTo(new File(path+File.separator+filename));
			
			String filepath=path+filename;
			System.out.print("filepath:");
			System.out.println(filepath);
			
			//设置filename为将文件名插入数据库做准备
			diagnose.setFilename(filename);
			diagnose.setFilepath(filepath);
			
			User user=(User) session.getAttribute(DymConstants.USER_SESSION);
			diagnose.setUser(user);
			
			//插入到数据库中

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
			//上传文件路径
			String path=session.getServletContext().getRealPath("/upload/");
			System.out.println(path);
			
			//上传文件名
			String filename=diagnose.getFile().getOriginalFilename();
			System.out.println(filename);
			
			//将上传文件保存到一个目标文件当中
			diagnose.getFile().transferTo(new File(path+File.separator+filename));
			
			String filepath=path+filename;
			
			//设置filename为将文件名插入数据库做准备
			diagnose.setFilename(filename);
			diagnose.setFilepath(filepath);
			
			User user=(User) session.getAttribute(DymConstants.USER_SESSION);
			diagnose.setUser(user);
			
			//插入到数据库中

			dymService.addDiagnose(diagnose);
			
			
		    mv.setViewName("redirect:/diagnose/selectMyDiagnose");
			
		}
		return mv;
	}
	
	
	
	
	
	
	
	/**
	 * 处理删除故障信息操作
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
	 * 处理修改故障信息操作
	 * @param flag 标记，为1跳转到修改页面，为2执行修改操作
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
	 * 处理下载故障信息文档操作
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
		//上传文件路径
		String path=session.getServletContext().getRealPath("/upload/");
		//获得要下载文件的File对象
		File file=new File(path+File.separator+filename);
		
		
		
		
		//创建springframework的httpheders对象
		HttpHeaders headers=new HttpHeaders();
		//下载显示的文件名，解决中文乱码问题
		String downloadFileName=new String(filename.getBytes("UTF-8"),"iso-8859-1");
		//通知浏览器以attachment（下载方式）打开图片
		headers.setContentDispositionFormData("attachment", downloadFileName);
		//二进制流数据（最常见的文件下载）
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
	}
	
}
