package com.spring.reset;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.board.dao.BoardFileDAO;
import com.spring.board.dvo.BoardFileVO;

@Component
public class FileCheckTask {

	@Autowired
	private BoardFileDAO dao;
	
	private String getFolder() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal=Calendar.getInstance();
		
		cal.add(Calendar.DATE,-1);
		
		String str=sdf.format(cal.getTime());
		
		return str.replace("-",File.separator);
	}
	
	@Scheduled(cron="0 0 2 * * *")
	public void checkFiles(HttpServletRequest request) throws Exception{
		List<BoardFileVO> fileList=dao.getOldFiles();
		String uploadFoder=getRootPath(request);
		List<Path> fileListPaths=fileList.stream().map(vo->Paths.get(uploadFoder,vo.getUploadPath(),vo.getUuid()+"_"+vo.getFileName()))
				.collect(Collectors.toList());
		
		fileList.stream().filter(vo->vo.getFiletype().equals("true"))
		.map(vo->Paths.get(uploadFoder,vo.getUploadPath(),"s_",vo.getUuid()+"_"+vo.getFileName())).forEach(p->fileListPaths.add(p));
		
		File targetDir=Paths.get(uploadFoder,getFolder()).toFile();
		
		File[] removeFile=targetDir.listFiles(file->fileListPaths.contains(file.toPath())==false);
		for(File file:removeFile) {
		file.delete();
		}
	}
	public String getRootPath(HttpServletRequest request) {
		String rootPatha="/resources/upload";
		return request.getSession().getServletContext().getRealPath(rootPatha);
		
	}
}
