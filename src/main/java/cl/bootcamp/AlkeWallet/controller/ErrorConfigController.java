package cl.bootcamp.AlkeWallet.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

public class ErrorConfigController implements ErrorController  {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return "error404.jsp";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return "error500.jsp";
	        }else if(statusCode == HttpStatus.FORBIDDEN.value()) {
	            return "error403.jsp";
	        }
	    }
	    return "error.jsp";
	}

    public String getErrorPath() {
        return "/error";
    }
}