package com.rhs.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface BCommand {

	void execute(Model model);

	void execute(HttpServletRequest request, HttpServletResponse response);

}
