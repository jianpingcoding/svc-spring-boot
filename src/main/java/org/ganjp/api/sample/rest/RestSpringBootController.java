package org.ganjp.api.sample.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ganjp.api.sample.rest.RestModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest")
public class RestSpringBootController {
    
    @RequestMapping("/string")
    @ResponseBody
    public String index() {
        return "Spring Boot Rest Controller";
    }

    @GetMapping("/map")
    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key", "value");
        return map;
    }

    @GetMapping("/model")
    public RestModel getModel() {
        RestModel restModel = new RestModel();
        restModel.setName("tester");
        return restModel;
    }

    @GetMapping("/models")
    public List<RestModel> getModels() {
        List<RestModel> restModels = new ArrayList<RestModel>();
        RestModel restModel = new RestModel();
        restModel.setName("tester");
        restModels.add(restModel);
        return restModels;
    }


    // private static final String template = "Hello, %s!";
    // @GetMapping("/greeting")
	// public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
	// 	return new Greeting(1, String.format(template, name));
	// }
}