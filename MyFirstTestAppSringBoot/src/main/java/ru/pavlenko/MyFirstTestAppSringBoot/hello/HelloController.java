package ru.pavlenko.MyFirstTestAppSringBoot.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class HelloController {
    private ArrayList<String> arrayList;
    private HashMap<Integer, String> hashMap;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s", name);
    }

    @GetMapping("/update-array")
    public void updateArrayList(@RequestParam(value = "s") String s) {
        if (arrayList == null){
            arrayList = new ArrayList<>();
        }

        arrayList.add(s);
    }

    @GetMapping("/show-array")
    public String showArrayList() {
        return arrayList.toString();
    }

    @GetMapping("/update-map")
    public void updateHashMap(@RequestParam(value = "i") Integer i, @RequestParam(value = "s") String s) {
        if (hashMap == null){
            hashMap = new HashMap<>();
        }

        hashMap.put(i, s);
    }

    @GetMapping("/show-map")
    public String showHashMap() {
        return hashMap.toString();
    }

    @GetMapping("/show-all-length")
    public String showAllLength(){
        return String.format("ArrayList length: %s\nHashMap length: %s", arrayList.size(), hashMap.size());
    }
}
