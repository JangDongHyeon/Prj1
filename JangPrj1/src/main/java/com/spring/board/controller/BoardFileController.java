package com.spring.board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.admin.controller.AdminController;
import com.spring.board.dvo.BoardFileVO;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping("/BFile/*")
public class BoardFileController {
	private static final Logger logger = LoggerFactory.getLogger(BoardFileController.class);
	
	@RequestMapping(value="uploadAjaxFile",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<BoardFileVO> uploadAjaxFilePost(@RequestParam("uploadFile")MultipartFile[] multipartFile,HttpServletRequest request) {
		
		List<BoardFileVO> fileList=new ArrayList<>();
		String uploadFolder=getRootPath(request);

		String uploadFolderPath=getFolder();
		
		File uploadPath=new File(uploadFolder,uploadFolderPath);

		if(uploadPath.exists()==false) {
			uploadPath.mkdirs();
		}

		for(MultipartFile multi:multipartFile) {
			BoardFileVO vo=new BoardFileVO();
			
			String uploadFileName=multi.getOriginalFilename();
			
			uploadFileName=uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			logger.info("only file name: " + uploadFileName);
			vo.setFileName(uploadFileName);
			
			UUID uuid=UUID.randomUUID();
			
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			try {
				File saveFile=new File(uploadPath, uploadFileName);
				
				multi.transferTo(saveFile);
				
				
				vo.setUuid(uuid.toString());
				vo.setUploadPath(uploadFolderPath);
				if(checkImageType(saveFile)) {
				
				vo.setImage(true);	
				FileOutputStream  thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));

				Thumbnailator.createThumbnail(multi.getInputStream(), thumbnail, 100, 100);

				thumbnail.close();
					
					
				}
				
				fileList.add(vo);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return fileList;
	}
	
	@RequestMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName,HttpServletRequest request) {

		logger.info("fileName: " + fileName);

		String uploadFilePath=getRootPath(request);
		File file = new File(uploadFilePath+"\\" + fileName);
		
		logger.info("file: " + file);

		ResponseEntity<byte[]> result = null;

		try {
			HttpHeaders header = new HttpHeaders();

			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping(value="deleteFile",method=RequestMethod.POST)
	@ResponseBody
	public String deleteFile(@RequestParam("fileName")String fileName,@RequestParam("type")String type,HttpServletRequest request) {
		logger.info("fileName:  "+fileName);

		
		File file;
		
		try {
			String uploadFilePath=getRootPath(request);
			file=new File(uploadFilePath+"//"+URLDecoder.decode(fileName,"UTF-8"));
			
			file.delete();
			
			if(type.equals("image")) {
				
				String largeFileName = file.getAbsolutePath().replace("s_", "");

				logger.info("largeFileName: " + largeFileName);

				file = new File(largeFileName);

				file.delete();
			}
			
		} catch (Exception e) {e.printStackTrace();
			// TODO: handle exception
		}
		return "deleted";
	}
	@RequestMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> download(@RequestHeader("User-Agent")String userAgent, String fileName,HttpServletRequest request) {
		String uploadFolder=getRootPath(request);
		Resource resource=new FileSystemResource(uploadFolder+"//"+fileName);
		
		if(resource.exists()==false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		String resourceName =resource.getFilename();
		
		logger.info("resourceName:"+resourceName);
		
		String resourceOriginalName =resourceName.substring(resourceName.indexOf("_")+1);
		logger.info("resourceOriginalName:"+resourceOriginalName);
		
		HttpHeaders headers = new HttpHeaders();
		try {
			
			boolean check=(userAgent.indexOf("MSIE")>-1||userAgent.indexOf("Trident") > -1);
			
			String downloadName = null;
			
			if (check) {
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF8").replaceAll("\\+", " ");
			} else {
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
			}
					
			headers.add("Content-Disposition", "attachment; filename=" + downloadName);

			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	
	public String getRootPath(HttpServletRequest request) {
		String rootPatha="/resources/upload";
		return request.getSession().getServletContext().getRealPath(rootPatha);
		
	}
	
	private String getFolder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}
	private boolean checkImageType(File file) {
		
		
		try {
			String contentType = Files.probeContentType(file.toPath());

			return contentType.startsWith("image");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	


}
	
	

