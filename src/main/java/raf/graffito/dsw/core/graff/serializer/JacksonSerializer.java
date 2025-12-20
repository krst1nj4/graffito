package raf.graffito.dsw.core.graff.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.graffito.dsw.core.graff.model.Project;

import java.io.File;
import java.io.IOException;

public class JacksonSerializer implements Serializer {

    private final ObjectMapper objectMapper;

    public JacksonSerializer() {
        objectMapper = new ObjectMapper();
        this.objectMapper.enable(com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public Project loadProject(File file) {
        try {
            return objectMapper.readValue(file, Project.class);
            ///  cita iz JSON fajla i mapira ga u Projcet objekar
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveProject(Project project, File file) {
        try {
            objectMapper.writeValue(file, project);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
