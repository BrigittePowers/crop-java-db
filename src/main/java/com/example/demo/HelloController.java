package com.example.demo;
import com.example.demo.models.Crop;
import com.example.demo.repository.ItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// public class = scope type
@RestController
public class HelloController {
    // here we would need to hookup to our repo

//    must return a string
    @GetMapping ("/")
    public String index () {
        return "Hello";
    }

    @GetMapping ("/crops/{id}")
    public Crop displayCrop (@PathVariable("id") String id) {
        // display data from our repo based on the id we find
        return null;
    }
//    @GetMapping ("/doggo")
//    public Doggo doggoName () {
//        return new Doggo("Eris");
//    }
}

// spring boot rest services / api
