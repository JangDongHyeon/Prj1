package com.spring.board.Util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;



public class UploadFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

public static String uploadFile(MultipartFile file,HttpServletRequest request) throws Exception{
	String originalFileName=file.getOriginalFilename();
	logger.info("originalFileName"+originalFileName);
	byte[] fileData=file.getBytes();
	
	String uuidFileName=getUuidFileName(originalFileName);
	
	String rootPath=getRootPath(originalFileName,request);
	
	String datePath=getDatePath(rootPath);
	//서버에 파일 저장
	File target=new File(rootPath+datePath,uuidFileName);
	FileCopyUtils.copy(fileData, target);
	//이미지 파일인 경우 썸네일이미지 저장
	if(MediUtils.getMediaType(originalFileName)!=null) {
		uuidFileName=makeThumbnail(rootPath, datePath, uuidFileName);
		
	}
	
	//파일 저장 경로 치환
	return replaceSavedFilePath(datePath, uuidFileName);
	
}
//파일 저장 경로 치환
private static String replaceSavedFilePath(String datePath,String fileName) {
	String saveFilePath=datePath+File.separator+fileName;
	return saveFilePath.replace(File.separatorChar,'/');
}

private static String getUuidFileName(String originalFileName) {
	return UUID.randomUUID().toString()+"_"+originalFileName;
}
//파일 출력을 위한 HttpHeader 설정
public static HttpHeaders getHttpHeaders(String fileName)throws Exception{
	MediaType mediaType=MediUtils.getMediaType(fileName);
	HttpHeaders httpHeaders=new HttpHeaders();
	//이미지 파일 0
	if(mediaType!=null) {
		httpHeaders.setContentType(mediaType);
	}
	//이미지 파일 x
	fileName=fileName.substring(fileName.indexOf("_")+1);//uuid 제거
	httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);//다운로드 MIME 타입
	//파일명 한글 인코딩처리
	httpHeaders.add("Content-Disposition", "attachment; filename=\""+new String(fileName.getBytes("UTF-8"),
			"ISO-8859-1")+"\"");
	return httpHeaders;
}


//기본 경로 주출
private static String getRootPath(String fileName,HttpServletRequest request) {
	String rootPath="/resources/upload";
	MediaType mediaType=MediUtils.getMediaType(fileName);
	if(mediaType!=null)
		return request.getSession().getServletContext().getRealPath(rootPath+"/images");
	return request.getSession().getServletContext().getRealPath(rootPath+"/files");
}
//날짜 폴더명 추출
private static String getDatePath(String uploadPath) {
	
	Calendar calendar=Calendar.getInstance();
	String yearPath=File.separator+calendar.get(Calendar.YEAR);
	String monthPath=yearPath+File.separator+new DecimalFormat("00").format(calendar.get(Calendar.MONTH)+1);
	String datePath=monthPath+File.separator+new DecimalFormat("00").format(calendar.get(Calendar.DATE));
	
	makeDateDir(uploadPath,yearPath,monthPath,datePath);
	
	return datePath;
	
}
//날짜 폴더명 생성
private static void makeDateDir(String uploadPath,String...paths) {
	if(new File(uploadPath+paths[paths.length-1]).exists())
		return;
	for(String path:paths) {
		File dirPath=new File(uploadPath+path);
		if(!dirPath.exists())
			dirPath.mkdirs();
	}
}
//썸네일 이미지 생성
private static String makeThumbnail(String uploadRootPath,String dataPath,String fileName)throws Exception {
	//원본이미지를 메모리상에 로딩
	BufferedImage originalImg=ImageIO.read(new File(uploadRootPath+dataPath,fileName));
	//원본이미지 축소
	BufferedImage thumbnailImg=Scalr.resize(originalImg, Scalr.Method.AUTOMATIC,Scalr.Mode.FIT_TO_HEIGHT,100 );
	//썸네일 파일명
	String thumbnailImgName="s_"+fileName;
	//썸네일 업로드 경로
	String fullPath=uploadRootPath+dataPath+File.separator+thumbnailImgName;
	//썸네일 파일 객채생성
	File newFile=new File(fullPath);
	//썸네일 파일 확장자 추출
	String formatName=MediUtils.getFormatName(fileName);
	//썸네일 파일 저장
	ImageIO.write(thumbnailImg, formatName, newFile);
			
	return thumbnailImgName;		
	
	
	
}

}
