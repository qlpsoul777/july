package com.qlp.cms.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qlp.cache.GlobalCache;
import com.qlp.cms.dto.StaticFileDto;
import com.qlp.cms.util.StaticFileUtil;
import com.qlp.core.Exception.ErrorDetail.BusiErrorEnum;
import com.qlp.core.utils.AssertUtil;

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
	
	/**
	 * 文件预览(该文件不能是文件夹)
	 * @param request
	 * @param response
	 * @param path
	 */
	@RequestMapping("/preView")
	public void preView(HttpServletResponse response,String path){
		AssertUtil.assertNotBlank(path, BusiErrorEnum.INPUT_NOT_EXIST, "文件路径不能为空");
		
		StaticFileUtil.preView(response,path);
	}
	
	/**
	 * 文件下载(只能是一个文件或者文件夹，如果是文件夹会打成zip包)
	 * @param request
	 * @param response
	 * @param path
	 */
	@RequestMapping("/download")
	public void download(HttpServletRequest request,HttpServletResponse response,String path){
		AssertUtil.assertNotBlank(path, BusiErrorEnum.INPUT_NOT_EXIST, "文件路径不能为空");
		
		File file = new File(GlobalCache.dataPath, path);
		StaticFileUtil.download(request,response,file);
	}

	/**
	 * 文件删除
	 * @param response
	 * @param paths
	 */
	@RequestMapping("/delete")
	public void delete(HttpServletResponse response,String paths){
		AssertUtil.assertNotBlank(paths, BusiErrorEnum.INPUT_NOT_EXIST, "文件路径不能为空");
		
		StaticFileUtil.batchDel(response,paths);
	}

	
}
