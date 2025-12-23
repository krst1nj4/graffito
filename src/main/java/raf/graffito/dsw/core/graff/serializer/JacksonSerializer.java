package raf.graffito.dsw.core.graff.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;
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
            Project project = objectMapper.readValue(file, Project.class);
            if (project != null) {
                System.out.println("Učitan projekat: " + project.getName());
                if (project.getChildren() != null) {
                    System.out.println("Broj prezentacija u JSON-u: " + project.getChildren().size());
                } else {
                    System.out.println("GREŠKA: Lista children je NULL nakon readValue!");
                }
                restoreParents(project);
            }
            return project;
            ///  cita iz JSON fajla i mapira ga u Project objekar
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void restoreParents(GraffNodeComposite parent) {
        if(parent.getChildren() == null) return;

        for (GraffNode child : parent.getChildren()) {
            child.setParent(parent);
            if (child instanceof GraffNodeComposite) {
                restoreParents((GraffNodeComposite) child);
            }
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
