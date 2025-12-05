package raf.graffito.dsw.core;
import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.graff.GraffRepositoryImplements;
import raf.graffito.dsw.core.logger.LoggerFactory;
import raf.graffito.dsw.core.messages.MessageGenerator;
import raf.graffito.dsw.gui.swing.MainFrame;

/**
 * Application Framework -> controller izmedju modela/akcija i view-a
 */
@Getter
@Setter
public class ApplicationFramework {
    // Buduća polja za model celog projekta

    protected Gui gui;
    protected GraffRepository graffRepository;
    private MessageGenerator dialogMsgGenerator;

    public void run() {
        this.gui.start();
    }

    public void initialize() {
        MainFrame mainFrame = MainFrame.getInstance();
        dialogMsgGenerator.addSubscriber(mainFrame);
        LoggerFactory lgrFactory = new LoggerFactory();
        dialogMsgGenerator.addSubscriber(lgrFactory.createLogger("consolelogger"));
        dialogMsgGenerator.addSubscriber(lgrFactory.createLogger("filelogger"));
        mainFrame.initTree();
        mainFrame.setVisible(true);
    }

    private static ApplicationFramework instance;

    private ApplicationFramework() {
        graffRepository = new GraffRepositoryImplements();
        dialogMsgGenerator = new MessageGenerator();
    }

    public static ApplicationFramework getInstance(){
        if(instance == null) {
            instance = new ApplicationFramework();
            instance.initialize();
        }

        return instance;
    }
}
