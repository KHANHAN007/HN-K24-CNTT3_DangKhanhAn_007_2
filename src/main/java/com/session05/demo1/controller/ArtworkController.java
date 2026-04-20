package com.session05.demo1.controller;

import com.session05.demo1.config.UploadPathConfig;
import com.session05.demo1.model.Artwork;
import com.session05.demo1.service.ArtworkService;
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
@RequestMapping("/artworks")
public class ArtworkController {
    @Autowired
    private ArtworkService service;

    @GetMapping
    public String list(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("artworks", service.search(keyword));
        return "artwork-list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("artwork", new Artwork());
        return "artwork-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("artwork") Artwork artwork,
                       BindingResult result,
                       @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        if (result.hasErrors()) {
            return "artwork-form";
        }

        if (file != null && !file.isEmpty()) {
            artwork.setArtImage(storeImage(file));
        }

        service.save(artwork);
        return "redirect:/artworks";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Artwork artwork = service.findById(id);
        if (artwork == null) {
            return "redirect:/artworks";
        }
        model.addAttribute("artwork", artwork);
        return "artwork-form";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("artwork") Artwork artwork,
                         BindingResult result,
                         @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        if (result.hasErrors()) {
            return "artwork-form";
        }

        Artwork existing = service.findById(artwork.getId());
        if (existing == null) {
            return "redirect:/artworks";
        }

        if (file != null && !file.isEmpty()) {
            artwork.setArtImage(storeImage(file));
        } else {
            artwork.setArtImage(existing.getArtImage());
        }

        service.update(artwork);
        return "redirect:/artworks";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/artworks";
    }

    private String storeImage(MultipartFile file) throws IOException {
        Path uploadPath = UploadPathConfig.resolveUploadDir();
        File dir = uploadPath.toFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = System.currentTimeMillis() + "_" + originalFileName;
        file.transferTo(uploadPath.resolve(fileName));
        return fileName;
    }
}

