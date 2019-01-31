package cn.iustu.site.service;

import cn.iustu.site.common.entity.Contact;

public interface ContactService {

    Contact get();

    //包括插入和更新
    void update(Contact contact);

}
