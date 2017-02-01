package com.jrodolfo.hibernateset.service;

import com.jrodolfo.hibernateset.dao.MessageBDao;
import com.jrodolfo.hibernateset.entity.MessageB;

import java.util.List;

/**
 * Class that provides service for MessageB and uses MessageBDAO
 * Created by Rod Oliveira (jrodolfo.com) on 2017-01-06
 */
public class MessageBService {

    MessageBDao messageBDao = new MessageBDao();

    public MessageB get(Long id) {
        return messageBDao.get(id);
    }

    public List<MessageB> getAll() {
        return messageBDao.getAll();
    }

    public MessageB create(String text) {
        return messageBDao.create(text);
    }

    public void save(List<MessageB> messageBList) {
        messageBDao.save(messageBList);
    }

    public void update(long id, String text) {
        messageBDao.update(id, text);
    }

    public void update(MessageB messageB) {
        messageBDao.update(messageB);
    }

    public void deleteAll() {
        messageBDao.deleteAll();
    }

    public void delete(long id) {
        messageBDao.delete(id);
    }
}
