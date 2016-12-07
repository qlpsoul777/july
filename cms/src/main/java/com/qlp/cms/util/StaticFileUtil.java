package com.qlp.cms.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.qlp.cache.GlobalCache;
import com.qlp.cms.dto.StaticFileDto;
import com.qlp.cms.entity.Site;
import com.qlp.constant.CmsConstant;
import com.qlp.core.Exception.ErrorDetail.SysErrorEnum;
import com.qlp.core.utils.AssertUtil;
import com.qlp.core.utils.DataConvertUtil;
import com.qlp.core.utils.DateUtil;
import com.qlp.core.utils.DateUtil.FormatDate;
import com.qlp.core.utils.LogUtil;
import com.qlp.core.utils.StringUtil;
import com.qlp.core.utils.UnitConverterUtil;

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
				
				List<StaticFileDto> content = new ArrayList<StaticFileDto>(total);
				StaticFileDto dto = null;
				for(int i = 0; i < total; i++){
					if((i >= pageNum * pageSize) && (i < (pageNum + 1) * pageSize)){
						dto = new StaticFileDto();
						dto.setName(files[i].getName());
						dto.setPath(filePath + files[i].getName());
						dto.setAbsolutePath(files[i].getAbsolutePath());
						dto.setSize(UnitConverterUtil.getFileSize(files[i].length()));
						dto.setModifyTime(DateUtil.stamp2DateStr(files[i].lastModified(), FormatDate.YMD_1));
						dto.setIsFile(files[i].isFile());
						content.add(dto);
					}
				}
				Pageable pageable = new PageRequest(pageNum,pageSize);
				pageInfo = new PageImpl<>(content, pageable, total);
				LogUtil.info(logger, "分页信息，当前第{0}页，每页显示{1}条，总共{2}页", pageNum,pageSize,pageInfo.getTotalPages());
			}
		}
		return pageInfo;
	}

}
