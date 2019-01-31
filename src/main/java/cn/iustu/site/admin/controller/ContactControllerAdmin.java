package cn.iustu.site.admin.controller;

import cn.iustu.site.common.entity.Contact;
import cn.iustu.site.common.model.Result;
import cn.iustu.site.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/contact")
public class ContactControllerAdmin {

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

    @PostMapping("/update")
    public Result save(Contact contact){
        Contact contact1 = contactService.get();
        //如果不存在记录，则新建一条
        if(contact1 == null){
            contact.setId(1);
            contactService.update(contact);
        }else{
            //判断参数是否为空，如果为空，则不进行修改
            if(!StringUtils.isEmpty(contact.getAddress())) contact1.setAddress(contact.getAddress());
            if(!StringUtils.isEmpty(contact.getEmail())) contact1.setEmail(contact.getEmail());
            if(!StringUtils.isEmpty(contact.getQq())) contact1.setQq(contact.getQq());
            if(!StringUtils.isEmpty(contact.getTel())) contact1.setTel(contact.getTel());
            //保存
            contactService.update(contact1);
        }

        return Result.success().setMsg("修改成功");
    }
}
