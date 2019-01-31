package cn.iustu.site.web.controller;

import cn.iustu.site.common.entity.Contact;
import cn.iustu.site.common.model.Result;
import cn.iustu.site.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/get")
    public Result get(){
        Contact contact = contactService.get();
        if(contact == null){
            return Result.fail().setMsg("记录为空");
        }else{
            return Result.success()
                    .add("qq", contact.getQq())
                    .add("email", contact.getEmail())
                    .add("tel", contact.getTel())
                    .add("address", contact.getAddress());
        }
    }
}
