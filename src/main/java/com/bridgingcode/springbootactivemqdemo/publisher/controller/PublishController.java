package com.bridgingcode.springbootactivemqdemo.publisher.controller;

import com.bridgingcode.springbootactivemqdemo.model.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/****************************************************
 *
 * @author BELEM Mahamadi
 *
 ***************************************************/

@RestController
public class PublishController {

    @Autowired
    private JmsTemplate jmsTemplate;
    @GetMapping("/")
	public String viewHomePage(Model model) {
		return "Bienvenue !! Vous Decouvrirez Ici Une IMPLEMENTATION TECHNIQUE d'UN CONSOMMATEUR via Soa Artemis Broker" ;	
	}

       /*return findPaginated(1, "firstName", "asc", model); */
       
    @PostMapping("/publishMessage")
    public ResponseEntity<String> publishMessage(@RequestBody SystemMessage systemMessage) {
        try {
            jmsTemplate.convertAndSend("Bhm coding:", systemMessage);

            return new ResponseEntity<>("Sent.", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
