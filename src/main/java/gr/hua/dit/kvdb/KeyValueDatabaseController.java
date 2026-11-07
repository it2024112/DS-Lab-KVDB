package gr.hua.dit.kvdb;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(produces = MediaType.TEXT_PLAIN_VALUE)

public class KeyValueDatabaseController {

    private Map<String, String> database;
    public KeyValueDatabaseController(){
        this.database = new HashMap<>();
    }

    @GetMapping("/")
    public String index(){
        return "Key-Value Database";
    }
    //insert
    @PostMapping("/kv/{key}")
    public void insert(@PathVariable String key, String value){
        if(this.database.containsKey(key)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "key already exists");
            //throw new IllegalArgumentException("key already exists");
        }
        this.database.put(key, value);
    }


    //update
    @PatchMapping("/kv/{key}")
    public String update(@PathVariable String key, String value){
        if(!this.database.containsKey(key)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "key does not exist");
            //throw new IllegalArgumentException("key already exists");
        }
        String oldValue = this.database.put(key, value);
        return oldValue == null ? "<null>" : oldValue;

 //       if (oldValue == null){
 //           return "<null>";
 //       } else {
 //           return oldValue;
 //       }

    }

    //upsert
    @PutMapping("/kv/{key}")
    public String upsert(@PathVariable String key, String value){
        String oldValue = this.database.put(key, value);
        return oldValue == null ? "<null>" : oldValue;
    }

    //delete
    @DeleteMapping("/kv/{key}")
    public String delete(String key){
        String oldValue = this.database.remove(key);
        return oldValue == null ? "<null>" : oldValue;
    }

    //get
    @GetMapping(value = "/kv/{key}")
    public String get(@PathVariable String key){
        String value = this.database.get(key);
        return value == null ? "<null>" : value;
    }

    //list
    @GetMapping(value = "/kv")
    public String list(){
        Set<String> keySet = this.database.keySet();
        String keys = String.join("\n", keySet);
        return keys;
    }

    @PostConstruct
    public void init(){
        this.database.put("name", "Key-Value Database");
        this.database.put("programmer", "John Doe");
        this.database.put("version", "0.1.0");
    }
}
