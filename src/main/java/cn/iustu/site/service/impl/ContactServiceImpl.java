package cn.iustu.site.service.impl;

import cn.iustu.site.common.entity.Contact;
import cn.iustu.site.dao.ContactRepository;
import cn.iustu.site.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact get() {
        try{
            return contactRepository.findById(1).get();
        }catch(NoSuchElementException e){
            return null;
        }
    }

    @Transactional
    @Override
    public void update(Contact contact) {
        contact.setId(1);
        contactRepository.save(contact);
    }
}
