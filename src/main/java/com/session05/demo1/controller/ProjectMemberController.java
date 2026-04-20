package com.session05.demo1.controller;

import com.session05.demo1.config.UploadPathConfig;
import com.session05.demo1.model.ProjectMember;
import com.session05.demo1.service.ProjectMemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Controller
@RequestMapping("/members")

public class ProjectMemberController {
    @Autowired
    private ProjectMemberService service;

    @GetMapping
    public String list(@RequestParam(required = false) String keyword,
                       @RequestParam(required = false) String position,
                       Model model){
        model.addAttribute("members", service.search(keyword, position));
        return "members-list";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("member", new ProjectMember());
        return "member-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("member") ProjectMember member,
                       BindingResult result,
                       @RequestParam("file") MultipartFile file) throws IOException {

        if (result.hasErrors()) return "member-form";

        if (!file.isEmpty()) {
            member.setAvatar(storeAvatar(file));
        }

        service.save(member);
        return "redirect:/members";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("member", service.findById(id));
        return "member-form";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute ProjectMember member,
                         @RequestParam("file") MultipartFile file) throws IOException {
        ProjectMember existing = service.findById(member.getId());
        if (existing == null) {
            return "redirect:/members";
        }

        if (!file.isEmpty()) {
            member.setAvatar(storeAvatar(file));
        } else {
            member.setAvatar(existing.getAvatar());
        }

        service.update(member);
        return "redirect:/members";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/members";
    }

    private String storeAvatar(MultipartFile file) throws IOException {
        Path uploadPath = UploadPathConfig.resolveUploadDir();
        File dir = uploadPath.toFile();
        if (!dir.exists()) dir.mkdirs();

        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = System.currentTimeMillis() + "_" + originalFileName;
        file.transferTo(uploadPath.resolve(fileName));
        return fileName;
    }
}
