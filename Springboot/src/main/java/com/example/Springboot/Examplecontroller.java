package com.example.Springboot;
//import org.hibernate.mapping.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;

//import org.hibernate.mapping.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.crossstore.HashMapChangeSet;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Examplecontroller {

    List<POST> list = new ArrayList<>();
    HashMap<Integer, POST> map = new HashMap<>();

    @GetMapping("/id/{id}")
    public String methodCall(@PathVariable("id") String id) {
        return "Response content for id: " + id;
    }

    @PostMapping("/id/post")
    public POST methodCall2(@RequestBody POST body){


        map.put(body.getId(),body);
        return body;
    }

    @GetMapping("get/id/{id}")
    public  ResponseEntity<POST> methodCall3(@PathVariable("id") int id){
        return new ResponseEntity<>(map.get(id),HttpStatus.FOUND);
    }

    @GetMapping("get/id")
    public Object methodCall4(@RequestParam("id") int id,@Nullable @RequestParam("name") String name){

        POST result = map.get(id);
        if (result == null) {
            return "Item not present";  // Custom message when id is not found
        }
        return result;




    }
    @GetMapping("/getall")
    public Object methodCall5(){
       // Map.Entry<Integer,POST> nmap = map.entrySet();
        return map.entrySet();
    }
}


