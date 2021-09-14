package com.gmail.elbaglikov.service;

import com.gmail.elbaglikov.bean.User;
import com.gmail.elbaglikov.bean.UserList;
import com.gmail.elbaglikov.exceptions.TransformXMLException;
import com.gmail.elbaglikov.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.List;

import static com.gmail.elbaglikov.Constants.XSL_PATH;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getAll() {
        return userRepository.getAll();
    }

    public List<User> getByBalance(int k) {
        return userRepository.getByBalance(k);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void saveAll(Iterable<User> balances) {
        userRepository.saveAll(balances);
    }

    public void saveXml(String xmlStr) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            StringWriter sw = new StringWriter();

            Source xslt = new StreamSource(getClass().getResourceAsStream(XSL_PATH));
            Transformer transformer = factory.newTransformer(xslt);
            Source xml = new StreamSource(new StringReader(xmlStr));
            transformer.transform(xml, new StreamResult(sw));
            JAXBContext jc = JAXBContext.newInstance(UserList.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            UserList users = (UserList) unmarshaller.unmarshal(new StringReader(sw.toString()));

            deleteAll();
            saveAll(users.getUsers());
        } catch (TransformerException | JAXBException e) {
            LOGGER.error("problem with transform xml", e);
            throw new TransformXMLException("problem with transform xml", e);
        }
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
