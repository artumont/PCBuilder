package com.pcbuilder.backend.routes;

import java.sql.Connection;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcbuilder.backend.helpers.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/components")
public class Hardware {
    private Logger logger;
    private Connection connection;

    public Hardware(Logger givenLogger, Connection givenConnection) {
        logger = givenLogger;
        connection = givenConnection;
    }

    @GetMapping("/cpu")
    public String getCpu(@RequestParam int count, int offset) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @GetMapping("/gpu")
    public String getGpu(@RequestParam int count, int offset) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @GetMapping("/ram")
    public String getRam(@RequestParam int count, int offset) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @GetMapping("/case")
    public String getCase(@RequestParam int count, int offset) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @GetMapping("/psu")
    public String getPsu(@RequestParam int count, int offset) {
        throw new UnsupportedOperationException("Not implemented");
    }
  
    @GetMapping("/motherboard")
    public String getMotherboard(@RequestParam int count, int offset) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @GetMapping("/storage")
    public String getStorage(@RequestParam int count, int offset) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
