package com.morkva.api.v1;

import com.morkva.entities.Quote;
import com.morkva.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "restQuoteController")
@RequestMapping("/api/v1/quote")
public class QuoteController {

    @Autowired
    QuoteService quoteService;

    @RequestMapping(value = "/random", method = RequestMethod.GET, headers = "Accept: application/json")
    public Quote getRandomQuote(){
        return quoteService.getRandom();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createQuote(@RequestParam String quote, @RequestParam String author){
        Quote qu = new Quote();
        qu.setValue(quote);
        qu.setAuthor(author);
        quoteService.create(qu);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void updateQuoteById(@PathVariable int id, @RequestParam String quote, @RequestParam String author){
        Quote qu = quoteService.getById(id);
        qu.setValue(quote);
        qu.setAuthor(author);
        quoteService.update(qu);

    }

//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//    public void deleteQuoteById(@PathVariable int id){
//        //deleteQuoteById
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept: application/json")
    public Quote getQuoteById(@PathVariable int id){
        return quoteService.getById(id);
    }

}
