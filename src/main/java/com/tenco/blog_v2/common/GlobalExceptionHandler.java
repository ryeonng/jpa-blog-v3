package com.tenco.blog_v2.common;

import com.tenco.blog_v2.common.errors.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller // IoC 대상 (뷰 렌더링)
public class GlobalExceptionHandler {

    /**
     * 400 BadRequest 예외 처리
     * @param ex
     * @param model
     * @return
     */
    @ExceptionHandler(Exception400.class)
    public ModelAndView handleException400(Exception400 ex, Model model) {
        // templates/err/400
        ModelAndView mav = new ModelAndView("err/400");
        mav.addObject("msg", ex.getMessage());
        return mav;
    }

    /**
     * 401 UnAuthorized 예외 처리
     * @param ex
     * @param model
     * @return
     */
    @ExceptionHandler(Exception401.class)
    public ModelAndView handleException401(Exception401 ex, Model model) {
        ModelAndView mav = new ModelAndView("err/401");
        mav.addObject("msg", ex.getMessage());
        return mav;
    }

    /**
     * 403 Forbidden 예외 처리
     * @param ex
     * @param model
     * @return
     */
    @ExceptionHandler(Exception403.class)
    public ModelAndView handleException400(Exception403 ex, Model model) {
        ModelAndView mav = new ModelAndView("err/403");
        mav.addObject("msg", ex.getMessage());
        return mav;
    }

    /**
     * 404 Not Found 예외 처리
     * @param ex
     * @param model
     * @return
     */
    @ExceptionHandler(Exception404.class)
    public ModelAndView handleException400(Exception404 ex, Model model) {
        ModelAndView mav = new ModelAndView("err/404");
        mav.addObject("msg", ex.getMessage());
        return mav;
    }

    /**
     * 500 Server Error 예외 처리
     * @param ex
     * @param model
     * @return
     */
    @ExceptionHandler(Exception500.class)
    public ModelAndView handleException400(Exception500 ex, Model model) {
        ModelAndView mav = new ModelAndView("err/500");
        mav.addObject("msg", ex.getMessage());
        return mav;
    }


}
