package com.rhs.MVCBoard;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rhs.Command.BCommand;
import com.rhs.Command.BContentCommand;
import com.rhs.Command.BDeleteCommand;
import com.rhs.Command.BModifyCommand;
import com.rhs.Command.BReplyCommand;
import com.rhs.Command.BReplyViewCommand;
import com.rhs.Command.BWriteCommand;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	BCommand command = null;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "list";
	}

	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("write()");

		model.addAttribute("request", request);
		command = new BWriteCommand();
		command.execute(model);

		return "redirect:list";
	}

	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println("write_view()");

		return "write_view";
	}

	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view()");

		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model);

		return "content_view";
	}

	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_view()");

		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);

		return "reply_view";
	}

	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply()");

		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.execute(model);

		return "redirect:content_view";
	}

	@RequestMapping("/modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("modify()");

		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);

		return "redirect:content_view";
	}
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model){
		System.out.println("delete()");
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}

}
