package com.morkva.api.v1;

import com.morkva.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/quote")
public class QuoteController {

    @Autowired
    QuoteService quoteService;

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public String getRandomQuote(){
        return null;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createQuote(@RequestParam String quote){
        //createNewQuote
        return null;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public String updateQuoteById(@PathVariable int id, @RequestParam String quote){
        //updateQuoteById
        return null;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteQuoteById(@PathVariable int id){
        //deleteQuoteById
        return null;
    }

}
