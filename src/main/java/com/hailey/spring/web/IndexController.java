package com.hailey.spring.web;

import com.hailey.spring.config.auth.LoginUser;
import com.hailey.spring.config.auth.dto.SessionUser;
import com.hailey.spring.service.PostsService;
import com.hailey.spring.web.dto.PostsResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

//IndexController: 페이지와 관련된 모든 컨트롤러 사용
@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    //private final HttpSession httpSession;

    //어느 컨트롤러이든지 @LoginUser만 사용하면 세션 정보를 가져올 수 있음
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
