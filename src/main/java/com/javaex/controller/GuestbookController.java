package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestbookController {
	//필드//생성자//메소드gs
	
	//메소드일반
	
	//주소록 리스트
	@RequestMapping(value="/addList", method={RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		System.out.println("GuestbookController.addList()");
		
		//personDao.personSelect() 리스트가져온다
		GuestbookDao guestbookDao = new GuestbookDao();
		List<GuestbookVo> guestbookList = guestbookDao.guestbookSelect();
		
		//model 주소를 받고 메소드를 이용해서 담는다  
		//--> DS request.setAttribute("personList", personList)
		model.addAttribute("guestbookList", guestbookList);
		
		//ds에게 포워딩을 위한 jsp정보 전달
		return "/WEB-INF/addList.jsp";
	}
	
	//주소록 등록
	@RequestMapping(value="/add", method={RequestMethod.GET,RequestMethod.POST})
	public String add(@ModelAttribute GuestbookVo guestbookVo){
		System.out.println("GuestbookController.add()");
		
		//dao를통해 저장한다
		GuestbookDao guestbookDao = new GuestbookDao();
		int count = guestbookDao.guestbookInsert(guestbookVo);
		
		//ds에게 리다이렉트을 위한 url정보 전달
		return "redirect:/addList";
	}
	
	//주소록 삭제폼
	@RequestMapping(value="/deleteForm", method={RequestMethod.GET,RequestMethod.POST})
	public String deleteForm(){
		System.out.println("GuestbookController.deleteForm()");
		
		
		//ds에게 포워딩을 위한 jsp정보 전달
		return "/WEB-INF/deleteForm.jsp";
	}
	
	//주소록 삭제
	@RequestMapping(value="/delete", method={RequestMethod.GET,RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo guestbookVo){
		System.out.println("GuestbookController.delete()");
		
		//dao를통해 삭제한다
		GuestbookDao guestbookDao = new GuestbookDao();
		int count = guestbookDao.guestbookDelete(guestbookVo);
		
		//ds에게 리다이렉트을 위한 url정보 전달
		return "redirect:/addList";
	}
}
