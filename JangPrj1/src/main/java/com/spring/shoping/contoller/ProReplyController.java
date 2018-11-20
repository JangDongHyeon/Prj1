package com.spring.shoping.contoller;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.shoping.dvo.ProReplyVO;
import com.spring.shoping.service.ProReplyService;

@RestController
@RequestMapping("/proReply/*")
public class ProReplyController {

	@Autowired
	private ProReplyService proReplyService;
	
	private static final Logger logger = LoggerFactory.getLogger(ShopingController.class);
	
	@RequestMapping("/list/{pseq}")
	@ResponseBody
	public List<ProReplyVO> replyList(@PathVariable("pseq")int pesq){
		logger.info("replyList...called");
		
		
		return proReplyService.list(pesq);
	}
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> insert(@RequestBody ProReplyVO vo ,HttpSession session){
		logger.info("replyInsert...called");
		String replyer=(String)session.getAttribute("userId");
		vo.setReply(replyer);
		
		ResponseEntity<String> reEntity=null;
		
		try {
			proReplyService.insert(vo);
			reEntity=new ResponseEntity<String>("success",HttpStatus.OK);
			
		} catch (Exception e) {
			reEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			// TODO: handle exception
		}
		
	return reEntity;
	}
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modify(@RequestBody ProReplyVO vo) {
		logger.info("replymodify...called");
	String msg="";
		try {
			proReplyService.update(vo);
			msg="success";
					
		} catch (Exception e) {
			msg=e.getMessage();
		
			// TODO: handle exception
		}
		return msg;
	}
	

	
	
	@RequestMapping(value="/detail/{rid}",method=RequestMethod.GET)
	public ProReplyVO detail(@PathVariable("rid")int rid) throws Exception {
		logger.info("replydetailGet...called");
		ProReplyVO vo=proReplyService.getReply(rid);
		
		return vo;
	}
	@RequestMapping("/delete/{rid}")
	public ResponseEntity<String> delete(@PathVariable("rid")int rid){
		logger.info("replyDelete...called");
		ResponseEntity<String> reEntity=null;
		try {
			proReplyService.delete(rid);
			reEntity=new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			reEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			// TODO: handle exception
		}
	return reEntity;
	}
	
	
	
}
