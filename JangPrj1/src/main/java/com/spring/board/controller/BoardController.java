package com.spring.board.controller;

import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.admin.dvo.PageMaker;
import com.spring.admin.dvo.SearchVO;
import com.spring.board.dao.BoardDAO;
import com.spring.board.dvo.BoardFileVO;
import com.spring.board.dvo.BoardVO;
import com.spring.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	private BoardService boardDAO;

	
	@RequestMapping("/boardSelect")
	public String boardSelect(Model model,@ModelAttribute("searchVO")SearchVO vo) {
		
		int count=boardDAO.boardPageCount(vo);
		vo.setNumPage(10);
		PageMaker maker=new PageMaker(vo, count);
		
		vo.setStartPage(maker.getStartPageto());
		vo.setEndPage(maker.getEndPageto());
		Map<String, Object> map=new HashMap<>();
		map.put("boardList",boardDAO.boardSelect(vo));
		map.put("pageMaker",maker);
		model.addAttribute("map",map);
		return "board/boardList";
	}
	@RequestMapping(value="boardInsert",method=RequestMethod.GET)
	public String boardInsertGET(Model model,@ModelAttribute("searchVO")SearchVO searchVO) {
	
		return "board/boardInsert";
	}
	
	@RequestMapping(value="boardInsert",method=RequestMethod.POST)
	public String boardInsertPOST(BoardVO vo,RedirectAttributes rttr,HttpSession session,@ModelAttribute("searchVO")SearchVO searchVO) {
		String id=(String)session.getAttribute("userId");
		vo.setBid(id);
		boardDAO.boardInsert(vo);
		rttr.addFlashAttribute("msg","글이등록되었습니다");
	
		
		return "redirect:/board/boardSelect";
	}
	@RequestMapping("/boardDetail")
	public String boardDetail(@RequestParam("bno")int bno,Model model,@ModelAttribute("searchVO")SearchVO searchVO,HttpSession session) {
		
		model.addAttribute("boardVO",boardDAO.boardDetail(bno, session));
		return "board/boardDetail";
	}
	
	@RequestMapping(value="boardModify",method=RequestMethod.GET)
	public String boardUpdateGET(@RequestParam("bno")int bno,Model model,@ModelAttribute("searchVO")SearchVO searchVO,HttpSession session) {
		model.addAttribute("boardVO",boardDAO.boardDetail(bno,session));
		return "board/boardUpdate";
	}
	@RequestMapping(value="boardModify",method=RequestMethod.POST)
	public String boardUpdatePOST(BoardVO vo,RedirectAttributes rttr,@ModelAttribute("searchVO")SearchVO searchVO) {

		if(boardDAO.boardUpdate(vo)) 
			rttr.addFlashAttribute("msg","글이 업데이트 되었습니다");
		return "redirect:/board/boardSelect";	
		
	}
	@RequestMapping("/boardDelete")
	public String boardDelete(@RequestParam("bno")int bno,RedirectAttributes rttr,SearchVO searchVO,HttpServletRequest request) {
		
		List<BoardFileVO> FileList=boardDAO.getBoardFileList(bno);
		
		if(boardDAO.boardDelete(bno)) {
			
			deleteFiles(FileList, request);
		
		
			rttr.addFlashAttribute("msg","글이 삭제되었습니다");
			rttr.addFlashAttribute("searchVO",searchVO);
		}	
		return "redirect:/board/boardSelect";	
	}
	
	@RequestMapping(value="/getBoardFileList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<BoardFileVO> getBoardFileList(@RequestParam("bno")int bno){
		
		return boardDAO.getBoardFileList(bno);
	}
	
	
	private void deleteFiles(List<BoardFileVO> boardFileList,HttpServletRequest request) {
		if(boardFileList==null||boardFileList.size()==0) {
			return;
		}
		String uploadPath=getRootPath(request);
		try {
			for(BoardFileVO vo:boardFileList) {
				Path file=Paths.get(uploadPath+"\\",vo.getUploadPath()+"\\"+vo.getUuid()+"_"+vo.getFileName());
				
				Files.deleteIfExists(file);
				
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail=Paths.get(uploadPath+"\\",vo.getUploadPath()+"\\s_"+vo.getUuid()+"_"+vo.getFileName());
							
					Files.delete(thumbNail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

	public String getRootPath(HttpServletRequest request) {
		String rootPatha="/resources/upload";
		return request.getSession().getServletContext().getRealPath(rootPatha);
		
	}
	
}
