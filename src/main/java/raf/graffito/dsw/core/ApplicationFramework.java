package raf.graffito.dsw.core;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationFramework {
    // Buduća polja za model celog projekta

    protected Gui gui;
    protected GraffRepository graffRepository;

    public void run() {
        this.gui.start();
    }

    public void initialize(Gui gui, GraffRepository graffRepository) {
        this.gui = gui;
        this.graffRepository = graffRepository;
    }

    private static ApplicationFramework instance;

    private ApplicationFramework() {

    }

    public static ApplicationFramework getInstance(){
        if(instance == null)
            instance = new ApplicationFramework();

        return instance;
    }
}
