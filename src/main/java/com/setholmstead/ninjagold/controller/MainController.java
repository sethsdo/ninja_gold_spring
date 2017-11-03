package com.setholmstead.ninjagold.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("sessionAttribute")
public class MainController {
	
	@RequestMapping("/")
	public String index(HttpSession session, Model model){
		//Integer gold = (Integer) session.getAttribute("gold");
		ArrayList<String> activity = new ArrayList<String>();
		if(session.getAttribute("gold") == null) {
			session.setAttribute("gold", 0);
		} 
		if(session.getAttribute("activity") == null) {
			session.setAttribute("activity", activity);
		}
		model.addAttribute("gold", session.getAttribute("gold"));
		model.addAttribute("activity", session.getAttribute("activity"));
		return "index";
	}
	
	@PostMapping("/process_money")
	public String processGold(@RequestParam(value="building") String build, HttpSession session) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		Random rand = new Random();
		int gold = 0;
		
		if (build.equals("farm")) {
			gold = rand.nextInt(20) + 10;
			System.out.println(build);
		}
		else if (build.equals("cave")) {
			gold = rand.nextInt(10) + 5;
			System.out.println(build);
		}
		else if (build.equals("house")) {
			gold = rand.nextInt(5) + 2;
			System.out.println(build);
		}
		else if (build.equals("casino")) {
			gold = rand.nextInt(50) + -50;
			
			System.out.println(build);
			if (gold > 0) {
				if ((int) session.getAttribute("gold") > 0) {
					ArrayList<String> newActivity = ((ArrayList<String>) session.getAttribute("activity"));
					newActivity.add("In earned " + gold + " from the " + build + "! -- " + timeStamp);
				}
				else {
					ArrayList<String> newActivity = ((ArrayList<String>) session.getAttribute("activity"));
					newActivity.add("You lost " + gold + " from the " + build + "! -- " + timeStamp);
					session.setAttribute("gold", (int) session.getAttribute("gold") + gold);
					return "redirect:/";
				}
			}
			else {
				ArrayList<String> newActivity = ((ArrayList<String>) session.getAttribute("activity"));
				newActivity.add("you lost " + gold + " from the " + build + "! -- " + timeStamp);
			}
			session.setAttribute("gold", (int) session.getAttribute("gold") + gold);
			return "redirect:/";
		}
		

		session.setAttribute("gold", (int) session.getAttribute("gold") + gold);
		System.out.print(session.getAttribute("gold"));
		System.out.println("after logic");
		@SuppressWarnings("unchecked")
		ArrayList<String> newActivity = ((ArrayList<String>) session.getAttribute("activity"));
		newActivity.add("Earned " + gold + " from the " + build + "! -- " + timeStamp);
		return "redirect:/";
	}
	
	@RequestMapping("/reset")
	public String reset(HttpSession session) {
		if(session.getAttribute("gold") != null) {
			session.setAttribute("gold", 0);
		} 
		if(session.getAttribute("activity") != null) {
			session.setAttribute("activity", null);
		}
		return "redirect:/";
	}
}
