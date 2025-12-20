package raf.graffito.dsw.core.graff.serializer;

import raf.graffito.dsw.core.graff.model.Project;

import java.io.File;

public interface Serializer {
    Project loadProject(File file);
    void saveProject(Project project, File file);
}
