package cn.lonecloud.controller;

import cn.lonecloud.model.ImageResult;
import cn.lonecloud.util.GenerateImage;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * 登录类
 */
@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginController {
    /**
     * 登录主页
     *
     * @param model
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, ServletResponse response, ServletRequest request) throws IOException {
        //生成验证码图片及标识结果
        ImageResult imageResult = GenerateImage.generateImage(request);
        //拿出image的名称和tip 返回页面
        model.addAttribute("tip", imageResult.getTip());
        model.addAttribute("name", imageResult.getName());
        //放入cookie
        ((HttpServletResponse) response).addCookie(new Cookie("note", imageResult.getUniqueKey()));
        //将结果放入session
        ((HttpServletRequest) request).getSession().setAttribute("imageResult", imageResult);
        return "/login";
    }

    /**
     * 刷新图片
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getPng")
    @ResponseBody
    public String getPng(ServletRequest request) throws IOException {
        ImageResult imageResult = GenerateImage.generateImage(request);
        ((HttpServletRequest) request).getSession().setAttribute("imageResult", imageResult);
        return imageResult.getName() + "," + imageResult.getTip();
    }

    /**
     * 验证消息
     *
     * @param location
     * @param request
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(String location, ServletRequest request, String userName, String password, RedirectAttributes redirectAttributes) {
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        Cookie note = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("note")) {
                note = cookie;
            }
        }
        ImageResult imageResult = (ImageResult) ((HttpServletRequest) request).getSession().getAttribute("imageResult");
        if (imageResult != null) {
            if (location != null && !location.trim().equals("")) {
                String[] locations = location.split(";");
                int count = 0;
                for (int i = 0; i < locations.length; i++) {
                    String[] loc = locations[i].split(",");
                    int x = Integer.valueOf(loc[0]);
                    int y = Integer.valueOf(loc[1]);
                    boolean result = validateLocation(imageResult, x, y);
                    if (!result) {
                        redirectAttributes.addFlashAttribute("msg", "验证码错误!");
                        return "redirect:/login";
                    } else {
                        count++;
                    }
                }
                if (count != imageResult.getKeySet().size()) {
                    redirectAttributes.addFlashAttribute("msg", "验证码错误!");
                    return "redirect:/login";
                } else {
                    return "/main";
                }
            } else {
                redirectAttributes.addFlashAttribute("msg", "您还没有选择图片验证码!");
                return "redirect:/login";
            }
        } else {
            redirectAttributes.addFlashAttribute("msg", "系统错误!");
            return "redirect:/login";
        }
    }

    /**
     * 验证是否正确
     *
     * @param result 用于存储的
     * @param x      x left
     * @param y      y top
     * @return
     */
    private boolean validateLocation(ImageResult result, int x, int y) {
        Set<Integer> keySet = result.getKeySet();
        //判断x
        for (Integer i : keySet) {
            int minX = 0;
            int maxX = 0;
            if (i < 4) {
                minX = i * 100;
            } else {
                minX = (i % 4 * 100);
            }
            maxX = minX + 100;
            if (x > minX && x < maxX) {
                int minY = 0;
                int maxY = 0;
                if (i < 4) {
                    minY = 0;
                    maxY = 100;
                } else {
                    minX = 100;
                    maxY = 200;
                }
                if (y > minY && y < maxY) {
                    return true;
                }
            }
        }
        return false;
    }

}
