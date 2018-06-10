package pl.mappers;

import pl.db.Questions;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLFileMapper {


    public static Questions getQuestionsFromFile(final File xmlFile) {
        Questions questions = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Questions.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            questions = (Questions) jaxbUnmarshaller.unmarshal(xmlFile);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return questions;
    }


}
