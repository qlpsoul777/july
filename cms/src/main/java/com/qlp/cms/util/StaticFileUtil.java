package com.qlp.cms.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.qlp.cache.GlobalCache;
import com.qlp.cms.dto.StaticFileDto;
import com.qlp.cms.entity.Site;
import com.qlp.constant.CmsConstant;
import com.qlp.core.Exception.ErrorDetail.BusiErrorEnum;
import com.qlp.core.Exception.ErrorDetail.SysErrorEnum;
import com.qlp.core.entity.MsgInfo;
import com.qlp.core.enums.ResponseTypeEnum;
import com.qlp.core.page.Page;
import com.qlp.core.page.PageImpl;
import com.qlp.core.page.PageRequest;
import com.qlp.core.page.Pageable;
import com.qlp.core.utils.AssertUtil;
import com.qlp.core.utils.DataConvertUtil;
import com.qlp.core.utils.DateUtil;
import com.qlp.core.utils.DateUtil.FormatDate;
import com.qlp.core.utils.FileNameUtil;
import com.qlp.core.utils.FileUtil;
import com.qlp.core.utils.LogUtil;
import com.qlp.core.utils.StringUtil;
import com.qlp.core.utils.UnitConverterUtil;
import com.qlp.core.web.WebUtil;

public class StaticFileUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(StaticFileUtil.class);
	
	/**
	 * 获取文件路径
	 * @param request
	 * @return
	 */
	public static String getFilePath(HttpServletRequest request){
		Site site = (Site) request.getSession().getAttribute(CmsConstant.SITE_KEY);
		AssertUtil.assertNotNull(site, SysErrorEnum.DOMAIN_NOT_FOUND, "无法从session中获取站点信息");
		
		String filePath = request.getParameter("filePath");
		if(StringUtil.isBlank(filePath)){
			filePath = File.separator + site.getNum() + File.separator;
		}
		LogUtil.info(logger, "文件管理传入的文件路径是：{0}", filePath);
		return filePath;
	}
	
	/**
	 * 获取指定文件路径下的文件分页信息
	 * @param request
	 * @param filePath
	 * @return
	 */
	public static Page<StaticFileDto> getPageInfo(HttpServletRequest request,String filePath){
		Page<StaticFileDto> pageInfo = null;
		
		LogUtil.info(logger, "当前文件路径是：{0}", filePath);
		File file = new File(GlobalCache.dataPath, filePath);
		if(file.exists()){
			File[] files = file.listFiles();
			int total;
			if((files != null) && (total = files.length) > 0){
				LogUtil.info(logger, "当前文件路径{0}下文件总数：{1}", filePath,total);
				Arrays.sort(files);
				
				int pageNum = DataConvertUtil.toInt(request.getParameter("currentPage"), 0);
				int pageSize = DataConvertUtil.toInt(request.getParameter("pageSize"), 10);
				
				List<StaticFileDto> content = new ArrayList<>(total);
				StaticFileDto dto;
				File fileDto;
				for(int i = 0; i < total; i++){
					if((i >= pageNum * pageSize) && (i < (pageNum + 1) * pageSize)){
						dto = new StaticFileDto();
						fileDto = files[i];
						dto.setName(fileDto.getName());
						if(fileDto.isDirectory()){
							dto.setPath(filePath + fileDto.getName() + File.separator);
						}else{
							dto.setPath(filePath + fileDto.getName());
							dto.setSize(UnitConverterUtil.getFileSize(fileDto.length()));
						}
						dto.setModifyTime(DateUtil.stamp2DateStr(fileDto.lastModified(), FormatDate.YMD_1));
						dto.setIsFile(fileDto.isFile());
						content.add(dto);
					}
				}
				Pageable pageable = new PageRequest(pageNum,pageSize);
				pageInfo = new PageImpl<>(content, pageable, total);
				LogUtil.info(logger, "分页信息，当前第{0}页，每页显示{1}条，总共{2}页", pageNum,pageSize,pageInfo.getTotalPage());
			}
		}
		return pageInfo;
	}
	
	public static void preView(HttpServletRequest request,HttpServletResponse response, String path) {
		
		File file = new File(GlobalCache.dataPath, path);
		
		AssertUtil.assertNotNull(file, BusiErrorEnum.INPUT_NOT_EXIST, "文件不能为null");
		AssertUtil.assertTrue(file.exists() && file.isFile(),BusiErrorEnum.INPUT_STATE_ILLEGAL, "文件不存在/文件不能是文件夹");
		
		String fileName = FileNameUtil.getAfterName(file.getName());
		ResponseTypeEnum typeEnum = ResponseTypeEnum.from(fileName);
		
		if(ResponseTypeEnum.DOWNLOAD.equals(typeEnum)){
			WebUtil.setDownloadResponseHeader(request,response,file);
		}else{
			String contentType = typeEnum.getContentType();
			LogUtil.info(logger, "预览文件名：{0}时ContentType为：{1}", fileName,contentType);
			response.setContentType(contentType);
		}
		
		FileUtil.writeOutFile(WebUtil.getFromResponse(response), file);
		
		
		
		
		
		
	}
	
	/**
	 * 
	 * @param file
	 * @param response
	 */
	public static void download(HttpServletRequest request,HttpServletResponse response,File file) {
		File downLoadFile;
		boolean isDelete = false;
		if(file.exists()){
			if(file.isFile()){
				downLoadFile = file;
			}else{
				isDelete = true;
				downLoadFile = createZipFile(GlobalCache.dataPath);
				FileUtil.compress(file, downLoadFile,true);
			}
			WebUtil.setDownloadResponseHeader(request,response,downLoadFile);
			FileUtil.writeOutFile(WebUtil.getFromResponse(response),downLoadFile,isDelete);
		}
		
	}

	public static void batchDel(HttpServletResponse response, String paths) {
		StringTokenizer st = new StringTokenizer(paths, ",");
		File file;
		MsgInfo info;
		try{
			while(st.hasMoreElements()){
				file = new File(GlobalCache.dataPath, (String) st.nextElement());
				FileUtil.deleteAllFiles(file);
			}
			info = new MsgInfo(Boolean.TRUE);
		}catch(Exception e){
			LogUtil.error(logger, "删除文件时出错：{1}",e);
			info = new MsgInfo(Boolean.FALSE,"删除失败");
		}
		WebUtil.responseJson(response,info);
	}
	
	public static void upload(MultipartFile uploadFile, String currentPath) {
		AssertUtil.assertNotNull(currentPath, BusiErrorEnum.INPUT_NOT_EXIST, "当前目录路径不能为空");
		
		File parent = new File(GlobalCache.dataPath, currentPath);
		if(!parent.exists()){
			parent.mkdirs();
		}
		String fileName = uploadFile.getOriginalFilename();
		LogUtil.info(logger, "上传文件名：{0}", fileName);
		File file = new File(parent,fileName);
		FileUtil.writeInFile(getFromMultipartFile(uploadFile),file);
		if(StringUtil.endsWithIgnoreCase(fileName, FileNameUtil.ZIP_SUFFIX)){
			FileUtil.deCompress(file, parent, true);
		}
	}
	
	private static InputStream getFromMultipartFile(MultipartFile uploadFile){
		InputStream is = null;
		try {
			is = uploadFile.getInputStream();
		} catch (IOException e) {
			
		}
		return is;
	}

	/**
	 * 在临时目录下生成zip文件
	 * @param parentPath 父级路径
	 * @return
	 */
	private static File createZipFile(String parentPath) {
		
		AssertUtil.assertNotNull(parentPath, BusiErrorEnum.INPUT_NOT_EXIST, "文件父级目录路径不能为空");
		
		File file = new File(parentPath,CmsConstant.TEMP_DIR);
		if(!file.exists()){
			file.mkdirs();
		}
		file = new File(file,FileNameUtil.buildZipName());
		return file;
	}

	

}
