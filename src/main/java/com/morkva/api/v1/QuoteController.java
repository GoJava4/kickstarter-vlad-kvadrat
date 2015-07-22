package com.morkva.api.v1;

import com.morkva.entities.Quote;
import com.morkva.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quote")
public class QuoteController {

    @Autowired
    QuoteService quoteService;

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public Quote getRandomQuote(){
        return null;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createQuote(@RequestParam String quote){
        //createNewQuote
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void updateQuoteById(@PathVariable int id, @RequestParam String quote){
        //updateQuoteById
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteQuoteById(@PathVariable int id){
        //deleteQuoteById
    }

}
