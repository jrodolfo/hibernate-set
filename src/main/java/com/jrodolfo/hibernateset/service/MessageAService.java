package com.jrodolfo.hibernateset.service;

import com.jrodolfo.hibernateset.dao.MessageADao;
import com.jrodolfo.hibernateset.entity.MessageA;

import java.util.List;

/**
 * Class that provides service for MessageA and uses MessageADAO
 * Created by Rod Oliveira (jrodolfo.com) on 2017-01-06
 */
public class MessageAService {

    MessageADao messageADao = new MessageADao();

    public MessageA get(Long id) {
        return messageADao.get(id);
    }

    public List<MessageA> getAll() {
        return messageADao.getAll();
    }

    public MessageA create(String text) {
        return messageADao.create(text);
    }

    public void save(List<MessageA> messageAList) {
        messageADao.save(messageAList);
    }

    public void update(long id, String text) {
        messageADao.update(id, text);
    }

    public void update(MessageA messageA) {
        messageADao.update(messageA);
    }

    public void deleteAll() {
        messageADao.deleteAll();
    }

    public void delete(long id) {
        messageADao.delete(id);
    }
}
