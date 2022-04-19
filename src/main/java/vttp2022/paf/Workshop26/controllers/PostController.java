package vttp2022.paf.Workshop26.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import vttp2022.paf.Workshop26.model.Post;
import vttp2022.paf.Workshop26.repository.PostRepository;

@Controller
@RequestMapping(path="/post")
public class PostController {

    @Autowired
    private PostRepository postRepo;

    @GetMapping(path="/{postId}")
    public String getPostById(@PathVariable Integer postId, Model model) {
        Optional<Post> opt = postRepo.getPostById(postId);
        model.addAttribute("post", opt.get());
        return "post";
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postPost(
        @RequestParam MultipartFile image, 
        @RequestPart String comment, 
        @RequestPart String poster, 
        Model model ) {

            String imageName = image.getOriginalFilename();
            long imageSize = image.getSize();
            String imageType = image.getContentType();
            byte[] buff = new byte[0]; // initialise the byte

            try {
                buff = image.getBytes();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Post p = new Post();
            p.setComment(comment);
            p.setPoster(poster);
            p.setImageType(imageType);
            p.setImage(buff);

            Integer updateCount = postRepo.insertPost(p);
            model.addAttribute("updateCount", updateCount);

            return "result";
    }
    
}
