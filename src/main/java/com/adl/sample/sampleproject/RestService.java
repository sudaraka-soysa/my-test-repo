package com.adl.sample.sampleproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CONTEXT_PATH")
public class RestService {

    public static final Logger logger = LoggerFactory.getLogger(RestService.class);

    @RequestMapping(value = "/welcome/{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
        public TestDto welcome(@PathVariable("id") long id){
            logger.info("Request Received with ID {}", id);
            TestDto testDto=new TestDto();
            testDto.setId(id+"");
            testDto.setMessage("Welcome to Swagger REST Template");
            return testDto;
    }

}
