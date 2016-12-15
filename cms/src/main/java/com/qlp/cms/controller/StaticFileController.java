package com.qlp.cms.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qlp.cache.GlobalCache;
import com.qlp.cms.dto.StaticFileDto;
import com.qlp.cms.util.StaticFileUtil;
import com.qlp.core.Exception.ErrorDetail.BusiErrorEnum;
import com.qlp.core.utils.AssertUtil;
import com.qlp.core.utils.FileUtil;
import com.qlp.core.utils.StringUtil;
import com.qlp.core.web.WebUtil;

/**
 * 文件管理controller
 * @author qlp
 *
 */
@Controller
@RequestMapping("/staticFile")
public class StaticFileController {
	
	/**
	 * 文件列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
		
		String filePath = StaticFileUtil.getFilePath(request);
		request.setAttribute("filePath", filePath);
		
		Page<StaticFileDto> pageInfo = StaticFileUtil.getPageInfo(request,filePath);
		request.setAttribute("pageInfo", pageInfo);
		return "/cms/staticFile/list";
	}
	
	@RequestMapping("/preView")
	public void preView(HttpServletRequest request,HttpServletResponse response,String path){
		AssertUtil.assertNotBlank(path, BusiErrorEnum.INPUT_NOT_EXIST, "文件路径不能为空");
		
		File file = new File(GlobalCache.dataPath, path);
		if(FileUtil.isNormalText(file)){
			response.setContentType("text/plain;charset=utf-8");
			FileUtil.writeOutFile(WebUtil.getFromResponse(response), file);
		}else if(FileUtil.isPhoto(file)){
			response.setContentType("image/jpeg");
			FileUtil.writeOutFile(WebUtil.getFromResponse(response), file);
		}else{
			StaticFileUtil.download(request,response,file);
		}
	}
	
	@RequestMapping("/download")
	public void download(HttpServletRequest request,HttpServletResponse response,String path){
		AssertUtil.assertNotBlank(path, BusiErrorEnum.INPUT_NOT_EXIST, "文件路径不能为空");
		
		File file = new File(GlobalCache.dataPath, path);
		StaticFileUtil.download(request,response,file);
		
	}
	
	@RequestMapping("/delete")
	public void delete(HttpServletRequest request,HttpServletResponse response,String paths){
		
		AssertUtil.assertNotBlank(paths, BusiErrorEnum.INPUT_NOT_EXIST, "文件路径不能为空");
		
		StaticFileUtil.batchDel(response,paths);
		
		
	}

	
}
